package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.Table;

public abstract class GameCollection extends Collection implements TableBridge {


    private TextView countDown, statusTxt, tableName;
    private ConstraintLayout block;
    private ImageView cardImg;
    protected RootActivity activity;
    private Table table;

    GameCollection(int res_id, Table table) {
        super(res_id);
        this.table = table;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        activity = (RootActivity) holder.getContex();
        block = holder.findViewById(R.id.road_grid_block);
        cardImg = holder.findViewById(R.id.card_img);
        statusTxt = holder.findViewById(R.id.status_txt);
        countDown = holder.findViewById(R.id.countdown_txt);
        ImageView dealerImg = holder.findViewById(R.id.dealer_img);
        TextView dealername = holder.findViewById(R.id.dealername_txt);
        dealername.setText(table.dealerName);
        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
        else dealerImg.setImageResource(R.drawable.hamster);
        tableName = holder.findViewById(R.id.table_name_txt);

        stageUpdate();
        table.bind(this);

    }


    void setTableName(String name){
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
    public void betCountdown(int sec) {
        countDown.setText(sec+"");
    }

    @Override
    public void tableUpdate() {

    }

}