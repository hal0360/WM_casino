package tw.com.lixin.wm_casino.holders;

import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Locale;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.CasinoGrid;

public class VerticalBacHolder  extends ItemHolder {

    private Table table;

    public VerticalBacHolder(Table table) {
        super(R.layout.bac_vertical_item);
        this.table = table;
    }

    @Override
    public void onBind() {



    }

    @Override
    public void onRecycle() {

    }
}