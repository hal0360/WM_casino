package tw.com.lixin.wm_casino;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import tw.com.atromoby.widgets.CustomInput;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.CmdImg;
import tw.com.lixin.wm_casino.popups.LanguagePopup;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class LoginActivity extends WMActivity {

    private LanguagePopup popup;
    private Map<Locale, CmdImg> LangSwitch = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        popup = new LanguagePopup(this);
        LobbySource source = LobbySource.getInstance();

        LangSwitch.put(Locale.US, f->{
            f.setImageResource(R.drawable.lang_us);
            setLangTxt("English");
        });
        LangSwitch.put(Locale.TAIWAN, f->{
            f.setImageResource(R.drawable.lang_tw);
            setLangTxt("繁體中文");
        });
        LangSwitch.put(Locale.CHINA, f->{
            f.setImageResource(R.drawable.lang_cn);
            setLangTxt("简体中文");
        });

        clicked(R.id.login_btn,v ->{
            CustomInput userIn = findViewById(R.id.user_input);
            CustomInput passIn = findViewById(R.id.pass_input);
            String user = userIn.getRawText();
            String pass = passIn.getRawText();

            loading();
            source.login(user,pass,data->{
                unloading();
                User.account(data.account);
                User.gameID(data.gameID);
                User.userName(data.userName);
                User.memberID(data.memberID);
                User.sid(data.sid);
                toActivity(LobbyActivity.class);
            }, fail->{
                unloading();
                alert(fail);
            });
        });

        clicked(R.id.demo_btn, v->{
            loading();
            source.login("ANONYMOUS","1234",data->{
                unloading();
                User.account(data.account);
                User.gameID(data.gameID);
                User.userName(data.userName);
                User.memberID(data.memberID);
                User.sid(data.sid);
                toActivity(LobbyActivity.class);
            }, fail->{
                unloading();
                alert(fail);
            });
        });

        clicked(R.id.lang_btn,v-> popup.show());
    }


    public void setLangTxt(String txt){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            TextView langT = findViewById(R.id.lang_txt);
            langT.setText(txt);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(LangSwitch.get(getLocale())).exec(findViewById(R.id.lang_btn));
    }

}
