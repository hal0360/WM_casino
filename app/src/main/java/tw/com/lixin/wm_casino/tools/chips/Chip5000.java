package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip5000 extends ChipView{
    public Chip5000(Context context) {
        super(context);
        init(5000, R.drawable.chip5000, R.drawable.chip5000f);
    }

    public Chip5000(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(5000, R.drawable.chip5000, R.drawable.chip5000f);
    }
}
