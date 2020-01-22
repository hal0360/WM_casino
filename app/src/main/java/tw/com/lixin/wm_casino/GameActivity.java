package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.collections.BacCollection;
import tw.com.lixin.wm_casino.collections.DragonTigerCollection;
import tw.com.lixin.wm_casino.collections.RouletteCollection;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.RouletteTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.models.TigerDragonTable;
import tw.com.lixin.wm_casino.tools.ProfileBar;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class GameActivity extends RootActivity {

    private LobbySource source;
    private CollectionsView tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        source = LobbySource.getInstance();
        tableList = findViewById(R.id.table_list);
        List<Collection> collections = new ArrayList<>();
        ProfileBar bar = findViewById(R.id.pro_bar);

        if(source.curGameID == 101){
            bar.setTitle(getString(R.string.wmbaccarat));
            SparseArray<Table> tables = source.allTables.get(101);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new BacCollection((BacTable) table));
            }
        }else if(source.curGameID == 102){
            bar.setTitle("WM"+getString(R.string.dragon_tiger));
            SparseArray<Table> tables = source.allTables.get(102);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new DragonTigerCollection((TigerDragonTable) table));
            }
        }else if(source.curGameID == 103){
            bar.setTitle("WM"+getString(R.string.dragon_tiger));
            SparseArray<Table> tables = source.allTables.get(103);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                collections.add(new RouletteCollection((RouletteTable) table));
            }
        }else{
            alert("error!");
            toActivity(LobbyActivity.class);
        }
        tableList.add(collections);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()){
            alert("connection lost");
            toActivity(LoginActivity.class);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  tableList.clean();
    }

}
