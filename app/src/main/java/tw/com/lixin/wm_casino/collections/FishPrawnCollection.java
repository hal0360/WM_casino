package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.FishPrawnActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.CrabGrid;
import tw.com.lixin.wm_casino.tools.grids.DiceGrid;

public class FishPrawnCollection extends GameCollection {
    public FishPrawnCollection(Table table) {
        super(table);
    }

    public FishPrawnCollection(Table table, FragDialog blob) {
        super(table, blob);
    }

    private CrabGrid crabGrid;

    @Override
    protected void started(CollectionHolder holder) {
        crabGrid = holder.findViewById(R.id.crab_grid);
        crabGrid.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);
    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return FishPrawnActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        crabGrid.drawRoad(table.mainArr);
        count1.setText(getString(R.string.odd) + ":" + table.data.oddCount);
        count2.setText(getString(R.string.even) + ":" + table.data.evenCount);
        count3.setText(getString(R.string.big) + ":" + table.data.bigCount);
        count4.setText(getString(R.string.small) + ":" + table.data.smallCount);
    }
}
