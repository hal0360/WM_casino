package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.RouletteActivity;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class RouletteCollection  extends GameCollection {


    public RouletteCollection(Table table) {
        super( table);
    }

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);


        setTableName(activity.getString(R.string.roulette) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(RouletteActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.firstRoad,(v,r)->{

        });

        count1.setText(getString(R.string.dragon_abb) + ":" + table.data.dragonCount);
        count2.setText(getString(R.string.tiger_abb) + ":" + table.data.tigerCount);
        count3.setText(getString(R.string.tie_abb) + ":" + table.data.tieCount);
    }
}
