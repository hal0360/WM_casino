package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.DragonTigerActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class DragonTigerCollection extends GameCollection {


    public DragonTigerCollection(Table table) {
        super(table);
    }

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

        setTableName(getString(R.string.dragon_tiger) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(DragonTigerActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.firstRoad, (v,r)->{
            if(r == 1){
                v.setBackgroundResource(Road.Bank);
            }else if(r == 2){
                v.setBackgroundResource(Road.Play);
            }else if(r == 6){
                v.setBackgroundResource(Road.Play_E);
            }else if(r == 5){
                v.setBackgroundResource(Road.Bank_E);
            }
        });
        count1.setText(getString(R.string.dragon_abb) + ":" + table.data.dragonCount);
        count2.setText(getString(R.string.tiger_abb) + ":" + table.data.tigerCount);
        count3.setText(getString(R.string.tie_abb) + ":" + table.data.tieCount);
    }
}
