package tw.com.lixin.wm_casino.models;


import android.graphics.Bitmap;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tw.com.atromoby.utils.Cmd;
import tw.com.lixin.wm_casino.LobbyActivity;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;


public abstract class Table {

    private TableBridge bridge;
    public int cardStatus = 0;
    private Handler handler;
    private Timer timer = new Timer();
    public int curTime;
    private boolean isBinded = false;

    public Bitmap dealerImage;
    public String dealerImageUrl;
    public String dealerName;
    public int number;
    public int stage;
    public int round;
    public int groupID, gameID;
    public int groupType;
    public int dealerID;

    public Table(Group group){
        historySetup(group.historyArr);
        dealerID = group.dealerID;
        stage = group.gameStage;
        groupID = group.groupID;
        groupType = group.groupType;
        round = group.gameNoRound;
        number = group.gameNo;
        dealerName = group.dealerName;
        dealerImageUrl = group.dealerImage;
        handler = LobbySource.getInstance().getGenHandler();
    }

    public void bind(TableBridge bridge){
      this.bridge = bridge;
        isBinded  = true;
    }

    public void unBind(){
        bridge = null;
        isBinded  = false;
    }

    public void loginSetup(TableData.Data data){
        handle(() -> bridge.tableLogin(data));
    }

    public void update(TableData.Data data){
        round = data.historyArr.size();
        historySetup(data.historyArr);
        groupType = data.groupType;
        handle(() -> bridge.gridUpdate());
    }

    public void statusUpdate(int stage){
        if (stage == 2) {
            timer.cancel();
        }
        cardStatus = stage;
        handle(() -> bridge.statusUpdate());
    }

    public void startCountDown(int mille){
        curTime = mille/1000;

        handle(() -> bridge.betCountdown(curTime));

        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(curTime > 0) curTime--;
                handle(() -> bridge.betCountdown(curTime));
            }
        }, 1000, 1000);
    }

    private void handle(Cmd cmd){
        if(isBinded) handler.post(()->{ if(isBinded) cmd.exec(); });
    }

    public static int resDivide(int rawVal){
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

    public abstract void historySetup(List<Integer> histories);
    public abstract void cardUpdate(int area, int id);
    public abstract void statusUpdate();
    public abstract void historyUpdate(TableData.Data data);
    public abstract void resultUpdate(TableData.Data data);

    public void receive20(int stage) {
        if (stage == 2) {
            timer.cancel();
        }
        cardStatus = stage;
        statusUpdate();
        handle(() -> bridge.statusUpdate());
    }

    public void receive24(int area, int id) {
        cardUpdate(area, id);
        handle(() -> bridge.cardUpdate(area, id));
    }

    public void receive21(TableData.Data data) {
    }

    public void receive26(TableData.Data data) {
        round = data.historyArr.size();
        historySetup(data.historyArr);
        groupType = data.groupType;
        historyUpdate(data);
        handle(() -> bridge.gridUpdate());
    }

    public void receive38(int mille) {
        curTime = mille/1000;

        handle(() -> bridge.betCountdown(curTime));

        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(curTime > 0) curTime--;
                handle(() -> bridge.betCountdown(curTime));
            }
        }, 1000, 1000);
    }

    public void receive25(TableData.Data data) {
        resultUpdate(data);
        handle(() -> bridge.resultUpdate());
    }

    // protected abstract void statusUpdate();
}
