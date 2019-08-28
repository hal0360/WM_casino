package tw.com.lixin.wm_casino.websocketSource;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.interfaces.CmdLog;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
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
        if(tableData.protocol == 20){
            if(tt != null)tt.statusUpdate(tableData.data.gameStage);
        }else if(tableData.protocol == 24){
            if(tt != null)tt.cardUpdate(tableData.data.cardArea, tableData.data.cardID);
            if(tableData.data.groupID == groupID) handle(() -> bridge.cardUpdate(tableData.data.cardArea, tableData.data.cardID));
        }else if(tableData.protocol == 26){
            if(tt != null)tt.update(tableData);
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
    }

}