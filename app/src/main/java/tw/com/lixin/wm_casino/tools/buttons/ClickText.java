package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.atromoby.widgets.CmdView;
import tw.com.lixin.wm_casino.App;

public class ClickText extends AppCompatTextView implements View.OnClickListener{

    private CmdView cmd;

    public ClickText(Context context) {super(context);}

    public ClickText(Context context, AttributeSet attrs) {
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

