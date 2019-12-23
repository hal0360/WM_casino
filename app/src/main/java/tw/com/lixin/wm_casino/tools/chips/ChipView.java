package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;

public class ChipView extends androidx.appcompat.widget.AppCompatImageView{

    protected Chip chip;
    protected int imageOn, imageOff;

    public ChipView(Context context) {
        super(context);

    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void init(int val, int imgOn, int imgOff){
        imageOn = imgOn;
        imageOff = imgOff;
        setImageResource(imgOff);
        chip = new Chip(val, imgOff, getId());
    }

    public Chip getChip(){
        return chip;
    }

    public void turnOn() {
        setBackgroundResource(R.drawable.chip_light);
        setImageResource(imageOn);
    }

    public void turnOff() {
        setBackgroundResource(0);
        setImageResource(imageOff);
    }

}
