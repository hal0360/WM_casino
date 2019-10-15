package tw.com.lixin.wm_casino.tools.gameComponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;

public class ProfileBar extends ConstraintLayout implements View.OnClickListener{

    private RootActivity activity;
    private TextView balance, name;
    private View settingBtn;

    public ProfileBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.profile_bar, this);
       // activity = con;
        balance = findViewById(R.id.balance_txt);
        settingBtn = findViewById(R.id.setting_btn);

        //   setMember(User.account());
        //  setBalance(User.balance());
    }


    public void setAll(){
        setMember(User.account());
        setBalance(User.balance());
    }

    public void setMember(String user) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            name = findViewById(R.id.name_txt);
            name.setText(user);
        }
    }

    @SuppressLint("SetTextI18n")
    public void setBalance(float bal) {
        balance.setText(bal+"");
    }

    @Override
    public void onClick(View v) {

    }
}
