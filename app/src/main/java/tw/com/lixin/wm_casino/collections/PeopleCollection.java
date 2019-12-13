package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;

public class PeopleCollection extends Collection {
    private String name;
    private int winRate, memberID;

    public PeopleCollection(int memberID, String name, int winRate) {
        super(R.layout.people_collection);

        this.memberID = memberID;
        this.winRate = winRate;
        this.name = name;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        TextView text = holder.findViewById(R.id.name_txt);
        text.setText(name);
        text = holder.findViewById(R.id.win_rate_txt);
        text.setText(winRate+"%");
    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
