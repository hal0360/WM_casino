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
import tw.com.lixin.wm_casino.popups.ProfilePopup;

public class ProfileBar extends ConstraintLayout implements View.OnClickListener{

    private TextView balance, title;

    public ProfileBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.profile_bar, this);
        balance = findViewById(R.id.balance_txt);
        title = findViewById(R.id.title);
        ImageView settingBtn = findViewById(R.id.setting_btn);
        ImageView balanceImg = findViewById(R.id.balance_img);
        setBackgroundColor(0xff0d0d0d);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ProfileBar);
        title.setText(a.getString(R.styleable.ProfileBar_title));
        boolean showBal = a.getBoolean(R.styleable.ProfileBar_show_balance, false);
        if(showBal) {
            balance.setVisibility(VISIBLE);
            balanceImg.setVisibility(VISIBLE);
        }
        a.recycle();
        post(this::updateBalance);
        settingBtn.setOnClickListener(this);
    }

    public void setTitle(String txt){
        title.setText(txt);
    }

    public String getTitle(){
        return title.getText().toString().trim();
    }

    @SuppressLint("SetTextI18n")
    public void setBalance(float bal) {
        balance.setText(bal+"");
    }

    @SuppressLint("SetTextI18n")
    public void updateBalance() {
        balance.setText(User.balance()+"");
    }


    @Override
    public void onClick(View v) {
        App.clicking();
        RootActivity activity = (RootActivity) getContext();
        ProfilePopup popup = new ProfilePopup();
        activity.showPopup(popup);
    }
}
