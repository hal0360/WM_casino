package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.R;

public class TableSwitchButton extends ConstraintLayout {

    public TableSwitchButton(Context context) {
        super(context);
        View.inflate(context, R.layout.table_switch_button, this);
    }

    public TableSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.table_switch_button, this);


    }
}
