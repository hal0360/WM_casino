package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip1 extends ChipView {
    public Chip1(Context context) {
        super(context);
        init(1, R.drawable.chip1, R.drawable.chip1f);
    }

    public Chip1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(1, R.drawable.chip1, R.drawable.chip1f);
    }

}
