package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class FantanActivity extends CasinoActivity {

    private TextGrid mainGrid;
    private TextView resultTxt, oddEvenTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fantan);
        super.onCreate(savedInstanceState);

        mainGrid = findViewById(R.id.main_grid);
        resultTxt = findViewById(R.id.result_txt);
        oddEvenTxt = findViewById(R.id.odd_even_txt);

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        addPage(R.id.page_4);
        setPageArrow();
        casinoArea.setVideo("live1" + source.table.groupID);
        betStarted();
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void betStarted() {
        resultTxt.setVisibility(INVISIBLE);
        oddEvenTxt.setVisibility(INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        resultTxt.setText(source.result+"");
        if ( (source.result & 1) == 0 ) oddEvenTxt.setText(getString(R.string.EVEN) + getString(R.string.dice));
        else oddEvenTxt.setText(getString(R.string.ODD) + getString(R.string.dice));

        if(source.result == 1) resultTxt.setBackgroundResource(R.drawable.grey_ball);
        else if(source.result == 2) resultTxt.setBackgroundResource(R.drawable.darkgreen_ball);
        else if(source.result == 3) resultTxt.setBackgroundResource(R.drawable.golden_ball);
        else if(source.result == 4) resultTxt.setBackgroundResource(R.drawable.brick_red_ball);

        resultTxt.setVisibility(VISIBLE);
        oddEvenTxt.setVisibility(VISIBLE);
    }

    @Override
    public void gridUpdate() {
        mainGrid.drawRoad(source.table.firstRoad,(v,r)->{
            v.setTextSize(8);
            v.setTextColor(0xffffffff);
            v.setText(r);
            if( r == 1){
                v.setTextImg(R.drawable.grey_ball);
            } else if( r == 2){
                v.setTextImg(R.drawable.darkgreen_ball);
            }else if(r == 3){
                v.setTextImg(R.drawable.golden_ball);
            }else if(r == 4){
                v.setTextImg(R.drawable.brick_red_ball);
            }
        });
    }
}
