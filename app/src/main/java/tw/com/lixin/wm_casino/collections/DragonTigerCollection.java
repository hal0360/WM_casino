package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.BaccaratActivity;
import tw.com.lixin.wm_casino.DragonTigerActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.TigerDragonTable;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class DragonTigerCollection extends GameCollection {

    private TextView  dragonRate, tigerRate, tieRate;
    private CasinoGrid firstGrid;
    private TigerDragonTable table;

    public DragonTigerCollection(TigerDragonTable table) {
        super(R.layout.dragon_tiger_collection, table);
        this.table = table;
    }

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);
        dragonRate = holder.findViewById(R.id.dragon_rate);
        tigerRate = holder.findViewById(R.id.tiger_rate);
        tieRate = holder.findViewById(R.id.tie_rate);
        firstGrid = holder.findViewById(R.id.first_grid);
        setTableName(activity.getString(R.string.dragon_tiger) + table.groupID);
        gridUpdate();
        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(DragonTigerActivity.class), activity::alert);
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstRoad);
        dragonRate.setText(table.dragonCount + "");
        tigerRate.setText(table.tigerCount + "");
        tieRate.setText(table.tieCount + "");
    }
}
