package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class MusicButton extends ConstraintLayout implements View.OnClickListener {

    private TextView musicTxt;
    private boolean turnOn;
    private MediaPlayer player;

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
        setBackgroundResource(R.drawable.music_on);
        musicTxt.setText("ON");
        turnOn = true;
        setOnClickListener(this);

//        player = MediaPlayer.create(context, R.raw.save_me);
       // player.setLooping(true);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        player.start();
        if(turnOn){
            turnOn = false;
            musicTxt.setText("OFF");
        }else{
            turnOn = true;
            musicTxt.setText("ON");
        }
    }
}

