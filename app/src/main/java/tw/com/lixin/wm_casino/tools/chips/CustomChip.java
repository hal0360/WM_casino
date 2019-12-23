package tw.com.lixin.wm_casino.tools.chips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.popups.NumberPadDialog;

public class CustomChip extends ConstraintLayout {

    public TextView numTxt;
    public Chip chip;
    private NumberPadDialog dialog;
    private Context context;

    public CustomChip(Context context) {
        super(context);
        View.inflate(context, R.layout.coustom_chip, this);
        this.context = context;
        numTxt = findViewById(R.id.num_txt);
       // chip = new Chip(1, R.drawable.chipcustom);
        setBackgroundResource(R.drawable.chip_custom);

    }

    public CustomChip(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.coustom_chip, this);
        this.context = context;
        numTxt = findViewById(R.id.num_txt);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CustomChip);
        int tSiaze = a.getInt(R.styleable.CustomChip_number_size, 8);
        numTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tSiaze);
        a.recycle();
        //chip = new Chip(1, R.drawable.chipcustom);
        setBackgroundResource(R.drawable.chip_custom);

    }

/*
    @Override
    public void onClick(View v) {

        turnOn();
        if(dialog == null) dialog = new NumberPadDialog(context,this);
        dialog.show();
    }*/

    @SuppressLint("SetTextI18n")
    public void turnOn() {
        numTxt.setText(chip.value + "");
    }

    public void turnOff() {
        numTxt.setText(context.getString(R.string.custom));
    }

}
