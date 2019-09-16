package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip100 extends ChipView{
    public Chip100(Context context) {
        super(context);
        init(100, R.drawable.chip100, R.drawable.chip100f);
    }

    public Chip100(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(100, R.drawable.chip100, R.drawable.chip100f);
    }
}