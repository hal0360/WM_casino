package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.dataModels.LoginData;
import tw.com.lixin.wm_casino.global.Url;
import tw.com.lixin.wm_casino.global.User;

public class SplashActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String pass = getPassedStr();
        LoginData loginData = new LoginData( User.account(), pass);
        App.socket.onSuccess(()->{
            App.socket.send(Json.to(loginData));
        });

        App.socket.onFail(()->{
            alert("connection error");
            toActivity(LoginActivity.class);
        });

        App.socket.onReceive(0,data -> {
            if(data.bOk){

        alert(data.gameID+"");

                User.account(data.account);
                User.gameID(data.gameID);
                User.userName(data.userName);
                User.memberID(data.memberID);
                User.sid(data.sid);
                App.socket.send(Json.to(new Client35()));
            }else {
                alert("Cannot login");
                toActivity(LoginActivity.class);
            }
        });

        App.socket.onReceive(35,data -> {

            /*
            App.games = data.gameArr;
            for(Game game: data.gameArr){
                if (game.gameID == 101)
                    bacGame = game;
            }
            setTables();
            App.cleanSocketCalls();
            App.socket.send(Json.to(loginData));
            toActivity(LobbyActivity.class);
            */

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        delay(500, ()->{
            App.socket.start(Url.Lobby);
        });

    }
}
