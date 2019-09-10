package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;

public class ChipView extends android.support.v7.widget.AppCompatImageView{

    private Chip chip;
    private int image;

    public ChipView(Context context) {
        super(context);
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChipView, 0, 0);
        image = a.getInt(R.styleable.ChipView_chip_image,R.drawable.chip1);
        int vall = a.getInt(R.styleable.ChipView_value,1);
        boolean isOn = a.getBoolean(R.styleable.ChipView_is_on,false);
        a.recycle();
        if(isOn) setBackgroundResource(R.drawable.chip_light);
        else  setBackgroundResource(0);
        chip = new Chip(vall, image);
    }

    public void turnOn(){
        setBackgroundResource(R.drawable.chip_light);
    }

    public void turnOff(){
        setBackgroundResource(0);
    }

    public Chip getChip(){
        return chip;
    }

}
