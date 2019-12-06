package tw.com.lixin.wm_casino.popups;

import android.util.SparseArray;
import android.view.ViewGroup;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.TableSwitchSelect;

public class TableSwitchPopup extends PopupFragment {

    private SparseArray<TableSwitchSelect> tableSelects = new SparseArray<>();
    private TableSwitchSelect curSelect;


    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.table_switch_popup);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        switchChannel(dialog, R.id.baccarat_game,101);
        switchChannel(dialog, R.id.dragon_tiger_game,102);
        switchChannel(dialog, R.id.roulette_game,103);
        switchChannel(dialog, R.id.sic_bo_game,104);
        switchChannel(dialog, R.id.niuniu_game,105);
        switchChannel(dialog, R.id.samgong_game,106);
        switchChannel(dialog, R.id.fantan_game,107);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

    private void switchChannel(FragDialog dialog, int rid, int gameId){
        TableSwitchSelect tView = dialog.findViewById(rid);
        tView.setOnClickListener(v->{
            if(curSelect != null) curSelect.unSelect();
            curSelect = tView;
            curSelect.select();
            getGame(gameId);
        });
        tableSelects.put(gameId, tView);
    }

    private void getGame(int gameId){
        Kit.alert(getContext(),""+gameId);
    }
}

