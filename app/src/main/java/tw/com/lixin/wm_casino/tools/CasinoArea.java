package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.popups.ProfilePopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;

public class CasinoArea extends ConstraintLayout implements GameBridge {

    private ProfileBar bar;
    private LimitPopup popup;
    private TextView gyuShu;

    public CasinoArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.casino_area, this);

        bar = findViewById(R.id.profile);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CasinoArea);


        a.recycle();


        TextView member = findViewById(R.id.member);
        gyuShu = findViewById(R.id.gyu_shu);
        ClickText limitBtn = findViewById(R.id.limit_btn);
        limitBtn.clicked(v->{
            popup = new LimitPopup();
            RootActivity activity = (RootActivity) getContext();
            activity.showPopup(popup);
        });

        post(()-> member.setText(User.userName()));

        post(()->{
          bar.updateBalance();
        });
    }

    public void setTitle(String txt){
        bar.setTitile(txt);
    }


    @Override
    public void betUpdate(boolean betOK) {

    }
}
