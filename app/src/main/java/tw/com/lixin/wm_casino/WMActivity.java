package tw.com.lixin.wm_casino;

import android.content.res.Configuration;
import android.os.Bundle;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.tools.LocaleUtils;

public abstract class WMActivity extends RootActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (overrideConfiguration != null) {
            overrideConfiguration.setLocale(LocaleUtils.sLocale);
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }


}
