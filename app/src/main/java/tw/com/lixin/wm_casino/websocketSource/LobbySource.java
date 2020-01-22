package tw.com.lixin.wm_casino.websocketSource;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.LobbyData;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.interfaces.CmdTable;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.RouletteTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.models.TigerDragonTable;


public class LobbySource extends CasinoSource{

    private static LobbySource single_instance = null;
    public static LobbySource getInstance()
    {
        if (single_instance == null) single_instance = new LobbySource();
        return single_instance;
    }

    private LobbySource() {
        defineURL("ws://gameserver.a45.me:15109");
        allTables = new SparseArray<>();
        tableProvider = new SparseArray<>();
        tableProvider.put(101, BacTable::new);
        tableProvider.put(102, TigerDragonTable::new);
        tableProvider.put(103, RouletteTable::new);
    }

    private LobbyBridge bridge;
    public int curGameID;
    public SparseIntArray peopleOnline = new SparseIntArray();
    public SparseArray<SparseArray<Table>> allTables;
    private SparseArray<CmdTable> tableProvider;

    public void bind(LobbyBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        bridge = null;
        binded(false);
    }

    @Override
    public void onReceive(String text) {

        TableData tableData = Json.from(text, TableData.class);
        TableData.Data data = tableData.data;

        SparseArray<Table> tableGroup = allTables.get(data.gameID);

        Table table = null;
        if(tableGroup != null) table = tableGroup.get(data.groupID);

        switch(tableData.protocol) {
            case 20:
                if(table != null){
                    table.receive20(data.gameStage);
                    if(data.gameStage == 4) tableGroup.remove(data.groupID);
                }
                return;
            case 21:
                if(table == null){
                    if(data.gameStage != 4 && !data.dealerImage.equals("") && !data.dealerName.equals("")){
                        CmdTable cmdTable = tableProvider.get(data.gameID);
                        if(cmdTable == null) return;
                        Group group = new Group();
                        group.dealerID = data.dealerID;
                        group.gameNo = data.gameNo;
                        group.dealerName = data.dealerName;
                        group.dealerImage = data.dealerImage;
                        group.gameNoRound = data.gameNoRound;
                        group.gameStage = 5;
                        group.groupID = data.groupID;
                        group.groupType = data.groupType;
                        group.historyArr = new ArrayList<>();
                        if(tableGroup != null) tableGroup.put(data.groupID, cmdTable.exec(group) );
                    }
                }else {
                    table.receive21(data);
                }
                return;
            case 26:
                if(table != null) table.receive26(tableData.data);
                return;
            case 38:
                if(table != null) table.receive38(tableData.data.timeMillisecond);
                return;
        }
        proLogData(text);
    }

    private void proLogData(String text){
        LobbyData lobbyData = Json.from(text, LobbyData.class);
        switch(lobbyData.protocol) {
            case 35:
                allTables = new SparseArray<>();
                for(Game game: lobbyData.data.gameArr){
                    CmdTable cmdTable = tableProvider.get(game.gameID);
                    if(cmdTable != null){
                        SparseArray<Table> tableGroup = new SparseArray<>();
                        for(Group tableStage: game.groupArr){
                            if ( tableStage.gameStage != 4 && !tableStage.dealerImage.equals("") && !tableStage.dealerName.equals("")){
                                tableGroup.put(tableStage.groupID,cmdTable.exec(tableStage));
                            }
                        }
                        allTables.put(game.gameID, tableGroup);
                    }
                }

                handle(()-> bridge.wholeDataUpdated());
                break;
            case 34:
                peopleOnline.put(lobbyData.data.gameID,lobbyData.data.onlinePeople);
                handle(()-> bridge.peopleOnlineUpdate(lobbyData.data.gameID, lobbyData.data.onlinePeople));
                break;
        }
    }

}
