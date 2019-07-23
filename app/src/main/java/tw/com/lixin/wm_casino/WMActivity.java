package tw.com.lixin.wm_casino;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.tools.LoadDialog;

public abstract class WMActivity extends RootActivity {


    private LoadDialog loadDialog;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        loadDialog = new LoadDialog(this);


    }

    public void loading(){
        loadDialog.show();
    }

    public void unloading(){
        loadDialog.dismiss();
    }

    public int orientation(){
        return getResources().getConfiguration().orientation;
    }

    @Override
    protected void onPause() {
        super.onPause();
        loadDialog.dismiss();
    }
}
