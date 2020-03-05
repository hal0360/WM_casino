package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.utils.Kit;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class ChipStack extends ConstraintLayout implements Animation.AnimationListener, View.OnClickListener {

    private ImageView coin1, coin2, coin3, coin4, coin5;
    private Animation animeDwn, animeUp;
    private int hit = 0;
    private List<Integer> ids;
    private TextView valTxt;
    public ChipStackData data;
    private GradientDrawable shape;
    private GameSource source;
    private int maxValue;
    private long area = 1;


    public ChipStack(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.chip_stack, this);

        TextView centerTxt = findViewById(R.id.center_txt);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChipStack);
        maxValue = a.getInt(R.styleable.ChipStack_max_val,5);

        int textDim = a.getInt(R.styleable.ChipStack_text_size,12);
        centerTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textDim);
        centerTxt.setTextColor(a.getColor(R.styleable.ChipStack_text_color, 0xffffffff));

        String cenStr = a.getString(R.styleable.ChipStack_text);
        if(cenStr != null){
            centerTxt.setVisibility(VISIBLE);
            centerTxt.setText(cenStr);
        }

        String areaStr = a.getString(R.styleable.ChipStack_area);
        if(areaStr != null) area = Long.parseLong(areaStr);
        a.recycle();


        setOnClickListener(this);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        coin4 = findViewById(R.id.coin4);
        coin5 = findViewById(R.id.coin5);
        valTxt = findViewById(R.id.bet_value);

        //temp
        coin1.setVisibility(View.INVISIBLE);
        coin2.setVisibility(View.INVISIBLE);
        coin3.setVisibility(View.INVISIBLE);
        coin4.setVisibility(View.INVISIBLE);
        coin5.setVisibility(View.INVISIBLE);
        valTxt.setVisibility(View.INVISIBLE);
        //temp

        coin1.setElevation(1);
        coin2.setElevation(1);
        coin3.setElevation(1);
        coin4.setElevation(1);
        coin5.setElevation(1);
        valTxt.setElevation(1);

        //TextView title = findViewById(R.id.table_title);
        //dtOdds = findViewById(R.id.dtOdds);
        //TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ChipStack);
        //title.setTextColor(a.getColor(R.styleable.ChipStack_title_color, 0xFFFFFFFF));
        //title.setText(a.getString(R.styleable.ChipStack_title));
        //betColor = a.getColor(R.styleable.ChipStack_bet_color, 0xFFA9DB8D);
        //normalColor = a.getColor(R.styleable.ChipStack_normal_color, 0xFF369762);
        shape = new GradientDrawable();
        shape.setColor(0xFF369762);
        shape.setStroke(2, 0xFF4AC283);
        shape.setCornerRadius(8);
        setBackground(shape);
       // a.recycle();
        animeDwn = AnimationUtils.loadAnimation(context, R.anim.coin_anime_down);
        animeDwn.setAnimationListener(this);
        animeUp = AnimationUtils.loadAnimation(context, R.anim.coin_anime_up);

        source = GameSource.getInstance();

        post(()->{
            int vid = getId();
            if(vid == NO_ID) throw new ArithmeticException(getClass().getSimpleName() + " HAVE NO ID");
            data = source.chipDatas.get(vid);
            if(data == null){
                data = new ChipStackData();
                source.chipDatas.put(getId(),data);
            }
            refresh();
            CasinoActivity casinoActivity = (CasinoActivity) context;
            casinoActivity.addToArea(this);
        });

    }

    public void addCoinToClient(Client22 client22){
        for(Chip coin: data.tempAddedCoin){
            client22.addBet(area,coin.value);
        }
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
    public void setAreaMax(long area, int maxVal){
        this.area = area;
        maxValue = maxVal;
    }

    @SuppressLint("SetTextI18n")
    public void refresh(){
        reset();
        for(Chip coin: data.addedCoin) noAnimeAdd(coin);
        for(Chip coin: data.tempAddedCoin) noAnimeAdd(coin);
        if(data.addedCoin.size() > 0 || data.tempAddedCoin.size() > 0){
            shape.setColor(0xFFA9DB8D);
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
        shape.setColor(0xFF369762);
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
        if(source.table.stage != 1) {
            Kit.alert(getContext(),"Please wait!!");
            return false;
        }
        if((data.value + coin.value) > maxValue){
            Kit.alert(getContext(),"Exceed max value!!");
            return false;
        }
        data.add(coin);
        App.betting();
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
        if( add(CasinoArea.curChip)) {
            CasinoActivity activity = (CasinoActivity) getContext();
            activity.stackBet();
        }
    }
}
