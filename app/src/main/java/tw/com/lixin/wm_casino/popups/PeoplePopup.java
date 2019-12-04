package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;

public class PeoplePopup extends PopupFragment {

    private ItemsView peopleView;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.people_popup);

        peopleView = dialog.findViewById(R.id.people_item_view);

    }

   // public void addPeople(PeopleHo){

  //  }
}