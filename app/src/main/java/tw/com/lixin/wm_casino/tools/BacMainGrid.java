package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.R;

public class BacMainGrid extends TableLayout {

    private BacRoadView[][] viewGrid;
    public int width, height;
    private Context context;
    private Animation fadeAnime;

    private View predictV;

    public BacMainGrid(Context context)
    {
        super(context);
        this.context = context;
    }

    public BacMainGrid(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        fadeAnime = AnimationUtils.loadAnimation(context, R.anim.prediction_fade);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.BacMainGrid);
        int gridX, gridY;
        gridY = a.getInt(R.styleable.BacMainGrid_grid_y,1);
        gridX = a.getInt(R.styleable.BacMainGrid_grid_x, 1);
        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGrid(gridX, gridY);
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

    public View setRoad(int x, int y, int ref){
        viewGrid[x][y].setRoad(ref);
        return viewGrid[x][y];
    }

    public void clear(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++)
                viewGrid[j][i].setBackgroundResource(0);
        }
    }

    public void drawRoad(List<Integer> big){
        int x = 0;
        int y = 0;

        for(int ref: big){
            if(y > 5) {
                y = 0;
                x++;
            }
            viewGrid[x][y].setRoad(ref);
            y++;
        }

    }

    private void iniGrid(int x, int y){
        width = x;
        height = y;
        viewGrid = new BacRoadView[x][y];
        BacRoadView view;

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

    public void setGrid(int x, int y){
        this.removeAllViews();
        iniGrid(x,y);
    }


}