package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;

public class AskButton extends ConstraintLayout implements View.OnTouchListener{

    private ImageView secSym, thirdSym, fourthSym;
    private CmdView cmdDown, cmdUp;

    public AskButton(Context context) {
        super(context);
        init(context, null);
    }

    public AskButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View.inflate(context, R.layout.ask_button, this);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.AskButton);
        int type = a.getInt(R.styleable.AskButton_ask_type, 1);
        TextView title = findViewById(R.id.title);
        if(type == 1){
            title.setText(context.getString(R.string.next_p));
        }else if(type == 2){
            title.setText(context.getString(R.string.next_b));
        }
        else if(type == 3){
            title.setText(context.getString(R.string.next_t));
        }
        else {
            title.setText(context.getString(R.string.next_d));
        }
        setBackgroundColor(0xffffffff);
        a.recycle();
        thirdSym = findViewById(R.id.third_sym);
        secSym = findViewById(R.id.second_sym);
        fourthSym = findViewById(R.id.fourth_sym);
        setOnTouchListener(this);

      //  setClickable(false);
       // setFocusable(false);
    }

    public void clickDown(CmdView cd){
        cmdDown = cd;
    }

    public void clickUp(CmdView cd){
        cmdUp = cd;
    }

    public void setAsk(int sec, int thir, int four){
        if(sec == 1) secSym.setImageResource(Road.Bank);
        else if(sec == 2) secSym.setImageResource(Road.Play);
        if(thir == 1) thirdSym.setImageResource(Road.Bank_S);
        else if(thir == 2) thirdSym.setImageResource(Road.Play_S);
        if(four == 1) fourthSym.setImageResource(Road.Bank_I);
        else if(four == 2) fourthSym.setImageResource(Road.Play_I);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(cmdDown != null){
                    cmdDown.exec(v);
                }
                return true; // if you want to handle the touch event
            case MotionEvent.ACTION_UP:
                if(cmdUp != null){
                    cmdUp.exec(v);
                }
                return true; // if you want to handle the touch event
        }
        return false;
    }
}
