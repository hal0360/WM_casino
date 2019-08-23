package tw.com.lixin.wm_casino;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.holders.BacHolder;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.websocketSource.BacSource;

public class GameActivity extends WMActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac);

        BacSource source = BacSource.getInstance();
        ItemsView tableList = findViewById(R.id.table_list);
        List<BacHolder> holders = new ArrayList<>();

        for(Table table: source.tables){
            holders.add(new BacHolder((BacTable) table));
        }
        tableList.add(holders);
    }


}
