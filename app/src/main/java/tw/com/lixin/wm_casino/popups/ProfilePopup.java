package tw.com.lixin.wm_casino.popups;

import android.app.Dialog;
import android.view.Gravity;
import android.view.ViewGroup;

import tw.com.atromoby.widgets.PopupFragment;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;

public class ProfilePopup extends PopupFragment {


    @Override
    public void dialogCreated(Dialog dialog) {
        dialog.setContentView(R.layout.profile_popup);

        setGravity(Gravity.TOP | Gravity.END);
        setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RootActivity activity = (RootActivity) getContext();
        assert activity != null;
        ((ClickConstraint) dialog.findViewById(R.id.music_btn)).clicked(v->{
            SoundPopup popup = new SoundPopup();
            popup.show(activity);
        });
        ((ClickConstraint) dialog.findViewById(R.id.language_btn)).clicked(v->{
            LanguagePopup popup = new LanguagePopup();
            popup.show(activity);
        });
    }

}
