package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.utils.TimeTask;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;


public class WinLossPopup extends PopupFragment {

    private TimeTask timeTask;

    private float winVal;

    public WinLossPopup(float win){
        winVal = win;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.win_loss_popup);
        TextView pay = dialog.findViewById(R.id.pay_txt);
        pay.setText(winVal+"");
        timeTask = new TimeTask();
        timeTask.delay(4000, this::dismiss);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {
        timeTask.clear();
    }
}
