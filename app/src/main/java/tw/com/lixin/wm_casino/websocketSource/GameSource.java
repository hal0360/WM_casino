package tw.com.lixin.wm_casino.websocketSource;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import okhttp3.WebSocket;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.GameData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.CmdTableLog;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.models.People;
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
    public int pplOnline;
    public List<People> peoples;

    public SparseArray<ChipStackData> chipDatas;
    public TableLogData.Data logData;

    public int result;
    public int playerScore, bankerScore;

    public SparseIntArray pokers = new SparseIntArray();

    public String videoSignal;
    public int totalBet;
    public int stage;

    public float moneyWin;

    public void bind(GameBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        this.bridge = null;
        binded(false);
    }

    public void bindPeople(PeoplePopup popup){ this.popup = popup; }

    public void unbindPeople(){
        this.popup = null;
    }

    public final void tableLogin(Table table, CmdTableLog logOK, CmdStr logFail){
      //  defineURL("ws://gameserver.a45.me:15" + table.gameID);

        defineURL("wss://a45gs-t.dartspharm.com/15" + table.gameID);

        this.table = table;
        cmdTableLog = logOK;
        cmdTableFail = logFail;
        pokers.clear();
        peoples = new ArrayList<>();
        chipDatas = new SparseArray<>();
        login(User.sid(),data->{
            Client10 client = new Client10(table.groupID);
            send(Json.to(client));
        }, fail-> cmdTableFail.exec(fail));
    }

    public void tableLogout( ){
        chipDatas = null;
        cmdTableLog = null;
        cmdTableFail = null;
        peoples = null;
        popup = null;
        unbind();
        unbindPeople();
        if(table!= null) table.unBind();
        close();
    }

    private void mssLogin(int gameID, int groupID){
        handleSimple(()->{
            MessageSource source = MessageSource.getInstance();
            source.mssLogin(gameID, groupID);
        });
    }

    @Override
    public void onReceive(String text) {


        Log.e("fuck",text);

        GameData gameData = Json.from(text, GameData.class);
        switch(gameData.protocol) {
            case 10:
                TableLogData tableLogData = Json.from(text, TableLogData.class);
                if(tableLogData.data.gameID == table.gameID && tableLogData.data.groupID == table.groupID && tableLogData.data.memberID == User.memberID()){
                    if(tableLogData.data.bOk){
                        stage = table.stage;
                        videoSignal = "wmvdo.nicejj";
                        logData = tableLogData.data;
                        cmdTableLog.exec(tableLogData.data);
                        mssLogin(table.gameID, table.groupID);
                    }
                    else{
                        tableLogout( );
                        cmdTableFail.exec("login failed");
                    }
                }
                break;
            case 20:
                if(gameData.data.groupID == table.groupID) {
                    stage = gameData.data.gameStage;
                    if(gameData.data.gameStage == 1) {
                        totalBet = 0;
                        for(int s = 0; s < chipDatas.size(); s++){
                            chipDatas.valueAt(s).clear();
                        }
                    }else if(gameData.data.gameStage == 2) {
                        for(int s = 0; s < chipDatas.size(); s++){
                            chipDatas.valueAt(s).cancelBet();
                        }
                    }
                    handle(() -> bridge.statusUpdate());
                }

                if(gameData.data.groupID == table.groupID && gameData.data.gameStage == 1) {
                    for(int s = 0; s < chipDatas.size(); s++){
                        chipDatas.valueAt(s).clear();
                    }
                }else if(gameData.data.groupID == table.groupID && gameData.data.gameStage == 2) {
                    for(int s = 0; s < chipDatas.size(); s++){
                        chipDatas.valueAt(s).cancelBet();
                    }
                }
                break;
            case 22:
                if(gameData.data.groupID == table.groupID) handle(() -> bridge.betUpdate(gameData.data.bOk));
                break;
            case 24:
                if(gameData.data.groupID == table.groupID){
                    pokers.put(gameData.data.cardArea,gameData.data.cardID);
                    handle(() -> bridge.cardUpdate(gameData.data.cardArea,gameData.data.cardID));
                }
                break;
            case 25:
                if(gameData.data.groupID == table.groupID) {
                    playerScore = gameData.data.playerScore;
                    bankerScore = gameData.data.bankerScore;
                    result = gameData.data.result;
                    handle(() -> bridge.resultUpdate());
                }
                break;
            case 30:
                User.balance(gameData.data.balance);
                handle(() -> bridge.balanceUpdate());
                break;
            case 31:
                if(gameData.data.groupID == table.groupID && gameData.data.memberID == User.memberID()){
                    moneyWin = gameData.data.moneyWin;
                    handle(() -> bridge.winLossUpdate(gameData.data.moneyWin));
                }
                break;
            case 28:
                if(gameData.data.groupID == table.groupID){
                    pplOnline = gameData.data.userCount;
                    peoples.add(new People(gameData.data.userName, gameData.data.winRate, gameData.data.memberID));
                    if(popup != null) handleSimple(()-> popup.poepleUpdate( gameData.data.userCount));
                }
                break;
            case 29:
                if(gameData.data.groupID == table.groupID){
                    pplOnline = gameData.data.userCount;
                    for (int i = 0; i < peoples.size(); i++) {
                        if(gameData.data.memberID == peoples.get(i).memberID) {
                            peoples.remove(i);
                            if(popup != null) handleSimple(()-> popup.poepleUpdate( gameData.data.userCount));
                            break;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if(connected){
            handle(() ->  bridge.serverFailed());
            close();
        }
        super.onFailure( webSocket,  t,  response);
    }
}