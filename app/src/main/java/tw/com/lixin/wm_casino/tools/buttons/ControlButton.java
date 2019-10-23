package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class ControlButton extends AppCompatTextView implements View.OnClickListener{

    private CmdView cmd;
    private Boolean disabled = true;


    public ControlButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ControlButton);
        int type = a.getInt(R.styleable.ControlButton_control_type, 3);

        setBackgroundResource(R.drawable.controll_off);
        setGravity(Gravity.CENTER);
        setTextColor(0xffffffff);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
        if(type == 1){
            setText(R.string.re_bet);
        }else if(type ==2){
            setText(R.string.confirm);
        }else{
            setText(R.string.cancel);
        }
        a.recycle();
        setOnClickListener(this);
    }

    public void disable(Boolean disabled){
        this.disabled = disabled;
        if(disabled){
            setBackgroundResource(R.drawable.controll_off);
        }else{
            setBackgroundResource(R.drawable.control_on);
        }
    }

    public boolean isDisabled(){
        return disabled;
    }

    public void clicked(CmdView cd){
        cmd = cd;
    }

    @Override
    public void onClick(View v) {
        App.controlling();
        if(cmd != null && !disabled){
            cmd.exec(v);
        }
    }
}