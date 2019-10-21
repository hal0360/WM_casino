package tw.com.lixin.wm_casino.tools.gameComponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;

public class ProfileBar extends ConstraintLayout implements View.OnClickListener{

    private TextView balance, title;
    private ImageView balanceImg, settingBtn, backBtn;

    public ProfileBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.profile_bar, this);
        balance = findViewById(R.id.balance_txt);
        title = findViewById(R.id.title);
        settingBtn = findViewById(R.id.setting_btn);
        backBtn = findViewById(R.id.back_btn);
        balanceImg = findViewById(R.id.balance_img);
        setBackgroundColor(0xff191919);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ProfileBar);
        title.setText(a.getString(R.styleable.ProfileBar_title));
        boolean showBal = a.getBoolean(R.styleable.ProfileBar_show_balance, false);
        if(showBal) {
            balance.setVisibility(VISIBLE);
            balanceImg.setVisibility(VISIBLE);
        }
        a.recycle();

        //   setMember(User.account());
        //  setBalance(User.balance());
        backBtn.setOnClickListener(v->{
           RootActivity activity = (RootActivity) getContext();
            activity.finish();
        });
    }

    public void setTitile(String txt){
        title.setText(txt);
    }

    @SuppressLint("SetTextI18n")
    public void setBalance(float bal) {
        balance.setText(bal+"");
    }

    @Override
    public void onClick(View v) {

    }
}
