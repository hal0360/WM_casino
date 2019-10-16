package tw.com.lixin.wm_casino.popups;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;

public class ProfilePopup extends PopupFragment {



    @Override
    public void dialogCreated(Dialog dialog) {
        dialog.setContentView(R.layout.profile_popup);

        setGravity(Gravity.TOP | Gravity.END);
        setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }






}
