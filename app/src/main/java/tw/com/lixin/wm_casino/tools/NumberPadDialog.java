package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.widget.TextView;

import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.R;

public class NumberPadDialog extends Popup {

    private TextView numBox;
    private String numTxt;

    public NumberPadDialog(Context context, TextView num) {
        super(context, R.layout.load_dialog_layout, R.style.LoadDialog);
        numBox = num;
        numTxt = numBox.getText().toString();

        clicked(R.id.pad0,v->{
            numTxt = numTxt + "0";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad1,v->{
            numTxt = numTxt + "1";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad2,v->{
            numTxt = numTxt + "2";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad3,v->{
            numTxt = numTxt + "3";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad4,v->{
            numTxt = numTxt + "4";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad5,v->{
            numTxt = numTxt + "5";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad6,v->{
            numTxt = numTxt + "6";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad7,v->{
            numTxt = numTxt + "7";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad8,v->{
            numTxt = numTxt + "8";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad9,v->{
            numTxt = numTxt + "9";
            numBox.setText(numTxt);
        });

        clicked(R.id.pad_back,v->{
            numTxt = numTxt.substring(0, numTxt.length() - 1);
            numBox.setText(numTxt);
        });

        clicked(R.id.pad_enter,v-> dismiss());
    }
}
