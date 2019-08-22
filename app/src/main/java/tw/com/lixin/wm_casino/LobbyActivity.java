package tw.com.lixin.wm_casino;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.ImageGetTask;
import tw.com.lixin.wm_casino.websocketSource.BacSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;


public class LobbyActivity extends WMActivity implements LobbyBridge {

    private LobbySource source;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        source = LobbySource.getInstance();
        if(!isPortrait()) setTextView(R.id.online_ppl_txt, source.totalOnline+"");
        if(!isPortrait()) setTextView(R.id.balance_txt, User.balance() +"");

        clicked(R.id.baccarat_game,v->{
            BacSource bacSource = BacSource.getInstance();
            bacSource.login(User.sid(),data->{

                alert("okokok");
              // toActivity(BacActivity.class);
            }, fail->{
                alert("Unable to connect to bac");
            });
        });


        clicked(R.id.roulette_game,v->{
            toActivity(BacActivity.class);
        });

        /*
        clicked(R.id.dragon_tiger_game,v->{
            BacSource bacSource = BacSource.getInstance();
            for(BacTable table: bacSource.tables){
                if(table.dealerImage != null) Log.e("dealerImage", "not null");
                else Log.e("dealerImage", "is null");
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();

        source.bind(this);
        if(source.isConnected()){
            source.send(Json.to(new Client35()));
        }else{
            loading();
            source.login(User.sid(),data->{
                unloading();
            }, fail->{
                alert("Connection lost");
                unloading();
                toActivity(LoginActivity.class);
            });
        }


    }


    @Override
    public void wholeDataUpdated() {
alert("data updated");
    }

    @Override
    public void balanceUpdated() {
        if(!isPortrait()) setTextView(R.id.balance_txt, User.balance() +"");
    }

    @Override
    public void peopleOnlineUpdate(int gameID, int number) {
        if(gameID != 109) return;
        if(!isPortrait()) setTextView(R.id.online_ppl_txt, number+"");
    }


    public void dealerImgLoaded(){

    }
}
