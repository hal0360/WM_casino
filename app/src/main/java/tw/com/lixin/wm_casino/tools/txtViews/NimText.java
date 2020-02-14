package tw.com.lixin.wm_casino.tools.txtViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.lixin.wm_casino.R;

public class NimText extends AppCompatTextView {

    @SuppressLint("SetTextI18n")
    public NimText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(0xffffffff);
        String str = new StringBuilder(getText().toString()).insert(1, " " + context.getString(R.string.nim) + " ").toString();

        setText(str);
    }


}