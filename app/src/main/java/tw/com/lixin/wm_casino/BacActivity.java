package tw.com.lixin.wm_casino;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.ItemHolder;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.holders.BacHolder;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.tools.BacCardArea;
import tw.com.lixin.wm_casino.tools.CasinoGrid;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.tools.ControlButton;
import tw.com.lixin.wm_casino.tools.ProfileSetting;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class BacActivity extends WMActivity implements TableBridge {

    private IjkVideoView video;
    private BacTable table;
    private GameSource source;
    private ChipStack playerPairStack, playerStack, tieStack, bankerStack, bankerPairStack;
    private BacCardArea cardArea;
    private CasinoGrid mainGrid, firstGrid, secGrid, thirdGrid, fourthGrid;
    private ProfileSetting profile;
    private ControlButton betBtn, cancelBtn, rebetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac);
        source = GameSource.getInstance();
        table = (BacTable) source.table;
        table.bind(this);
        cardArea = findViewById(R.id.card_area);
        playerPairStack = findViewById(R.id.player_pair_stack);
        playerStack = findViewById(R.id.player_stack);
        tieStack = findViewById(R.id.tie_stack);
        bankerPairStack = findViewById(R.id.bank_pair_stack);
        bankerStack = findViewById(R.id.bank_stack);
        mainGrid = findViewById(R.id.main_grid);
        firstGrid = findViewById(R.id.first_grid);
        secGrid = findViewById(R.id.second_grid);
        thirdGrid = findViewById(R.id.third_grid);
        fourthGrid = findViewById(R.id.fourth_grid);
        profile = findViewById(R.id.profile_setting);
        betBtn = findViewById(R.id.confirm_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        rebetBtn = findViewById(R.id.rebet_btn);


        playerPairStack.setUp(table.playPairStack);

        cardArea.reset(table.cardStatus, table.pokers);

        String path = "rtmp://wmvdo.nicejj.cn/live1/stream1";

        video = findViewById(R.id.my_player);
        video.setVideoPath(path);
        video.start();




        betBtn.clicked(v -> {

            Client22 client22 = new Client22();
            table.bankPairStack.addCoinToClient(client22, 4);
            table.tieStack.addCoinToClient(client22, 3);
            table.bankStack.addCoinToClient(client22, 1);
            table.playPairStack.addCoinToClient(client22, 5);
            table.playStack.addCoinToClient(client22, 2);

            if (client22.data.betArr.size() > 0) {
                //alert(Json.to(client22));
                source.send(Json.to(client22));
            }
            else alert("You haven't put any money!");
        });

        cancelBtn.clicked(v -> {

            checkStackEmpty();
        });

        rebetBtn.clicked(v -> {

        });

    }

    private void checkStackEmpty() {
        if (table.playStack.isEmpty() && table.playPairStack.isEmpty() && table.bankPairStack.isEmpty() && table.bankStack.isEmpty() && table.tieStack.isEmpty()) {
            cancelBtn.disable(true);
            rebetBtn.disable(true);
        } else {
            cancelBtn.disable(false);
            rebetBtn.disable(false);
        }
    }


    @Override
    public void resultUpadte() {

    }

    @Override
    public void balanceUpdate(float value) {

    }

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){ alert("bet succ!");
        }else{ alert("fail!"); }
    }

    @Override
    public void tableLogin(boolean logOk) {

    }

    @Override
    public void cardUpdate(int area, int img) {
        cardArea.update(area, img);
    }

    @Override
    public void statusUpdate() {
        cardArea.setVisibility(View.GONE);
        if (table.cardStatus == 0) {

        } else if (table.cardStatus == 1) {

        } else if (table.cardStatus == 2) {

        } else if (table.cardStatus == 3) {

        } else {

        }
    }

    @Override
    public void gridUpdate() {
        int indexx = 0;
        firstGrid.drawRoad(table.firstGrid);
        secGrid.drawRoad(table.secGrid);
        thirdGrid.drawRoad(table.thirdGrid);
        fourthGrid.drawRoad(table.fourthGrid);
        for (int x = 0; x < mainGrid.width; x++) {
            for (int y = 0; y < mainGrid.height; y++) {
                if (indexx >= table.mainRoad.size()) return;
                mainGrid.insertImage(x, y, table.mainRoad.get(indexx));
                indexx++;
               // posX = x;
               // posY = y;
            }
        }
    }

    @Override
    public void betCountdown(int sec) {

    }

    public void betCalled(View view) {
        alert("shitty");
    }
}
