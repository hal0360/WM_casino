package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.People;

public class PeopleCollection extends Collection {
   private People people;

    public PeopleCollection(People people) {
        super(R.layout.people_collection);
        this.people = people;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        TextView text = holder.findViewById(R.id.name_txt);
        text.setText(people.name);
        text = holder.findViewById(R.id.win_rate_txt);
        text.setText(people.winRate+"%");
    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
