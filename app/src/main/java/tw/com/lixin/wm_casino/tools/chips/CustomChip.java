package tw.com.lixin.wm_casino.tools.chips;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.tools.NumberPadDialog;

public class CustomChip extends ConstraintLayout implements View.OnClickListener {

    public TextView numTxt;
    public Chip chip;
    private NumberPadDialog dialog;
    private Context context;

    public CustomChip(Context context) {
        super(context);
        View.inflate(context, R.layout.coustom_chip, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        this.context = context;
        numTxt = findViewById(R.id.num_txt);
        chip = new Chip(1, R.drawable.chipcustom, getId());
        setBackgroundResource(R.drawable.chip_custom);
        setOnClickListener(this);
    }

    public CustomChip(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.coustom_chip, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        this.context = context;
        numTxt = findViewById(R.id.num_txt);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CustomChip);
        int tSiaze = a.getInt(R.styleable.CustomChip_number_size, 8);
        numTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tSiaze);
        a.recycle();
        chip = new Chip(1, R.drawable.chipcustom, getId());
        setBackgroundResource(R.drawable.chip_custom);
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        /*
        turnOn();
        if(!Chip.isCustom){
            Activity activity = (Activity) context;
            ChipView bridge = activity.findViewById(Chip.curChip.chipId);
            bridge.turnOff();
        }
        Chip.isCustom = true;
        Chip.curChip = chip;
        */
        turnOn();
        if(dialog == null) dialog = new NumberPadDialog(context,this);
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void turnOn() {
        numTxt.setText(chip.value + "");
    }

    public void turnOff() {
        numTxt.setText(context.getString(R.string.custom));
    }

}
