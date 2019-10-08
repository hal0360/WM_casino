package tw.com.lixin.wm_casino;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.holders.BacHolder;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.websocketSource.GameSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class GameActivity extends WMActivity implements LobbyBridge, GameBridge {

    LobbySource lobbySource;
    GameSource gameSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSource = GameSource.getInstance();
        lobbySource = LobbySource.getInstance();

        ItemsView tableList = findViewById(R.id.table_list);
        List<BacHolder> holders = new ArrayList<>();

        if(gameSource.gameID == 101){
//            setTextView(R.id.game_title, getString(R.string.wmbaccarat));
            for(Table table: gameSource.tables) holders.add(new BacHolder((BacTable) table));
        }else if(gameSource.gameID == 102){

        }else{
            alert("error!");
            toActivity(LobbyActivity.class);
        }


        tableList.add(holders);
    }


    @Override
    public void onResume() {
        super.onResume();

        lobbySource.bind(this);
        gameSource.bind(this);

        if(!lobbySource.isConnected()){
            loading();
            lobbySource.login(User.sid(), data->{
                unloading();
            }, fail->{
                alert("lobby connection lost");
                unloading();
                toActivity(LoginActivity.class);
            });
        }
        if(!gameSource.isConnected()){
            loading();
            gameSource.login(User.sid(), data->{
                unloading();
            }, fail->{
                alert(" Game connection lost");
                unloading();
                toActivity(LoginActivity.class);
            });
        }

    }

    @Override
    public void tableLogin(TableData.Data data) {
        if(data.bOk){
            BacActivity.bacStarted(data);
            pushActivity(BacActivity.class);
        }
        else alert("Cannot login to this table");
    }

    @Override
    public void resultUpdate(TableData.Data data) {

    }

    @Override
    public void balanceUpdate(float value) {

    }

    @Override
    public void betUpdate(boolean betOK) {

    }

    @Override
    public void cardUpdate(int area, int img) {

    }

    @Override
    public void statusUpdate(int stage) {

    }

    @Override
    public void gridUpdate() {

    }

    @Override
    public void winLossUpdate(TableData.Data data) {

    }

    @Override
    public void startCountDown(int milli) {

    }


    @Override
    public void peopleOnlineUpdate(int gameID, int number) {

    }

//not used
    @Override
    public void wholeDataUpdated() { }
}
