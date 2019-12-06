package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;


public class LoadDialog extends PopupFragment {

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.load_dialog_layout);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }
}
