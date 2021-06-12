package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.List;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.NiuGird;

public class NiuNiuActivity  extends CasinoActivity {

    private NiuGird mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_niu_niu);
        super.onCreate(savedInstanceState);

        mainGrid = findViewById(R.id.main_grid);
        casinoArea.setVideo("rtmp://wmvdo.sun1127.cn/nn" + source.table.groupID + "/720p");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {
        limitPopup.addLimit(getString(R.string.equal), "1:1", 100,1 );
        limitPopup.addLimit(getString(R.string.double_normal), "1:1", 20,1 );
        limitPopup.addLimit(getString(R.string.double_bull), "1:2", 20,1 );
        limitPopup.addLimit(getString(R.string.double_niu_niu), "1:3", 20,1 );
        limitPopup.addLimit(getString(R.string.direct), "1:5", 20,1 );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        List<int[]> raw = App.getNiuniu(source.result);
        String poop = getString(R.string.niu) + (raw.get(0)[0] - 1);
        if(raw.get(0)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(0)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(0)[0] == 12) poop = getString(R.string.wugong);
        setResultText(R.id.player1_txt, getString(R.string.player1) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(1)[0] - 1);
        if(raw.get(1)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(1)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(1)[0] == 12) poop = getString(R.string.wugong);
        setResultText(R.id.player2_txt, getString(R.string.player2) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(2)[0] - 1);
        if(raw.get(2)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(2)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(2)[0] == 12) poop = getString(R.string.wugong);
        setResultText(R.id.player3_txt, getString(R.string.player3) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(3)[0] - 1);
        if(raw.get(3)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(3)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(3)[0] == 12) poop = getString(R.string.wugong);
        setResultText(R.id.banker_txt, getString(R.string.banker) + ": " + poop);
    }

    @Override
    public void gridUpdate() {
        mainGrid.drawRoad(source.table.mainArr);
    }
}
