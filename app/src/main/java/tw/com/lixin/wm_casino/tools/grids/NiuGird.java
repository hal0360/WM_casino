package tw.com.lixin.wm_casino.tools.grids;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import androidx.core.content.ContextCompat;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.grids.CellView.WordView;

public class NiuGird extends TableLayout {

    private WordView[][] viewGrid;
    public int width, height;
    private Context context;
    private   int scale;

    public NiuGird(Context context)
    {
        super(context);
        this.context = context;
    }

    public NiuGird(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.NiuGird);
        int gridX, gridY;
        gridY = 4;
        gridX = a.getInt(R.styleable.NiuGird_grid_x, 0);

        scale = a.getInt(R.styleable.NiuGird_scale, 1);

        setDividerDrawable(ContextCompat.getDrawable(context, R.drawable.table_divider));
        setShowDividers(TableRow.SHOW_DIVIDER_MIDDLE);

        setBackgroundColor(Color.parseColor("#ffffff"));
        a.recycle();
        iniGrid(gridX, gridY);
    }

    public void drawRoad(List<Integer> road){
        int shift = road.size() - width;
        if (shift<0) shift = 0;
        clear();
        int index = 0;
        while (shift<road.size()) {
            List<int[]> res = App.getNiuniu(road.get(shift));
            viewGrid[index][0].setTextColor(0xffffffff);
            viewGrid[index][1].setTextColor(0xffffffff);
            viewGrid[index][2].setTextColor(0xffffffff);
            viewGrid[index][3].setTextColor(0xffffffff);
            if(scale == 1) {
                viewGrid[index][0].setTextSize(12);
                viewGrid[index][1].setTextSize(12);
                viewGrid[index][2].setTextSize(12);
                viewGrid[index][3].setTextSize(12);
            }else if(scale == 3){
                viewGrid[index][0].setTextSize(9);
                viewGrid[index][1].setTextSize(9);
                viewGrid[index][2].setTextSize(9);
                viewGrid[index][3].setTextSize(9);
            }

            viewGrid[index][1].setText(res.get(0)[0]);
            if(res.get(0)[1] == 0){
                viewGrid[index][1].setTextImg(R.drawable.dark_grey_ball);
            }else {
                viewGrid[index][1].setTextImg(R.drawable.dark_blue_ball);
            }
            viewGrid[index][2].setText(res.get(1)[0]);
            if(res.get(1)[1] == 0){
                viewGrid[index][2].setTextImg(R.drawable.dark_grey_ball);
            }else {
                viewGrid[index][2].setTextImg(R.drawable.sky_blue_ball);
            }
            viewGrid[index][3].setText(res.get(2)[0]);
            if(res.get(2)[1] == 0){
                viewGrid[index][3].setTextImg(R.drawable.dark_grey_ball);
            }else {
                viewGrid[index][3].setTextImg(R.drawable.till_blue_ball);
            }
            viewGrid[index][0].setText(res.get(3)[0]);
            viewGrid[index][0].setTextImg(R.drawable.dark_grey_ball);

            shift++;
            index++;
        }
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

            if(scale == 1) view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.5f));
            else  if(scale == 3) view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 2.7f));
            else  view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 2.3f));

            view.setTextColor(0xffffffff);
            view.setTextSize(9);
            if(i == 0){
                view.setText(context.getString(R.string.banker));
                view.setBackgroundColor(0xff808080);

            }else if(i == 1){
                view.setText(context.getString(R.string.player1));
                view.setBackgroundColor(0xff00008b);
            }else if(i == 2){
                view.setText(context.getString(R.string.player2));
                view.setBackgroundColor(0xff00bfff);
            }else {
                view.setText(context.getString(R.string.player3));
                view.setBackgroundColor(0xff20b2aa);
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