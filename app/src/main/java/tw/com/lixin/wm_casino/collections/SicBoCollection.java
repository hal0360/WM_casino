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
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class SicBoCollection extends GameCollection {

    public SicBoCollection(Table table) {
        super(table);
    }

    public SicBoCollection(Table table, FragDialog r) {
        super(table,r);
    }

    private DiceGrid diceGrid;

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

        diceGrid = holder.findViewById(R.id.dice_grid);
        diceGrid.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);

        setTableName(getString(R.string.sic_bo) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(SicBoActivity.class), activity::alert);
        });
    }

    @Override
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
    }
}


