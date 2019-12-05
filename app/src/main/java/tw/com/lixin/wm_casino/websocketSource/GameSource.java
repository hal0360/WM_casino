package tw.com.lixin.wm_casino.websocketSource;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.GameData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.holders.PeopleHolder;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.CmdTableLog;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.popups.PeoplePopup;

public class GameSource extends CasinoSource{

    private static GameSource single_instance = null;
    public static GameSource getInstance()
    {
        if (single_instance == null) single_instance = new GameSource();
        return single_instance;
    }

    public Table table;
    private GameBridge bridge;
    private CmdTableLog cmdTableLog;
    private CmdStr cmdTableFail;

    private PeoplePopup popup;
    public int peopleOnline;
    public List<PeopleHolder> peopleHolders;

    public void bind(GameBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        this.bridge = null;
        unbindPeple();
        binded(false);
    }

    public void bindPeople(PeoplePopup popup){
        this.popup = popup;
    }

    public void unbindPeple(){
        this.popup = null;
    }

    public final void tableLogin(Table table, CmdTableLog logOK, CmdStr logFail){
        defineURL("ws://gameserver.a45.me:15" + table.gameID);
        this.table = table;
        cmdTableLog = logOK;
        cmdTableFail = logFail;
        login(User.sid(),data->{
            peopleHolders = new ArrayList<>();
            Client10 client = new Client10(table.groupID);
            send(Json.to(client));
        }, fail-> cmdTableFail.exec(fail));
    }

    public void tableLogout( ){
        cmdTableLog = null;
        cmdTableFail = null;
        peopleHolders = null;
        unbind();
        close();
    }

    @Override
    public void onReceive(String text) {
        GameData gameData = Json.from(text, GameData.class);
        switch(gameData.protocol) {
            case 10:
                TableLogData tableLogData = Json.from(text, TableLogData.class);
                if(tableLogData.data.gameID == table.gameID && tableLogData.data.groupID == table.groupID && tableLogData.data.memberID == User.memberID()){
                    if(tableLogData.data.bOk){
                        cmdTableLog.exec(tableLogData.data);
                    }
                    else{
                        tableLogout( );
                        cmdTableFail.exec("login failed");
                    }
                }
                break;
            case 22:
                if(gameData.data.groupID == table.groupID) handle(() -> bridge.betUpdate(gameData.data.bOk));
                break;
            case 23:
                if(gameData.data.groupID == table.groupID && gameData.data.memberID == User.memberID()) handle(() -> bridge.balanceUpdate(gameData.data.balance));
                break;
            case 31:
                if(gameData.data.groupID == table.groupID && gameData.data.memberID == User.memberID()) handle(() -> bridge.winLossUpdate(gameData.data.moneyWin));
                break;
            case 28:
                if(gameData.data.groupID == table.groupID){
                    PeopleHolder holder = new PeopleHolder(gameData.data.memberID, gameData.data.userName, gameData.data.winRate);
                    peopleHolders.add(holder);
                    if(popup != null) handleSimple(()-> popup.peopleIn(holder, gameData.data.userCount));
                }

               // if(popup != null && gameData.data.groupID == table.groupID) handleSimple(()-> popup.peopleIn(gameData.data.memberID, gameData.data.userName, gameData.data.winRate));
                break;
            case 29:
              // if(popup != null && gameData.data.groupID == table.groupID) handleSimple(()-> popup.peopleOut(gameData.data.memberID));
                break;
        }
    }
}