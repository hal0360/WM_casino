package tw.com.lixin.wm_casino.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.util.SparseIntArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tw.com.atromoby.utils.Cmd;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;


public abstract class Table {

    private TableBridge bridge;
    private Handler handler;
    private Timer timer = new Timer();
    public int curTime;
    private boolean isBinded = false;

    public Bitmap dealerImage;
    public String dealerName;
    public int number;
    public int stage;
    public int round;
    public int groupID, gameID;
    public int groupType;
    public int dealerID;
    public int result = -99;
    public static int curStage;
    public SparseIntArray pokers = new SparseIntArray();

    public Table(Group group){

        TableData tData = new TableData();
        tData.data.historyArr = group.historyArr;
        historyUpdate(tData.data);
        dealerID = group.dealerID;
        stage = group.gameStage;
        groupID = group.groupID;
        groupType = group.groupType;
        round = group.gameNoRound;
        number = group.gameNo;
        dealerName = group.dealerName;
        handler = LobbySource.getInstance().getGenHandler();

        try {
            InputStream in = new java.net.URL(group.dealerImage).openStream();
            dealerImage = BitmapFactory.decodeStream(in);
        }catch(IOException e) {
            Log.e(dealerName + " BitError", e.getMessage());
        }
    }

    public void bind(TableBridge bridge){
        this.bridge = bridge;
        isBinded  = true;
    }

    public void unBind(){
        bridge = null;
        isBinded  = false;
    }

    private void handle(Cmd cmd){
        if(isBinded) handler.post(()->{ if(isBinded) cmd.exec(); });
    }

    static int resDivide(int rawVal){
        List<Integer> powers = new ArrayList<>();
        for(int i = 8; i >= 0; i-- ){
            int boss = (int) Math.pow(2,i);
            if(rawVal >= boss){
                powers.add(0,boss);
                rawVal = rawVal - boss;
                if(rawVal <= 0){
                    break;
                }
            }
        }
        return powers.get(0);
    }

    public abstract void historyUpdate(TableData.Data data);
    public abstract void resultUpdate(TableData.Data data);
   // public abstract void stageUpdate();

    public void receive20(int stage) {
        if (stage == 2) {
            timer.cancel();
        }
        if (stage == 1) {
            result = -99;
            pokers.clear();
        }
        this.stage = stage;
        if(isBinded) curStage = stage;
        handle(() -> bridge.stageUpdate());
        if(stage == 4) unBind();
    }

    public void receive24(int area, int id) {
        pokers.put(area,id);
        handle(() -> bridge.cardUpdate(area, id));
    }

    public void receive21(TableData.Data data) {
        round = data.gameNoRound;
        number = data.gameNo;
        groupType = data.groupType;

    }

    public void receive26(TableData.Data data) {
        historyUpdate(data);
        handle(() -> bridge.gridUpdate());
    }

    public void receive38(int mille) {
        curTime = mille/1000;

        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(curTime > 0) curTime--;
                handle(() -> bridge.betCountdown(curTime));
            }
        }, 0, 1000);
    }

    public void receive25(TableData.Data data) {
        result = data.result;
        resultUpdate(data);
        handle(() -> bridge.resultUpdate());
    }

    // protected abstract void statusUpdate();
}
