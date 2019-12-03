package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.holders.LimitHolder;

public class LimitPopup extends PopupFragment {

    private ItemsView limitView;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.limit_popup);

        limitView = dialog.findViewById(R.id.limit_items_view);

    }

    public void addLimit(String name, String odds, String max, String min){
        limitView.add(new LimitHolder(name,odds,max,min));
    }

}
