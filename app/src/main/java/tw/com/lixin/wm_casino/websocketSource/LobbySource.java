package tw.com.lixin.wm_casino.websocketSource;

import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

import okhttp3.Response;
import okhttp3.WebSocket;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.dataModels.LobbyData;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.Table;


public class LobbySource extends CasinoSource{

    private static LobbySource single_instance = null;

    private App app;

    public static LobbySource getInstance()
    {
        if (single_instance == null) single_instance = new LobbySource();
        return single_instance;
    }

    private LobbySource() {
        app = App.getThisApp();
       // defineURL("ws://gameserver.a45.me:15109");
        defineURL("wss://a45gs-t.dartspharm.com/15109");

        allTables = new SparseArray<>();
    }

    private LobbyBridge bridge;
    public int curGameID;
    public SparseIntArray peopleOnline = new SparseIntArray();
    public SparseArray<SparseArray<Table>> allTables;

    public void bind(LobbyBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        bridge = null;
        binded(false);
    }

    public void logout(){
        unbind();
        close();
    }

    @Override
    public void onReceive(String text) {

        Log.e("lobbySource", text);



        TableData tableData = Json.from(text, TableData.class);
        TableData.Data data = tableData.data;
        SparseArray<Table> tableGroup = allTables.get(data.gameID);
        Table table = null;
        if(tableGroup != null) table = tableGroup.get(data.groupID);

        switch(tableData.protocol) {
            case 20:
                if(table != null){
                    table.receive20(data.gameStage);
                   // if(data.gameStage == 4) tableGroup.remove(data.groupID);
                }
                return;
            case 21:
                if(table != null) table.receive21(data);
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

                    SparseArray<Table> tableGroup = new SparseArray<>();
                    for(Group tableStage: game.groupArr){


                        if ( tableStage.gameStage != 4 && !tableStage.dealerImage.equals("") && !tableStage.dealerName.trim().equals("")){

                            Log.e("anime", tableStage.dealerName.trim());

                            tableGroup.put(tableStage.groupID, new Table(tableStage,game.gameID));
                        }


                    }
                    allTables.put(game.gameID, tableGroup);


                }

                handle(()-> bridge.wholeDataUpdated());


                break;
            case 34:
                peopleOnline.put(lobbyData.data.gameID,lobbyData.data.onlinePeople);
                handle(()-> bridge.peopleOnlineUpdate(lobbyData.data.gameID, lobbyData.data.onlinePeople));
                break;
            case 30:
                User.balance(lobbyData.data.balance);
                break;
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if(connected) handleSimple(()-> app.lobbyFail());
        super.onFailure( webSocket,  t,  response);
    }

}
