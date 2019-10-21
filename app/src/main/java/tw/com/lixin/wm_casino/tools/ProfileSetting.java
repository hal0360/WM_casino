package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;

public class ProfileSetting extends ConstraintLayout implements View.OnClickListener{

    private Context context;
    private TextView balance, name;
    private View settingBtn;

    public ProfileSetting(Context context) {
        super(context);
        View.inflate(context, R.layout.profile_setting, this);
        init(context);
    }

    public ProfileSetting(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.profile_setting, this);
        init(context);
    }

    private void init( Context con){
        context = con;
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
