package tw.com.lixin.wm_casino.collections;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.models.Table;

public class FishPrawnCollection extends GameCollection {
    public FishPrawnCollection(Table table) {
        super(table);
    }

    public FishPrawnCollection(Table table, FragDialog blob) {
        super(table, blob);
    }

    @Override
    protected void started(CollectionHolder holder) {

    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return null;
    }

    @Override
    public void gridUpdate() {

    }
}
