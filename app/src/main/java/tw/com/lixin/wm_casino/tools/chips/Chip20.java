package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip20 extends ChipView{
    public Chip20(Context context) {
        super(context);
        init(20, R.drawable.chip20, R.drawable.chip20f);
    }

    public Chip20(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(20, R.drawable.chip20, R.drawable.chip20f);
    }
}
