package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.Popup;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.CasinoArea;
import tw.com.lixin.wm_casino.tools.chips.CustomChip;

public class NumberPadDialog extends PopupFragment {

    @SuppressLint("SetTextI18n")
    private void setText(String val){

    }

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
            setText("3");
        });

        dialog.clicked(R.id.pad4,v->{
            setText("4");
        });

        dialog.clicked(R.id.pad5,v->{
            setText("5");
        });

        dialog.clicked(R.id.pad6,v->{
            setText("6");
        });

        dialog.clicked(R.id.pad7,v->{
            setText("7");
        });

        dialog.clicked(R.id.pad8,v->{
            setText("8");
        });

        dialog.clicked(R.id.pad9,v->{
            setText("9");
        });

        dialog.clicked(R.id.pad_back,v->{
          
            setText("");
        });

        dialog.clicked(R.id.pad_enter,v-> dismiss());
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }
}
