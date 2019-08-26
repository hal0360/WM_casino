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
        if(!isPortrait()) setTextView(R.id.balance_txt, User.balance() +"");

        clicked(R.id.baccarat_game,v->{
            BacSource bacSource = BacSource.getInstance();
            lobbySource.gameID = 101;
            lobbySource.gameName = getString(R.string.wmbaccarat);
            loading();
            bacSource.login(User.sid(),data->{
                Game bacGame = lobbySource.findGame(101);
                if(bacGame == null) return;
                bacSource.tables.clear();
                for(Group tableStage: bacGame.groupArr){
                    if ( tableStage.gameStage != 4){
                        BacTable table = new BacTable();
                        table.setUp(tableStage.historyArr);
                        table.stage = tableStage.gameStage;
                        table.groupID = tableStage.groupID;
                        table.groupType = tableStage.groupType;
                        table.score = tableStage.bankerScore;
                        table.round = tableStage.gameNoRound;
                        table.number = tableStage.gameNo;
                        table.dealerName = tableStage.dealerName;
                        table.dealerImageUrl = tableStage.dealerImage;
                        bacSource.tables.add(table);
                    }
                }
                new ImageGetTask(this, bacSource.tables).execute();

            }, fail->{
                unloading();
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

    /*
    private void gameSetUp(GameSource source){
        loading();
        source.login(User.sid(),data->{
            Game bacGame = lobbySource.findGame(101);
            if(bacGame == null) return;
            source.tables.clear();
            for(Group tableStage: bacGame.groupArr){
                if ( tableStage.gameStage != 4){
                    BacTable table = new BacTable();
                    table.setUp(tableStage.historyArr);
                    table.stage = tableStage.gameStage;
                    table.groupID = tableStage.groupID;
                    table.groupType = tableStage.groupType;
                    table.score = tableStage.bankerScore;
                    table.round = tableStage.gameNoRound;
                    table.number = tableStage.gameNo;
                    table.dealerName = tableStage.dealerName;
                    table.dealerImageUrl = tableStage.dealerImage;
                    bacSource.tables.add(table);
                }
            }
            new ImageGetTask(this, bacSource.tables).execute();

        }, fail->{
            unloading();
            alert("Unable to connect to bac");
        });
    }*/

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
        if(gameID != 109) return;
        if(!isPortrait()) setTextView(R.id.online_ppl_txt, number+"");
    }

    public void dealerImgLoaded(){
        unloading();
        toActivity(GameActivity.class);
    }
}
