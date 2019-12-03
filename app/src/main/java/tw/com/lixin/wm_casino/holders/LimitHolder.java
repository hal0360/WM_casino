package tw.com.lixin.wm_casino.holders;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;

public class LimitHolder extends ItemHolder {

    private String name, odds, max, min;

    public LimitHolder(String name, String odds, String max, String min) {
        super(R.layout.limit_item);
        this.odds = odds;
        this.max = max;
        this.min = min;
        this.name = name;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind() {
        TextView text = findViewById(R.id.name_txt);
        text.setText(name);
        text = findViewById(R.id.odds_txt);
        text.setText(odds);
        text = findViewById(R.id.min_txt);
        text.setText(min);
        text = findViewById(R.id.max_txt);
        text.setText(max);
    }

    @Override
    public void onRecycle() {

    }
}
