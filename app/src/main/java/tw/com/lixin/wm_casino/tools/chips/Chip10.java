package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip10 extends ChipView{
    public Chip10(Context context) {
        super(context);
        init(10, R.drawable.chip10, R.drawable.chip10f);
    }

    public Chip10(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(10, R.drawable.chip10, R.drawable.chip10f);
    }
}
