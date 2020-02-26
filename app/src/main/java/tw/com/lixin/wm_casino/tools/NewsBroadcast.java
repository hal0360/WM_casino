package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class NewsBroadcast extends ConstraintLayout {


    public NewsBroadcast(Context context) {
        super(context);
        View.inflate(context, R.layout.news_broadcast, this);

        TextView mar = findViewById(R.id.mar_txt);

        mar.setSelected(true);
    }

    public NewsBroadcast(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.news_broadcast, this);

        TextView mar = findViewById(R.id.mar_txt);

        mar.setSelected(true);

    }


}