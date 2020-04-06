package tw.com.lixin.wm_casino.tools.grids.CellView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tw.com.lixin.wm_casino.R;

public class CrabView extends LinearLayout {

    private ImageView dice1, dice2, dice3;

    public CrabView(Context context) {
        super(context);
        View.inflate(context, R.layout.crab_view, this);
        setOrientation(VERTICAL);
        setBackgroundResource(R.drawable.crab_background);
        dice1 = findViewById(R.id.dice_1);
        dice2 = findViewById(R.id.dice_2);
        dice3 = findViewById(R.id.dice_3);
    }

    public void clear(){
        dice1.setImageResource(0);
        dice2.setImageResource(0);
        dice3.setImageResource(0);
    }

    public void setRoad(int ref){
        String number = String.valueOf(ref);
        char[] digiChar = number.toCharArray();

        // if(digiChar[0] == '1')
        swiRoad(dice1, digiChar[0]);
        swiRoad(dice2, digiChar[1]);
        swiRoad(dice3, digiChar[2]);

    }

    private void swiRoad(ImageView dice, char digi){
        switch(digi) {
            case '1' :
                dice.setImageResource(R.drawable.fish);
                break;
            case '2':
                dice.setImageResource(R.drawable.shrimp);
                break;
            case '3' :
                dice.setImageResource(R.drawable.hulu);
                break;
            case '4' :
                dice.setImageResource(R.drawable.coin);
                break;
            case '5' :
                dice.setImageResource(R.drawable.crab);
                break;
            case '6' :
                dice.setImageResource(R.drawable.chicken);
                break;
        }
    }
}
