package tw.com.lixin.wm_casino.tools.txtViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.lixin.wm_casino.CasinoActivity;

public class ResultText extends AppCompatTextView {

    @SuppressLint("SetTextI18n")
    public ResultText(Context context, AttributeSet attrs) {
        super(context, attrs);
        post(()->{
            CasinoActivity casinoActivity = (CasinoActivity) context;
            casinoActivity.addTxt(this);
        });
    }


}
