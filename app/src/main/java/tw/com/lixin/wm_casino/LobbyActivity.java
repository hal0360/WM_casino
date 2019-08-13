package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
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

        });
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


}
