package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import tw.com.lixin.wm_casino.models.TigerDragonTable;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.grids.BacMainGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class DragonTigerActivity extends CasinoActivity  {

    private BacMainGrid mainGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private CasinoGrid firstGrid;
    private TigerDragonTable table;
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
        betStarted();
        setTextView(R.id.tie_pair_dtO, "1:" + source.logData.dtOdds.get(3));
        setTextView(R.id.banker_pair_dtO, "1:" + source.logData.dtOdds.get(4));
        setTextView(R.id.player_pair_dtO, "1:" + source.logData.dtOdds.get(5));
        setTextView(R.id.banker_dtO, "1:" + source.logData.dtOdds.get(1));
        setTextView(R.id.player_dtO, "1:" + source.logData.dtOdds.get(2));
        addCard(1, R.id.tiger_poker);
        addCard(2, R.id.dragon_poker);
        table = (TigerDragonTable) source.table;

      //  if(table.stage != 1) setScores();

        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/live" + table.groupID + "/stream1");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.banker), source.logData.dtOdds.get(1), source.logData.maxBet01,1 );
        limitPopup.addLimit(getString(R.string.player), source.logData.dtOdds.get(2), source.logData.maxBet02,1 );
        limitPopup.addLimit(getString(R.string.tie), source.logData.dtOdds.get(3), source.logData.maxBet03,1 );
        limitPopup.addLimit(getString(R.string.banker_pair), source.logData.dtOdds.get(4), source.logData.maxBet04,1 );
        limitPopup.addLimit(getString(R.string.player_pair), source.logData.dtOdds.get(5), source.logData.maxBet04,1 );
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
        firstGrid.askRoad(table.firstGrid.posXX, table.firstGrid.posYY, table.firstGrid.resX);
        secondGrid.askRoad(table.secGrid.posXX, table.secGrid.posYY, table.secGrid.resX);
        thirdGrid.askRoad(table.thirdGrid.posXX, table.thirdGrid.posYY, table.thirdGrid.resX);
        fourthGrid.askRoad(table.fourthGrid.posXX, table.fourthGrid.posYY, table.fourthGrid.resX);
        if(win == 1){ mainGrid.askRoad(1);
        }else{ mainGrid.askRoad(5); }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstGrid);
        secondGrid.drawRoad(table.secGrid);
        thirdGrid.drawRoad(table.thirdGrid);
        fourthGrid.drawRoad(table.fourthGrid);
        mainGrid.drawRoad(table.mainRoad);
        setTextView(R.id.dragon_count, table.dragonCount+"");
        setTextView(R.id.tiger_count, table.tigerCount+"");
        setTextView(R.id.tie_count, table.tieCount+"");
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


