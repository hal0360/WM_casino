package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.tools.chips.CustomChip;

public class NumberPadDialog extends Popup {

    private CustomChip chip;
    private Context context;

    public NumberPadDialog(Context context, CustomChip chippy) {
        super(context, R.layout.number_pad);
        chip = chippy;
        this.context = context;

        clicked(R.id.pad0,v-> setText("0"));

        clicked(R.id.pad1,v->{
            setText("1");
        });

        clicked(R.id.pad2,v->{
            setText("2");
        });

        clicked(R.id.pad3,v->{
            setText("3");
        });

        clicked(R.id.pad4,v->{
            setText("4");
        });

        clicked(R.id.pad5,v->{
            setText("5");
        });

        clicked(R.id.pad6,v->{
            setText("6");
        });

        clicked(R.id.pad7,v->{
            setText("7");
        });

        clicked(R.id.pad8,v->{
            setText("8");
        });

        clicked(R.id.pad9,v->{
            setText("9");
        });

        clicked(R.id.pad_back,v->{
            String numStr = chip.numTxt.getText().toString();
            numStr = numStr.substring(0, numStr.length() - 1);
            chip.numTxt.setText(numStr);
            setText("");
        });

        clicked(R.id.pad_enter,v-> dismiss());
    }

    @SuppressLint("SetTextI18n")
    private void setText(String val){
        String numStr = chip.numTxt.getText().toString();
        try {
            numStr = numStr + val;
            chip.chip.value = Integer.parseInt(numStr);
            chip.numTxt.setText(numStr);
        } catch(NumberFormatException e){
            chip.chip.value = 1;
            chip.numTxt.setText(1+"");
        }
    }
}
