package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;


public class ChipStack extends ConstraintLayout implements Animation.AnimationListener{

    private ImageView coin1, coin2, coin3, coin4, coin5;
    private Animation animeDwn, animeUp;
    private int hit = 0;
    private List<Integer> ids = new ArrayList<>();
    private TextView valTxt, title, dtOdds;
    public ChipStackData data;
    private Boolean disabled = true;

    public void disable(Boolean disabled){
        this.disabled = disabled;
    }

    public ChipStack(Context context) {
        super(context);
        init(context, null);
    }

    public ChipStack(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void cancelBet(){
        data.cancelBet();
        refresh();
    }

    public void repeatBet(){
        data.repeatBet();
        refresh();
    }

    public void clearCoin(){
        data.clear();
        reset();
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.chip_stack, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        this.setClipChildren(false);
        this.setClipToPadding(false);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        coin4 = findViewById(R.id.coin4);
        coin5 = findViewById(R.id.coin5);
        valTxt = findViewById(R.id.bet_value);
        title = findViewById(R.id.table_title);
        dtOdds = findViewById(R.id.dtOdds);
        setBackgroundResource(R.drawable.chip_stack_border);
        //TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChipStack, 0, 0);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChipStack);
        title.setTextColor(a.getColor(R.styleable.ChipStack_title_color, 0xFFFFFFFF));
        title.setText(a.getString(R.styleable.ChipStack_title));
        a.recycle();

        animeDwn = AnimationUtils.loadAnimation(context, R.anim.coin_anime_down);
        animeDwn.setAnimationListener(this);
        animeUp = AnimationUtils.loadAnimation(context, R.anim.coin_anime_up);
    }

    public void setUp(ChipStackData cData){
        data = cData;
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
    }

    public void refresh(){
        reset();
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
    }

    private void reset(){
        hit = 0;
        valTxt.setText(data.value + "");
        coin1.setVisibility(View.INVISIBLE);
        coin2.setVisibility(View.INVISIBLE);
        coin3.setVisibility(View.INVISIBLE);
        coin4.setVisibility(View.INVISIBLE);
        coin5.setVisibility(View.INVISIBLE);
        valTxt.setVisibility(View.INVISIBLE);
        setBackgroundResource(R.drawable.chip_stack_border);
        ids = new ArrayList<>();
    }

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
        setBackgroundResource(R.drawable.chip_stack_border_win);
    }

    public void add(Chip coin){

        if(disabled) return;
        if(!data.add(coin)) return;
        setBackgroundResource(R.drawable.chip_stack_border_on);
        valTxt.setVisibility(View.VISIBLE);
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

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        coin1.setImageResource(ids.get(0));
        coin2.setImageResource(ids.get(1));
        coin3.setImageResource(ids.get(2));
        coin3.setImageResource(ids.get(3));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {}
    @Override
    public void onAnimationStart(Animation animation) {}

}
