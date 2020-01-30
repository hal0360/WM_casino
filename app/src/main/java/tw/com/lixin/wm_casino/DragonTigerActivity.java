package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.List;

import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.models.TigerDragonTable;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.tools.grids.DragonTigerGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class DragonTigerActivity extends CasinoActivity  {

    private TextGrid mainGrid, firstGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private TextView tigerScore, tigerTxt, dragonScore, dragonTxt;
    private AskButton askTiger, askDragon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dragon_tiger);
        super.onCreate(savedInstanceState);

        addToArea(R.id.dragon_stack,source.logData.maxBet01,1);
        addToArea(R.id.tiger_stack,source.logData.maxBet02,2);
        addToArea(R.id.tie_stack,source.logData.maxBet03,3);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        tigerScore = findViewById(R.id.tiger_score);
        tigerTxt = findViewById(R.id.tiger_txt);
        dragonScore = findViewById(R.id.dragon_score);
        dragonTxt = findViewById(R.id.dragon_txt);
        askTiger = findViewById(R.id.ask_tiger_btn);
        askDragon = findViewById(R.id.ask_dragon_btn);
        betStarted();
        setTextView(R.id.tie_dtO, "1:" + source.logData.dtOdds.get(3));
        setTextView(R.id.dragon_dtO, "1:" + source.logData.dtOdds.get(1));
        setTextView(R.id.tiger_dtO, "1:" + source.logData.dtOdds.get(2));
        addCard(1, R.id.tiger_poker);
        addCard(2, R.id.dragon_poker);

      //  if(table.stage != 1) setScores();
        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/dt" + source.table.groupID + "/stream1");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
       // limitPopup.addLimit(getString(R.string.banker), source.logData.dtOdds.get(1), source.logData.maxBet01,1 );
      //  limitPopup.addLimit(getString(R.string.player), source.logData.dtOdds.get(2), source.logData.maxBet02,1 );
       // limitPopup.addLimit(getString(R.string.tie), source.logData.dtOdds.get(3), source.logData.maxBet03,1 );
    }

    @Override
    public void betStarted() {
        tigerScore.setVisibility(INVISIBLE);
        tigerTxt.setVisibility(INVISIBLE);
        dragonScore.setVisibility(INVISIBLE);
        dragonTxt.setVisibility(INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void setScores(){
        tigerScore.setText(source.playerScore+"");
        dragonScore.setText(source.bankerScore+"");
        tigerScore.setVisibility(VISIBLE);
        tigerTxt.setVisibility(VISIBLE);
        dragonScore.setVisibility(VISIBLE);
        dragonTxt.setVisibility(VISIBLE);
    }

    private void askRoad(int win) {
      //  firstGrid.askRoad(table.firstGrid.posXX, table.firstGrid.posYY, table.firstGrid.resX);
      // secondGrid.askRoad(table.secGrid.posXX, table.secGrid.posYY, table.secGrid.resX);
      //  thirdGrid.askRoad(table.thirdGrid.posXX, table.thirdGrid.posYY, table.thirdGrid.resX);
       // fourthGrid.askRoad(table.fourthGrid.posXX, table.fourthGrid.posYY, table.fourthGrid.resX);
       // if(win == 1){ mainGrid.askRoad(1);
       // }else{ mainGrid.askRoad(5); }
    }

    @Override
    public void gridUpdate() {
        setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad);
        setTextView(R.id.dragon_count, source.table.data.dragonCount+"");
        setTextView(R.id.tiger_count, source.table.data.tigerCount+"");
        setTextView(R.id.tie_count, source.table.data.tieCount+"");
        askTiger.setAsk(source.table.data.tigerAsk3,source.table.data.tigerAsk4,source.table.data.tigerAsk5);
        askDragon.setAsk(source.table.data.dragonAsk3,source.table.data.dragonAsk4,source.table.data.dragonAsk5);
    }

    private void setRoads(List<Integer> main, ItemRoad first, ItemRoad second, ItemRoad third, ItemRoad fourth){
        firstGrid.drawRoad(first, (v,r)->{
            if(r == 1) v.setBackgroundResource(Road.Bank);
            else if(r == 2) v.setBackgroundResource(Road.Play);
            else if(r == 6) v.setBackgroundResource(Road.Play_E);
            else if(r == 5) v.setBackgroundResource(Road.Bank_E);
            else v.setBackgroundResource(0);
        });
        secondGrid.drawRoad(second, (v,r)->{
            if(r == 1) v.setBackgroundResource(Road.Bank);
            else if(r == 2) v.setBackgroundResource(Road.Play);
        });
        thirdGrid.drawRoad(third, (v,r)->{
            if(r == 1) v.setBackgroundResource(Road.Bank_S);
            else if(r == 2) v.setBackgroundResource(Road.Play_S);
        });
        fourthGrid.drawRoad(fourth, (v,r)->{
            if(r == 1) v.setBackgroundResource(Road.Bank_I);
            else if(r == 2) v.setBackgroundResource(Road.Play_I);
        });
        mainGrid.drawMainRoad(main, (v,r) ->{
            /*
            v.setTextColor(0xffffffff);
            v.setTextSize(7f);
            if(r == 1){
                v.setBackgroundResource(R.drawable.red_road);
                v.setText(getString(R.string.dragon_road));
            }
            else if(r == 2){
                v.setBackgroundResource(R.drawable.blue_road);
                v.setText(getString(R.string.tiger_road));
            }
            else if(r == 4){
                v.setBackgroundResource(R.drawable.green_road);
                v.setText(getString(R.string.tie_road));
            }*/
        });
    }

    @Override
    public void resultUpdate() {
        if (source.result == 1) {

        } else if (source.result == 2) {

        } else if (source.result == 4) {

        }
        setScores();
    }

}


