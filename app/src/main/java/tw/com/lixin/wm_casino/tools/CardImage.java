package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;

public class CardImage extends AppCompatImageView {

    public int area;

    public CardImage(Context context) {
        super(context);
    }

    public CardImage(Context context, AttributeSet attrs) {
        super(context, attrs);

        setImageResource(R.drawable.poker_d1);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardImage);
        area = a.getInt(R.styleable.CardImage_card_area,1);
        a.recycle();

        post(()->{
            CasinoActivity casinoActivity = (CasinoActivity) context;
            casinoActivity.addCard(this);
        });
    }
}
