package tw.com.lixin.wm_casino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.grids.CellView.DiceView;
import tw.com.lixin.wm_casino.tools.grids.DiceGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SicBoActivity extends CasinoActivity {

    private ConstraintLayout page1, page2, page3, page4, page5;
    private ClickImage leftArrow, rightArrow, leftGridArrow, rightGridArrow;

    private TextView sumTxt, oddEvenTxt, sizeTxt;
    private ImageView diceR1, diceR2, diceR3;
    private DiceGrid mainGrid;
    private TextGrid firstGrid, secondGrid;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sic_bo);
        super.onCreate(savedInstanceState);

        sumTxt = findViewById(R.id.sum_txt);
        oddEvenTxt = findViewById(R.id.oddeven_txt);
        sizeTxt = findViewById(R.id.size_txt);
        diceR1 = findViewById(R.id.dice_r_1);
        diceR2 = findViewById(R.id.dice_r_2);
        diceR3 = findViewById(R.id.dice_r_3);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);

        setPageArrow(R.id.arrow_left,R.id.arrow_right);
        if(isPortrait()) setPageArrow(R.id.arrow_left_grid,R.id.arrow_right_grid);
        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/sb" +  String.format("%02d", source.table.groupID) + "/stream1");

    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void betStarted() {
        sumTxt.setVisibility(INVISIBLE);
        sizeTxt.setVisibility(INVISIBLE);
        diceR1.setVisibility(INVISIBLE);
        diceR2.setVisibility(INVISIBLE);
        diceR3.setVisibility(INVISIBLE);
        oddEvenTxt.setVisibility(INVISIBLE);
    }

    @Override
    public void resultUpdate() {
        String number = String.valueOf(source.result);
        char[] digiChar = number.toCharArray();
        DiceView.swiRoad(diceR1,digiChar[0]);
        DiceView.swiRoad(diceR2,digiChar[1]);
        DiceView.swiRoad(diceR3,digiChar[2]);
        int sumTol = (digiChar[0] - '0') + (digiChar[1] - '0') + (digiChar[2] - '0');
        if(sumTol % 2 == 0) oddEvenTxt.setText(R.string.EVEN);
        else oddEvenTxt.setText(R.string.ODD);
        if(sumTol < 11) sizeTxt.setText(R.string.SMALL);
        else sizeTxt.setText(R.string.BIG);
        sumTxt.setVisibility(VISIBLE);
        sizeTxt.setVisibility(VISIBLE);
        diceR1.setVisibility(VISIBLE);
        diceR2.setVisibility(VISIBLE);
        diceR3.setVisibility(VISIBLE);
        oddEvenTxt.setVisibility(VISIBLE);
    }

    @Override
    public void gridUpdate() {
        mainGrid.drawRoad(source.table.mainArr);
        firstGrid.drawRoad(source.table.secondRoad, (v,r)->{
            v.setTextSize(8);
            if(r == 1) {
                v.setTextColor(0xffff0000);
                v.setText(getString(R.string.big));
            }
            else if(r == 2) {
                v.setTextColor(0xff0000ff);
                v.setText(getString(R.string.small));
            }
            else if(r == 3) {
                v.setTextColor(0xff32cd32);
                v.setText(getString(R.string.wei));
            }
        });
        secondGrid.drawRoad(source.table.thirdRoad,(v,r)->{
            v.setTextSize(8);
            if(r == 1) {
                v.setTextColor(0xffff0000);
                v.setText(getString(R.string.odd));
            }
            else if(r == 2) {
                v.setTextColor(0xff0000ff);
                v.setText(getString(R.string.even));
            }
            else if(r == 3) {
                v.setTextColor(0xff32cd32);
                v.setText(getString(R.string.wei));
            }
        });
    }
}
