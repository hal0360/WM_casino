package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class ProfileSetting extends ConstraintLayout implements View.OnClickListener{

    private Context context;
    private TextView balance;
    private ImageView settingBtn;

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
    }

    @Override
    public void onClick(View v) {

    }
}
