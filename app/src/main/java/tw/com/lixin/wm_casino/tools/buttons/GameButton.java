package tw.com.lixin.wm_casino.tools.buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class GameButton extends ClickConstraint {
    private TextView pplnum;
    public GameButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.game_button, this);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.GameButton);
        TextView nameT = findViewById(R.id.game_txt);
        nameT.setText(a.getString(R.styleable.GameButton_game_name));
        ImageView gameImg = findViewById(R.id.game_img);
        gameImg.setImageResource(a.getResourceId(R.styleable.TableSwitchSelect_game_image,0));
        pplnum = findViewById(R.id.ppl_txt);
        a.recycle();
    }

    @SuppressLint("SetTextI18n")
    public void setPeopleNumber(int number){
        pplnum.setText(number+"");
    }
}
