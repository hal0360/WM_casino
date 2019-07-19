package tw.com.lixin.wm_casino.models;

import android.view.View;
import android.widget.TextView;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wmcasino.CasinoActivity;
import tw.com.lixin.wmcasino.CoinHolder;
import tw.com.lixin.wmcasino.R;
import tw.com.lixin.wmcasino.Tools.NumberPadPopup;

public class CostomCoinHolder extends ItemHolder {

    public int value, img_res;
    public CoinHolder coinHolder;
    public TextView display;

    public CostomCoinHolder() {
        super(R.layout.custom_coin_item);
        this.value = 0;
        img_res = R.drawable.casino_item_chip;
        coinHolder = new CoinHolder(img_res,10);
    }

    public void setVal(int val){
        this.value = val;
        coinHolder.value = value;

            if(!coinHolder.selected){
                coinHolder.selected = true;
                CasinoActivity act = (CasinoActivity) getContex();
                act.curCoin.selected = false;
                act.curCoin = coinHolder;
                act.coinsView.refresh();
            }

    }

    @Override
    public void onBind() {

        View coin = findViewById(R.id.coin);
        coin.setBackgroundResource(img_res);
        View chip = findViewById(R.id.chip);

        display = findViewById(R.id.display_txt);

        clicked(R.id.coin, v -> {

            new NumberPadPopup(getContex(),this).show();


        });

        if(coinHolder.selected){
            chip.setBackgroundResource(R.drawable.outer_glow);
        }else {
            chip.setBackgroundResource(0);
        }

    }

    @Override
    public void onRecycle() {

    }




}
