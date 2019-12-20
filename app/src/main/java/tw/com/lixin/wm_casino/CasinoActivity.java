package tw.com.lixin.wm_casino;

import android.os.Bundle;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.tools.CasinoArea;

public class CasinoActivity extends RootActivity {

    protected CasinoArea casinoArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        casinoArea = findViewById(R.id.casino_area);
    }

    public CasinoArea getArea(){
        return  casinoArea;
    }
}
