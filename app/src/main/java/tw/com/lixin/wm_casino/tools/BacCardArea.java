package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Poker;

public class BacCardArea extends ConstraintLayout {

    private SparseArray<ImageView> pokers;

    @SuppressLint("FindViewByIdCast")
    public BacCardArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.bac_card_area, this);
        setBackgroundColor(Color.parseColor("#000000"));

        pokers = new SparseArray<>();
        pokers.put(1,findViewById(R.id.player_poker1));
        pokers.put(3,findViewById(R.id.player_poker2));
        pokers.put(5,findViewById(R.id.player_poker3));
        pokers.put(2,findViewById(R.id.banker_poker1));
        pokers.put(4,findViewById(R.id.banker_poker2));
        pokers.put(6,findViewById(R.id.banker_poker3));

    }

    public void update(int area, int img) {
        ImageView pokerImg = pokers.get(area);
        pokerImg.setVisibility(View.VISIBLE);
        pokerImg.setImageResource(Poker.NUM(img));
    }

    public void clean(){
        pokers.get(1).setVisibility(INVISIBLE);
        pokers.get(2).setVisibility(INVISIBLE);
        pokers.get(3).setVisibility(INVISIBLE);
        pokers.get(4).setVisibility(INVISIBLE);
        pokers.get(5).setVisibility(INVISIBLE);
        pokers.get(6).setVisibility(INVISIBLE);
    }

    public void reset(int status, SparseIntArray dataPokers) {
        if(status == 2){setVisibility(View.VISIBLE);
            clean();
        }else{
            setVisibility(View.GONE);
            return;
        }
        for(int i = 0; i < dataPokers.size(); i++) {
            int key = dataPokers.keyAt(i);
            update(key, dataPokers.get(key));
        }
    }

}
