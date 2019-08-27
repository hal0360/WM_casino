package tw.com.lixin.wm_casino.models;


import android.graphics.Bitmap;
import android.os.Handler;
import android.util.SparseIntArray;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tw.com.atromoby.utils.Cmd;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.global.Poker;
import tw.com.lixin.wm_casino.interfaces.TableBridge;


public abstract class Table {

    private TableBridge bridge;
    public int cardStatus = 0;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    public int curTime;
    private boolean isBinded = false;

    public Bitmap dealerImage;
    public String dealerImageUrl;
    public String dealerName;
    public int score;
    public int number;
    public int stage;
    public int round;
    public int groupID;
    public int groupType;
    public SparseIntArray pokers = new SparseIntArray();

    public void bind(TableBridge bridge){
      this.bridge = bridge;
        isBinded  = true;
    }

    public void unBind(){
        bridge = null;
        isBinded  = false;
    }

    public abstract void historySetup(List<Integer> histories);

    public void update(TableData data){
        handle(() -> bridge.gridUpdate());
    }

    public void statusUpdate(int stage){
        if (stage == 1) {
            pokers.clear();
        } else if (stage == 2) {
            timer.cancel();
        }
        cardStatus = stage;
        handle(() -> bridge.statusUpdate());
    }

    public void cardUpdate(int area, int id){
        pokers.put(area,Poker.NUM(id ));
        handle(() -> bridge.cardUpdate(area, Poker.NUM(id)));
    }

    public void startCountDown(int mille){
        curTime = mille/1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                curTime--;
                handle(() -> bridge.betCountdown(curTime));
            }
        }, 1000, curTime);
    }

    public void handle(Cmd cmd){
        if(isBinded) handler.post(()->{ if(isBinded) cmd.exec(); });
    }

}
