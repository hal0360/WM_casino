package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.LimitCollection;

public class LimitPopup extends PopupFragment {

    private CollectionsView limitCollections;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.limit_popup);

        limitCollections = dialog.findViewById(R.id.limit_items_view);

    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    public void addLimit(String name, String odds, int max, int min){
        limitCollections.add(new LimitCollection(name,odds,max+"",min+""));
    }

}
