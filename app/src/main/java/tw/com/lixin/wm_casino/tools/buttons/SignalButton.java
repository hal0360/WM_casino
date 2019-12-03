package tw.com.lixin.wm_casino.tools.buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.popups.SignalPopup;
import tw.com.lixin.wm_casino.popups.TableSwitchPopup;

public class SignalButton extends ClickConstraint {

    private SignalPopup popup;

    @SuppressLint("SetTextI18n")
    public SignalButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_button, this);
        ImageView btnImg = findViewById(R.id.btn_img);
        btnImg.setImageResource(R.drawable.signal);
        TextView numberTxt = findViewById(R.id.num_txt);
        numberTxt.setVisibility(VISIBLE);
        numberTxt.setBackgroundResource(R.drawable.green_ball);
        numberTxt.setText(1+"");
        btnImg.setImageResource(R.drawable.signal);

        post(()-> popup = new SignalPopup());

        clicked(v->{
            RootActivity activity = (RootActivity) getContext();
            activity.showPopup(popup);
        });

    }

}

