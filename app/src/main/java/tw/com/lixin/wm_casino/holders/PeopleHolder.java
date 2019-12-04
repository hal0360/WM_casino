package tw.com.lixin.wm_casino.holders;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;

public class PeopleHolder extends ItemHolder {

    private String name;
    private int winRate;

    public PeopleHolder(String name, int winRate) {
        super(R.layout.people_item);

        this.winRate = winRate;
        this.name = name;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind() {
        TextView text = findViewById(R.id.name_txt);
        text.setText(name);
        text = findViewById(R.id.win_rate_txt);
        text.setText(winRate+"%");
    }

    @Override
    public void onRecycle() {

    }
}