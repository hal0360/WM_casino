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
import tw.com.lixin.wm_casino.interfaces.CmdViewRes;
import tw.com.lixin.wm_casino.models.ItemRoad;

public class CasinoDoubleGrid  extends TableLayout {

    private View[][] viewGrid;
    public int width, height;
    private Context context;

    public CasinoDoubleGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public CasinoDoubleGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CasinoDoubleGrid);
        int gridX, gridY;
        gridY = a.getInt(R.styleable.CasinoDoubleGrid_grid_y,0);
        gridX = a.getInt(R.styleable.CasinoDoubleGrid_grid_x, 0);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGridDouble(gridX, gridY);
    }

    public void drawRoad(ItemRoad road, CmdViewRes cmd){
        int shift = road.maxX - width + 1 ;
        if (shift <= 0) shift = 0;
        for(int x = 0; x < width; x++){
            for(int y=0; y<6; y++) {
                viewGrid[x][y].setBackgroundResource(0);
                cmd.exec(viewGrid[x][y], road.road[x + shift][y]);
            }
        }
    }

    public void clear(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++)
                viewGrid[j][i].setBackgroundResource(0);
        }
    }

    private void iniGridDouble(int x, int y){
        width = x*2;
        height = y*2;
        viewGrid = new View[width][height];
        View view;

        for(int i=0; i<y; i++){

            TableRow tr_head = new TableRow(context);
            tr_head.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
            tr_head.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            tr_head.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f));

            for(int j=0; j<x; j++){

                TableLayout tableLayout = new TableLayout(context);
                tableLayout.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                for(int a=0; a<2; a++){
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT, 1.0f));
                    for(int b=0; b<2; b++){
                        view = new View(context);
                        view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                        row.addView(view);
                        viewGrid[2*j+b][2*i+a] = view;
                    }
                    tableLayout.addView(row);
                }
                tr_head.addView(tableLayout);

            }
            this.addView(tr_head);
        }
    }

}
