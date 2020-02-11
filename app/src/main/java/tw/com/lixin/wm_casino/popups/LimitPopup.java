package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.LimitCollection;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;

public class LimitPopup extends PopupFragment {

    private CollectionsView limitCollections;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.limit_popup);

        limitCollections = dialog.findViewById(R.id.limit_items_view);
        ClickText limitBtn = dialog.findViewById(R.id.limit_btn);
        ClickText settingBtn = dialog.findViewById(R.id.setting_btn);

        limitBtn.clicked(v-> {
            limitBtn.setBackgroundResource(R.drawable.message_butt_border);
            settingBtn.setBackgroundResource(0);
            dialog.findViewById(R.id.limit_box).bringToFront();
        });

        settingBtn.clicked(v-> {
            settingBtn.setBackgroundResource(R.drawable.message_butt_border);
            limitBtn.setBackgroundResource(0);
            dialog.findViewById(R.id.setting_box).bringToFront();
        });

        CasinoActivity casinoActivity = (CasinoActivity) dialog.getRootActivity();
        casinoActivity.limitShows(this);

        ClickText close = dialog.findViewById(R.id.close_btn);
        close.clicked(v-> dismiss());

    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    public void addLimit(String name, String odds, int max, int min){


        limitCollections.add(new LimitCollection(name,odds,max+"",min+""));
    }

}
