package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.lixin.wm_casino.collections.GameCollection;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.ProfileBar;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class GameActivity extends WMActivity {

    private CollectionsView tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LobbySource source = LobbySource.getInstance();
        tableList = findViewById(R.id.table_list);
        List<GameCollection> collections = new ArrayList<>();
        ProfileBar bar = findViewById(R.id.pro_bar);

        bar.setTitle(getString(App.appNames.get(source.curGameID)));
        SparseArray<Table> tables = source.allTables.get(source.curGameID);
        for(int i = 0; i < tables.size(); i++) {
            collections.add(App.collectionProvider.get(source.curGameID).exec(tables.valueAt(i)));
        }
        tableList.add(collections);


    }

    public void freeCollection(){

        for (Collection collection : tableList.getCollections()){
            GameCollection gameCollection = (GameCollection) collection;
            gameCollection.unbindTable();
        }
    }

    @Override
    public void onBackPressed() {
        toActivity(LobbyActivity.class);
    }

}
