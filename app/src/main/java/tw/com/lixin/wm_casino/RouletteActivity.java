package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

public class RouletteActivity extends CasinoActivity {

    private TextGrid firstGrid, secondGrid, thirdGrid, fourthGrid;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_roulette);
        super.onCreate(savedInstanceState);

        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);

        /*
        setStackAreaMax(R.id.stack1_1,8190, 50);
        setStackAreaMax(R.id.stack2_1,33546240, 50);
        setStackAreaMax(R.id.stack3_1,137405399040L, 50);
        setStackAreaMax(R.id.stack1_2,524286, 100);
        setStackAreaMax(R.id.stack2_2,45991767380L, 100);
        setStackAreaMax(R.id.stack3_2,137438429184L, 100);
        setStackAreaMax(R.id.stack1_3,91625968980L, 100);
        setStackAreaMax(R.id.stack2_3,91447186090L, 100);
        setStackAreaMax(R.id.stack3_3,45812984490L, 100);
        setStackAreaMax(R.id.stack_row1,78536544840L, 50);
        setStackAreaMax(R.id.stack_row2,39268272420L, 50);
        setStackAreaMax(R.id.stack_row3,19634136210L, 50);
         */

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        setPageArrow();
        if(isPortrait()){
            addGrid(firstGrid);
            addGrid(secondGrid);
            addGrid(thirdGrid);
            setGridArrow();
        }
        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/rou" + source.table.groupID + "/720p");

    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.red_black_even), "1:1", 100,1 );
        limitPopup.addLimit(getString(R.string.column_dozen), "1:2", 50,1 );
        limitPopup.addLimit(getString(R.string.line), "1:5", 40,1 );
        limitPopup.addLimit(getString(R.string.triangle), "1:8", 20,1 );
        limitPopup.addLimit(getString(R.string.street), "1:11", 10,1 );
        limitPopup.addLimit(getString(R.string.separate), "1:17", 10,1 );
        limitPopup.addLimit(getString(R.string.direct), "1:35", 3,1 );
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        setResultText(R.id.result_txt,source.result+"");
        if ( (source.result & 1) == 0 ) setResultText(R.id.odd_even_txt,getString(R.string.EVEN));
        else setResultText(R.id.odd_even_txt,getString(R.string.ODD));
        if(source.result >18 ) setResultText(R.id.size_txt,getString(R.string.BIG) + "19-36");
        else setResultText(R.id.size_txt,getString(R.string.SMALL) + "1-18");
        if(source.result <13 ) setResultText(R.id.dozen_txt,getString(R.string.dozen1));
        else if(source.result >24) setResultText(R.id.size_txt,getString(R.string.dozen3));
        else setResultText(R.id.size_txt,getString(R.string.dozen2));
        if(source.result % 3 == 0) setResultText(R.id.column_txt,getString(R.string.column1));
        else if(source.result % 3 == 2) setResultText(R.id.column_txt,getString(R.string.column2));
        else setResultText(R.id.column_txt,getString(R.string.column3));
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
