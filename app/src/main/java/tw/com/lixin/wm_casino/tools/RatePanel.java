package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.lixin.wm_casino.R;

public class RatePanel extends ConstraintLayout{

    private Context context;

    public RatePanel(Context context) {
        super(context);
        init(context);
    }

    public RatePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        View.inflate(context, R.layout.rate_panel, this);
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            findViewById(R.id.collapse_btn).setOnClickListener(v->{
                findViewById(R.id.expanded_panel).setVisibility(GONE);
                findViewById(R.id.expanded_buttons).setVisibility(GONE);
                findViewById(R.id.collapsed_panel).setVisibility(VISIBLE);
                findViewById(R.id.expand_btn).setVisibility(VISIBLE);
            });
            findViewById(R.id.expand_btn).setOnClickListener(v->{
                findViewById(R.id.expanded_panel).setVisibility(VISIBLE);
                findViewById(R.id.expanded_buttons).setVisibility(VISIBLE);
                findViewById(R.id.collapsed_panel).setVisibility(GONE);
                findViewById(R.id.expand_btn).setVisibility(GONE);
            });
            findViewById(R.id.expanded_panel).setVisibility(VISIBLE);
            findViewById(R.id.expanded_buttons).setVisibility(VISIBLE);
            findViewById(R.id.collapsed_panel).setVisibility(GONE);
            findViewById(R.id.expand_btn).setVisibility(GONE);
        }else{
            setBackgroundColor(Color.parseColor("#0A2A1C"));
        }
    }

}
