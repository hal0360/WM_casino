package tw.com.lixin.wm_casino.popups;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.TableSwitchSelect;

public class TableSwitchPopup extends Popup {

    private SparseArray<TableSwitchSelect> tableSelects = new SparseArray<>();
    private TableSwitchSelect curSelect;

    private Context context;

    public TableSwitchPopup(Context context) {
        super(context, R.layout.table_switch_popup);



        this.context = context;

        switchChannel(R.id.baccarat_game,101);
        switchChannel(R.id.dragon_tiger_game,102);
        switchChannel(R.id.roulette_game,103);
        switchChannel(R.id.sic_bo_game,104);
        switchChannel(R.id.niuniu_game,105);
        switchChannel(R.id.samgong_game,106);
        switchChannel(R.id.fantan_game,107);
    }

    private void switchChannel(int rid, int gameId){
        TableSwitchSelect tView = findViewById(rid);
        clicked(tView, v->{
             if(curSelect != null) curSelect.unSelect();
             curSelect = tView;
             curSelect.select();
             getGame(gameId);
        });
        tableSelects.put(gameId, tView);
    }

    private void getGame(int gameId){
        Kit.alert(context,""+gameId);
    }
}

