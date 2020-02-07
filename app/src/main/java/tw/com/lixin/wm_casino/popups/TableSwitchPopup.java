package tw.com.lixin.wm_casino.popups;

import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.LobbyActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.BacCollection;
import tw.com.lixin.wm_casino.collections.DragonTigerCollection;
import tw.com.lixin.wm_casino.collections.NiuCollection;
import tw.com.lixin.wm_casino.collections.RouletteCollection;
import tw.com.lixin.wm_casino.collections.SamgongCollection;
import tw.com.lixin.wm_casino.collections.SicBoCollection;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.TableSwitchSelect;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class TableSwitchPopup extends PopupFragment {

    private SparseArray<TableSwitchSelect> tableSelects = new SparseArray<>();
    private TableSwitchSelect curSelect;
    private CollectionsView tableList;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.table_switch_popup);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        tableList = dialog.findViewById(R.id.table_list);

        switchChannel(dialog, R.id.baccarat_game,101);
        switchChannel(dialog, R.id.dragon_tiger_game,102);
        switchChannel(dialog, R.id.roulette_game,103);
        switchChannel(dialog, R.id.sic_bo_game,104);
        switchChannel(dialog, R.id.niuniu_game,105);
        switchChannel(dialog, R.id.samgong_game,106);
        switchChannel(dialog, R.id.fantan_game,107);

        getGame(101,dialog);

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
            getGame(gameId,dialog);
        });
        tableSelects.put(gameId, tView);
    }

    private void getGame(int gameId, FragDialog dialog){
        LobbySource source = LobbySource.getInstance();
        List<Collection> collections = new ArrayList<>();
        if(gameId == 101){
            SparseArray<Table> tables = source.allTables.get(101);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new BacCollection(table,dialog));
            }
        }else if(gameId == 102){
            SparseArray<Table> tables = source.allTables.get(102);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new DragonTigerCollection(table,dialog));
            }
        }else if(gameId == 103){
            SparseArray<Table> tables = source.allTables.get(103);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new RouletteCollection(table,dialog));
            }
        }else if(gameId == 104){
            SparseArray<Table> tables = source.allTables.get(104);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new SicBoCollection(table,dialog));
            }
        }else if(gameId == 105){
            SparseArray<Table> tables = source.allTables.get(105);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new NiuCollection(table,dialog));
            }
        }else if(gameId == 106){
            SparseArray<Table> tables = source.allTables.get(106);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new SamgongCollection(table,dialog));
            }
        }
        tableList.replace(collections);
    }
}

