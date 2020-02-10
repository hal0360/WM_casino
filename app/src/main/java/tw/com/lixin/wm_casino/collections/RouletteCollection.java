package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.RouletteActivity;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.Table;

public class RouletteCollection  extends GameCollection {


    public RouletteCollection(Table table) {
        super( table);
    }

    public RouletteCollection(Table table, FragDialog f) {
        super(table,f);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return RouletteActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.mainRoad,(v,r)->{
            v.setTextSize(10);
            v.setTextColor(0xffffffff);
            if( (r > 0 && r < 11) || (r > 18 && r < 29) ){
                v.setText(r);
                if ( (r & 1) == 0 ) v.setTextImg(R.drawable.black_ball);
                else v.setTextImg(R.drawable.dark_red_ball);
            } else if( (r > 10 && r < 19) || (r > 28 && r < 37) ){
                v.setText(r);
                if ( (r & 1) == 0 ) v.setTextImg(R.drawable.dark_red_ball);
                else v.setTextImg(R.drawable.black_ball);
            }else if(r == Road.ZERO){
                v.setText("0");
                v.setTextImg(R.drawable.green_ball);
            }
        });

        count1.setText(getString(R.string.odd) + ":" + table.data.oddCount);
        count2.setText(getString(R.string.even) + ":" + table.data.evenCount);
        count3.setText(getString(R.string.oh) + ":" + table.data.zeroCount);
    }
}
