package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import tw.com.lixin.wm_casino.R;

public class BetCountdown extends ConstraintLayout {

    private TextView countdown;
    private ImageView dealImg;
    private Timer timer = new Timer();
    private Handler handler;
    private int curTime;

    public BetCountdown(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.bet_countdown, this);

        setBackgroundResource(R.drawable.countdown_border);
        countdown = findViewById(R.id.count_txt);
        dealImg = findViewById(R.id.dealer_img);
        handler = new Handler();
    }

    public void startCountDown(int sec){
        curTime = sec;
        countdown.setText(""+curTime);
        reCur();
    }

    private void reCur(){
        handler.postDelayed(() -> {
            if(curTime > 0){
                curTime--;
                countdown.setText(""+curTime);
                reCur();
            }
        }, 1000);

    }

    public void stopCountdown(){
        curTime = 0;
    }

    public void dealing(){
        dealImg.setVisibility(VISIBLE);
    }

    public void betting(){
        dealImg.setVisibility(INVISIBLE);
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
