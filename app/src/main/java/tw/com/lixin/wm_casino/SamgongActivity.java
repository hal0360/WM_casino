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
        casinoArea.setVideo("live10");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

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
