package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.RouletteActivity;
import tw.com.lixin.wm_casino.models.RouletteTable;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class RouletteCollection  extends GameCollection {

    private TextView dragonRate, tigerRate, tieRate;
    private TextGrid firstGrid;
    private RouletteTable table;

    public RouletteCollection(RouletteTable table) {
        super(R.layout.roulette_collection, table);
        this.table = table;
    }

    @Override
    public void onBind(CollectionHolder holder) {
        super.onBind(holder);

      //  dragonRate = holder.findViewById(R.id.dragon_rate);
      //  tigerRate = holder.findViewById(R.id.tiger_rate);
      //  tieRate = holder.findViewById(R.id.tie_rate);

        firstGrid = holder.findViewById(R.id.first_grid);
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
        firstGrid.drawRoad(table.firstRoad);
       // dragonRate.setText(table.dragonCount + "");
        //tigerRate.setText(table.tigerCount + "");
       // tieRate.setText(table.tieCount + "");
    }
}
