package tw.com.lixin.wm_casino;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseArray;

import java.util.Locale;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.popups.LoadDialog;
import tw.com.lixin.wm_casino.tools.LocaleUtils;
import tw.com.lixin.wm_casino.tools.buttons.GameButton;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class LobbyActivity extends WMActivity implements LobbyBridge {

    private LobbySource lobbySource;
    private SparseArray<GameButton> gameButtons;
    private LoadDialog loading;

    public static boolean langChanged = false;



    private void setApplicationLanguage(String newLanguage) {
        Resources activityRes = getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(newLanguage);
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());
        Resources applicationRes = getApplicationContext().getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf, applicationRes.getDisplayMetrics());
    }


    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (overrideConfiguration != null) {
            overrideConfiguration.setLocale(LocaleUtils.sLocale);
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lobby);

        lobbySource = LobbySource.getInstance();
        loading = new LoadDialog();
        App.music_on();

        if(!langChanged) {
            showPopup(loading);
            lobbySource.send(Json.to(new Client35()));
        }
        langChanged = false;

        gameButtons = new SparseArray<>();
        gameButtons.put(101,findViewById(R.id.baccarat_game));
        gameButtons.put(102,findViewById(R.id.dragon_tiger_game));
        gameButtons.put(103,findViewById(R.id.roulette_game));
        gameButtons.put(104,findViewById(R.id.sic_bo_game));
        gameButtons.put(105,findViewById(R.id.niuniu_game));
        gameButtons.put(106,findViewById(R.id.samgong_game));
        gameButtons.put(107,findViewById(R.id.fantan_game));
        gameButtons.put(108,findViewById(R.id.se_die_game));
        gameButtons.put(110,findViewById(R.id.fish_prawn_game));
        gameButtons.put(111,findViewById(R.id.golden_flower_game));
        gameButtons.put(128,findViewById(R.id.anda_game));

        for(int i = 0; i < gameButtons.size(); i++) {
            int key = gameButtons.keyAt(i);
            gameButtons.get(key).setPeopleNumber(lobbySource.peopleOnline.get(key));
            gameButtons.get(key).clicked(v-> enterGame(key));
        }
    }

    public void enterGame(int gameid){
        lobbySource.curGameID = gameid;
        toActivity(GameActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        lobbySource.bind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        lobbySource.unbind();
    }


    @Override
    public void wholeDataUpdated() {
        loading.dismiss();
    }

    @Override
    public void peopleOnlineUpdate(int gameID, int number) {
        GameButton button = gameButtons.get(gameID);
        if(button != null) button.setPeopleNumber(number);
    }
}
