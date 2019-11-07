package tw.com.lixin.wm_casino.popups;

import android.view.Gravity;
import android.view.ViewGroup;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class ProfilePopup extends PopupFragment {

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.profile_popup);

        dialog.setGravity(Gravity.TOP | Gravity.END);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        SoundPopup soundPopup = new SoundPopup();
        soundPopup.initiate(getRoot());
        LanguagePopup languagePopup = new LanguagePopup();
        languagePopup.initiate(getRoot());
        RulePopup rulePopup = new RulePopup();
        rulePopup.initiate(getRoot());

        ((ClickConstraint) dialog.findViewById(R.id.music_btn)).clicked(v-> soundPopup.show());

        ((ClickConstraint) dialog.findViewById(R.id.language_btn)).clicked(v-> languagePopup.show());

        ((ClickConstraint) dialog.findViewById(R.id.rule_btn)).clicked(v-> rulePopup.show());
    }

}
