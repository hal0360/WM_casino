package tw.com.lixin.wm_casino.tools.buttons;

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
import tw.com.lixin.wm_casino.models.TigerDragonTable;

public class AskButton extends ConstraintLayout {

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
        View.inflate(context, R.layout.ask_button, this);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.AskButton);
        int type = a.getInt(R.styleable.AskButton_ask_type, 1);
        TextView title = findViewById(R.id.title);
        if(type == 1){
            title.setText(context.getString(R.string.next_p));
        }else if(type == 2){
            title.setText(context.getString(R.string.next_b));
        }
        else if(type == 3){
            title.setText(context.getString(R.string.next_t));
        }
        else {
            title.setText(context.getString(R.string.next_d));
        }
        setBackgroundColor(0xffffffff);
        a.recycle();
        thirdSym = findViewById(R.id.third_sym);
        secSym = findViewById(R.id.second_sym);
        fourthSym = findViewById(R.id.fourth_sym);
    }

    public void askBac(BacTable table, int win){
        table.askRoadThird(win);
        table.askRoadSec(win);
        table.askRoadFirst(win);
        table.askRoadFourth(win);
        secSym.setImageResource(table.secGrid.resX);
        thirdSym.setImageResource(table.thirdGrid.resX);
        fourthSym.setImageResource(table.fourthGrid.resX);
    }

    public void askTigerDragon(TigerDragonTable table, int win){
        table.askRoadThird(win);
        table.askRoadSec(win);
        table.askRoadFirst(win);
        table.askRoadFourth(win);
        secSym.setImageResource(table.secGrid.resX);
        thirdSym.setImageResource(table.thirdGrid.resX);
        fourthSym.setImageResource(table.fourthGrid.resX);
    }
}
