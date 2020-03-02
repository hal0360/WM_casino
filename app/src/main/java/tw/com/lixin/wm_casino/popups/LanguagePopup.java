package tw.com.lixin.wm_casino.popups;

import android.view.Gravity;
import android.view.ViewGroup;

import java.util.Locale;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.LobbyActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.LocaleUtils;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class LanguagePopup extends PopupFragment {

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.language_popup);
        dialog.setGravity(Gravity.TOP|Gravity.END);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ClickConstraint english = dialog.findViewById(R.id.english_btn);
        ClickConstraint chineseSim = dialog.findViewById(R.id.chinese_sim_btn);
        ClickConstraint chineseTra = dialog.findViewById(R.id.chinese_tra_btn);

        if(LocaleUtils.sLocale == Locale.US){
            setSelected(english);
        }else if(LocaleUtils.sLocale == Locale.CHINA){
            setSelected(chineseSim);
        }else if(LocaleUtils.sLocale == Locale.TAIWAN){
            setSelected(chineseTra);
        }

        english.clicked(v-> resetSelected(Locale.US));
        chineseSim.clicked(v-> resetSelected( Locale.CHINA));
        chineseTra.clicked(v-> resetSelected( Locale.TAIWAN));

    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    private void setSelected(ClickConstraint btn){
        btn.setBackgroundResource(R.drawable.language_select_border);
    }

    private void resetSelected(Locale loc){
        RootActivity activity = (RootActivity) getContext();
        assert activity != null;
        App.switchLanguage(loc);
        LobbyActivity.langChanged = true;
        activity.recreate();
        dismiss();
    }
}
