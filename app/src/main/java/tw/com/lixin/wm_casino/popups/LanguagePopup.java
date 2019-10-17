package tw.com.lixin.wm_casino.popups;

import android.app.Dialog;
import android.view.Gravity;
import android.view.ViewGroup;

import java.util.Locale;

import tw.com.atromoby.widgets.PopupFragment;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class LanguagePopup extends PopupFragment {

    private RootActivity activity;
    private ClickConstraint selected, english, chineseSim, chineseTra;

    @Override
    public void dialogCreated(Dialog dialog) {
        dialog.setContentView(R.layout.language_popup);
        setGravity(Gravity.TOP|Gravity.END);
        setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        activity = (RootActivity) getContext();
        english = dialog.findViewById(R.id.english_btn);
        chineseSim = dialog.findViewById(R.id.chinese_sim_btn);
        chineseTra = dialog.findViewById(R.id.chinese_tra_btn);

        if(RootActivity.locale == Locale.US){
            setSelected(english);
        }else if(RootActivity.locale == Locale.CHINA){
            setSelected(chineseSim);
        }else if(RootActivity.locale == Locale.TAIWAN){
            setSelected(chineseTra);
        }

        english.clicked(v-> resetSelected(english, Locale.US));
        chineseSim.clicked(v-> resetSelected(chineseSim, Locale.CHINA));
        chineseTra.clicked(v-> resetSelected(chineseTra, Locale.TAIWAN));

    }

    private void setSelected(ClickConstraint btn){
        btn.setRadius(25);
        btn.setStroke(1, 0xff1E90FF);
        selected = btn;
    }

    private void resetSelected(ClickConstraint btn, Locale loc){
        activity.switchLocale(loc);
        btn.setRadius(25);
        btn.setStroke(1, 0xff1E90FF);
        selected.setStroke(0,0x00000000);
        selected = btn;
    }
}
