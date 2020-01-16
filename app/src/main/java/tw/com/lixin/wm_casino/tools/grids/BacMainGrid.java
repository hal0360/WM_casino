package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
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
import tw.com.lixin.wm_casino.tools.grids.CellView.BacRoadView;

public class BacMainGrid extends TableLayout {

    private BacRoadView[][] viewGrid;
    private int posX, posY;
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

        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);
        setBackgroundColor(Color.parseColor("#ffffff"));

        viewGrid = new BacRoadView[14][6];
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

    public void clearAskViews(){
        if(predictV == null) return;
        predictV.clearAnimation();
        predictV.setBackgroundResource(0);
    }

    public void askRoad(int ref) {
        clearAskViews();
        if (posY > 5) {
            predictV = viewGrid[posX+1][0];
        }else{
            predictV = viewGrid[posX][posY+1];
        }
        predictV.startAnimation(fadeAnime);
    }

    public void setRoad(int x, int y, int ref){
        viewGrid[x][y].setRoad(ref);
    }

    public void clear(){
        for(int i=0; i<6; i++){
            for(int j=0; j<14; j++)
                viewGrid[j][i].clear();
        }
    }

    public void drawRoad(List<Integer> big){
      posX = 0;
        posY = 0;
        for(int ref: big){
            if(posY > 5) {
                posY = 0;
                posX++;
            }
            viewGrid[posX][posY].setRoad(ref);
            posY++;
        }
        posY--;
    }
}