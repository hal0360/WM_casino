package tw.com.lixin.wm_casino.collections;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.FishPrawnActivity;
import tw.com.lixin.wm_casino.GoldenFlowerActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.Table;

public class GoldenFlowerCollection extends GameCollection {
    public GoldenFlowerCollection(Table table) {
        super(table);
    }

    public GoldenFlowerCollection(Table table, FragDialog blob) {
        super(table, blob);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return GoldenFlowerActivity.class;
    }

    @Override
    public void gridUpdate() {


        textGrid.drawRoad(table.firstRoad, (v,r)->{
            if(r == 1){
                v.setTextImg(Road.Bank);
            }else if(r == 2){
                v.setTextImg(Road.Play);
            }
        });
        count1.setText(getString(R.string.dragon_abb) + table.data.dragonCount);
        count2.setText(getString(R.string.tiger_abb) + table.data.phoenixCount);
        count3.setText(getString(R.string.tie_abb) + table.data.tieCount);

    }
}
