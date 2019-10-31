package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.holders.BacHolder;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.gameComponents.ProfileBar;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;

public class GameActivity extends WMActivity {

    private LobbySource source;
    private ItemsView tableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        source = LobbySource.getInstance();
        tableList = findViewById(R.id.table_list);
        List<BacHolder> holders = new ArrayList<>();
        ProfileBar bar = findViewById(R.id.pro_bar);


        if(source.curGameID == 101){
            bar.setTitile(getString(R.string.wmbaccarat));
            SparseArray<Table> tables = source.allTables.get(101);
            for(int i = 0; i < tables.size(); i++) {
                Table table = tables.valueAt(i);
                holders.add(new BacHolder((BacTable) table));
            }
        }else if(source.curGameID == 102){

        }else{
            alert("error!");
            toActivity(LobbyActivity.class);
        }
        tableList.add(holders);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()){
            alert("connection lost");
            unloading();
            toActivity(LoginActivity.class);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tableList.clean();
    }


}
