package tw.com.lixin.wm_casino.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

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


public class Table {

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
    public TableData.HisData data;

    public List<Integer> mainRoad;
    public ItemRoad firstRoad;
    public ItemRoad secondRoad;
    public ItemRoad thirdRoad;
    public ItemRoad fourthRoad;
    public List<Integer> mainRoadAsk1;
    public ItemRoad firstRoadAsk1;
    public ItemRoad secondRoadAsk1;
    public ItemRoad thirdRoadAsk1;
    public ItemRoad fourthRoadAsk1;
    public List<Integer> mainRoadAsk2;
    public ItemRoad firstRoadAsk2;
    public ItemRoad secondRoadAsk2;
    public ItemRoad thirdRoadAsk2;
    public ItemRoad fourthRoadAsk2;

    public Table(Group group, int gid){

        gameID = gid;
        TableData tData = new TableData();
        tData.data.historyArr = group.historyArr;
        tData.data.historyData = group.historyData;
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

    public void historyUpdate(TableData.Data data) {

        if(gameID == 101 && data.historyData.dataArr1BankerAsk != null){
            mainRoadAsk1 = data.historyData.dataArr1BankerAsk;
            firstRoadAsk1 = new ItemRoad(data.historyData.dataArr2BankerAsk);
            secondRoadAsk1 = new ItemRoad(data.historyData.dataArr3BankerAsk);
            thirdRoadAsk1 = new ItemRoad(data.historyData.dataArr4BankerAsk);
            fourthRoadAsk1 = new ItemRoad(data.historyData.dataArr5BankerAsk);
            mainRoadAsk2 = data.historyData.dataArr1PlayerAsk;
            firstRoadAsk2 = new ItemRoad(data.historyData.dataArr2PlayerAsk);
            secondRoadAsk2 = new ItemRoad(data.historyData.dataArr3PlayerAsk);
            thirdRoadAsk2 = new ItemRoad(data.historyData.dataArr4PlayerAsk);
            fourthRoadAsk2 = new ItemRoad(data.historyData.dataArr5PlayerAsk);
        } else if(gameID == 102 && data.historyData.dataArr1DragonAsk != null){
            mainRoadAsk1 = data.historyData.dataArr1DragonAsk;
            firstRoadAsk1 = new ItemRoad(data.historyData.dataArr2DragonAsk);
            secondRoadAsk1 = new ItemRoad(data.historyData.dataArr3DragonAsk);
            thirdRoadAsk1 = new ItemRoad(data.historyData.dataArr4DragonAsk);
            fourthRoadAsk1 = new ItemRoad(data.historyData.dataArr5DragonAsk);
            mainRoadAsk2 = data.historyData.dataArr1TigerAsk;
            firstRoadAsk2 = new ItemRoad(data.historyData.dataArr2TigerAsk);
            secondRoadAsk2 = new ItemRoad(data.historyData.dataArr3TigerAsk);
            thirdRoadAsk2 = new ItemRoad(data.historyData.dataArr4TigerAsk);
            fourthRoadAsk2 = new ItemRoad(data.historyData.dataArr5TigerAsk);
        }

        mainRoad = data.historyArr;
        firstRoad = new ItemRoad(data.historyData.dataArr2);
        secondRoad = new ItemRoad(data.historyData.dataArr3);
        thirdRoad = new ItemRoad(data.historyData.dataArr4);
        fourthRoad = new ItemRoad(data.historyData.dataArr5);
        this.data = data.historyData;
    }


    //public abstract void historyUpdate(TableData.Data data);
   // public abstract void stageUpdate();

    public void receive20(int stage) {
        if (stage == 2) {
            timer.cancel();
        }
        this.stage = stage;

        handle(() -> bridge.stageUpdate());
        if(stage == 4) unBind();
    }

    public void receive21(TableData.Data data) {
        round = data.gameNoRound;
        number = data.gameNo;
        groupType = data.groupType;

        handle(() -> bridge.tableUpdate());
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

}
