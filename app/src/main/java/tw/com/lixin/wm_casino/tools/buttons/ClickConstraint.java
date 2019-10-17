package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class ClickConstraint extends ConstraintLayout implements View.OnClickListener{

    private CmdView cmd;
    private  GradientDrawable shape;

    public ClickConstraint(Context context) {super(context);}

    public ClickConstraint(Context context, AttributeSet attrs) {
        super(context, attrs);
        shape =  new GradientDrawable();
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ClickConstraint);

        shape.setColor(a.getColor(R.styleable.ClickConstraint_fill_color, 0x00000000));
        shape.setStroke(a.getDimensionPixelSize(R.styleable.ClickConstraint_stroke_width, 0), a.getColor(R.styleable.ClickConstraint_stroke_color, 0x00000000));
        shape.setCornerRadius( a.getDimensionPixelSize(R.styleable.ClickConstraint_border_radius, 0) );

        a.recycle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(shape);
        }

        setOnClickListener(this);
    }

    public void setStroke(int width, int color){
        shape.setStroke(App.dpToPixel(width, getContext()), color);
    }

    public void setColor(int color){
        shape.setColor(color);
    }

    public void setRadius(int dip){
        shape.setCornerRadius( App.dpToPixel(dip, getContext()) );
    }

    public void clicked(CmdView cd){
        cmd = cd;
    }

    @Override
    public void onClick(View v) {
        App.clicking();
        if(cmd != null){
            cmd.exec(v);
        }
    }
}
