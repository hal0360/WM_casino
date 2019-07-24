package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;


public class LobbyActivity extends WMActivity implements LobbyBridge {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


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
