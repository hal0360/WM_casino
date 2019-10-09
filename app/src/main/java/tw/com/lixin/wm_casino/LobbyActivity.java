package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.view.View;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.tools.ImageGetTask;
import tw.com.lixin.wm_casino.tools.ProfileSetting;
import tw.com.lixin.wm_casino.websocketSource.GameSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;


public class LobbyActivity extends WMActivity implements LobbyBridge {

    private LobbySource lobbySource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        lobbySource = LobbySource.getInstance();
        if(!isPortrait()) setTextView(R.id.online_ppl_txt, lobbySource.peopleOnline.get(109)+"");
        ProfileSetting profileSetting = findViewById(R.id.profile_setting);
        profileSetting.setAll();

    }

    public void enterGame(View view){
        loading();
        int gameid = Integer.parseInt((String) view.getTag());
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
        if(!isPortrait()) setTextView(R.id.online_ppl_txt, lobbySource.peopleOnline.get(109) + "");

    }

    public void dealerImgLoaded(){
        unloading();
        toActivity(GameActivity.class);
    }
}
