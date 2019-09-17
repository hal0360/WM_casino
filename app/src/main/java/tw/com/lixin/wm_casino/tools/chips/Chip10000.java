package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip10000 extends ChipView{
    public Chip10000(Context context) {
        super(context);
        init(10000, R.drawable.chip10000, R.drawable.chip10000f);
    }

    public Chip10000(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(10000, R.drawable.chip10000, R.drawable.chip10000f);
    }
}
