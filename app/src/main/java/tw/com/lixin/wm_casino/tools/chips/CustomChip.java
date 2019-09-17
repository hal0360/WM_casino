package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;

public class CustomChip extends ConstraintLayout implements View.OnClickListener{

    private TextView numTxt;

    public CustomChip(Context context) {
        super(context);
    }

    public CustomChip(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.chip_stack, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        numTxt = findViewById(R.id.num_txt);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CustomChip);
        int tSiaze = a.getInt(R.styleable.CustomChip_number_size, 8);
        numTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,tSiaze);
        a.recycle();

    }

    /*
    public void turnOn(){
        setImageResource(imageOn);
    }

    public void turnOff(){
        setImageResource(imageOff);
    }

    public Chip getChip(){
        return chip;
    }*/

    @Override
    public void onClick(View v) {

    }
}
