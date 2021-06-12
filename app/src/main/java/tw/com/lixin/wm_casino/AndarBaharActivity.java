package tw.com.lixin.wm_casino;

import tw.com.lixin.wm_casino.global.Poker;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.CasinoDoubleGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AndarBaharActivity extends CasinoActivity {

    private TextGrid mainGrid, firstGrid;
    private CasinoDoubleGrid secondGrid, thirdGrid, fourthGrid;
    private ImageView jokerPoker, andaPoker, bahapoker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_andar_bahar);
        super.onCreate(savedInstanceState);

        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);

        addCard(1, R.id.joker_card);
        addCard(2, R.id.anda_poker);
        addCard(3, R.id.baba_poker);

        jokerPoker = findViewById(R.id.joker_card);
        andaPoker = findViewById(R.id.anda_poker);
        bahapoker = findViewById(R.id.baba_poker);

        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/ab" + source.table.groupID + "/720p");
    }

    @Override
    public void cardUpdate(int area, int img) {

        if(area == 1){
            jokerPoker.setImageResource(Poker.NUM(img));
            jokerPoker.setVisibility(View.VISIBLE);
        }else if(area >= 200){
            bahapoker.setImageResource(Poker.NUM(img));
            bahapoker.setVisibility(View.VISIBLE);
        }else if(area >= 100){
            andaPoker.setImageResource(Poker.NUM(img));
            andaPoker.setVisibility(View.VISIBLE);
        }

      //  ImageView pokerImg = pokers.get(area);
       // if(pokerImg != null){
       //     pokerImg.setVisibility(View.VISIBLE);
        //    pokerImg.setImageResource(Poker.NUM(img));
       // }
    }


    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void resultUpdate() {

    }

    private void setRoads(ItemRoad main, ItemRoad first, ItemRoad second, ItemRoad third, ItemRoad fourth){

        firstGrid.drawRoad(first, (v,r)->{
            if(r == 2) v.setTextImg(Road.Bank);
            else if(r == 1) v.setTextImg(Road.Play);

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



        List<Double> arr;
        int intRes, x, y;
        x= 0;
        y = 0;
        for(Object ojo :source.table.data.dataArr2){
            arr = (List<Double>) ojo;
            for(double douRes: arr){
                intRes = (int) douRes;
                if(intRes == 2){
                    mainGrid.drawFrom(x,y,R.drawable.red_road,"d");
                }else if(intRes == 1){
                    mainGrid.drawFrom(x,y,R.drawable.blue_road,"s");
                }
                y++;
                if(y > 5) {
                    y = 0;
                    x++;
                }
            }
        }



    }

    @Override
    public void gridUpdate() {

        setRoads(source.table.mainRoad, source.table.firstRoad, source.table.secondRoad, source.table.thirdRoad, source.table.fourthRoad);


    }
}