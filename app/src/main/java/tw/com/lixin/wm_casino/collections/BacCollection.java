package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.BaccaratActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.CellView.WordView;

public class BacCollection extends GameCollection {


    public BacCollection(Table table) {
        super(table);
    }

    public BacCollection(Table table, FragDialog f) {
        super(table,f);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return BaccaratActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {

      //  Log.e("stubby",Arrays.deepToString(table.firstRoad.road));
      // Log.e("stubby",table.groupID +" " + table.dealerName);
        textGrid.drawRoad(table.firstRoad, WordView::setTextImg);


        count1.setText(getString(R.string.banker_abb) + table.data.bankerCount);
        count2.setText(getString(R.string.player_abb) + table.data.playerCount);
        count3.setText(getString(R.string.tie_abb) + table.data.tieCount);
    }
}
