package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;

public class BetStack extends ConstraintLayout implements Animation.AnimationListener, View.OnClickListener {

    private ImageView coin1, coin2, coin3, coin4, coin5;
    private Animation animeDwn, animeUp;
    private int hit = 0;
    private List<Integer> ids = new ArrayList<>();
    public ChipStackData data;
    private TextView valTxt;
    private GradientDrawable shape;
   // private StackCallBridge bridge;


    public BetStack(Context context, AttributeSet attrs) {
        super(context, attrs);

        View.inflate(context, R.layout.bet_stack, this);
        setOnClickListener(this);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        coin4 = findViewById(R.id.coin4);
        coin5 = findViewById(R.id.coin5);
        valTxt = findViewById(R.id.bet_value);

        shape = new GradientDrawable();
        shape.setColor(0xFF369762);

        shape.setCornerRadius(7);
        setBackground(shape);

        animeDwn = AnimationUtils.loadAnimation(context, R.anim.coin_anime_down);
        animeDwn.setAnimationListener(this);
        animeUp = AnimationUtils.loadAnimation(context, R.anim.coin_anime_up);


    }

    public void cancelBet(){
        data.cancelBet();
        refresh();
    }

    public void repeatBet(){
        data.repeatBet();
        refresh();
    }

    public void comfirmBet(){
        data.comfirmBet();
        refresh();
    }

    public void clearCoin(){
        data.clear();
        reset();
    }

    @SuppressLint("SetTextI18n")
    public void setUp(ChipStackData cData, StackCallBridge bridge){

        data = cData;
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
        if(!data.isAddEmpty() || !data.isTempEmpty()){
          //  shape.setColor(betColor);
            valTxt.setVisibility(View.VISIBLE);
            valTxt.setText(data.value + "");
        }
      //  this.bridge = bridge;
    }

    @SuppressLint("SetTextI18n")
    public void refresh(){
        reset();
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
        if(!data.isAddEmpty() || !data.isTempEmpty()){
         //   shape.setColor(betColor);
            valTxt.setVisibility(View.VISIBLE);
            valTxt.setText(data.value + "");
        }
    }

    @SuppressLint("SetTextI18n")
    private void reset(){
        hit = 0;
        valTxt.setText(data.value + "");
        coin1.setVisibility(View.INVISIBLE);
        coin2.setVisibility(View.INVISIBLE);
        coin3.setVisibility(View.INVISIBLE);
        coin4.setVisibility(View.INVISIBLE);
        coin5.setVisibility(View.INVISIBLE);
        valTxt.setVisibility(View.INVISIBLE);
      // shape.setColor(normalColor);
        ids = new ArrayList<>();
    }

    @SuppressLint("SetTextI18n")
    private void noAnimeAdd(Chip coin){

        ids.add(coin.image);
        if(hit == 0){
            coin1 .setImageResource(coin.image);
            coin1.setVisibility(View.VISIBLE);
        }else if(hit == 1){
            coin2.setImageResource(coin.image);
            coin2.setVisibility(View.VISIBLE);
        }else if( hit == 2){
            coin3.setImageResource(coin.image);
            coin3.setVisibility(View.VISIBLE);
        }else if(hit == 3){
            coin4.setImageResource(coin.image);
            coin4.setVisibility(View.VISIBLE);
        }else if(hit == 4){
            coin5.setImageResource(coin.image);
            coin5.setVisibility(View.VISIBLE);
        } else{
            ids.remove(0);
            coin5.setImageResource(coin.image);
            coin1.setImageResource(ids.get(0));
            coin2.setImageResource(ids.get(1));
            coin3.setImageResource(ids.get(2));
            coin3.setImageResource(ids.get(3));
        }
        hit++;
    }

    @SuppressLint("SetTextI18n")
    public boolean add(Chip coin){
        if(!data.add(coin)) return false;
        valTxt.setVisibility(View.VISIBLE);

        shape.setColor(0xFFA9DB8D);

        valTxt.setText(data.value + "");
        ids.add(coin.image);
        if(hit == 0){
            coin1.setImageResource(coin.image);
            coin1.setVisibility(View.VISIBLE);
            coin1.startAnimation(animeUp);
        }else if(hit == 1){
            coin2.setImageResource(coin.image);
            coin2.setVisibility(View.VISIBLE);
            coin2.startAnimation(animeUp);
        }else if( hit == 2){
            coin3.setImageResource(coin.image);
            coin3.setVisibility(View.VISIBLE);
            coin3.startAnimation(animeUp);
        }else if(hit == 3){
            coin4.setImageResource(coin.image);
            coin4.setVisibility(View.VISIBLE);
            coin4.startAnimation(animeUp);
        }else if(hit == 4){
            coin5.setImageResource(coin.image);
            coin5.setVisibility(View.VISIBLE);
            coin5.startAnimation(animeUp);
        }else{
            ids.remove(0);
            coin5.setImageResource(coin.image);
            coin5.startAnimation(animeUp);
            coin1.startAnimation(animeDwn);
        }
        hit++;
        return true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        coin1.setImageResource(ids.get(0));
        coin2.setImageResource(ids.get(1));
        coin3.setImageResource(ids.get(2));
        coin4.setImageResource(ids.get(3));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}
    @Override
    public void onAnimationStart(Animation animation) {}

    @Override
    public void onClick(View v) {
       // if(bridge != null ) bridge.stackBet(this);
    }
}