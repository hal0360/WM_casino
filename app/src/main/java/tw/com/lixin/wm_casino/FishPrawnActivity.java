
package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.view.View;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.CellView.DiceView;
import tw.com.lixin.wm_casino.tools.grids.CrabGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import tw.com.lixin.wm_casino.tools.txtViews.ResultText;

public class FishPrawnActivity extends CasinoActivity {

    private TextGrid firstGrid, secondGrid;
    private CrabGrid mainGrid;
    private ResultText diceR1, diceR2, diceR3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fish_prawn);
        super.onCreate(savedInstanceState);

        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        mainGrid = findViewById(R.id.main_grid);
        diceR1 = findViewById(R.id.dice_r_1);
        diceR2 = findViewById(R.id.dice_r_2);
        diceR3 = findViewById(R.id.dice_r_3);

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        addPage(R.id.page_4);
        addPage(R.id.page_5);
        setPageArrow();
        if(isPortrait()){
            addGrid(mainGrid);
            addGrid(firstGrid);
            addGrid(secondGrid);
            setGridArrow();
        }
        casinoArea.setVideo("fish" + source.table.groupID);
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void resultUpdate() {
        String number = String.valueOf(source.result);
        char[] digiChar = number.toCharArray();
        DiceView.swiRoad(diceR1,digiChar[0]);
        DiceView.swiRoad(diceR2,digiChar[1]);
        DiceView.swiRoad(diceR3,digiChar[2]);
        int sumTol = (digiChar[0] - '0') + (digiChar[1] - '0') + (digiChar[2] - '0');

        if(sumTol % 2 == 0) setResultText(R.id.oddeven_txt, getString(R.string.EVEN));
        else setResultText(R.id.oddeven_txt, getString(R.string.ODD));

        if(sumTol < 11) setResultText(R.id.size_txt, getString(R.string.SMALL));
        else setResultText(R.id.size_txt, getString(R.string.BIG));

        setResultText(R.id.sum_txt, sumTol+"");
        diceR1.setVisibility(View.VISIBLE);
        diceR2.setVisibility(View.VISIBLE);
        diceR3.setVisibility(View.VISIBLE);
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
