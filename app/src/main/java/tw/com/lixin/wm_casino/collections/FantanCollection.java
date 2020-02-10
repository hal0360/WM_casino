package tw.com.lixin.wm_casino.collections;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
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
        return null;
    }

    @Override
    public void gridUpdate() {

    }
}
