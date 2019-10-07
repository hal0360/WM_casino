package tw.com.lixin.wm_casino.holders;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.ItemHolder;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.BacActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.tools.CasinoGrid;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacHolder extends ItemHolder implements TableBridge{

    private BacTable table;
    private TextView countDown, dealing, waiting;
    private ConstraintLayout block;
    private ImageView cardImg;
    private CasinoGrid mainGrid, firstGrid, secondGrid, thirdGrid, fourthGrid;

    public BacHolder(BacTable table) {
        super(R.layout.bac_item);
        this.table = table;
    }

    @Override
    public void onBind() {
        firstGrid = findViewById(R.id.first_grid);
        if(isPortrait()){
            block = findViewById(R.id.road_grid_block);
            cardImg = findViewById(R.id.card_img);
        }else {
            mainGrid = findViewById(R.id.main_grid);
            secondGrid = findViewById(R.id.second_grid);
            thirdGrid = findViewById(R.id.third_grid);
            fourthGrid = findViewById(R.id.fourth_grid);
        }
        countDown = findViewById(R.id.countdown_txt);
        dealing = findViewById(R.id.dealing_txt);
        waiting = findViewById(R.id.waiting_txt);
        ImageView dealerImg = findViewById(R.id.dealer_img);
        setTextView(R.id.dealername_txt, table.dealerName);
        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
        else dealerImg.setImageResource(R.drawable.hamster);
        if(table.groupType == 5) setTextView(R.id.table_name_txt, getContex().getString(R.string.baccarat_fast) + table.groupID);
        else setTextView(R.id.table_name_txt, getContex().getString(R.string.baccarat) + table.groupID);
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
            dealing.setVisibility(View.INVISIBLE);
            waiting.setVisibility(View.INVISIBLE);
            countDown.setText(table.curTime + "");
            if(isPortrait()) block.setVisibility(View.INVISIBLE);
        }else {
            if(table.cardStatus == 2){
                if(isPortrait()) setTextView(R.id.status_txt, getContex().getString(R.string.dealing));
                if(isPortrait()) cardImg.setImageResource(R.drawable.card_dealing);
                waiting.setVisibility(View.INVISIBLE);
                dealing.setVisibility(View.VISIBLE);
            }else{
                if(isPortrait()) setTextView(R.id.status_txt, getContex().getString(R.string.waiting));
                if(isPortrait()) cardImg.setImageResource(R.drawable.card_waiting);
                waiting.setVisibility(View.VISIBLE);
                dealing.setVisibility(View.INVISIBLE);
            }
            countDown.setVisibility(View.INVISIBLE);
            if(isPortrait()) block.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void gridUpdate() {
        if(!isPortrait()){
            int mainX = 0;
            int mainY = 0;
            for(int mainImg: table.mainRoad){
                mainGrid.insertImage(mainX, mainY, mainImg);
                mainY++;
                if(mainY == 6){
                    mainX++;
                    mainY = 0;
                }
            }
            secondGrid.drawRoad(table.secGrid);
            thirdGrid.drawRoad(table.thirdGrid);
            fourthGrid.drawRoad(table.fourthGrid);

            int bankPer, playPer, tiePer;
            if(table.round == 0){
                bankPer = 0;
                playPer = 0;
                tiePer = 0;
            }else {
                bankPer = table.bankCount*100/table.round;
                playPer = table.playCount*100/table.round;
                tiePer = table.tieCount*100/table.round;
            }
            setTextView(R.id.bank_percent, bankPer+"");
            setTextView(R.id.play_percent, playPer+"");
            setTextView(R.id.tie_percent, tiePer+"");
            ProgressBar bankPro = findViewById(R.id.bank_progress);
            bankPro.setProgress(bankPer);
            ProgressBar playPro = findViewById(R.id.play_progress);
            playPro.setProgress(playPer);
            ProgressBar tiePro = findViewById(R.id.tie_progress);
            tiePro.setProgress(tiePer);
            setTextView(R.id.table_round, table.round + "");
        }
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
    public void tableLogin(boolean logOk) {
        RootActivity context = (RootActivity) getContex();
        if(logOk) context.pushActivity(BacActivity.class);
        else Kit.alert(context,"Cannot login to this table");
    }

    @Override
    public void resultUpadte() { }
    @Override
    public void balanceUpdate(float value) { }
    @Override
    public void betUpdate(boolean betOK) { }
    @Override
    public void cardUpdate(int area, int img) { }
}
