package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.AskButton;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;


public class DragonTigerActivity extends CasinoActivity  {

    private TextGrid mainGrid, firstGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private AskButton askTiger, askDragon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dragon_tiger);
        super.onCreate(savedInstanceState);

        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        askTiger = findViewById(R.id.ask_tiger_btn);
        askDragon = findViewById(R.id.ask_dragon_btn);

        setTextView(R.id.tie_dtO, "1:8");
        setTextView(R.id.dragon_dtO, "1:1");
        setTextView(R.id.tiger_dtO, "1:1");

        addCard(1, R.id.tiger_poker);
        addCard(2, R.id.dragon_poker);

        askDragon.clickDown(v-> setRoads(source.table.mainRoadAsk1, source.table.firstRoadAsk1, source.table.secondRoadAsk1, source.table.thirdRoadAsk1, source.table.fourthRoadAsk1));
        askDragon.clickUp(v-> setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad));
        askTiger.clickDown(v-> setRoads(source.table.mainRoadAsk2, source.table.firstRoadAsk2, source.table.secondRoadAsk2, source.table.thirdRoadAsk2, source.table.fourthRoadAsk2));
        askTiger.clickUp(v-> setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad));

        casinoArea.setVideo("rtmp://rtmp://wmvdo.sun1127.cn/dt1/720p");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.banker), "1", source.logData.maxBet01,1 );
        limitPopup.addLimit(getString(R.string.player), "1", source.logData.maxBet02,1 );
        limitPopup.addLimit(getString(R.string.tie), "8", source.logData.maxBet03,1 );
    }

    @Override
    public void gridUpdate() {
        setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad);
        setTextView(R.id.dragon_count, source.table.data.dragonCount+"");
        setTextView(R.id.tiger_count, source.table.data.tigerCount+"");
        setTextView(R.id.tie_count, source.table.data.tieCount+"");
        askTiger.setAsk(source.table.data.tigerAsk3,source.table.data.tigerAsk4,source.table.data.tigerAsk5);
        askDragon.setAsk(source.table.data.dragonAsk3,source.table.data.dragonAsk4,source.table.data.dragonAsk5);
    }

    private void setRoads(ItemRoad main, ItemRoad first, ItemRoad second, ItemRoad third, ItemRoad fourth){

        firstGrid.drawRoad(first, (v,r)->{
            if(r == 1) v.setTextImg(Road.Bank);
            else if(r == 2) v.setTextImg(Road.Play);
            else if(r == 6) v.setTextImg(Road.Play_E);
            else if(r == 5) v.setTextImg(Road.Bank_E);
        });
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
        mainGrid.drawRoad(main, (v,r) ->{
            v.setTextColor(0xffffffff);
            v.setTextSize(7);
            if(r == 1){
                v.setTextImg(R.drawable.red_road);
                v.setText(getString(R.string.dragon_road));
            }
            else if(r == 2){
                v.setTextImg(R.drawable.blue_road);
                v.setText(getString(R.string.tiger_road));
            }
            else if(r == 4){
                v.setTextImg(R.drawable.green_road);
                v.setText(getString(R.string.tie_road));
            }
        });

    }

    @Override
    public void resultUpdate() {
        if (source.result == 1) {

        } else if (source.result == 2) {

        } else if (source.result == 4) {

        }
        setResultText(R.id.tiger_score, source.playerScore+"");
        setResultText(R.id.dragon_score, source.bankerScore+"");
        showResultText(R.id.tiger_txt);
        showResultText(R.id.dragon_txt);
    }

}


