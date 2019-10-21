package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class MusicButton extends ConstraintLayout implements View.OnClickListener {

    private TextView musicTxt;

    public MusicButton(Context context) {
        super(context);
        init(context);
    }

    public MusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("SetTextI18n")
    private void init(Context context) {
        View.inflate(context, R.layout.music_button, this);
       // setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        musicTxt = findViewById(R.id.music_txt);

        if(App.musicOn){
            setBackgroundResource(R.drawable.music_on);
            musicTxt.setText("ON");
        }else {
            setBackgroundResource(R.drawable.music_off);
            musicTxt.setText("OFF");
        }

        setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if(App.musicOn){
            App.music_off();
            musicTxt.setText("OFF");
            setBackgroundResource(R.drawable.music_off);
        }else{
            App.music_on();
            musicTxt.setText("ON");
            setBackgroundResource(R.drawable.music_on);
        }
    }
}

