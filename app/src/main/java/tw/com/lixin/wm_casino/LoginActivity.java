package tw.com.lixin.wm_casino;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.CustomInput;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client35;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.CmdImg;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.popups.LanguagePopup;
import tw.com.lixin.wm_casino.popups.LoadDialog;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class LoginActivity extends RootActivity implements LobbyBridge {

   // private LanguagePopup popup;
    private Map<Locale, CmdImg> LangSwitch = new HashMap<>();
    private LobbySource source;
    private LanguagePopup popup = new LanguagePopup();
    private LoadDialog loading = new LoadDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // popup = new LanguagePopup(this);
        source = LobbySource.getInstance();
        popup.initiate(this);
        loading.initiate(this);
        LangSwitch.put(Locale.US, f-> f.setImageResource(R.drawable.lang_us));
        LangSwitch.put(Locale.TAIWAN, f-> f.setImageResource(R.drawable.lang_tw));
        LangSwitch.put(Locale.CHINA, f-> f.setImageResource(R.drawable.lang_cn));

        source.bind(this);

        clicked(R.id.login_btn,v ->{
            CustomInput userIn = findViewById(R.id.user_input);
            CustomInput passIn = findViewById(R.id.pass_input);
            String user = userIn.getRawText();
            String pass = passIn.getRawText();
            loading.show();
            source.login(user,pass,data->{
                User.account(data.account);
                User.gameID(data.gameID);
                User.userName(data.userName);
                User.memberID(data.memberID);
                User.sid(data.sid);
                source.send(Json.to(new Client35()));
            }, fail->{
                loading.dismiss();
                alert(fail);
            });
        });

        clicked(R.id.demo_btn, v->{
            loading.show();
            source.login("ANONYMOUS","1234",data->{
                User.account(data.account);
                User.gameID(data.gameID);
                User.userName(data.userName);
                User.memberID(data.memberID);
                User.sid(data.sid);
                source.send(Json.to(new Client35()));
            }, fail->{
                loading.dismiss();
                alert(fail);
            });
        });

       // clicked(R.id.lang_btn,v-> popup.show());


        clicked(R.id.lang_btn,v-> popup.show());


    }

    @Override
    public void onPause() {
        super.onPause();
        source.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        source.bind(this);
        Objects.requireNonNull(LangSwitch.get(getLocale())).exec(findViewById(R.id.lang_btn));
    }

    @Override
    public void wholeDataUpdated() {
        App.music_on();
        loading.dismiss();
        toActivity(LobbyActivity.class);
    }

    @Override
    public void peopleOnlineUpdate(int gameID, int number) {
    }
}
