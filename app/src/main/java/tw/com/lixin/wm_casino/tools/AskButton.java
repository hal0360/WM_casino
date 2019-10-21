package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.BacTable;

public class AskButton extends ConstraintLayout {

    private Context context;
    private ImageView secSym, thirdSym, fourthSym;

    public AskButton(Context context) {
        super(context);
        init(context, null);
    }

    public AskButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        View.inflate(context, R.layout.ask_button, this);
        int orientation = getResources().getConfiguration().orientation;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.AskButton);
        int type = a.getInt(R.styleable.AskButton_ask_type, 1);
        TextView title = findViewById(R.id.title);
        if(type == 1){
            title.setText(context.getString(R.string.next_p));
            if(orientation == Configuration.ORIENTATION_LANDSCAPE) setBackgroundResource(R.drawable.blue_next);
        }else {
            title.setText(context.getString(R.string.next_b));
            if(orientation == Configuration.ORIENTATION_LANDSCAPE) setBackgroundResource(R.drawable.red_next);
        }
        a.recycle();
        thirdSym = findViewById(R.id.third_sym);
        secSym = findViewById(R.id.second_sym);
        fourthSym = findViewById(R.id.fourth_sym);
    }

    public void ask(BacTable table, int win){
        table.askRoadThird(win);
        table.askRoadSec(win);
        table.askRoadFirst(win);
        table.askRoadFourth(win);
        secSym.setImageResource(table.secGrid.resX);
        thirdSym.setImageResource(table.thirdGrid.resX);
        fourthSym.setImageResource(table.fourthGrid.resX);
    }
}
