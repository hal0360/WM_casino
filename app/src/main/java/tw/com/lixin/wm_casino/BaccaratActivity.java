package tw.com.lixin.wm_casino;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.popups.WinLossPopup;
import tw.com.lixin.wm_casino.tools.CasinoGrid;
import tw.com.lixin.wm_casino.tools.ProfileSetting;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.buttons.ControlButton;
import tw.com.lixin.wm_casino.tools.gameComponents.BacCardArea;
import tw.com.lixin.wm_casino.tools.gameComponents.BetCountdown;
import tw.com.lixin.wm_casino.tools.gameComponents.ChipStack;
import tw.com.lixin.wm_casino.tools.gameComponents.ProfileBar;
import tw.com.lixin.wm_casino.tools.gameComponents.RatePanel;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

import android.os.Bundle;
import android.view.View;

import static tw.com.lixin.wm_casino.models.BacTable.bankPairStackData;
import static tw.com.lixin.wm_casino.models.BacTable.bankStackData;
import static tw.com.lixin.wm_casino.models.BacTable.playPairStackData;
import static tw.com.lixin.wm_casino.models.BacTable.playStackData;
import static tw.com.lixin.wm_casino.models.BacTable.tieStackData;

public class BaccaratActivity extends WMActivity implements GameBridge, TableBridge, StackCallBridge {

    private int posX, posY;
    private IjkVideoView video;
    private BacTable table;
    private GameSource source;
    private ChipStack playerPairStack, playerStack, tieStack, bankerStack, bankerPairStack;
    private BacCardArea cardArea;
    private CasinoGrid mainGrid, firstGrid, secGrid, thirdGrid, fourthGrid;
    private ControlButton betBtn, cancelBtn, rebetBtn;
    private BetCountdown countdown;
    private AskButton askBank, askPlay;
    private RatePanel panel;
    private WinLossPopup winPopup;
    private ProfileBar profile;

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
        winPopup =  new WinLossPopup();

        video.setVideoPath("rtmp://wmvdo.nicejj.cn/live" + table.groupID + "/stream1");

        betBtn.clicked(v -> {
            Client22 client22 = new Client22();
            bankPairStackData.addCoinToClient(client22, 4);
            tieStackData.addCoinToClient(client22, 3);
            bankStackData.addCoinToClient(client22, 1);
            playPairStackData.addCoinToClient(client22, 5);
            playStackData.addCoinToClient(client22, 2);
            if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
            else alert("You haven't put any money!");
        });

        cancelBtn.clicked(v -> {
            table.cancelBets();
            checkStack();
        });

        rebetBtn.clicked(v -> {
            table.rebetBets();
            checkStack();
        });

        clicked(askBank, v -> {
            askBank.ask(table,1);
            askRoad(1);
        });

        clicked(askPlay, v -> {
            askPlay.ask(table,2);
            askRoad(2);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()) finish();
        table.bindGame(this);
        source.bind(this);
        playerPairStack.setUp(playPairStackData, this);
        bankerPairStack.setUp(bankPairStackData, this);
        playerStack.setUp(playStackData, this);
        bankerStack.setUp(bankStackData, this);
        tieStack.setUp(tieStackData, this);
        cardArea.setUp(table.pokers);

        if(table.stage == 1){
            cardArea.setVisibility(View.INVISIBLE);
            countdown.betting();
        }else {
            cardArea.setVisibility(View.VISIBLE);
            if(table.pokerWin != -1) cardArea.showScore(table.playerScore, table.bankerScore);
            countdown.dealing();
        }
        checkStack();
        gridUpdate();

        video.start();
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
        if(win == 1){
            if(posY < 5) mainGrid.askRoad(posX, posY + 1, R.drawable.casino_roadbank);
            else mainGrid.askRoad(posX+1, 0, R.drawable.casino_roadbank);
        }else{
            if(posY < 5) mainGrid.askRoad(posX, posY + 1, R.drawable.casino_roadplay);
            else mainGrid.askRoad(posX+1, 0, R.drawable.casino_roadplay);
        }
    }



    private void checkStack() {
        if (playStackData.isTempEmpty() && playPairStackData.isTempEmpty() && bankPairStackData.isTempEmpty() && bankStackData.isTempEmpty() && tieStackData.isTempEmpty()) {
            cancelBtn.disable(true);
            betBtn.disable(true);
        } else {
            cancelBtn.disable(false);
            betBtn.disable(false);
        }
        playerStack.refresh();
        playerPairStack.refresh();
        bankerPairStack.refresh();
        bankerStack.refresh();
        tieStack.refresh();
        if (playStackData.isAddEmpty() && playPairStackData.isAddEmpty() && bankPairStackData.isAddEmpty() && bankStackData.isAddEmpty() && tieStackData.isAddEmpty()) {
            rebetBtn.disable(true);
        }else{
            rebetBtn.disable(false);
        }
    }

    @Override
    public void stageUpdate() {
        if (table.stage == 1) {
            winPopup.dismiss();
            countdown.betting();
            cardArea.reset();
            checkStack();
        } else if (table.stage == 2) {
            countdown.dealing();
            cardArea.setVisibility(View.VISIBLE);
            checkStack();
        } else if (table.stage == 4) {

        }


    }

    @Override
    public void gridUpdate() {
        int indexx = 0;
        firstGrid.drawRoad(table.firstGrid);
        secGrid.drawRoad(table.secGrid);
        thirdGrid.drawRoad(table.thirdGrid);
        fourthGrid.drawRoad(table.fourthGrid);
        for (int x = 0; x < mainGrid.width; x++) {
            for (int y = 0; y < mainGrid.height; y++) {
                if (indexx >= table.mainRoad.size()) return;
                mainGrid.insertImage(x, y, table.mainRoad.get(indexx));
                indexx++;
                posX = x;
                posY = y;
            }
        }
    }

    @Override
    public void betCountdown(int sec) {
        countdown.setSecond(sec);
    }

    @Override
    public void resultUpdate() {
        if (table.pokerWin == 1) {

        } else if (table.pokerWin == 2) {

        } else if (table.pokerWin == 4) {

        }
        if(table.pokerWin != -1){
            cardArea.showScore(table.playerScore, table.bankerScore);
        }
    }

    @Override
    public void cardUpdate(int area, int img) {
        cardArea.update(area, img);
    }

    @Override
    public void balanceUpdate(float value) {
        profile.setBalance(value);
    }

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            cancelBtn.disable(true);
            rebetBtn.disable(true);
            betBtn.disable(true);
            playPairStackData.comfirmBet();
            playStackData.comfirmBet();
            bankStackData.comfirmBet();
            bankPairStackData.comfirmBet();
            tieStackData.comfirmBet();
        }else{ alert("bet fail!"); }
    }

    @Override
    public void winLossUpdate(TableData.Data data) {
        winPopup.setPay(data.moneyWin);
        winPopup.show(this);
    }

    @Override
    public void stackBet(ChipStack stack) {
        if(table.stage != 1) {
            alert("Please wait!!");
            return;
        }
        if(stack.add(Chip.curChip)){
            betBtn.disable(false);
            cancelBtn.disable(false);
            rebetBtn.disable(false);
        }else{ alert("max value exceeded"); }
    }



}
