package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.SamgongActivity;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.SamGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class SamgongCollection extends GameCollection {

    public SamgongCollection(Table table) {
        super(table);
    }

    public SamgongCollection(Table table, FragDialog f) {
        super(table,f);
    }

    private SamGrid samGrid;

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

        samGrid = holder.findViewById(R.id.sam_grid);
        samGrid.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);

        setTableName(getString(R.string.samgong) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(SamgongActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        samGrid.drawRoad(table.mainArr);
        count1.setText(getString(R.string.player1) + ":" + table.data.player1Count);
        count2.setText(getString(R.string.player2) + ":" + table.data.player2Count);
        count3.setText(getString(R.string.player3) + ":" + table.data.player3Count);
    }
}


