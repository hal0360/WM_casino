package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import static android.view.View.INVISIBLE;

public class RouletteActivity extends CasinoActivity {

    private TextGrid firstGrid, secondGrid, thirdGrid, fourthGrid;
    private TextView resultTxt, oddEvenTxt;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_roulette);
        super.onCreate(savedInstanceState);

        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        resultTxt = findViewById(R.id.result_txt);
        oddEvenTxt = findViewById(R.id.odd_even_txt);

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        setPageArrow(R.id.arrow_left, R.id.arrow_right);
        if(isPortrait()){
            addGrid(firstGrid);
            addGrid(secondGrid);
            addGrid(thirdGrid);
            setGridArrow(R.id.arrow_left_grid, R.id.arrow_right_grid);
        }
        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/rou" + String.format("%02d", source.table.groupID) + "/stream1");

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
        if ( (source.result & 1) == 0 ) oddEvenTxt.setText(getString(R.string.EVEN));
        else oddEvenTxt.setText(getString(R.string.ODD));
    }

    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(source.table.mainRoad,(v,r)->{
            v.setTextSize(8);
            v.setTextColor(0xffffffff);
            if( (r > 0 && r < 11) || (r > 18 && r < 29) ){
                v.setText(r);
                if ( (r & 1) == 0 ) v.setTextImg(R.drawable.black_ball);
                else v.setTextImg(R.drawable.dark_red_ball);
            } else if( (r > 10 && r < 19) || (r > 28 && r < 37) ){
                v.setText(r);
                if ( (r & 1) == 0 ) v.setTextImg(R.drawable.dark_red_ball);
                else v.setTextImg(R.drawable.black_ball);
            }else if(r == Road.ZERO){
                v.setText("0");
                v.setTextImg(R.drawable.green_ball);
            }
        });
        secondGrid.drawRoad(source.table.secondRoad, (v,r)->{
            v.setTextSize(8);
            if(r == 1){
                v.setText(getString(R.string.red));
                v.setTextColor(0xffb22222);
            }else if(r == 2){
                v.setText(getString(R.string.black));
                v.setTextColor(0xff000000);
            }else if(r == 3){
                v.setText(getString(R.string.green));
                v.setTextColor(0xff228b22);
            }
        });
        thirdGrid.drawRoad(source.table.thirdRoad, (v,r)->{
            v.setTextSize(8);
            if(r == 1){
                v.setText(getString(R.string.big));
                v.setTextColor(0xffb22222);
            }else if(r == 2){
                v.setText(getString(R.string.small));
                v.setTextColor(0xff000000);
            }else if(r == 3){
                v.setText(getString(R.string.oh));
                v.setTextColor(0xff228b22);
            }
        });
        fourthGrid.drawRoad(source.table.fourthRoad, (v,r)->{
            v.setTextSize(8);
            if(r == 1){
                v.setText(getString(R.string.odd));
                v.setTextColor(0xffb22222);
            }else if(r == 2){
                v.setText(getString(R.string.even));
                v.setTextColor(0xff000000);
            }else if(r == 3){
                v.setText(getString(R.string.oh));
                v.setTextColor(0xff228b22);
            }
        });
    }
}
