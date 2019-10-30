package tw.com.lixin.wm_casino.websocketSource;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.CmdStr;
import tw.com.lixin.wm_casino.interfaces.CmdTableLog;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.models.Table;

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
    private boolean tableLogged = false;

    public void bind(GameBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    private void unbind(){
        this.bridge = null;
        binded(false);
    }

    public final void tableLogin(Table table, CmdTableLog logOK, CmdStr logFail){
        defineURL("ws://gameserver.a45.me:15" + table.gameID);
        this.table = table;
        cmdTableLog = logOK;
        cmdTableFail = logFail;
        login(table.gameID,User.sid(),data->{
            Client10 client = new Client10(table.groupID);
            send(Json.to(client));
        }, fail-> cmdTableFail.exec(fail));
    }

    public void tableLogout( ){
        tableLogged = false;
        unbind();
        close();
    }

    @Override
    public void onReceive(String text) {
        TableData tableData = Json.from(text, TableData.class);
        switch(tableData.protocol) {
            case 10:
                if(!tableLogged){
                    TableLogData tableLogData = Json.from(text, TableLogData.class);
                    if(tableLogData.data.gameID == table.gameID && tableLogData.data.groupID == table.groupID){
                        if(tableLogData.data.bOk){
                            cmdTableLog.exec(tableLogData.data);
                            tableLogged = true;
                        }
                        else{
                            tableLogout( );
                            cmdTableFail.exec("login failed");
                        }
                    }
                }
                break;
            case 22:
                if(tableData.data.groupID == table.groupID) handle(() -> bridge.betUpdate(tableData.data.bOk));
                break;
            case 23:
                if(tableData.data.groupID == table.groupID) handle(() -> bridge.balanceUpdate(tableData.data.balance));
                break;
            case 31:
                if(tableData.data.groupID == table.groupID) handle(() -> bridge.winLossUpdate(tableData.data));
                break;
        }
    }
}