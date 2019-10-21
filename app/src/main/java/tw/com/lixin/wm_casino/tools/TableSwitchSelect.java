package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class TableSwitchSelect extends ConstraintLayout {

    private ImageView gameArrow;

    public TableSwitchSelect(Context context) {
        super(context);
    }

    public TableSwitchSelect(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.table_switch_select, this);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.TableSwitchSelect);
        TextView nameT = findViewById(R.id.game_name);
        nameT.setText(a.getString(R.styleable.TableSwitchSelect_game_name));
        ImageView gameImg = findViewById(R.id.game_img);
        gameImg.setImageResource(a.getResourceId(R.styleable.TableSwitchSelect_game_image,0));
        a.recycle();

        gameArrow = findViewById(R.id.game_arrow);
    }

    public void select(){
        setBackgroundResource(R.drawable.table_switch_selected);
        gameArrow.setVisibility(VISIBLE);
    }

    public void unSelect(){
        setBackgroundResource(0);
        gameArrow.setVisibility(INVISIBLE);
    }
}
