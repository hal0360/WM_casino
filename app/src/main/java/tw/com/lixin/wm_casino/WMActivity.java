package tw.com.lixin.wm_casino;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import tw.com.atromoby.widgets.RootActivity;

public abstract class WMActivity extends RootActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            landscape_created();
        }else{
            portrait_created();
        }
    }



    protected abstract void landscape_created();

    protected abstract void portrait_created();
}
