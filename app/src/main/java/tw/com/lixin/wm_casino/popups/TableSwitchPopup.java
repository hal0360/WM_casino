package tw.com.lixin.wm_casino.popups;

import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.collections.GameCollection;
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
        switchChannel(dialog, R.id.se_die_game,108);
        switchChannel(dialog, R.id.fish_prawn_game,110);
        switchChannel(dialog, R.id.golden_flower_game,111);


        LobbySource source = LobbySource.getInstance();
        curSelect = tableSelects.get(source.curGameID);
        SparseArray<Table> tables = source.allTables.get(source.curGameID);
        List<GameCollection> collections = new ArrayList<>();
        for(int i = 0; i < tables.size(); i++) {
            collections.add(App.tableProvider.get(source.curGameID).exec(tables.valueAt(i),dialog));
        }
        tableList.add(collections);
    }

    @Override
    public void dialogClosed(FragDialog dialog) {
        for (Collection collection : tableList.getCollections()){
            GameCollection gameCollection = (GameCollection) collection;
            gameCollection.unbindTable();
        }
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
        for (Collection collection : tableList.getCollections()){
            GameCollection gameCollection = (GameCollection) collection;
            gameCollection.unbindTable();
        }
        List<GameCollection> collections = new ArrayList<>();
        SparseArray<Table> tables = source.allTables.get(gameId);
        for(int i = 0; i < tables.size(); i++) {
            collections.add(App.tableProvider.get(gameId).exec(tables.valueAt(i),dialog));
        }
        tableList.replace(collections);
    }
}

