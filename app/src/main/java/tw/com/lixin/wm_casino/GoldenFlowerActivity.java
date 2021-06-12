package tw.com.lixin.wm_casino;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.tools.grids.NiuGird;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import android.os.Bundle;

public class GoldenFlowerActivity extends CasinoActivity {

    private TextGrid grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_golden_flower);
        super.onCreate(savedInstanceState);

        grid = findViewById(R.id.first_grid);
        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/gf" + source.table.groupID + "/720p");


        addCard(1, R.id.player_poker1);
        addCard(3, R.id.player_poker2);
        addCard(5, R.id.player_poker3);
        addCard(2, R.id.banker_poker1);
        addCard(4, R.id.banker_poker2);
        addCard(6, R.id.banker_poker3);

    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void resultUpdate() {

    }

    @Override
    public void gridUpdate() {

        grid.drawRoad(source.table.firstRoad, (v,r)->{
            if(r == 1){
                v.setTextImg(Road.Bank);
            }else if(r == 2){
                v.setTextImg(Road.Play);
            }
        });


    }
}