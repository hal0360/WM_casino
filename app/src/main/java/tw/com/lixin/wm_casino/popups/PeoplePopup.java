package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.widget.TextView;

import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.PeopleCollection;
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
        pplTxt.setText("(" + source.peopleOnline + ")");
        peopleCollections.add(source.peopleCollections);
        source.bindPeople(this);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    public void peopleIn(PeopleCollection collection, int pplOnline){
        peopleCollections.add(collection);
        pplTxt.setText("(" + pplOnline + ")");
    }

    public void peopleOut(int index, int pplOnline){
        peopleCollections.delete(index);
        pplTxt.setText("(" + pplOnline + ")");
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);

        GameSource source = GameSource.getInstance();
       // source.unbindPeple();
    }

   // public void addPeople(PeopleHo){

  //  }
}