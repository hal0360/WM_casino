package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.ItemRoad;


public class CasinoGrid extends TableLayout {

    private View[][] viewGrid;
    public int width, height;
    private Context context;

    public CasinoGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public CasinoGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CasinoGrid);
        int gridX, gridY;
        gridY = a.getInt(R.styleable.CasinoGrid_grid_y,0);
        gridX = a.getInt(R.styleable.CasinoGrid_grid_x, 0);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGrid(gridX, gridY);
    }

    public void drawRoad(ItemRoad road){
        int shift = road.maxX - width + 1 ;
        if (shift <= 0) shift = 0;
        for(int x = 0; x < width; x++){
            for(int y=0; y<6; y++) {
                viewGrid[x][y].setBackgroundResource(road.road[x + shift][y]);
            }
        }
    }

    public void clear(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++)
                viewGrid[j][i].setBackgroundResource(0);
        }
    }

    private void iniGrid(int x, int y){
        width = x;
        height = y;
        viewGrid = new View[x][y];
        View view;

        for(int i=0; i<y; i++){

            TableRow row = new TableRow(context);
            row.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
            row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f));

            for(int j=0; j<x; j++){
                view = new View(context);
                view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(view);
                viewGrid[j][i] = view;

            }
            this.addView(row);
        }
    }

}
