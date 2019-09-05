package tw.com.lixin.wm_casino.holders;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
            firstGrid.setGrid(13, 6);
        }else {
            mainGrid = findViewById(R.id.main_grid);
            secondGrid = findViewById(R.id.second_grid);
            thirdGrid = findViewById(R.id.third_grid);
            fourthGrid = findViewById(R.id.fourth_grid);
            mainGrid.setGrid(14, 6);
            firstGrid.setGrid(16, 6);
            secondGrid.setGridDouble(10, 3);
            thirdGrid.setGridDouble(10, 3);
            fourthGrid.setGridDouble(10, 3);



            ProgressBar bankPro = findViewById(R.id.bank_progress);

            bankPro.setMax(50);
            bankPro.setProgress(20);



            /*
            mainGrid.post(() -> {
                double dim = mainGrid.getMeasuredHeight() / 6.0;
                mainGrid.getLayoutParams().width = (int) Math.round(dim * 14);
                mainGrid.setGrid(14, 6);
                int indexx = 0;
                for (int x = 0; x < mainGrid.width; x++) {
                    for (int y = 0; y < mainGrid.height; y++) {
                        if (indexx >= table.mainRoad.size()) return;
                        mainGrid.insertImage(x, y, table.mainRoad.get(indexx));
                        indexx++;
                    }
                }
                setFirstGrid();
            });
            secondGrid.post(() -> {
                double dim = secondGrid.getMeasuredHeight() / 3.0;
                int wGrid = (int) Math.round(secondGrid.getMeasuredWidth()/dim);
                secondGrid.setGridDouble(wGrid, 3);
                secondGrid.drawRoad(table.secGrid);
            });
            thirdGrid.post(() -> {
                double dim = thirdGrid.getMeasuredHeight() / 3.0;
                int wGrid = (int) Math.round(thirdGrid.getMeasuredWidth()/dim);
                thirdGrid.setGridDouble(wGrid, 3);
                thirdGrid.drawRoad(table.thirdGrid);
            });
            fourthGrid.post(() -> {
                double dim = fourthGrid.getMeasuredHeight() / 3.0;
                int wGrid = (int) Math.round(fourthGrid.getMeasuredWidth()/dim);
                fourthGrid.setGridDouble(wGrid, 3);
                fourthGrid.drawRoad(table.fourthGrid);
            });*/

        }

        ImageView dealerImg = findViewById(R.id.dealer_img);
        setTextView(R.id.dealername_txt, table.dealerName);
        if(table.dealerImage != null) dealerImg.setImageBitmap(table.dealerImage);
        setTextView(R.id.table_no, table.groupID+"");

        /*
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
*/

    }

    private void setFirstGrid(){
        double dim = firstGrid.getMeasuredHeight() / 6.0;
        int wGrid = (int) Math.round(firstGrid.getMeasuredWidth()/dim);
        firstGrid.setGrid(wGrid, 6);
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
        int bankTol = table.bankCount + table.bankPairCount;
        int playTol = table.playCount + table.playPairCount;
        if(!isPortrait()){
            int indexx = 0;
            for (int x = 0; x < mainGrid.width; x++) {
                for (int y = 0; y < mainGrid.height; y++) {
                    if (indexx >= table.mainRoad.size()) return;
                    mainGrid.insertImage(x, y, table.mainRoad.get(indexx));
                    indexx++;
                }
            }
            secondGrid.drawRoad(table.secGrid);
            thirdGrid.drawRoad(table.thirdGrid);
            fourthGrid.drawRoad(table.fourthGrid);
            int bankPer = bankTol*100/table.round;
            int playPer = playTol*100/table.round;
            int tiePer = 100 - bankPer - playPer;
            setTextView(R.id.bank_percent, bankPer+"");
            setTextView(R.id.play_percent, playPer+"");
            setTextView(R.id.tie_percent, tiePer+"");
            ProgressBar bankPro = findViewById(R.id.bank_progress);
            bankPro.setProgress(bankPer);
            ProgressBar playPro = findViewById(R.id.play_progress);
            playPro.setProgress(playPer);
            ProgressBar tiePro = findViewById(R.id.tie_progress);
            tiePro.setProgress(tiePer);
        }
        firstGrid.drawRoad(table.firstGrid);
        setTextView(R.id.bank_rate, table.bankCount + table.bankPairCount + "");
        setTextView(R.id.play_rate, table.playCount + table.playPairCount + "");
        setTextView(R.id.tie_rate, table.tieCount + "");
    }

    @Override
    public void betCountdown(int sec) {
        countDown.setText(sec+"");
    }
}
