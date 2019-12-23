package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.CasinoArea;

public class NumberPadDialog extends PopupFragment {



    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.number_pad);

        CasinoActivity activity = (CasinoActivity) dialog.getRootActivity();
        CasinoArea area = activity.getArea();

        dialog.clicked(R.id.pad0,v-> area.setCusChip(0));

        dialog.clicked(R.id.pad1,v->{
            area.setCusChip(1);
        });

        dialog.clicked(R.id.pad2,v->{
            area.setCusChip(2);
        });

        dialog.clicked(R.id.pad3,v->{
            area.setCusChip(3);
        });

        dialog.clicked(R.id.pad4,v->{
            area.setCusChip(4);
        });

        dialog.clicked(R.id.pad5,v->{
            area.setCusChip(5);
        });

        dialog.clicked(R.id.pad6,v->{
            area.setCusChip(6);
        });

        dialog.clicked(R.id.pad7,v->{
            area.setCusChip(7);
        });

        dialog.clicked(R.id.pad8,v->{
            area.setCusChip(8);
        });

        dialog.clicked(R.id.pad9,v->{
            area.setCusChip(9);
        });

        dialog.clicked(R.id.pad_back,v->{
            area.setCusBack();
        });

        dialog.clicked(R.id.pad_enter,v-> dismiss());
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }
}
