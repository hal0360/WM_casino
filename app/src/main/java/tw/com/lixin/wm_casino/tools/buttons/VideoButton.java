package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;

public class VideoButton extends ClickConstraint {

    private TextView numberTxt;
    private ImageView btnImg;
    private int type;

    public VideoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_button, this);
        numberTxt = findViewById(R.id.num_txt);
        btnImg = findViewById(R.id.btn_img);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.VideoButton);
        type = a.getInt(R.styleable.VideoButton_button_type, 1);
        if(type == 2){
            numberTxt.setVisibility(INVISIBLE);
            btnImg.setImageResource(R.drawable.message);
        }else if(type ==3){
            numberTxt.setBackgroundResource(R.drawable.green_ball);
            btnImg.setImageResource(R.drawable.signal);
        }else if(type ==4){
            numberTxt.setVisibility(INVISIBLE);
            btnImg.setImageResource(R.drawable.changetable);
        }
        a.recycle();

    }


}
