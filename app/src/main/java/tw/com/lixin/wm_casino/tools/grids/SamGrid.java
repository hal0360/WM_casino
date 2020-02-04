package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.grids.CellView.WordView;

public class SamGrid extends TableLayout {

    private WordView[][] viewGrid;
    public int width, height;
    private Context context;

    public SamGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public SamGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SamGrid);
        int gridX, gridY;
        gridY = 3;
        gridX = a.getInt(R.styleable.SamGrid_grid_x, 0);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGrid(gridX, gridY);
    }

    public void clear(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++) viewGrid[j][i].clear();
        }
    }

    private void iniGrid(int x, int y){
        width = x;
        height = y;
        viewGrid = new WordView[x][y];
        WordView view;

        for(int i=0; i<y; i++){
            TableRow row = new TableRow(context);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f));

            row.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider_white));
            row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            view = new WordView(context);
            view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 2.0f));
            view.setTextColor(0xffffffff);
            view.setTextSize(9);
            view.setBackgroundColor(0xff696969);
            if(i == 0){
                view.setText(context.getString(R.string.player1));
            }else if(i == 1){
                view.setText(context.getString(R.string.player2));
            }else {
                view.setText(context.getString(R.string.player3));
            }
            row.addView(view);

            for(int j=0; j<x; j++){
                view = new WordView(context);
                view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(view);
                viewGrid[j][i] = view;
            }
            this.addView(row);
        }
    }

}