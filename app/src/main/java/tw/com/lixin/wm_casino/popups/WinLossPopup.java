package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.widget.TextView;

import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;


public class WinLossPopup extends PopupFragment {

    private TextView pay;

    @SuppressLint("SetTextI18n")
    public void setPay(float win){
        pay.setText(win+"");
    }

    @Override
    public void dialogCreated(Dialog dialog) {
        pay = findViewById(R.id.pay_txt);

    }
}
