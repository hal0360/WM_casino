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


public class BaccaratActivity extends RootActivity implements GameBridge, TableBridge, StackCallBridge {

    public static boolean comission = false;

    private IjkVideoView video;
    private BacTable table;
    private GameSource source;
    private ChipStack playerPairStack, playerStack, tieStack, bankerStack, bankerPairStack;
    private BacCardArea cardArea;
    private CasinoGrid firstGrid;
    private BacMainGrid mainGrid;
    private CasinoDoubleGrid secGrid, thirdGrid, fourthGrid;
    private ControlButton betBtn, cancelBtn, rebetBtn;
    private BetCountdown countdown;
    private AskButton askBank, askPlay;
    private RatePanel panel;
    private ProfileBar profile;
    private TextView bankerCount, playerCount, tieCount, bankPairCount, playPairCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccarat);
        source = GameSource.getInstance();
        table = (BacTable) source.table;
        cardArea = findViewById(R.id.card_area);
        playerPairStack = findViewById(R.id.player_pair_stack);
        playerStack = findViewById(R.id.player_stack);
        tieStack = findViewById(R.id.tie_stack);
        bankerPairStack = findViewById(R.id.bank_pair_stack);
        bankerStack = findViewById(R.id.bank_stack);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        betBtn = findViewById(R.id.confirm_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        rebetBtn = findViewById(R.id.rebet_btn);
        countdown = findViewById(R.id.countdown);
        askBank = findViewById(R.id.ask_bank_btn);
        askPlay = findViewById(R.id.ask_play_btn);
        video = findViewById(R.id.video);
        panel = findViewById(R.id.panel);
        profile = findViewById(R.id.profile);
        bankerCount = findViewById(R.id.tiger_count);
        playerCount = findViewById(R.id.dragon_count);
        tieCount = findViewById(R.id.tie_count);
        bankPairCount = findViewById(R.id.banker_pair_count);
        playPairCount = findViewById(R.id.player_pair_count);
        video.setVideoPath("rtmp://wmvdo.nicejj.cn/live" + table.groupID + "/stream1");

        betBtn.clicked(v -> {
            Client22 client22 = new Client22();
            bankerPairStack.addCoinToClient(client22, 4);
            tieStack.addCoinToClient(client22, 3);
            bankerStack.addCoinToClient(client22, 1);
            playerPairStack.addCoinToClient(client22, 5);
            playerStack.addCoinToClient(client22, 2);
            if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
            else alert("You haven't put any money!");
        });

        cancelBtn.clicked(v -> {
            playerPairStack.cancelBet();
            playerStack.cancelBet();
            bankerPairStack.cancelBet();
            tieStack.cancelBet();
            bankerStack.cancelBet();
            betBtn.disable(true);
            cancelBtn.disable(true);
        });

        rebetBtn.clicked(v -> {
            playerPairStack.repeatBet();
            playerStack.repeatBet();
            bankerPairStack.repeatBet();
            tieStack.repeatBet();
            bankerStack.repeatBet();
            betBtn.disable(false);
            cancelBtn.disable(false);
        });

        clicked(askBank, v -> {
            askBank.askBac(table,1);
            askRoad(1);
        });

        clicked(askPlay, v -> {
            askPlay.askBac(table,2);
            askRoad(2);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()){
            alert("connection lost");
            finish();
            return;
        }
        panel.setGyuShu(table.number,table.round);
        profile.updateBalance();
        video.start();
        table.bind(this);
        source.bind(this);
        playerStack.setUp(source.logData.dtOdds.get(2),source.logData.maxBet02,this);
        bankerStack.setUp(source.logData.dtOdds.get(1),source.logData.maxBet01,this);
        tieStack.setUp(source.logData.dtOdds.get(3),source.logData.maxBet03,this);
        playerPairStack.setUp(source.logData.dtOdds.get(5),source.logData.maxBet04,this);
        bankerPairStack.setUp(source.logData.dtOdds.get(4),source.logData.maxBet04,this);
        cardArea.setUp(table.pokers);

        if(table.stage == 1){
            cardArea.setVisibility(View.INVISIBLE);
            countdown.betting();
        }else {
            cardArea.setVisibility(View.VISIBLE);
            if(table.result != -99) cardArea.showScore(table.playerScore, table.bankerScore);
            countdown.dealing();
        }
        gridUpdate();
    }

    @Override
    public void onBackPressed() {
        source.tableLogout();
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        video.stopPlayback();
        table.unBind();
        source.unbind();
    }

    private void askRoad(int win) {
        firstGrid.askRoad(table.firstGrid.posXX, table.firstGrid.posYY, table.firstGrid.resX);
        secGrid.askRoad(table.secGrid.posXX, table.secGrid.posYY, table.secGrid.resX);
        thirdGrid.askRoad(table.thirdGrid.posXX, table.thirdGrid.posYY, table.thirdGrid.resX);
        fourthGrid.askRoad(table.fourthGrid.posXX, table.fourthGrid.posYY, table.fourthGrid.resX);
        if(win == 1){ mainGrid.askRoad(1);
        }else{ mainGrid.askRoad(5); }
    }

    @Override
    public void stageUpdate() {
        if (table.stage == 1) {
            panel.setGyuShu(table.number,table.round);
            profile.updateBalance();
            countdown.betting();
            cardArea.reset();
            playerPairStack.clearCoin();
            playerStack.clearCoin();
            bankerPairStack.clearCoin();
            tieStack.clearCoin();
            bankerStack.clearCoin();
        } else if (table.stage == 2) {
            countdown.dealing();
            cardArea.setVisibility(View.VISIBLE);
            playerPairStack.cancelBet();
            playerStack.cancelBet();
            bankerPairStack.cancelBet();
            tieStack.cancelBet();
            bankerStack.cancelBet();
            cancelBtn.disable(true);
            rebetBtn.disable(true);
            betBtn.disable(true);
        } else if (table.stage == 4) {

        }

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
    public void betCountdown(int sec) {
        countdown.setSecond(sec);
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

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            playerPairStack.comfirmBet();
            playerStack.comfirmBet();
            bankerPairStack.comfirmBet();
            tieStack.comfirmBet();
            bankerStack.comfirmBet();
            cancelBtn.disable(true);
            rebetBtn.disable(false);
            betBtn.disable(true);
        }else{ alert("bet fail!"); }
    }

    @Override
    public void stackBet(ChipStack stack) {
        if(table.stage != 1) {
            alert("Please wait!!");
            return;
        }
        /*
        if(stack.add(Chip.curChip)){
            betBtn.disable(false);
            cancelBtn.disable(false);
        }else{ alert("max value exceeded"); }*/
    }


}
