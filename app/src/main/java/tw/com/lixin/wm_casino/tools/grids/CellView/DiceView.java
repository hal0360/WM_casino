package tw.com.lixin.wm_casino.tools.grids.CellView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.txtViews.ResultText;

public class DiceView extends LinearLayout {

    private ImageView dice1, dice2, dice3;

    public DiceView(Context context) {
        super(context);
        View.inflate(context, R.layout.dice_view, this);
        setOrientation(VERTICAL);
        setBackgroundResource(R.drawable.dice_background);
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
                dice.setImageResource(R.drawable.dice_1);
                break;
            case '2':
                dice.setImageResource(R.drawable.dice_2);
                break;
            case '3' :
                dice.setImageResource(R.drawable.dice_3);
                break;
            case '4' :
                dice.setImageResource(R.drawable.dice_4);
                break;
            case '5' :
                dice.setImageResource(R.drawable.dice_5);
                break;
            case '6' :
                dice.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public static void swiRoad(ResultText dice, char digi){
        switch(digi) {
            case '1' :
                dice.setBackgroundResource(R.drawable.dice_1);
                break;
            case '2':
                dice.setBackgroundResource(R.drawable.dice_2);
                break;
            case '3' :
                dice.setBackgroundResource(R.drawable.dice_3);
                break;
            case '4' :
                dice.setBackgroundResource(R.drawable.dice_4);
                break;
            case '5' :
                dice.setBackgroundResource(R.drawable.dice_5);
                break;
            case '6' :
                dice.setBackgroundResource(R.drawable.dice_6);
                break;
        }
    }

}
