package tw.com.lixin.wm_casino.popups;

import android.content.Context;
import android.view.Gravity;

import java.util.Locale;

import tw.com.atromoby.widgets.Popup;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;

public class LanguagePopup extends Popup {

    private RootActivity activity;

    public LanguagePopup(Context context) {
        super(context, R.layout.language_popup, R.style.SettingCasDialog);

        setGravity(Gravity.TOP|Gravity.END);

        activity = (RootActivity) context;

        clicked(R.id.english_btn,v->{
            activity.switchLocale(Locale.US);
            dismiss();
        });

        clicked(R.id.chinese_sim_btn,v->{
            activity.switchLocale(Locale.CHINA);
            dismiss();
        });

        clicked(R.id.chinese_tra_btn,v->{
             activity.switchLocale(Locale.TAIWAN);
            dismiss();
        });


    }



}
