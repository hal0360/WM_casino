package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.tools.grids.CellView.BacRoadView;

public class BacMainGrid extends TableLayout {

    private BacRoadView[][] viewGrid;
    public int width, height;


    public BacMainGrid(Context context)
    {
        super(context);
    }

    public BacMainGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));

        viewGrid = new BacRoadView[14][6];
        BacRoadView view;
        width = 14;
        height = 6;

        for(int i=0; i<6; i++){

            TableRow row = new TableRow(context);
            row.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
            row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f));

            for(int j=0; j<14; j++){
                view = new BacRoadView(context);
                view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(view);
                viewGrid[j][i] = view;
            }
            this.addView(row);
        }

    }

    public void drawRoad(ItemRoad road){
        for(int x = 0; x < width; x++){
            for(int y=0; y<6; y++) {
                viewGrid[x][y].clear();
                viewGrid[x][y].setRoad(road.road[x][y]);
            }
        }
    }


    public void clear(){
        for(int i=0; i<6; i++){
            for(int j=0; j<14; j++)
                viewGrid[j][i].clear();
        }
    }

}