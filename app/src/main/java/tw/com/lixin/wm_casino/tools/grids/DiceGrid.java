package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.util.List;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.grids.CellView.DiceView;

public class DiceGrid extends LinearLayout {

    private DiceView[] viewGrid;
    public int width;

    public DiceGrid(Context context)
    {
        super(context);
    }

    public DiceGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DiceGrid);
        width = a.getInt(R.styleable.TextGrid_grid_x, 0);
        viewGrid = new DiceView[width];
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundColor(0xff000000);

        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider_big));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);

        for(int i=0; i < width; i++){
            DiceView row = new DiceView(context);
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
