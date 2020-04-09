package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.SicBoActivity;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.DiceGrid;

public class SicBoCollection extends GameCollection {

    public SicBoCollection(Table table) {
        super(table);
    }

    public SicBoCollection(Table table, FragDialog r) {
        super(table,r);
    }

    private DiceGrid diceGrid;

    @Override
    protected void started(CollectionHolder holder) {
        diceGrid = holder.findViewById(R.id.dice_grid);
        diceGrid.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);
    }




    protected Class<? extends AppCompatActivity> toGameActicity() {
        return SicBoActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        diceGrid.drawRoad(table.mainArr);
        count1.setText(getString(R.string.odd) + ":" + table.data.oddCount);
        count2.setText(getString(R.string.even) + ":" + table.data.evenCount);
        count3.setText(getString(R.string.big) + ":" + table.data.bigCount);
        count4.setText(getString(R.string.small) + ":" + table.data.smallCount);
    }
}


