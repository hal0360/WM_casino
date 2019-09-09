package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.models.chips.Chip;

public class ChipView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener{

    private Chip chip;

    public ChipView(Context context) {
        super(context);
        this.setAlpha(0.5f);
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setAlpha(0.5f);
    }


    @Override
    public void onClick(View v) {

    }
}
