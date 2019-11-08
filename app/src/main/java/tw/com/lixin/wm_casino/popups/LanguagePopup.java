package tw.com.lixin.wm_casino.popups;

import android.view.Gravity;
import android.view.ViewGroup;

import java.util.Locale;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class LanguagePopup extends PopupFragment {

    private ClickConstraint selected, english, chineseSim, chineseTra;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.language_popup);
        dialog.setGravity(Gravity.TOP|Gravity.END);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

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
        btn.setBackgroundResource(R.drawable.language_select_border);
        selected = btn;
    }

    private void resetSelected(ClickConstraint btn, Locale loc){
        RootActivity activity = (RootActivity) getContext();
        assert activity != null;
        activity.switchLocale(loc);
        btn.setBackgroundResource(R.drawable.language_select_border);
        selected.setBackgroundResource(0);
        selected = btn;
    }
}
