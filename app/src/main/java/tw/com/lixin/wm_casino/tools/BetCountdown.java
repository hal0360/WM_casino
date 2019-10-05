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
        setBackgroundColor(Color.parseColor("#000000"));
        setBackgroundResource(R.drawable.countdown_border);
        countdown = findViewById(R.id.count_txt);
        dealImg = findViewById(R.id.dealer_img);

    }
}
