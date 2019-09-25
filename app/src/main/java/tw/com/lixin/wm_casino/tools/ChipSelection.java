package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.chips.ChipView;

public class ChipSelection extends ConstraintLayout implements View.OnClickListener{

    private ArrayList<ChipView> chipViews;

    private ChipView selectedChip;

    public ChipSelection(Context context) {
        super(context);
        View.inflate(context, R.layout.chip_selection, this);

    }

    public ChipSelection(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.chip_selection, this);

    }


    @SuppressLint("FindViewByIdCast")
    private void init(){

        findViewById(R.id.chip1).setOnClickListener(this);
        findViewById(R.id.chip5).setOnClickListener(this);
        findViewById(R.id.chip10).setOnClickListener(this);
        findViewById(R.id.chip50).setOnClickListener(this);
        findViewById(R.id.chip100).setOnClickListener(this);
        findViewById(R.id.chip20).setOnClickListener(this);
        findViewById(R.id.chip500).setOnClickListener(this);
        findViewById(R.id.chip1000).setOnClickListener(this);
        findViewById(R.id.chip5000).setOnClickListener(this);
        findViewById(R.id.chip10000).setOnClickListener(this);

        chipViews.add(findViewById(R.id.chip1));
    }

    public void setSelectedChip(int resId){
        selectedChip = findViewById(resId);
    }

    @Override
    public void onClick(View v) {
        selectedChip.turnOff();
        ChipView selChip = (ChipView) v;
        selChip.turnOn();
       // selectedChip =
    }
}
