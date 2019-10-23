package tw.com.lixin.wm_casino.tools.gameComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.R;

public class RatePanel extends ConstraintLayout{


    public RatePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.rate_panel, this);
        setBackgroundColor(0x80000000);

    }
}
