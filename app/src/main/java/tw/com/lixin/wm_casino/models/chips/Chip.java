package tw.com.lixin.wm_casino.models.chips;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Chip extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener{
    public int value, imageOn, imageOff;
    private boolean isOn = false;

    public static Chip CurChip;

    public Chip(Context context) {
        super(context);
        this.setAlpha(0.5f);
    }

    public Chip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setAlpha(0.5f);
    }


    public void turnOn(){
        isOn = true;
        CurChip = this;
    }

    public void turnOff(){
        isOn = false;
        CurChip = null;
    }

    @Override
    public void onClick(View v) {

    }
}
