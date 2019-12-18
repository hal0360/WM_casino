package tw.com.lixin.wm_casino.websocketSource;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.collections.PeopleCollection;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.GameData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.CmdTableLog;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.popups.PeoplePopup;
import tw.com.lixin.wm_casino.popups.WinLossPopup;

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
    public List<PeopleCollection> peopleCollections;


    public SparseArray<ChipStackData> chipDatas;
    public TableLogData.Data logData;


    public void bind(GameBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        this.bridge = null;
        this.popup = null;
        binded(false);
    }

    public void bindPeople(PeoplePopup popup){
        this.popup = popup;
    }

    public final void tableLogin(Table table, CmdTableLog logOK, CmdStr logFail){
        chipDatas = new SparseArray<>();
        defineURL("ws://gameserver.a45.me:15" + table.gameID);
        this.table = table;
        cmdTableLog = logOK;
        cmdTableFail = logFail;
        login(User.sid(),data->{
            peopleCollections = new ArrayList<>();
            Client10 client = new Client10(table.groupID);
            send(Json.to(client));
        }, fail-> cmdTableFail.exec(fail));
    }

    public void tableLogout( ){
        chipDatas = null;
        cmdTableLog = null;
        cmdTableFail = null;
        peopleCollections = null;
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
                        logData = tableLogData.data;
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
                if(gameData.data.groupID == table.groupID && gameData.data.memberID == User.memberID()){
                    User.balance(gameData.data.balance);
                }
                break;
            case 31:
                if(gameData.data.groupID == table.groupID && gameData.data.memberID == User.memberID()){
                    RootActivity activity = (RootActivity) bridge;
                    WinLossPopup popup = new WinLossPopup();
                    popup.setPay(gameData.data.moneyWin);
                    activity.showPopup(new WinLossPopup());
                }
                break;
            case 28:
                if(gameData.data.groupID == table.groupID){
                    PeopleCollection collection = new PeopleCollection(gameData.data.memberID, gameData.data.userName, gameData.data.winRate);
                    peopleCollections.add(collection);
                    if(popup != null) handleSimple(()-> popup.peopleIn(collection, gameData.data.userCount));
                }

               // if(popup != null && gameData.data.groupID == table.groupID) handleSimple(()-> popup.peopleIn(gameData.data.memberID, gameData.data.userName, gameData.data.winRate));
                break;
            case 29:
              // if(popup != null && gameData.data.groupID == table.groupID) handleSimple(()-> popup.peopleOut(gameData.data.memberID));
                break;
        }
    }
}