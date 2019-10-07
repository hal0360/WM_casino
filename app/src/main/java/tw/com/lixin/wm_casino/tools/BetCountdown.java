package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class BetCountdown extends ConstraintLayout {

    private TextView countdown;
    private ImageView dealImg;

    public BetCountdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.bet_countdown, this);

        setBackgroundResource(R.drawable.countdown_border);
        countdown = findViewById(R.id.count_txt);
        dealImg = findViewById(R.id.dealer_img);

    }

    public void statusCheck(int status){
        if(status == 1){
            dealImg.setVisibility(INVISIBLE);
        }else {
            dealImg.setVisibility(VISIBLE);
        }
    }

    public void setSecond(int sec){
        countdown.setText(sec+"");
    }
}
