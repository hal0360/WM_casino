package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;

public class RulePopup extends PopupFragment {
    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.rule_popup);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }
}
