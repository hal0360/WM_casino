package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.DragonTigerActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.Table;

public class DragonTigerCollection extends GameCollection {


    public DragonTigerCollection(Table table) {
        super(table);
    }

    public DragonTigerCollection(Table table, FragDialog f) {
        super(table,f);
    }


    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return DragonTigerActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {

        if(table.firstRoad == null) Log.e("hi leg","fantasy");

        textGrid.drawRoad(table.firstRoad, (v,r)->{
            if(r == 1){
                v.setTextImg(Road.Bank);
            }else if(r == 2){
                v.setTextImg(Road.Play);
            }else if(r == 6){
                v.setTextImg(Road.Play_E);
            }else if(r == 5){
                v.setTextImg(Road.Bank_E);
            }
        });
        count1.setText(getString(R.string.dragon_abb) + table.data.dragonCount);
        count2.setText(getString(R.string.tiger_abb) + table.data.tigerCount);
        count3.setText(getString(R.string.tie_abb) + table.data.tieCount);
    }
}
