package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.List;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.SamGrid;

public class SamgongActivity extends CasinoActivity {

    private SamGrid mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_samgong);
        super.onCreate(savedInstanceState);

        mainGrid = findViewById(R.id.main_grid);

        addPage(R.id.page_1);
        addPage(R.id.page_2);
        addPage(R.id.page_3);
        setPageArrow();
        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/sg" + source.table.groupID + "/720p");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit("(" + getString(R.string.player) + ")" + getString(R.string.win), "1:0.96", 100,1 );
        limitPopup.addLimit("(" + getString(R.string.player) + ")" + getString(R.string.lose), "1:0.96", 100,1 );
        limitPopup.addLimit("(" + getString(R.string.player) + ")" + getString(R.string.tie), "1:8", 20,1 );
        limitPopup.addLimit("(" + getString(R.string.player) + ")" + getString(R.string.three_face), "1:16", 10,1 );
        limitPopup.addLimit("(" + getString(R.string.pairPlus) + ")" + getString(R.string.pair), "1:1", 5,1 );
        limitPopup.addLimit("(" + getString(R.string.pairPlus) + ")" + getString(R.string.straight), "1:6", 5,1 );
        limitPopup.addLimit("(" + getString(R.string.pairPlus) + ")" + getString(R.string.flush), "1:3", 5,1 );
        limitPopup.addLimit("(" + getString(R.string.pairPlus) + ")" + getString(R.string.three_of_kind), "1:30", 5,1 );
        limitPopup.addLimit("(" + getString(R.string.pairPlus) + ")" + getString(R.string.straight_flush), "1:40", 5,1 );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        List<int[]> raw = App.getSamgong(source.result);
        setResultText(R.id.player1_txt,getString(R.string.player1) + ": " + App.sam.get(raw.get(0)[0]));
        setResultText(R.id.player2_txt,getString(R.string.player2) + ": " + App.sam.get(raw.get(1)[0]));
        setResultText(R.id.player3_txt,getString(R.string.player3) + ": " + App.sam.get(raw.get(2)[0]));
        setResultText(R.id.banker_txt,getString(R.string.banker) + ": " + App.sam.get(raw.get(3)[0]));
    }

    @Override
    public void gridUpdate() {
        mainGrid.drawRoad(source.table.mainArr);
    }
}
