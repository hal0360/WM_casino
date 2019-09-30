package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.R;

public class TableSwitchButton extends ConstraintLayout {

    private Context context;

    public TableSwitchButton(Context context) {
        super(context);
        init(context);
    }

    public TableSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.table_switch_button, this);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        View.inflate(context, R.layout.table_switch_button, this);

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setBackgroundResource(R.drawable.table_switch_border);
        }else {
            setBackgroundResource(R.drawable.video_button_border);
        }
    }
}
