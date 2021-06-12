package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import tw.com.lixin.wm_casino.tools.txtViews.ResultText;


public class FantanActivity extends CasinoActivity {

    private TextGrid mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fantan);
        super.onCreate(savedInstanceState);


        mainGrid = findViewById(R.id.main_grid);

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        addPage(R.id.page_4);
        setPageArrow();
        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/ft" + source.table.groupID + "/720p");


    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.ODD), "1:0.95", 100,1 );
        limitPopup.addLimit(getString(R.string.EVEN), "1:0.95", 100,1 );
        limitPopup.addLimit(getString(R.string.fan), "1:2.85", 25,1 );
        limitPopup.addLimit(getString(R.string.nim), "1:1.9", 35,1 );
        limitPopup.addLimit(getString(R.string.kwok), "1:0.95", 100,1 );
        limitPopup.addLimit(getString(R.string.nga), "1:0.475", 100,1 );
        limitPopup.addLimit(getString(R.string.ssh), "1:0.31", 100,1 );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        ResultText resultTxt = getTxt(R.id.result_txt);
        ResultText oddEvenTxt = getTxt(R.id.odd_even_txt);
        resultTxt.setText(source.result+"");
        if ( (source.result & 1) == 0 ) oddEvenTxt.setText(getString(R.string.EVEN) + getString(R.string.dice));
        else oddEvenTxt.setText(getString(R.string.ODD) + getString(R.string.dice));
        if(source.result == 1) resultTxt.setBackgroundResource(R.drawable.grey_ball);
        else if(source.result == 2) resultTxt.setBackgroundResource(R.drawable.darkgreen_ball);
        else if(source.result == 3) resultTxt.setBackgroundResource(R.drawable.golden_ball);
        else if(source.result == 4) resultTxt.setBackgroundResource(R.drawable.brick_red_ball);
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
