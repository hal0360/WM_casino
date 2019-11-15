package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Poker;

public class DragonTigerCardArea extends ConstraintLayout {

    private SparseArray<ImageView> pokers;
    private TextView tigerScore, tigerTxt, dragonScore, dragonTxt;

    @SuppressLint("FindViewByIdCast")
    public DragonTigerCardArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.dragon_tiger_card_area, this);
        setBackgroundColor(0xBF000000);

        tigerScore = findViewById(R.id.tiger_score);
        tigerTxt = findViewById(R.id.tiger_txt);
        dragonScore = findViewById(R.id.dragon_score);
        dragonTxt = findViewById(R.id.dragon_txt);

        pokers = new SparseArray<>();
        pokers.put(1,findViewById(R.id.tiger_poker));
        pokers.put(2,findViewById(R.id.dragon_poker));
    }

    public void setUp(SparseIntArray dataPokers){
        clean();
        for(int i = 0; i < dataPokers.size(); i++) {
            int key = dataPokers.keyAt(i);
            update(key, dataPokers.get(key));
        }
    }

    @SuppressLint("SetTextI18n")
    public void showScore(int tiger, int dragon){
        tigerScore.setText(tiger+"");
        dragonScore.setText(dragon+"");
        tigerScore.setVisibility(VISIBLE);
        tigerTxt.setVisibility(VISIBLE);
        dragonScore.setVisibility(VISIBLE);
        dragonTxt.setVisibility(VISIBLE);
    }

    public void update(int area, int img) {
        ImageView pokerImg = pokers.get(area);
        pokerImg.setVisibility(View.VISIBLE);
        pokerImg.setImageResource(Poker.NUM(img));
    }

    public void clean(){
        pokers.get(1).setVisibility(INVISIBLE);
        pokers.get(2).setVisibility(INVISIBLE);
        tigerScore.setVisibility(INVISIBLE);
        tigerTxt.setVisibility(INVISIBLE);
        dragonScore.setVisibility(INVISIBLE);
        dragonTxt.setVisibility(INVISIBLE);
    }

    public void reset() {
        clean();
        setVisibility(GONE);
    }
}
