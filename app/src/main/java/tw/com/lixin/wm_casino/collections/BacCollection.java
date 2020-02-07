package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.BaccaratActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.CellView.WordView;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacCollection extends GameCollection {


    public BacCollection(Table table) {
        super(table);
    }

    public BacCollection(Table table, FragDialog f) {
        super(table,f);
    }

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

        setTableName(getString(R.string.baccarat) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(BaccaratActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.firstRoad, WordView::setTextImg);
        count1.setText(getString(R.string.banker_abb) + ":" + table.data.bankerCount);
        count2.setText(getString(R.string.player_abb) + ":" + table.data.playerCount);
        count3.setText(getString(R.string.tie_abb) + ":" + table.data.tieCount);
    }
}
