package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;


public class ChipStack extends ConstraintLayout implements Animation.AnimationListener, View.OnClickListener {

    private ImageView coin1, coin2, coin3, coin4, coin5, chipCheck;
    private Animation animeDwn, animeUp;
    private int hit = 0;
    private List<Integer> ids = new ArrayList<>();
    private TextView valTxt, title, dtOdds;
    public ChipStackData data;
    private int orientation;
    private StackCallBridge bridge;

    public ChipStack(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.chip_stack, this);
        setOnClickListener(this);
        orientation = getResources().getConfiguration().orientation;
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        coin4 = findViewById(R.id.coin4);
        coin5 = findViewById(R.id.coin5);
        valTxt = findViewById(R.id.bet_value);
        title = findViewById(R.id.table_title);
        dtOdds = findViewById(R.id.dtOdds);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChipStack);
        title.setTextColor(a.getColor(R.styleable.ChipStack_title_color, 0xFF9EB5AD));
        title.setText(a.getString(R.styleable.ChipStack_title));

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            setBackgroundResource(R.drawable.chip_stack_border);
        }else{
            View tiltView = findViewById(R.id.text_block);
            tiltView.setRotation(a.getInt(R.styleable.ChipStack_tilt, 0));
            chipCheck = findViewById(R.id.chip_check_img);
        }

        a.recycle();

       // setRotation(34);

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

    public void setUp(ChipStackData cData, StackCallBridge bridge){
        dtOdds.setText("1:"+cData.score);
        data = cData;
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
        this.bridge = bridge;
        checkChips();
    }

    public void refresh(){
        reset();
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
        checkChips();
    }

    private void checkChips(){
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            if(data.tempAddedCoin.size() == 0){
                if(data.addedCoin.size() > 0){
                    chipCheck.setVisibility(VISIBLE);
                    chipCheck.setImageResource(R.drawable.chip_check_on);
                }else{
                    chipCheck.setVisibility(INVISIBLE);
                    chipCheck.setImageResource(R.drawable.chip_check_off);
                }
            }else{
                chipCheck.setVisibility(VISIBLE);
                chipCheck.setImageResource(R.drawable.chip_check_off);
            }
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

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            setBackgroundResource(R.drawable.chip_stack_border);
        }else{
            chipCheck.setVisibility(INVISIBLE);
            chipCheck.setImageResource(R.drawable.chip_check_off);
            title.setTextColor(0xFF9EB5AD);
        }

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
        valTxt.setVisibility(View.VISIBLE);
        valTxt.setText(data.value + "");
    }

    public void win(){
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            chipCheck.setVisibility(VISIBLE);
            title.setTextColor(0xFFFFFFE0);
        }else {
            setBackgroundResource(R.drawable.chip_stack_border_win);
        }
    }

    @SuppressLint("SetTextI18n")
    public boolean add(Chip coin){
        if(!data.add(coin)) return false;
        valTxt.setVisibility(View.VISIBLE);

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            setBackgroundResource(R.drawable.chip_stack_border_on);
        }else{
            chipCheck.setVisibility(VISIBLE);
            chipCheck.setImageResource(R.drawable.chip_check_off);
        }

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
        if(bridge != null ) bridge.stackBet(this);
    }
}
