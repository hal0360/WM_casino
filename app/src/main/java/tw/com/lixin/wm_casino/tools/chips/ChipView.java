package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.models.Chip;

public class ChipView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {

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
        setImageResource(imgOn);
        chip = new Chip(val, imgOff, getId());
    }

    public void turnOn() {
        setImageResource(imageOn);
    }

    public void turnOff() {
        setImageResource(imageOff);
    }

    @Override
    public void onClick(View v) {
        turnOn();
        if(Chip.isCustom){
            CustomChip bridge = findViewById(Chip.curChip.chipId);
            bridge.turnOff();
        }else {
            ChipView bridge = findViewById(Chip.curChip.chipId);
            bridge.turnOff();
        }
        Chip.isCustom = false;
        Chip.curChip = chip;
    }
}
