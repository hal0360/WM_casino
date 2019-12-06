package tw.com.lixin.wm_casino.popups;

import android.view.Gravity;
import android.view.ViewGroup;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class ProfilePopup extends PopupFragment {


    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.profile_popup);

        dialog.setGravity(Gravity.TOP | Gravity.END);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        SoundPopup soundPopup = new SoundPopup();
        LanguagePopup languagePopup = new LanguagePopup();
        RulePopup rulePopup = new RulePopup();

        ((ClickConstraint) dialog.findViewById(R.id.music_btn)).clicked(v->{
            RootActivity activity =(RootActivity) dialog.getContext();
            activity.showPopup(soundPopup);
        });

        ((ClickConstraint) dialog.findViewById(R.id.language_btn)).clicked(v-> {
            RootActivity activity =(RootActivity) dialog.getContext();
            activity.showPopup(languagePopup);
        });

        ((ClickConstraint) dialog.findViewById(R.id.rule_btn)).clicked(v->{
            RootActivity activity =(RootActivity) dialog.getContext();
            activity.showPopup(rulePopup);
        });
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}
