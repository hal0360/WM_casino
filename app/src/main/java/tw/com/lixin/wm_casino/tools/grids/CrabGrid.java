package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.core.content.ContextCompat;

import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.grids.CellView.CrabView;

public class CrabGrid extends LinearLayout {

    private CrabView[] viewGrid;
    public int width;

    public CrabGrid(Context context)
    {
        super(context);
    }

    public CrabGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CrabGrid);
        width = a.getInt(R.styleable.CrabGrid_grid_x, 0);
        viewGrid = new CrabView[width];
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundColor(0xff000000);

        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider_big));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);

        for(int i=0; i < width; i++){
            CrabView row = new CrabView(context);
            row.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
            viewGrid[i] = row;
            this.addView(row);
        }
        a.recycle();
    }

    public void drawRoad(List<Integer> road){
        int shift = road.size() - width;
        if (shift<0) shift = 0;
        clear();
        int index = 0;
        while (shift<road.size()) {
            viewGrid[index].setRoad(road.get(shift));
            shift++;
            index++;
        }
    }


    public void clear(){
        for(int i=0; i<width; i++){
            viewGrid[i].clear();
        }
    }

}
