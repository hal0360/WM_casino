package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.R;

public class SignalButton extends ConstraintLayout {

    private Context context;

    public SignalButton(Context context) {
        super(context);
        init(context);
    }

    public SignalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        View.inflate(context, R.layout.signal_button, this);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setBackgroundResource(R.drawable.signal4);
        }
    }
}
