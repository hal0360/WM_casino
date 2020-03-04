package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.NiuNiuActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.grids.NiuGird;

public class NiuCollection extends GameCollection {

    public NiuCollection(Table table) {
        super(table);
    }

    public NiuCollection(Table table, FragDialog f) {
        super(table,f);
    }

    private NiuGird niuGird;

    @Override
    protected void started(CollectionHolder holder) {
        niuGird = holder.findViewById(R.id.niu_grid);
        niuGird.setVisibility(View.VISIBLE);
        textGrid.setVisibility(View.GONE);
    }

    @Override
    protected Class<? extends AppCompatActivity> toGameActicity() {
        return NiuNiuActivity.class;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void gridUpdate() {
        niuGird.drawRoad(table.mainArr);
        count1.setText(getString(R.string.player1_abb) + ":" + table.data.player1Count);
        count2.setText(getString(R.string.player2_abb) + ":" + table.data.player2Count);
        count3.setText(getString(R.string.player3_abb) + ":" + table.data.player3Count);
    }
}


