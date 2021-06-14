package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.grids.BacMainGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;

public class BaccaratActivity extends CasinoActivity implements GameBridge, TableBridge {

    public static int comission = 0;
    private BacMainGrid mainGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private CasinoGrid firstGrid;
    private RadioButton commBtn;
    private AskButton askBanker, askPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_baccarat);
        super.onCreate(savedInstanceState);

        commBtn = findViewById(R.id.comm_btn);

        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);

        askBanker = findViewById(R.id.ask_bank_btn);
        askPlayer = findViewById(R.id.ask_play_btn);

        setTextView(R.id.tie_pair_dtO, "1:8" );
        setTextView(R.id.banker_pair_dtO, "1:11" );
        setTextView(R.id.player_pair_dtO, "1:11" );
        setTextView(R.id.banker_dtO, "1:0.95" );
        setTextView(R.id.player_dtO, "1:1" );

        addCard(1, R.id.player_poker1);
        addCard(3, R.id.player_poker2);
        addCard(5, R.id.player_poker3);
        addCard(2, R.id.banker_poker1);
        addCard(4, R.id.banker_poker2);
        addCard(6, R.id.banker_poker3);

        askBanker.clickDown(v-> setRoads(source.table.mainRoadAsk1, source.table.firstRoadAsk1, source.table.secondRoadAsk1, source.table.thirdRoadAsk1, source.table.fourthRoadAsk1));
        askBanker.clickUp(v-> setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad));
        askPlayer.clickDown(v-> setRoads(source.table.mainRoadAsk2, source.table.firstRoadAsk2, source.table.secondRoadAsk2, source.table.thirdRoadAsk2, source.table.fourthRoadAsk2));
        askPlayer.clickUp(v-> setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad));

        commBtn.setOnClickListener(v->{
            App.clicking();
            if(commBtn.isChecked()){
                setTextView(R.id.banker_dtO, "1:1" );
                comission = 1;
            }else {
                setTextView(R.id.banker_dtO, "1:0.95" );
                comission = 0;
            }
        });

        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/live" + source.table.groupID + "/720p");
    }

    private void setRoads(ItemRoad main, ItemRoad first, ItemRoad second, ItemRoad third, ItemRoad fourth){
        firstGrid.drawRoad(first);
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
        mainGrid.drawRoad(main);
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.banker), "1:0.95", source.logData.maxBet01,1 );
        limitPopup.addLimit(getString(R.string.player), "1:1", source.logData.maxBet02,1 );
        limitPopup.addLimit(getString(R.string.tie), "1:8", source.logData.maxBet03,1 );
        limitPopup.addLimit(getString(R.string.banker_pair), "1:11", source.logData.maxBet04,1 );
        limitPopup.addLimit(getString(R.string.player_pair), "1:11", source.logData.maxBet04,1 );
        limitPopup.addLimit(getString(R.string.BIG), "1:0.5", 100,1 );
        limitPopup.addLimit(getString(R.string.SMALL), "1:1.5", 100,1 );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad);
        askPlayer.setAsk(source.table.data.playerAsk3,source.table.data.playerAsk4,source.table.data.playerAsk5);
        askBanker.setAsk(source.table.data.bankerAsk3,source.table.data.bankerAsk4,source.table.data.bankerAsk5);
        setTextView(R.id.bank_count, source.table.data.bankerCount+"");
        setTextView(R.id.play_count, source.table.data.playerCount+"");
        setTextView(R.id.tie_count, source.table.data.tieCount+"");
        setTextView(R.id.bank_pair_count, source.table.data.bankerPairCount+"");
        setTextView(R.id.play_pair_count, source.table.data.playerPairCount+"");
    }

    @Override
    public void resultUpdate() {
        int resres = resDivide(source.result);
        if (resres == 1) {

        } else if (resres == 2) {

        } else if (resres == 4) {

        }

        setResultText(R.id.player_score, source.playerScore+"");
        setResultText(R.id.banker_score, source.bankerScore+"");
        showResultText(R.id.player_txt);
        showResultText(R.id.banker_txt);
    }

    private int resDivide(int rawVal){
        List<Integer> powers = new ArrayList<>();
        for(int i = 8; i >= 0; i-- ){
            int boss = (int) Math.pow(2,i);
            if(rawVal >= boss){
                powers.add(0,boss);
                rawVal = rawVal - boss;
                if(rawVal <= 0){
                    break;
                }
            }
        }
        return powers.get(0);
    }

}
