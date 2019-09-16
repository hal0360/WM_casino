package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip5 extends ChipView{
    public Chip5(Context context) {
        super(context);
        init(5, R.drawable.chip5, R.drawable.chip5f);
    }

    public Chip5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(5, R.drawable.chip5, R.drawable.chip5f);
    }
}
