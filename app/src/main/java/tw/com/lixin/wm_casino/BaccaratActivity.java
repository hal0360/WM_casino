package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.BacMainGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BaccaratActivity extends CasinoActivity implements GameBridge, TableBridge {

    public static boolean comission = false;
    private BacMainGrid mainGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private CasinoGrid firstGrid;
    private BacTable table;
    private TextView playerScore, playerTxt, bankerScore, bankerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_baccarat);
        super.onCreate(savedInstanceState);

        addToArea(R.id.banker_stack,source.logData.maxBet01,1);
        addToArea(R.id.player_stack,source.logData.maxBet02,2);
        addToArea(R.id.tie_stack,source.logData.maxBet03,3);
        addToArea(R.id.banker_pair_stack,source.logData.maxBet04,4);
        addToArea(R.id.player_pair_stack,source.logData.maxBet04,5);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        playerScore = findViewById(R.id.player_score);
        playerTxt = findViewById(R.id.player_txt);
        bankerScore = findViewById(R.id.banker_score);
        bankerTxt = findViewById(R.id.banker_txt);
        betStarted();

      //  setTextView(R.id.tie_pair_dtO, "1:" + source.logData.dtOdds.get(3));
      //  setTextView(R.id.banker_pair_dtO, "1:" + source.logData.dtOdds.get(4));
      //  setTextView(R.id.player_pair_dtO, "1:" + source.logData.dtOdds.get(5));
     //   setTextView(R.id.banker_dtO, "1:" + source.logData.dtOdds.get(1));
      //  setTextView(R.id.player_dtO, "1:" + source.logData.dtOdds.get(2));

        addCard(1, R.id.player_poker1);
        addCard(3, R.id.player_poker2);
        addCard(5, R.id.player_poker3);
        addCard(2, R.id.banker_poker1);
        addCard(4, R.id.banker_poker2);
        addCard(6, R.id.banker_poker3);
        table = (BacTable) source.table;
        if(table.stage != 1) setScores();
        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/live" + table.groupID + "/stream1");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
      //  limitPopup.addLimit(getString(R.string.banker), source.logData.dtOdds.get(1), source.logData.maxBet01,1 );
      //  limitPopup.addLimit(getString(R.string.player), source.logData.dtOdds.get(2), source.logData.maxBet02,1 );
      //  limitPopup.addLimit(getString(R.string.tie), source.logData.dtOdds.get(3), source.logData.maxBet03,1 );
      //  limitPopup.addLimit(getString(R.string.banker_pair), source.logData.dtOdds.get(4), source.logData.maxBet04,1 );
       // limitPopup.addLimit(getString(R.string.player_pair), source.logData.dtOdds.get(5), source.logData.maxBet04,1 );
    }

    @Override
    public void betStarted() {
        playerScore.setVisibility(INVISIBLE);
        playerTxt.setVisibility(INVISIBLE);
        bankerScore.setVisibility(INVISIBLE);
        bankerTxt.setVisibility(INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void setScores(){
        playerScore.setText(source.playerScore+"");
        bankerScore.setText(source.bankerScore+"");
        playerScore.setVisibility(VISIBLE);
        playerTxt.setVisibility(VISIBLE);
        bankerScore.setVisibility(VISIBLE);
        bankerTxt.setVisibility(VISIBLE);
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
        mainGrid.drawRoad(table.bigRoad);
        setTextView(R.id.bank_count, table.bankCount+"");
        setTextView(R.id.play_count, table.playCount+"");
        setTextView(R.id.tie_count, table.tieCount+"");
        setTextView(R.id.bank_pair_count, table.bankPairCount+"");
        setTextView(R.id.play_pair_count, table.playPairCount+"");
    }

    @Override
    public void resultUpdate() {
        Table.resDivide(source.result);
        if (source.result == 1) {

        } else if (source.result == 2) {

        } else if (source.result == 4) {

        }
        setScores();
    }

}
