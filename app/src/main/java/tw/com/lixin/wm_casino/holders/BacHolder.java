package tw.com.lixin.wm_casino.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.ItemHolder;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.BacActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.tools.CasinoGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacHolder extends ItemHolder implements TableBridge{

    private BacTable table;
    private TextView countDown;
    private ConstraintLayout block;
    private ImageView cardImg;
    private CasinoGrid firstGrid;

    public BacHolder(BacTable table) {
        super(R.layout.bac_item);
        this.table = table;
    }

    @Override
    public void onBind() {
        firstGrid = findViewById(R.id.first_grid);
        block = findViewById(R.id.road_grid_block);
        cardImg = findViewById(R.id.card_img);
        TextView bidMe = findViewById(R.id.bidme_txt);
        countDown = findViewById(R.id.countdown_txt);
        ImageView dealerImg = findViewById(R.id.dealer_img);
        setTextView(R.id.dealername_txt, table.dealerName);
        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
        else dealerImg.setImageResource(R.drawable.hamster);
        if(table.groupType == 5) setTextView(R.id.table_name_txt, getContex().getString(R.string.baccarat_fast) + table.groupID);
        else setTextView(R.id.table_name_txt, getContex().getString(R.string.baccarat) + table.groupID);
        if(table.groupID == 3) bidMe.setVisibility(View.VISIBLE);
        else bidMe.setVisibility(View.INVISIBLE);
        gridUpdate();
        statusUpdate();
        table.bind(this);

        findViewById(R.id.root).setOnClickListener(v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table);
        });
    }

    @Override
    public void onRecycle() {
        table.unBind();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void statusUpdate() {
        if(table.cardStatus == 1){
            countDown.setVisibility(View.VISIBLE);
            countDown.setText(table.curTime + "");
            block.setVisibility(View.INVISIBLE);
        }else {
            if(table.cardStatus == 2){
                setTextView(R.id.status_txt, getContex().getString(R.string.dealing));
                cardImg.setImageResource(R.drawable.card_dealing);
            }else{
                setTextView(R.id.status_txt, getContex().getString(R.string.waiting));
                cardImg.setImageResource(R.drawable.card_waiting);
            }
            countDown.setVisibility(View.INVISIBLE);
            block.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstGrid);
        setTextView(R.id.bank_rate, table.bankCount + "");
        setTextView(R.id.play_rate, table.playCount + "");
        setTextView(R.id.tie_rate, table.tieCount + "");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void betCountdown(int sec) {
        countDown.setText(sec+"");
    }

    @Override
    public void tableLogin(TableData.Data data) {
        if(data.bOk){
            BacActivity.bacStarted(data);
            RootActivity act = (RootActivity) getContex();
            act.pushActivity(BacActivity.class);
        }
        else alert("Cannot login to this table");
    }

}
