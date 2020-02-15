package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.FantanActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;

public class FantanCollection extends GameCollection {
    public FantanCollection(Table table) {
        super(table);
    }

    public FantanCollection(Table table, FragDialog blob) {
        super(table, blob);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return FantanActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.firstRoad,(v,r)->{
            v.setTextSize(11);
            v.setTextColor(0xffffffff);
            v.setText(r);
            if( r == 1){
                v.setTextImg(R.drawable.grey_ball);
            } else if( r == 2){
                v.setTextImg(R.drawable.darkgreen_ball);
            }else if(r == 3){
                v.setTextImg(R.drawable.golden_ball);
            }else if(r == 4){
                v.setTextImg(R.drawable.brick_red_ball);
            }
        });

        count1.setText(1 + ":" + table.data.number1Count);
        count2.setText(2 + ":" + table.data.number2Count);
        count3.setText(3 + ":" + table.data.number3Count);
    }
}
