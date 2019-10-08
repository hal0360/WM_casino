package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.tools.AskButton;
import tw.com.lixin.wm_casino.tools.BacCardArea;
import tw.com.lixin.wm_casino.tools.BetCountdown;
import tw.com.lixin.wm_casino.tools.CasinoGrid;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.tools.ControlButton;
import tw.com.lixin.wm_casino.tools.ProfileSetting;
import tw.com.lixin.wm_casino.tools.RatePanel;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacActivity extends WMActivity implements TableBridge, StackCallBridge {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac);
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
        video = findViewById(R.id.my_player);
        panel = findViewById(R.id.panel);

        betBtn.clicked(v -> {
            Client22 client22 = new Client22();
            table.bankPairStack.addCoinToClient(client22, 4);
            table.tieStack.addCoinToClient(client22, 3);
            table.bankStack.addCoinToClient(client22, 1);
            table.playPairStack.addCoinToClient(client22, 5);
            table.playStack.addCoinToClient(client22, 2);
            if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
            else alert("You haven't put any money!");
        });

        cancelBtn.clicked(v -> cancellAllbets());

        rebetBtn.clicked(v -> {
            playerStack.repeatBet();
            playerPairStack.repeatBet();
            bankerStack.repeatBet();
            bankerPairStack.repeatBet();
            tieStack.repeatBet();
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
        table.bind(this);
        video.setVideoPath("rtmp://wmvdo.nicejj.cn/live" + table.groupID + "/stream1");
        video.start();
        playerPairStack.setUp(table.playPairStack, this);
        bankerPairStack.setUp(table.bankPairStack, this);
        playerStack.setUp(table.playStack, this);
        bankerStack.setUp(table.bankStack, this);
        tieStack.setUp(table.tieStack, this);
        cardArea.statusCheck(table.cardStatus, table.pokers);
        countdown.statusCheck(table.cardStatus);
        checkStackEmpty();
        panel.setUp(table);
        if(!isPortrait()) panel.percentUpdate(table.round, table.playCount, table.tieCount, table.bankCount);

        table.bind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        video.stopPlayback();
        table.unBind();
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

    private void checkStackEmpty() {
        if (table.playStack.isTempEmpty() && table.playPairStack.isTempEmpty() && table.bankPairStack.isTempEmpty() && table.bankStack.isTempEmpty() && table.tieStack.isTempEmpty()) {
            rebetBtn.disable(true);
            cancelBtn.disable(true);
            betBtn.disable(true);
        } else {
            rebetBtn.disable(false);
            cancelBtn.disable(false);
            betBtn.disable(false);
        }
    }

    private void cancellAllbets(){
        playerStack.cancelBet();
        playerPairStack.cancelBet();
        bankerStack.cancelBet();
        bankerPairStack.cancelBet();
        tieStack.cancelBet();
        betBtn.disable(true);
        cancelBtn.disable(true);
        rebetBtn.disable(true);
    }

    @Override
    public void resultUpadte() {
        if (table.pokerWin == 1) {

        } else if (table.pokerWin == 2) {

        } else if (table.pokerWin == 4) {

        }
        if(table.pokerWin == -1){
            setTextView(R.id.bank_score,"");
            setTextView(R.id.play_score,"");
        }else{
            setTextView(R.id.bank_score,""+table.bankerScore);
            setTextView(R.id.play_score,""+table.playerScore);
        }
    }

    @Override
    public void balanceUpdate(float value) {
        ProfileSetting profile = findViewById(R.id.profile_setting);
        profile.setBalance(value);
    }

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            cancelBtn.disable(true);
            rebetBtn.disable(true);
            betBtn.disable(true);
        }else{ alert("fail!"); }
    }

    @Override
    public void tableLogin(boolean logOk) {

    }

    @Override
    public void cardUpdate(int area, int img) {
        cardArea.update(area, img);
    }

    @Override
    public void statusUpdate() {
        if (table.cardStatus == 0) {

        } else if (table.cardStatus == 1) {
            playerStack.reset();
            playerPairStack.reset();
            tieStack.reset();
            bankerPairStack.reset();
            bankerStack.reset();
            countdown.betting();
        } else if (table.cardStatus == 2) {
            cancellAllbets();
            countdown.dealing();
        } else if (table.cardStatus == 3) {

        } else {

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
    public void stackBet(ChipStack stack) {
        if(table.cardStatus != 1) {
            alert("Please wait!!");
            return;
        }
        if(stack.add(Chip.curChip)){ checkStackEmpty();
        }else{ alert("max value exceeded"); }
    }
}
