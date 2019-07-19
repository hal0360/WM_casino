package tw.com.lixin.wm_casino.models;

import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Locale;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wmcasino.BacActivity;
import tw.com.lixin.wmcasino.R;
import tw.com.lixin.wmcasino.Tools.CasinoGrid;
import tw.com.lixin.wmcasino.WMActivity;
import tw.com.lixin.wmcasino.websocketSource.LobbySource;

public class VerticalTableHolder extends ItemHolder {

    private Table table;

    public VerticalTableHolder(Table table) {
        super(R.layout.table_vertical_item);
        this.table = table;
    }

    @Override
    public void onBind() {

        CasinoGrid grid = findViewById(R.id.road_grid);

        grid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    grid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                double dim = grid.getHeight() / 6.0;
                int wGrid = (int) Math.round(grid.getWidth()/dim);
                grid.setGrid(wGrid, 6);
                grid.drawRoad(table.firstGrid);

            }
        });

        TextView gyuTxt = findViewById(R.id.gyu_shu);
        gyuTxt.setText(getContex().getString(R.string.table_number) + "  " + table.number + " -- " + table.round);
        TextView numTxt = findViewById(R.id.table_num);
        numTxt.setText(String.format(Locale.US,"%02d", table.groupID));


        clicked(R.id.table_grid,v->{
            WMActivity activity = (WMActivity) getContex();
            LobbySource source = LobbySource.getInstance();

            Kit.alert(activity,"gsgscc");
            source.tableLogin(table,ok->{
                if(ok){
                    Kit.alert(activity,"Cannot go to this table ok");
                    activity.toActivity(BacActivity.class);

                }else{
                    Kit.alert(activity,"Cannot go to this table ");
                }
            });


        });

    }

    @Override
    public void onRecycle() {

    }
}
