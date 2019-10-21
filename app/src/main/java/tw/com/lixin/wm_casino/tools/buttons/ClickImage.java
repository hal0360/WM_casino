package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;

public class ClickImage extends androidx.appcompat.widget.AppCompatImageView implements View.OnClickListener{

    private CmdView cmd;

    public ClickImage(Context context) {super(context);}

    public ClickImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
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

