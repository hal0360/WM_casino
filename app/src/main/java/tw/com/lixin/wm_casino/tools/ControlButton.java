package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.R;

public class ControlButton extends ConstraintLayout implements View.OnClickListener{

    private TextView title;
    private CmdView cmd;
    private Boolean disabled = true;

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

        this.setAlpha(0.5f);

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

    public void disable(Boolean disabled){
        this.disabled = disabled;
        if(disabled){
            this.setAlpha(0.5f);
        }else{
            this.setAlpha(1.0f);
        }
    }

    public boolean isDisabled(){
        return disabled;
    }

    public void clicked(CmdView cd){
        setOnClickListener(this);
        cmd = cd;
    }

    @Override
    public void onClick(View v) {
        if(cmd != null && !disabled){
            cmd.exec(v);
        }
    }
}