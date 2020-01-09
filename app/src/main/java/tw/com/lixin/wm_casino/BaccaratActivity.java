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
import android.view.View;
import android.widget.TextView;


public class BaccaratActivity extends CasinoActivity implements GameBridge, TableBridge {

    public static boolean comission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_baccarat);
        super.onCreate(savedInstanceState);

        addToArea(R.id.banker_stack,source.logData.maxBet01,1);
        addToArea(R.id.player_stack,source.logData.maxBet02,2);
        addToArea(R.id.tie_stack,source.logData.maxBet03,3);
        addToArea(R.id.banker_pair_stack,source.logData.maxBet04,4);
        addToArea(R.id.player_pair_stack,source.logData.maxBet04,5);
    }


    /*
    private void askRoad(int win) {
        firstGrid.askRoad(table.firstGrid.posXX, table.firstGrid.posYY, table.firstGrid.resX);
        secGrid.askRoad(table.secGrid.posXX, table.secGrid.posYY, table.secGrid.resX);
        thirdGrid.askRoad(table.thirdGrid.posXX, table.thirdGrid.posYY, table.thirdGrid.resX);
        fourthGrid.askRoad(table.fourthGrid.posXX, table.fourthGrid.posYY, table.fourthGrid.resX);
        if(win == 1){ mainGrid.askRoad(1);
        }else{ mainGrid.askRoad(5); }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstGrid);
        secGrid.drawRoad(table.secGrid);
        thirdGrid.drawRoad(table.thirdGrid);
        fourthGrid.drawRoad(table.fourthGrid);
        mainGrid.drawRoad(table.bigRoad);
        bankPairCount.setText(table.bankPairCount+"");
        bankerCount.setText(table.bankCount+"");
        playerCount.setText(table.playCount+"");
        tieCount.setText(table.tieCount+"");
        playPairCount.setText(table.playPairCount+"");
    }

    @Override
    public void resultUpdate() {

        if (table.result == 1) {

        } else if (table.result == 2) {

        } else if (table.result == 4) {

        }
        if(table.result != -1){
            cardArea.showScore(table.playerScore, table.bankerScore);
        }
    }

    @Override
    public void cardUpdate(int area, int img) {
        cardArea.update(area, img);
    }

    */


}
