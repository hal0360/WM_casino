package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class ControlButton extends ConstraintLayout {
    TextView title;
    public ControlButton(Context context) {
        super(context);
        View.inflate(context, R.layout.control_button, this);
    }

    public ControlButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.control_button, this);
        title = findViewById(R.id.title);
        int orientation = getResources().getConfiguration().orientation;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ControlButton);
        int type = a.getInt(R.styleable.ControlButton_control_type, 3);

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setBackgroundResource(R.drawable.table_switch_border);
            ImageView immg = findViewById(R.id.con_img);
            if(type == 1){
                title.setText(R.string.re_bet);
                immg.setImageResource(R.drawable.repeat);
            }else if(type ==2){
                title.setText(R.string.confirm);
                immg.setImageResource(R.drawable.confirm);
            }else{
                title.setText(R.string.cancel);
                immg.setImageResource(R.drawable.cancel);
            }
        }else {
            setBackgroundResource(R.drawable.bet_button_border);
            if(type == 1){
                title.setText(R.string.re_bet);
            }else if(type ==2){
                title.setText(R.string.confirm);
            }else{
                title.setText(R.string.cancel);
            }
        }
        a.recycle();
    }
}