package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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


        Log.e("peoplePop", "created");


        GameSource source = GameSource.getInstance();
        pplTxt = dialog.findViewById(R.id.player_num_txt);
        pplTxt.setText("(" + source.pplOnline + ")");

        List<PeopleCollection> collections = new ArrayList<>();
        for (People people: source.peoples){
            collections.add(new PeopleCollection(people));
        }
        peopleCollections.add(collections);

        source.bindPeople(this);

    }

    @Override
    public void dialogClosed(FragDialog dialog) {
        Log.e("peoplePop", "closed");
        GameSource source = GameSource.getInstance();
        source.unbindPeople();
    }

    public void poepleUpdate(int pplOnline){
        pplTxt.setText("(" + pplOnline + ")");
        GameSource source = GameSource.getInstance();
        List<PeopleCollection> collections = new ArrayList<>();
        for (People people: source.peoples){
            collections.add(new PeopleCollection(people));
        }
        peopleCollections.add(collections);
    }


}