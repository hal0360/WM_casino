package tw.com.lixin.wm_casino;

import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.models.TigerDragonTable;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.tools.DragonTigerCardArea;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.buttons.ControlButton;
import tw.com.lixin.wm_casino.tools.BetCountdown;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.tools.ProfileBar;
import tw.com.lixin.wm_casino.tools.RatePanel;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


 /*
public class DragonTigerActivity extends RootActivity  {

    private static int thisStage;
    public static ChipStackData tigerStackData, tieStackData, dragonStackData;
    public static void bacStarted(TableLogData.Data data){
        tigerStackData = new ChipStackData();
        dragonStackData = new ChipStackData();
        tieStackData = new ChipStackData();
        tigerStackData.score = data.dtOdds.get(2);
        dragonStackData.score = data.dtOdds.get(1);
        tieStackData.score = data.dtOdds.get(3);
        tigerStackData.maxValue = data.maxBet02;
        tieStackData.maxValue = data.maxBet03;
        dragonStackData.maxValue = data.maxBet01;
        thisStage = 1;
    }

    private int posX, posY;
    private IjkVideoView video;
    private TigerDragonTable table;
    private GameSource source;
    private ChipStack tigerStack, tieStack, dragonStack;
    private DragonTigerCardArea cardArea;
    private CasinoGrid mainGrid, firstGrid, secGrid, thirdGrid, fourthGrid;
    private ControlButton betBtn, cancelBtn, rebetBtn;
    private BetCountdown countdown;
    private AskButton askTiger, askDragon;
    private RatePanel panel;
    private ProfileBar profile;
    private TextView tigerCount, dragonCount, tieCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_tiger);

        source = GameSource.getInstance();
        table = (TigerDragonTable) source.table;
        cardArea = findViewById(R.id.card_area);
        tigerStack = findViewById(R.id.tiger_stack);
        tieStack = findViewById(R.id.tie_stack);
        dragonStack = findViewById(R.id.dragon_stack);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        betBtn = findViewById(R.id.confirm_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        rebetBtn = findViewById(R.id.rebet_btn);
        countdown = findViewById(R.id.countdown);
        askDragon = findViewById(R.id.ask_dragon_btn);
        askTiger = findViewById(R.id.ask_tiger_btn);
        video = findViewById(R.id.video);
        panel = findViewById(R.id.panel);
        profile = findViewById(R.id.profile);
        tigerCount = findViewById(R.id.tiger_count);
        dragonCount = findViewById(R.id.dragon_count);
        tieCount = findViewById(R.id.tie_count);
        video.setVideoPath("rtmp://wmvdo.nicejj.cn/dt" + table.groupID + "/stream1");

        betBtn.clicked(v -> {
            Client22 client22 = new Client22();
            tieStackData.addCoinToClient(client22, 3);
            dragonStackData.addCoinToClient(client22, 1);
            tigerStackData.addCoinToClient(client22, 2);
            if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
            else alert("You haven't put any money!");
        });

        cancelBtn.clicked(v -> {
            dragonStack.cancelBet();
            tigerStack.cancelBet();
            tieStack.cancelBet();
            betBtn.disable(true);
            cancelBtn.disable(true);
        });

        rebetBtn.clicked(v -> {
            dragonStack.repeatBet();
            tigerStack.repeatBet();
            tieStack.repeatBet();
            betBtn.disable(false);
            cancelBtn.disable(false);
        });

        clicked(askDragon, v -> {
            askDragon.askTigerDragon(table,1);
            askRoad(1);
        });

        clicked(askTiger, v -> {
            askTiger.askTigerDragon(table,2);
            askRoad(2);
        });
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

    @Override
    public void onPause() {
        super.onPause();
        video.stopPlayback();
        table.unBind();
        source.unbind();
    }


    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            dragonStack.comfirmBet();
            tigerStack.comfirmBet();
            tieStack.comfirmBet();
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

        if(stack.add(Chip.curChip)){
            betBtn.disable(false);
            cancelBtn.disable(false);
        }else{ alert("max value exceeded"); }
    }

    @Override
    public void stageUpdate() {
        thisStage = table.stage;
        if (table.stage == 1) {
            countdown.betting();
            cardArea.reset();
            tigerStack.clearCoin();
            dragonStack.clearCoin();
            tieStack.clearCoin();
        } else if (table.stage == 2) {
            countdown.dealing();
            cardArea.setVisibility(View.VISIBLE);
            tigerStack.cancelBet();
            dragonStack.cancelBet();
            tieStack.cancelBet();;
            cancelBtn.disable(true);
            rebetBtn.disable(true);
            betBtn.disable(true);
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
        tigerCount.setText(table.tigerCount+"");
        dragonCount.setText(table.dragonCount+"");
        tieCount.setText(table.tieCount);
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
            cardArea.showScore(table.tigerScore, table.dragonScore);
        }
    }

    @Override
    public void cardUpdate(int area, int img) {
        cardArea.update(area, img);
    }


}
*/

