package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.GameActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.TextGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public abstract class GameCollection extends Collection implements TableBridge {

    private TextView countDown, statusTxt, tableName, dealTxt;
    private ConstraintLayout block;
    private ImageView cardImg;
    private RootActivity activity;
    protected Table table;
     TextView count1, count2, count3, count4;
     TextGrid textGrid;
     private boolean snall = false;

    GameCollection(Table table) {
        super(R.layout.table_collection);
        this.table = table;
    }

    GameCollection(Table table, FragDialog blob) {
        super(R.layout.game_collection);
        this.table = table;
        snall = true;
        activity = blob.getRootActivity();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {

        textGrid = holder.findViewById(R.id.text_grid);
        dealTxt = holder.findViewById(R.id.deal_txt);
        block = holder.findViewById(R.id.road_grid_block);
        cardImg = holder.findViewById(R.id.card_img);
        statusTxt = holder.findViewById(R.id.status_txt);
        countDown = holder.findViewById(R.id.countdown_txt);
        count1 = holder.findViewById(R.id.count1);
        count2 = holder.findViewById(R.id.count2);
        count3 = holder.findViewById(R.id.count3);
        count4 = holder.findViewById(R.id.count4);
        count4.setText("");

        if(!snall){
            activity = (RootActivity) holder.getContex();
            ImageView dealerImg = holder.findViewById(R.id.dealer_img);
            TextView dealername = holder.findViewById(R.id.dealername_txt);
            dealername.setText(table.dealerName);
            if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
            else dealerImg.setImageResource(R.drawable.hamster);
        }

        holder.clicked(R.id.root,v->{
            GameSource source = GameSource.getInstance();
            if(snall){
                source.tableLogin(table,data -> activity.toActivity(toGameActicity()), activity::alert);
            }else {
                GameActivity gameActivity = (GameActivity) activity;
                gameActivity.freeCollection();
                source.tableLogin(table,data -> activity.toActivity(toGameActicity()), activity::alert);
            }
        });
        tableName = holder.findViewById(R.id.table_name_txt);
        setTableName(getString(App.appNames.get(table.gameID)) + table.groupID);
        stageUpdate();
        table.bind(this);
        started(holder);
        gridUpdate();
    }

    public void unbindTable(){
        table.unBind();
    }

    protected abstract void started(CollectionHolder holder);

    protected abstract Class<? extends AppCompatActivity> toGameActicity();

    public String getString(int rid){
        return activity.getString(rid);
    }

    private void setTableName(String name){
        tableName.setText(name);
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
            dealTxt.setVisibility(View.INVISIBLE);
            countDown.setText(table.curTime + "");
            block.setVisibility(View.INVISIBLE);
        }else {
            if(table.stage == 2){
                statusTxt.setText(activity.getString(R.string.dealing));
                cardImg.setImageResource(R.drawable.card_dealing);
            }else if(table.stage == 0){
                statusTxt.setText(activity.getString(R.string.waiting));
                cardImg.setImageResource(R.drawable.card_waiting);
            }
            countDown.setVisibility(View.INVISIBLE);
            dealTxt.setVisibility(View.VISIBLE);
            block.setVisibility(View.VISIBLE);
        }

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