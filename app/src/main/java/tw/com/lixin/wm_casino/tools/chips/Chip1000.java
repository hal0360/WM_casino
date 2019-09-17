package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip1000 extends ChipView{
    public Chip1000(Context context) {
        super(context);
        init(1000, R.drawable.chip1000, R.drawable.chip1000f);
    }

    public Chip1000(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(1000, R.drawable.chip1000, R.drawable.chip1000f);
    }
}