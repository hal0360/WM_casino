package tw.com.lixin.wm_casino.holders;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.tools.CasinoGrid;

public class BacHolder extends ItemHolder implements TableBridge {

    private BacTable table;
    private TextView countDown, dealing, waiting;
    private ConstraintLayout block;
    private CasinoGrid grid;
    private ImageView cardImg;


    public BacHolder(BacTable table) {
        super(R.layout.bac_item);
        this.table = table;
    }

    @Override
    public void onBind() {
        grid = findViewById(R.id.road_grid);
        block = findViewById(R.id.road_grid_block);
        ImageView dealerImg = findViewById(R.id.dealer_img);
        countDown = findViewById(R.id.countdown_txt);
        dealing = findViewById(R.id.dealing_txt);
        waiting = findViewById(R.id.waiting_txt);
        cardImg = findViewById(R.id.card_img);
        statusUpdate();
        table.bind(this);
        Activity act = (Activity) getContex();
        if(table.groupType == 5) setTextView(R.id.table_name_txt, act.getString(R.string.baccarat_fast) + table.groupID);
        else setTextView(R.id.table_name_txt, act.getString(R.string.baccarat) + table.groupID);
        int bankTol = table.bankCount + table.bankPairCount;
        int playTol = table.playCount + table.playPairCount;
        setTextView(R.id.win_rate_txt, act.getString(R.string.banker_abb) + ":" + bankTol + act.getString(R.string.player_abb) + ":" + playTol + act.getString(R.string.tie_abb) + ":" + table.tieCount);
        setTextView(R.id.dealername_txt, table.dealerName);

        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);

        grid.post(() -> {
            double dim = grid.getMeasuredHeight() / 6.0;
            int wGrid = (int) Math.round(grid.getMeasuredWidth()/dim);
            grid.setGrid(wGrid, 6);
            grid.drawRoad(table.firstGrid);

        });
    }

    @Override
    public void onRecycle() {
        table.unBind();
    }

    @Override
    public void statusUpdate() {
        if(table.cardStatus == 1){
            countDown.setVisibility(View.VISIBLE);
            dealing.setVisibility(View.INVISIBLE);
            waiting.setVisibility(View.INVISIBLE);
            block.setVisibility(View.INVISIBLE);
            countDown.setText(table.curTime + "");
        }else {
            if(table.cardStatus == 2){
                setTextView(R.id.status_txt, getContex().getString(R.string.dealing));
                cardImg.setImageResource(R.drawable.card_dealing);
                waiting.setVisibility(View.INVISIBLE);
                dealing.setVisibility(View.VISIBLE);
            }else{
                setTextView(R.id.status_txt, getContex().getString(R.string.waiting));
                cardImg.setImageResource(R.drawable.card_waiting);
                waiting.setVisibility(View.VISIBLE);
                dealing.setVisibility(View.INVISIBLE);
            }
            countDown.setVisibility(View.INVISIBLE);
            block.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void gridUpdate() {
        grid.drawRoad(table.firstGrid);
    }

    @Override
    public void betCountdown(int sec) {
        countDown.setText(sec+"");
    }
}
