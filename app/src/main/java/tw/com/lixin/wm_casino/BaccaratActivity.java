package tw.com.lixin.wm_casino;

import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.tools.grids.BacMainGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.buttons.ControlButton;
import tw.com.lixin.wm_casino.tools.BacCardArea;
import tw.com.lixin.wm_casino.tools.BetCountdown;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.tools.ProfileBar;
import tw.com.lixin.wm_casino.tools.RatePanel;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.INVISIBLE;


public class BaccaratActivity extends CasinoActivity implements GameBridge, TableBridge {

    public static boolean comission = false;

    private BacMainGrid mainGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private CasinoGrid firstGrid;
    private BacTable table;
    private SparseArray<ImageView> pokers;
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
        pokers = new SparseArray<>();
        pokers.put(1,findViewById(R.id.player_poker1));
        pokers.put(3,findViewById(R.id.player_poker2));
        pokers.put(5,findViewById(R.id.player_poker3));
        pokers.put(2,findViewById(R.id.banker_poker1));
        pokers.put(4,findViewById(R.id.banker_poker2));
        pokers.put(6,findViewById(R.id.banker_poker3));
        table = (BacTable) source.table;
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
        if(table.result != -1){
         //   cardArea.showScore(table.playerScore, table.bankerScore);
        }
    }

    @Override
    public void cardUpdate(int area, int img) {
      //  cardArea.update(area, img);
    }
}
