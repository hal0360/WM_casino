package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.NiuNiuActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.NiuGird;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class NiuCollection extends GameCollection {

    public NiuCollection(Table table) {
        super(table);
    }

    private NiuGird niuGird;

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

        niuGird = holder.findViewById(R.id.niu_grid);
        niuGird.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);

        setTableName(getString(R.string.niuniu) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(NiuNiuActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        //niuGird.drawRoad(table.mainArr);
        count1.setText(getString(R.string.player1) + ":" + table.data.player1Count);
        count2.setText(getString(R.string.player2) + ":" + table.data.player2Count);
        count3.setText(getString(R.string.player3) + ":" + table.data.player3Count);
    }
}


