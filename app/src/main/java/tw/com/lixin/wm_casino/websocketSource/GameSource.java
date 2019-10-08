package tw.com.lixin.wm_casino.websocketSource;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.interfaces.CmdLog;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;

public class GameSource extends CasinoSource{

    private static GameSource single_instance = null;
    public static GameSource getInstance()
    {
        if (single_instance == null) single_instance = new GameSource();
        return single_instance;
    }

    public int groupID = -11;
    public int gameID = -11;
    public String gameName = "skull";
    public Table table;
    public List<Table> tables = new ArrayList<>();
    private GameBridge bridge;

    public void addTables(int gameid){
        Game bacGame = LobbySource.getInstance().findGame(gameid);
        if(bacGame == null) return;
        tables.clear();
        for(Group tableStage: bacGame.groupArr){
            if ( tableStage.gameStage != 4 && !tableStage.dealerImage.equals("")){
                if (gameid == 101) tables.add(new BacTable(tableStage));
            }
        }
    }

    private Table findTable(int id){
        for(Table tTable: tables) if(id == tTable.groupID) return tTable;
        return null;
    }

    public final void gameLogin(int gameNum, String sid, CmdLog logOK, CmdStr logFail){
        gameID = gameNum;
        defineURL("ws://gameserver.a45.me:15" + gameNum);
        super.login(sid, logOK, logFail);
    }

    public void bind(GameBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        this.bridge = null;
        binded(false);
    }

    public void tableLogin(Table table){
        this.table = table;
        groupID = table.groupID;
        Client10 client = new Client10(table.groupID);
        send(Json.to(client));
    }

   // public abstract void onData(int protocol, TableData.Data data);

    @Override
    public void onReceive(String text) {
        TableData tableData = Json.from(text, TableData.class);
        Table tt = findTable(tableData.data.groupID);

        if(tt == null) return;
        switch(tableData.protocol) {
            case 20:
                tt.statusUpdate(tableData.data.gameStage);
                if(tableData.data.groupID == groupID) handle(() -> bridge.statusUpdate(tableData.data.gameStage));
                break;
            case 24:
                tt.cardUpdate(tableData.data.cardArea, tableData.data.cardID);
                if(tableData.data.groupID == groupID) handle(() -> bridge.cardUpdate(tableData.data.cardArea, tableData.data.cardID));
                break;
            case 26:
                tt.update(tableData.data);
                if(tableData.data.groupID == groupID) handle(() -> bridge.gridUpdate());
                break;
            case 38:
                tt.startCountDown(tableData.data.timeMillisecond);
                if(tableData.data.groupID == groupID) handle(() -> bridge.startCountDown(tableData.data.timeMillisecond));
                break;
            case 25:
                if(tableData.data.groupID == groupID) handle(() -> bridge.resultUpdate(tableData.data));
                break;
            case 10:
                tt.loginSetup(tableData.data);
                if(tableData.data.groupID == groupID) handle(() -> bridge.tableLogin(tableData.data));
                break;
            case 22:
                if(tableData.data.groupID == groupID) handle(() -> bridge.betUpdate(tableData.data.bOk));
                break;
            case 23:
                if(tableData.data.groupID == groupID) handle(() -> bridge.balanceUpdate(tableData.data.balance));
                break;
            case 31:
                if(tableData.data.groupID == groupID) handle(() -> bridge.winLossUpdate(tableData.data));
                break;
        }
    }

    /*
    @Override
    public void onReceive(String text) {
        TableData tableData = Json.from(text, TableData.class);
        Table tt = findTable(tableData.data.groupID);
        if(tt == null) return;
        switch(tableData.protocol) {
            case 20:
                tt.statusUpdate(tableData.data.gameStage);
                break;
            case 24:
                tt.cardUpdate(tableData.data.cardArea, tableData.data.cardID);
                break;
            case 26:
                tt.update(tableData.data);
                break;
            case 38:
                tt.startCountDown(tableData.data.timeMillisecond);
                break;
            case 25:
                tt.resultUpdate(tableData.data);
                break;
            case 10:
                tt.loginSetup(tableData.data);
                break;
            case 22:
                tt.betUpdate(tableData.data.bOk);
                break;
            case 23:
                tt.balanceUpdate(tableData.data.balance);
                break;
            case 31:
               // tt.balanceUpdate(tableData.data.balance);
                break;
        }
    }*/

    /*
    @Override
    public void onReceive(String text) {
        TableData tableData = Json.from(text, TableData.class);
        Table tt = findTable(tableData.data.groupID);
        if(tableData.protocol == 20){
            if(tt != null)tt.statusUpdate(tableData.data.gameStage);
        }else if(tableData.protocol == 24){
            if(tt != null)tt.cardUpdate(tableData.data.cardArea, tableData.data.cardID);
            if(tableData.data.groupID == groupID) handle(() -> bridge.cardUpdate(tableData.data.cardArea, tableData.data.cardID));
        }else if(tableData.protocol == 26){
            if(tt != null)tt.update(tableData.data);
        }else if(tableData.protocol == 38){
            if(tt != null)tt.startCountDown(tableData.data.timeMillisecond);
        }else if(tableData.protocol == 25){
            if(tt != null)tt.resultUpdate(tableData.data);
            if(tableData.data.groupID == groupID) handle(() -> bridge.resultUpadte());
        }
        if(tableData.data.groupID != groupID) return;
        if(tableData.protocol == 10){
            if(tableData.data.bOk) table.loginSetup(tableData.data);
            handle(() -> bridge.tableLogin(tableData.data.bOk));
        }else if(tableData.protocol == 22){
            handle(() -> bridge.betUpdate(tableData.data.bOk));
        }else if(tableData.protocol == 23){
            handle(() -> bridge.balanceUpdate(tableData.data.balance));
        }else if(tableData.protocol == 31){

        }
    }*/

}