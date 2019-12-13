package tw.com.lixin.wm_casino.tools.grids.CellView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.R;

public class BacRoadView extends ConstraintLayout{

    private TextView roadTxt;
    private View redDot, blueDot;

    public BacRoadView(Context context) {
        super(context);
        View.inflate(context, R.layout.bac_road, this);

        roadTxt = findViewById(R.id.road_txt);
        redDot = findViewById(R.id.red_dot);
        blueDot = findViewById(R.id.blue_dot);
    }

    public void clear(){
        redDot.setVisibility(INVISIBLE);
        blueDot.setVisibility(INVISIBLE);
        roadTxt.setText("");
        setBackgroundResource(0);
    }

    public void setRoad(int ref){
        switch(ref) {
            case 1 :
                setBackgroundResource(R.drawable.red_road);
                roadTxt.setText(getContext().getString(R.string.banker_road));
                break;
            case 2 :
                setBackgroundResource(R.drawable.red_road);
                roadTxt.setText(getContext().getString(R.string.banker_road));
                redDot.setVisibility(VISIBLE);
                break;
            case 3 :
                setBackgroundResource(R.drawable.red_road);
                roadTxt.setText(getContext().getString(R.string.banker_road));
                blueDot.setVisibility(VISIBLE);
                break;
            case 4 :
                setBackgroundResource(R.drawable.red_road);
                roadTxt.setText(getContext().getString(R.string.banker_road));
                blueDot.setVisibility(VISIBLE);
                redDot.setVisibility(VISIBLE);
                break;
            case 5 :
                setBackgroundResource(R.drawable.blue_road);
                roadTxt.setText(getContext().getString(R.string.player_road));
                break;
            case 6 :
                setBackgroundResource(R.drawable.blue_road);
                roadTxt.setText(getContext().getString(R.string.player_road));
                redDot.setVisibility(VISIBLE);
                break;
            case 7 :
                setBackgroundResource(R.drawable.blue_road);
                roadTxt.setText(getContext().getString(R.string.player_road));
                blueDot.setVisibility(VISIBLE);
                break;
            case 8 :
                setBackgroundResource(R.drawable.blue_road);
                roadTxt.setText(getContext().getString(R.string.player_road));
                blueDot.setVisibility(VISIBLE);
                redDot.setVisibility(VISIBLE);
                break;
            case 9 :
                setBackgroundResource(R.drawable.green_road);
                roadTxt.setText(getContext().getString(R.string.tie_road));
                break;
            case 10 :
                setBackgroundResource(R.drawable.green_road);
                roadTxt.setText(getContext().getString(R.string.tie_road));
                redDot.setVisibility(VISIBLE);
                break;
            case 11 :
                setBackgroundResource(R.drawable.green_road);
                roadTxt.setText(getContext().getString(R.string.tie_road));
                blueDot.setVisibility(VISIBLE);
                break;
            case 12 :
                setBackgroundResource(R.drawable.green_road);
                roadTxt.setText(getContext().getString(R.string.tie_road));
                blueDot.setVisibility(VISIBLE);
                redDot.setVisibility(VISIBLE);
                break;
        }
    }


}
