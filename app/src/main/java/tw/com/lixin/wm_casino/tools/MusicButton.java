package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class MusicButton extends ConstraintLayout implements View.OnClickListener {

    private TextView musicTxt;
    private boolean turnOn;

    public MusicButton(Context context) {
        super(context);
        init(context);
    }

    public MusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.music_button, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        musicTxt = findViewById(R.id.music_txt);
        setBackgroundResource(R.drawable.music_on);
        musicTxt.setText("ON");
        turnOn = true;
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(turnOn){
            turnOn = false;
            musicTxt.setText("OFF");
        }else{
            turnOn = true;
            musicTxt.setText("ON");
        }
    }
}

