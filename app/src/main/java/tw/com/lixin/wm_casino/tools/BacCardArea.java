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
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacCardArea extends ConstraintLayout {

    private SparseArray<ImageView> pokers;
    private TextView playerScore, playerTxt, bankerScore, bankerTxt;

    @SuppressLint("FindViewByIdCast")
    public BacCardArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.bac_card_area, this);
        setBackgroundColor(0xBF000000);

        playerScore = findViewById(R.id.tiger_score);
        playerTxt = findViewById(R.id.player_txt);
        bankerScore = findViewById(R.id.dragon_score);
        bankerTxt = findViewById(R.id.banker_txt);

        pokers = new SparseArray<>();
        pokers.put(1,findViewById(R.id.player_poker1));
        pokers.put(3,findViewById(R.id.player_poker2));
        pokers.put(5,findViewById(R.id.player_poker3));
        pokers.put(2,findViewById(R.id.banker_poker1));
        pokers.put(4,findViewById(R.id.banker_poker2));
        pokers.put(6,findViewById(R.id.banker_poker3));

        GameSource source = GameSource.getInstance();
       // setUp(source.table.pokers);
    }

    public void setUp(SparseIntArray dataPokers){
        clean();
        for(int i = 0; i < dataPokers.size(); i++) {
            int key = dataPokers.keyAt(i);
            update(key, dataPokers.get(key));
        }
    }

    @SuppressLint("SetTextI18n")
    public void showScore(int play, int bank){
        playerScore.setText(play+"");
        bankerScore.setText(bank+"");
        playerScore.setVisibility(VISIBLE);
        playerTxt.setVisibility(VISIBLE);
        bankerScore.setVisibility(VISIBLE);
        bankerTxt.setVisibility(VISIBLE);
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
        playerScore.setVisibility(INVISIBLE);
        playerTxt.setVisibility(INVISIBLE);
        bankerScore.setVisibility(INVISIBLE);
        bankerTxt.setVisibility(INVISIBLE);
    }

    public void reset() {
        clean();
        setVisibility(GONE);
    }

}
