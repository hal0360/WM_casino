package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.SamGrid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SamgongActivity extends CasinoActivity {

    private TextView player1, player2, player3, banker;
    private SamGrid mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_samgong);
        super.onCreate(savedInstanceState);

        casinoArea.setTitle(getString(R.string.samgong) + source.table.groupID);
        player1 = findViewById(R.id.player1_txt);
        player2 = findViewById(R.id.player2_txt);
        player3 = findViewById(R.id.player3_txt);
        banker = findViewById(R.id.banker_txt);
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
        player1.setText(getString(R.string.player1) + ": " + App.sam.get(raw.get(0)[0]));
        player2.setText(getString(R.string.player2) + ": " + App.sam.get(raw.get(1)[0]));
        player3.setText(getString(R.string.player3) + ": " + App.sam.get(raw.get(2)[0]));
        banker.setText(getString(R.string.banker) + ": " + App.sam.get(raw.get(3)[0]));
        player1.setVisibility(VISIBLE);
        player2.setVisibility(VISIBLE);
        player3.setVisibility(VISIBLE);
        banker.setVisibility(VISIBLE);
    }

    @Override
    public void gridUpdate() {
        mainGrid.drawRoad(source.table.mainArr);
    }
}
