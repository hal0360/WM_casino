package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.BaccaratActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.tools.grids.CasinoGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacCollection extends Collection implements TableBridge {

    private BacTable table;
    private TextView countDown, statusTxt, bankRate, playRate, tieRate;
    private ConstraintLayout block;
    private ImageView cardImg;
    private CasinoGrid firstGrid;
    private RootActivity activity;

    public BacCollection(BacTable table) {
        super(R.layout.bac_collection);
        this.table = table;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        activity = (RootActivity) holder.getContex();
        firstGrid = holder.findViewById(R.id.first_grid);
        block = holder.findViewById(R.id.road_grid_block);
        bankRate = holder.findViewById(R.id.bank_rate);
        playRate = holder.findViewById(R.id.play_rate);
        tieRate = holder.findViewById(R.id.tie_rate);
        cardImg = holder.findViewById(R.id.card_img);
        statusTxt = holder.findViewById(R.id.status_txt);
        TextView bidMe = holder.findViewById(R.id.bidme_txt);
        countDown = holder.findViewById(R.id.countdown_txt);
        ImageView dealerImg = holder.findViewById(R.id.dealer_img);
        TextView dealername = holder.findViewById(R.id.dealername_txt);
        dealername.setText(table.dealerName);
        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
        else dealerImg.setImageResource(R.drawable.hamster);
        TextView tablename = holder.findViewById(R.id.table_name_txt);
        if(table.groupType == 5) tablename.setText(activity.getString(R.string.baccarat_fast) + table.groupID);
        else tablename.setText(activity.getString(R.string.baccarat) + table.groupID);
        if(table.groupID == 3) bidMe.setVisibility(View.VISIBLE);
        else bidMe.setVisibility(View.INVISIBLE);
        gridUpdate();
        stageUpdate();
        table.bind(this);

        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            source.tableLogin(table,data -> activity.pushActivity(BaccaratActivity.class), activity::alert);
        });
    }

    @Override
    public void onRecycle(CollectionHolder holder) {
        table.unBind();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void stageUpdate() {
        if(table.stage == 1){
            countDown.setVisibility(View.VISIBLE);
            countDown.setText(table.curTime + "");
            block.setVisibility(View.INVISIBLE);
        }else {
            if(table.stage == 2){
                statusTxt.setText(activity.getString(R.string.dealing));
                cardImg.setImageResource(R.drawable.card_dealing);
            }else{
                statusTxt.setText(activity.getString(R.string.waiting));
                cardImg.setImageResource(R.drawable.card_waiting);
            }
            countDown.setVisibility(View.INVISIBLE);
            block.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        firstGrid.drawRoad(table.firstGrid);
        bankRate.setText(table.bankCount + "");
        playRate.setText(table.playCount + "");
        tieRate.setText(table.tieCount + "");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void betCountdown(int sec) {
        countDown.setText(sec+"");
    }

    @Override
    public void tableUpdate() {

    }

}
