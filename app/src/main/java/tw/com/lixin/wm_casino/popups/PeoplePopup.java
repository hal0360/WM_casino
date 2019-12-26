package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.widget.TextView;

import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.PeopleCollection;
import tw.com.lixin.wm_casino.models.People;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

@SuppressLint("SetTextI18n")
public class PeoplePopup extends PopupFragment {

    private CollectionsView peopleCollections;
    private TextView pplTxt;

    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.people_popup);
        peopleCollections = dialog.findViewById(R.id.people_item_view);

        GameSource source = GameSource.getInstance();
        pplTxt = dialog.findViewById(R.id.player_num_txt);
        pplTxt.setText("(" + source.pplOnline + ")");
        for (People people: source.peoples){
            peopleCollections.add(new PeopleCollection(people));
        }
        source.bindPeople(this);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {
        GameSource source = GameSource.getInstance();
        source.unbindPeople();
    }

    public void peopleIn(People people, int pplOnline){
        peopleCollections.add(new PeopleCollection(people));
        pplTxt.setText("(" + pplOnline + ")");
    }

    public void peopleOut(int index, int pplOnline){
        peopleCollections.delete(index);
        pplTxt.setText("(" + pplOnline + ")");
    }

}