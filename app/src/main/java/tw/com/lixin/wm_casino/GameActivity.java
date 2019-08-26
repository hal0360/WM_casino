package tw.com.lixin.wm_casino;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.holders.BacHolder;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.websocketSource.BacSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class GameActivity extends WMActivity implements GameBridge, LobbyBridge {

    LobbySource lobbySource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac);

        BacSource source = BacSource.getInstance();
        LobbySource lobbySource = LobbySource.getInstance();
        setTextView(R.id.game_title, lobbySource.gameName);
        ItemsView tableList = findViewById(R.id.table_list);
        List<BacHolder> holders = new ArrayList<>();

        if(lobbySource.gameID == 101){
            for(Table table: source.tables){
                holders.add(new BacHolder((BacTable) table));
            }
        }else if(lobbySource.gameID == 102){

        }else{
            alert("error!");
            toActivity(LobbyActivity.class);
        }


        tableList.add(holders);
    }

    @Override
    public void tableLogin(boolean logOk) {

    }

    @Override
    public void balanceUpdate(float value) {

    }

    @Override
    public void peopleOnlineUpdate(int gameID, int number) {

    }

//not used
    @Override
    public void resultUpadte() {}
    @Override
    public void betUpdate(boolean betOK) {}
    @Override
    public void wholeDataUpdated() { }
}
