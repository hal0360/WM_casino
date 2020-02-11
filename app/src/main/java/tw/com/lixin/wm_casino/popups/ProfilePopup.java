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

        ((ClickConstraint) dialog.findViewById(R.id.music_btn)).clicked(v-> dialog.getRootActivity().showPopup(new SoundPopup()));

        ((ClickConstraint) dialog.findViewById(R.id.language_btn)).clicked(v-> dialog.getRootActivity().showPopup(new LanguagePopup()));

        ((ClickConstraint) dialog.findViewById(R.id.rule_btn)).clicked(v-> dialog.getRootActivity().showPopup(new RulePopup()));
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}
