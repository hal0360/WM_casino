package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.tools.ImageGetTask;
import tw.com.lixin.wm_casino.tools.ProfileSetting;
import tw.com.lixin.wm_casino.tools.buttons.GameButton;
import tw.com.lixin.wm_casino.websocketSource.GameSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class LobbyActivity extends WMActivity implements LobbyBridge {

    private LobbySource lobbySource;

    private SparseArray<GameButton> gameButtons;


    @SuppressLint("FindViewByIdCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        lobbySource = LobbySource.getInstance();
        gameButtons = new SparseArray<>();
        gameButtons.put(101,findViewById(R.id.baccarat_game));
        gameButtons.put(102,findViewById(R.id.dragon_tiger_game));
        gameButtons.put(103,findViewById(R.id.roulette_game));
        gameButtons.put(104,findViewById(R.id.sic_bo_game));
        gameButtons.put(105,findViewById(R.id.niuniu_game));
        gameButtons.put(106,findViewById(R.id.samgong_game));
        gameButtons.put(107,findViewById(R.id.fantan_game));
        gameButtons.put(109,findViewById(R.id.golden_flower_game));
        gameButtons.put(110,findViewById(R.id.fish_prawn_game));
        for(int i = 0; i < gameButtons.size(); i++) {
            int key = gameButtons.keyAt(i);
            gameButtons.get(key).clicked(v-> enterGame(key));
        }
    }

    public void enterGame(int gameid){
        loading();
        GameSource gameSource = GameSource.getInstance();
        gameSource.addTables(gameid);
        gameSource.gameLogin(gameid,User.sid(),data->{
            new ImageGetTask(this, gameSource.tables).execute();
        }, fail->{
            unloading();
            alert("Unable to connect to game server");
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        loading();
        lobbySource.bind(this);
        if(lobbySource.isConnected()){
            lobbySource.send(Json.to(new Client35()));
        }else{
            lobbySource.login(User.sid(),data->{
                unloading();
            }, fail->{
                alert("Connection lost");
                unloading();
                toActivity(LoginActivity.class);
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        lobbySource.unbind();
    }

    @Override
    public void wholeDataUpdated() {
        unloading();
alert("data updated");
    }

    @Override
    public void peopleOnlineUpdate(int gameID, int number) {
        GameButton button = gameButtons.get(gameID);
        if(button != null) button.setPeopleNumber(number);
    }

    public void dealerImgLoaded(){
        unloading();
        toActivity(GameActivity.class);
    }
}
