package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.CmdTxtView;
import tw.com.lixin.wm_casino.interfaces.CmdViewRes;
import tw.com.lixin.wm_casino.models.GridRoad;
import tw.com.lixin.wm_casino.models.ItemRoad;
import tw.com.lixin.wm_casino.models.RoadItem;


public class CasinoGrid extends TableLayout {

    private View[][] viewGrid;
    public int width, height;
    private Context context;
    private Animation fadeAnime;

    private View predictV;

    public CasinoGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public CasinoGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        fadeAnime = AnimationUtils.loadAnimation(context, R.anim.prediction_fade);
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

    public void drawRoad(List<RoadItem> items){
        if(items.size() == 0) return;
        int offset = items.get(0).x + 1 - width;
        if(offset < 0) offset = 0;
        int absX;
        for(RoadItem item: items){
            absX = item.x - offset;
            if (absX < 0) break;
            insertImage(absX, item.y, item.res);
        }
    }


    public void drawRoad(GridRoad road){
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
                insertImage(x,y,road.road[x + shift][y]);
            }
        }
    }

    public void clearAskViews(){
        if(predictV == null) return;
        predictV.clearAnimation();
        predictV.setBackgroundResource(0);
    }

    public void askRoad(int posX, int posY, int res) {
        clearAskViews();
        if (width > posX) {
            predictV = insertImage(posX, posY, res);
            predictV.startAnimation(fadeAnime);
        }
    }

    public View insertImage(int x, int y, int image_res){
        viewGrid[x][y].setBackgroundResource(image_res);
        return viewGrid[x][y];
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

    public void setGrid(int x, int y){
        this.removeAllViews();
        iniGrid(x,y);
    }
}
