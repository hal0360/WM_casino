package tw.com.lixin.wm_casino.tools.txtViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.lixin.wm_casino.R;

public class NgaText extends AppCompatTextView {

    @SuppressLint("SetTextI18n")
    public NgaText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(0xffffffff);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NgaText);
        String txt = a.getString(R.styleable.NgaText_xml_txt);
        a.recycle();

        setText(getText().toString() + " " + txt);
    }


}
