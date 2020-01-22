package tw.com.lixin.wm_casino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.models.RouletteTable;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;

import android.view.View;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

public class RouletteActivity extends CasinoActivity {

    private TextGrid firstGrid, secondGrid, thirdGrid, fourthGrid;
    private RouletteTable table;
    private TextView resultTxt, oddEvenTxt;
    private ConstraintLayout page1, page2;
    private ClickImage leftArrow, rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_roulette);
        super.onCreate(savedInstanceState);

        page1 = findViewById(R.id.page_1);
        page2 = findViewById(R.id.page_2);
        leftArrow = findViewById(R.id.arrow_left);
        rightArrow = findViewById(R.id.arrow_right);
        firstGrid = findViewById(R.id.first_grid);
        secondGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        resultTxt = findViewById(R.id.result_txt);
        oddEvenTxt = findViewById(R.id.odd_even_txt);
        table = (RouletteTable) source.table;
        casinoArea.setVideo("rtmp://wmvdo.nicejj.cn/rou0" + table.groupID + "/stream1");
        betStarted();

        leftArrow.clicked(v->{
            page1.bringToFront();
            leftArrow.bringToFront();
            rightArrow.bringToFront();
        });

        rightArrow.clicked(v->{
            page2.bringToFront();
            leftArrow.bringToFront();
            rightArrow.bringToFront();

        });
    }

    @Override
    public void limitShows(LimitPopup limitPopup) {

    }

    @Override
    public void betStarted() {
        resultTxt.setVisibility(INVISIBLE);
        oddEvenTxt.setVisibility(INVISIBLE);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void resultUpdate() {

        resultTxt.setText(source.result+"");
        if ( (source.result & 1) == 0 ) oddEvenTxt.setText(getString(R.string.EVEN));
        else oddEvenTxt.setText(getString(R.string.ODD));
    }

    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstRoad);
        secondGrid.drawRoad(table.secondRoad);
        thirdGrid.drawRoad(table.thirdRoad);
        fourthGrid.drawRoad(table.fourthRoad);
    }
}
