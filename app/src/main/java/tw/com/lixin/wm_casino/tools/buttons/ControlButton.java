package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class ControlButton extends AppCompatTextView implements View.OnClickListener{

    private CmdView cmd;
    private Boolean disabled = true;

    public ControlButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(R.drawable.controll_off);
        setGravity(Gravity.CENTER);
        setTextColor(0xffffffff);

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