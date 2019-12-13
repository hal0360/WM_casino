package tw.com.lixin.wm_casino.collections;

import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;

public class LimitCollection extends Collection {

    private String name, odds, max, min;

    public LimitCollection(String name, String odds, String max, String min) {
        super(R.layout.limit_collection);
        this.odds = odds;
        this.max = max;
        this.min = min;
        this.name = name;
    }
    @Override
    public void onBind(CollectionHolder holder) {
        TextView text = holder.findViewById(R.id.name_txt);
        text.setText(name);
        text = holder.findViewById(R.id.odds_txt);
        text.setText(odds);
        text = holder.findViewById(R.id.min_txt);
        text.setText(min);
        text = holder.findViewById(R.id.max_txt);
        text.setText(max);
    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
