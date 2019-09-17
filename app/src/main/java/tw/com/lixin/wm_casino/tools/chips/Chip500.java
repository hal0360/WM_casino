package tw.com.lixin.wm_casino.tools.chips;

import android.content.Context;
import android.util.AttributeSet;

import tw.com.lixin.wm_casino.R;

public class Chip500 extends ChipView{
    public Chip500(Context context) {
        super(context);
        init(500, R.drawable.chip500, R.drawable.chip500f);
    }

    public Chip500(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(500, R.drawable.chip500, R.drawable.chip500f);
    }
}
