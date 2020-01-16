package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
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
        addCard(1, R.id.player_poker1);
        addCard(3, R.id.player_poker2);
        addCard(5, R.id.player_poker3);
        addCard(2, R.id.banker_poker1);
        addCard(4, R.id.banker_poker2);
        addCard(6, R.id.banker_poker3);
        table = (BacTable) source.table;
        if(table.stage != 1) setScores();
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
        playerScore.setText(table.playerScore+"");
        bankerScore.setText(table.bankerScore+"");
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

    public void cleanCard(){
        pokers.get(1).setVisibility(INVISIBLE);
        pokers.get(2).setVisibility(INVISIBLE);
        pokers.get(3).setVisibility(INVISIBLE);
        pokers.get(4).setVisibility(INVISIBLE);
        pokers.get(5).setVisibility(INVISIBLE);
        pokers.get(6).setVisibility(INVISIBLE);
        playerScore.setVisibility(INVISIBLE);
        playerTxt.setVisibility(INVISIBLE);
        bankerScore.setVisibility(INVISIBLE);
        bankerTxt.setVisibility(INVISIBLE);
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
        if (table.result == 1) {

        } else if (table.result == 2) {

        } else if (table.result == 4) {

        }
        setScores();
    }

}
