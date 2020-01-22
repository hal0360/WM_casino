package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.RoadItem;

public class TextGrid extends TableLayout {

    private TextView[][] viewGrid;
    public int width, height;
    private Context context;

    public TextGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public TextGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.TextGrid);
        int gridX, gridY;
        gridY = a.getInt(R.styleable.TextGrid_grid_y,0);
        gridX = a.getInt(R.styleable.TextGrid_grid_x, 0);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGrid(gridX, gridY);

    }

    public void drawRoad(List<RoadItem> items){
        if(items.size() == 0) return;
        int offset = items.get(0).x + 1 - width;
        if(offset < 0) offset = 0;
        int absX;
        for(RoadItem item: items){
            absX = item.x - offset;
            if (absX < 0) break;
            insertImage(absX, item.y, item );
        }
    }

    private void iniGrid(int x, int y){
        width = x;
        height = y;
        viewGrid = new TextView[x][y];
        TextView view;

        for(int i=0; i<y; i++){

            TableRow row = new TableRow(context);
            row.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
            row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            row.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.0f));

            for(int j=0; j<x; j++){
                view = new TextView(context);
                view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(view);
                viewGrid[j][i] = view;

            }
            this.addView(row);
        }
    }

    public View insertImage(int x, int y, RoadItem item){
        TextView word = viewGrid[x][y];
        word.setGravity(Gravity.CENTER);
        word.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 7f);
        word.setBackgroundResource(item.resID);
        word.setTextColor(item.color);
        if(item.strID == 0) word.setText(item.word);
        else word.setText(context.getString(item.strID));
        return viewGrid[x][y];
    }

    public void clear(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++)
                viewGrid[j][i].setBackgroundResource(0);
        }
    }


}
