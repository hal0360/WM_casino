package tw.com.lixin.wm_casino.collections;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.AndarBaharActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;
import tw.com.lixin.wm_casino.models.Table;

public class AndaCollection extends GameCollection{
    public AndaCollection(Table table) {
        super(table);
    }

    public AndaCollection(Table table, FragDialog blob) {
        super(table, blob);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return AndarBaharActivity.class;
    }

    @Override
    public void gridUpdate() {
        textGrid.drawRoad(table.firstRoad, (v,r)->{
            if(r == 1){
                v.setTextImg(Road.Play);
            }else if(r == 2){
                v.setTextImg(Road.Bank);
            }
        });
        count1.setText("安達: " + table.data.andarCount);
        count2.setText("巴哈: "+ table.data.baharCount);
        count3.setVisibility(View.INVISIBLE);
    }
}
