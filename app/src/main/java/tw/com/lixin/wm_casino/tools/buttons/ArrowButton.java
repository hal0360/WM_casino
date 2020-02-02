package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class ArrowButton extends AppCompatImageView implements View.OnClickListener{

    private CmdView cmd;

    public ArrowButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ArrowButton);
        int direction = a.getInt(R.styleable.ArrowButton_direction, 1);
        if(direction == 1){
            setImageResource(R.drawable.arrow_left);
        } else {
            setImageResource(R.drawable.arrow_right);
        }
        a.recycle();
        setOnClickListener(this);
    }

    public void clicked(CmdView cd){
        cmd = cd;
    }

    @Override
    public void onClick(View v) {
        App.clicking();
        if(cmd != null){
            cmd.exec(v);
        }
        bringToFront();
    }
}
