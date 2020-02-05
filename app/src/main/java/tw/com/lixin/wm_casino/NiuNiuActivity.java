package tw.com.lixin.wm_casino;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.grids.NiuGird;
import tw.com.lixin.wm_casino.tools.grids.SamGrid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class NiuNiuActivity  extends CasinoActivity {

    private TextView player1, player2, player3, banker;
    private NiuGird mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_niu_niu);
        super.onCreate(savedInstanceState);

        player1 = findViewById(R.id.player1_txt);
        player2 = findViewById(R.id.player2_txt);
        player3 = findViewById(R.id.player3_txt);
        banker = findViewById(R.id.banker_txt);
        mainGrid = findViewById(R.id.main_grid);

        casinoArea.setTitle(getString(R.string.niuniu) + source.table.groupID);
       // casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/live10/stream1");
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void betStarted() {
        player1.setVisibility(INVISIBLE);
        player2.setVisibility(INVISIBLE);
        player3.setVisibility(INVISIBLE);
        banker.setVisibility(INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {
        List<int[]> raw = App.getNiuniu(source.result);
        String poop = getString(R.string.niu) + (raw.get(0)[0] - 1);
        if(raw.get(0)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(0)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(0)[0] == 12) poop = getString(R.string.wugong);
        player1.setText(getString(R.string.player1) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(1)[0] - 1);
        if(raw.get(1)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(1)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(1)[0] == 12) poop = getString(R.string.wugong);
        player2.setText(getString(R.string.player2) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(2)[0] - 1);
        if(raw.get(2)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(2)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(2)[0] == 12) poop = getString(R.string.wugong);
        player3.setText(getString(R.string.player3) + ": " + poop);

        poop = getString(R.string.niu) + (raw.get(3)[0] - 1);
        if(raw.get(3)[0] == 1) poop = getString(R.string.no_niu);
        else if(raw.get(3)[0] == 11) poop = getString(R.string.niuniu);
        else if(raw.get(3)[0] == 12) poop = getString(R.string.wugong);
        banker.setText(getString(R.string.banker) + ": " + poop);

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
