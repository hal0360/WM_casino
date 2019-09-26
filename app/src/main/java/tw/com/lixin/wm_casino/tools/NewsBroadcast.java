package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.R;

public class NewsBroadcast extends ConstraintLayout implements View.OnClickListener{

    private Context context;

    public NewsBroadcast(Context context) {
        super(context);
        View.inflate(context, R.layout.news_broadcast, this);
        init(context);
    }

    public NewsBroadcast(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.news_broadcast, this);
        init(context);
    }

    private void init( Context con){
        context = con;
        setBackgroundResource(R.drawable.grey_lobby_area);
    }

    @Override
    public void onClick(View v) {

    }
}