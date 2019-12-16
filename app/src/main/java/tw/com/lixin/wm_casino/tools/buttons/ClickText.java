package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.interfaces.CmdText;

public class ClickText extends AppCompatTextView implements View.OnClickListener{

    private CmdText cmd;

    public ClickText(Context context) {super(context);}

    public ClickText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public String getRawText(){
        return getText().toString();
    }

    public void clicked(CmdText cd){
        cmd = cd;
    }

    @Override
    public void onClick(View v) {
        App.clicking();
        if(cmd != null){
            cmd.exec(this);
        }
    }
}

