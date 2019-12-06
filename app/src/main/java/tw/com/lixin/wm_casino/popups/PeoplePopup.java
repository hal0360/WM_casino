package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.widget.TextView;

import java.util.List;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.holders.PeopleHolder;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

@SuppressLint("SetTextI18n")
public class PeoplePopup extends PopupFragment {

    private ItemsView peopleView;
    private TextView pplTxt;

    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.people_popup);
        peopleView = dialog.findViewById(R.id.people_item_view);

        GameSource source = GameSource.getInstance();
        pplTxt = dialog.findViewById(R.id.player_num_txt);
        pplTxt.setText("(" + source.peopleOnline + ")");
        peopleView.add(source.peopleHolders);
        source.bindPeople(this);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    public void peopleIn(PeopleHolder holder, int pplOnline){
        peopleView.add(holder);
        pplTxt.setText("(" + pplOnline + ")");
    }

    public void peopleOut(int index, int pplOnline){
        peopleView.delete(index);
        pplTxt.setText("(" + pplOnline + ")");
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);

        GameSource source = GameSource.getInstance();
        source.unbindPeple();
    }

   // public void addPeople(PeopleHo){

  //  }
}