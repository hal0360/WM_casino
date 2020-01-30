package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.CmdTxtView;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.tools.grids.CellView.WordView;

public class TextGrid extends TableLayout {

    private AppCompatImageView[][] viewGrid;
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

    public void drawMainRoad(List<Integer> road, CmdTxtView cmd){
        if(road.size() == 0) return;
        int quotient = road.size() / height;
        int remainder = road.size() % height;
        if(remainder > 0) quotient++;
        int shift = quotient - width;
        if (shift < 0) shift = 0;
        List<Integer>  newRoad =  road.subList(shift*6, road.size());

        int posX = 0;
        int posY = 0;
        for(int ref: newRoad){
            if(posY > 5) {
                posY = 0;
                posX++;
            }
            cmd.exec(viewGrid[posX][posY], ref);
            posY++;
        }
    }

    public void drawRoad(ItemRoad road, CmdTxtView cmd){
        int shift = road.posX - width + 1 ;
        int wLim;
        if (shift <= 0){
            shift = 0;
            wLim = road.posX + 1;
        }else{
            wLim = width;
        }
        for(int x = 0; x < wLim; x++){
            for(int y=0; y<6; y++){
                cmd.exec(viewGrid[x][y], road.road[x + shift][y]);
            }
        }
    }

    private void iniGrid(int x, int y){
        width = x;
        height = y;
        viewGrid = new AppCompatImageView[x][y];
        AppCompatImageView view;

        for(int i=0; i<y; i++){

            TableRow row = new TableRow(context);
            row.setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
            row.setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
            row.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1.0f));
            for(int j=0; j<x; j++){
                view = new AppCompatImageView(context);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(view);
                viewGrid[j][i] = view;
            }
            this.addView(row);
        }
    }

    public void clear(){
        for(int i=0; i<height; i++){
           // for(int j=0; j<width; j++) viewGrid[j][i].clear();
        }
    }

}
