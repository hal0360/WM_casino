package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip50 extends ChipView{
    public Chip50(Context context) {
        super(context);
        init(50, R.drawable.chip50, R.drawable.chip50f);
    }

    public Chip50(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(50, R.drawable.chip50, R.drawable.chip50f);
    }
}
