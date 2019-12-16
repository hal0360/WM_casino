package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;

public class RatePanel extends ConstraintLayout{

    private LimitPopup popup;
    private TextView gyuShu;

    public RatePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.rate_panel, this);
        setBackgroundColor(0x80000000);

        TextView member = findViewById(R.id.member);
        gyuShu = findViewById(R.id.gyu_shu);

        popup = new LimitPopup();

        ClickText limitBtn = findViewById(R.id.limit_btn);
        limitBtn.clicked(v->{
            RootActivity activity = (RootActivity) getContext();
            activity.showPopup(popup);
        });

        post(()-> member.setText(User.userName()));
    }


    @SuppressLint("SetTextI18n")
    public void setGyuShu(int num, int round){
        gyuShu.setText(num + "/" + round);
    }
}
