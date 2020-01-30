package tw.com.lixin.wm_casino.tools.grids.CellView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class WordView extends FrameLayout {

    private TextView roadTxt;

    public WordView(Context context) {
        super(context);

        roadTxt = new TextView(context);
        roadTxt.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        roadTxt.setGravity(Gravity.CENTER);
        addView(roadTxt);
    }

    public void clear(){
        roadTxt.setText("");
        roadTxt.setBackgroundResource(0);
    }

    public void setTextColor(int color){
        roadTxt.setTextColor(color);
    }

    public void setTextImg(int rid){
        roadTxt.setBackgroundResource(rid);
    }

    public void setTextSize(float dps){
        roadTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dps);
    }

    public void setText(String txt){
        roadTxt.setText(txt);
    }

    @SuppressLint("SetTextI18n")
    public void setText(int txt){
        roadTxt.setText(txt+"");
    }

    @SuppressLint("SetTextI18n")
    public void setText(float txt){
        roadTxt.setText(txt+"");
    }
}

