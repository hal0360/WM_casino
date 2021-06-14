package tw.com.lixin.wm_casino.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tw.com.atromoby.utils.Cmd;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.Road;
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
    public List<Integer> mainArr;

    public ItemRoad mainRoad;
    public ItemRoad firstRoad;
    public ItemRoad secondRoad;
    public ItemRoad thirdRoad;
    public ItemRoad fourthRoad;
    public ItemRoad mainRoadAsk1;
    public ItemRoad firstRoadAsk1;
    public ItemRoad secondRoadAsk1;
    public ItemRoad thirdRoadAsk1;
    public ItemRoad fourthRoadAsk1;
    public ItemRoad mainRoadAsk2;
    public ItemRoad firstRoadAsk2;
    public ItemRoad secondRoadAsk2;
    public ItemRoad thirdRoadAsk2;
    public ItemRoad fourthRoadAsk2;



    public Table(Group group, int gid){

        gameID = gid;

        TableData tData = new TableData();
        tData.data.historyArr = group.historyArr;
        tData.data.historyData = group.historyData;

        dealerID = group.dealerID;
        stage = group.gameStage;
        groupID = group.groupID;
        groupType = group.groupType;
        round = group.gameNoRound;
        number = group.gameNo;
        dealerName = group.dealerName;
        handler = LobbySource.getInstance().getGenHandler();


        if(group.timeMillisecond > 0){
            receive38(group.timeMillisecond);
        }


/*
        try {
            InputStream in = new java.net.URL(group.dealerImage).openStream();
            dealerImage = BitmapFactory.decodeStream(in);
        }catch(IOException e) {
            Log.e(dealerName + " BitError", e.getMessage());
        }

 */
        historyUpdate(tData.data);



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

    public void historyUpdate(TableData.Data data) {

        mainArr = data.historyArr;
        this.data = data.historyData;

        if(gameID == 101 ){


            apeSkull();
            for(int val: data.historyArr) divide(val);

         //   Log.e("stubby",sortedRoad.toString());
            mainRoad = new ItemRoad(bigRoad, 9);
            firstRoad = new ItemRoad(sortedRoad,"");



            /*
            apeSkull();
            for(int val: data.historyData.dataArr1BankerAsk) divide(val);
            mainRoadAsk1 = new ItemRoad(bigRoad, 9);
            firstRoadAsk1 = new ItemRoad(sortedRoad,"");
            apeSkull();
            for(int val: data.historyData.dataArr1PlayerAsk) divide(val);
            mainRoadAsk2 = new ItemRoad(bigRoad, 9);
            firstRoadAsk2 = new ItemRoad(sortedRoad,"");
            bigRoad = null;
            sortedRoad = null;
             */

            secondRoad = new ItemRoad(data.historyData.dataArr3);
            thirdRoad = new ItemRoad(data.historyData.dataArr4);
            fourthRoad = new ItemRoad(data.historyData.dataArr5);

/*
            secondRoadAsk1 = new ItemRoad(data.historyData.dataArr3BankerAsk,"");
            thirdRoadAsk1 = new ItemRoad(data.historyData.dataArr4BankerAsk,"");
            fourthRoadAsk1 = new ItemRoad(data.historyData.dataArr5BankerAsk,"");
            secondRoadAsk2 = new ItemRoad(data.historyData.dataArr3PlayerAsk,"");
            thirdRoadAsk2 = new ItemRoad(data.historyData.dataArr4PlayerAsk,"");
            fourthRoadAsk2 = new ItemRoad(data.historyData.dataArr5PlayerAsk,"");
 */

        } else if(gameID == 102 ){

            mainRoad =  new ItemRoad(data.historyArr, 9);
            firstRoad = new ItemRoad(data.historyData.dataArr2);
            secondRoad = new ItemRoad(data.historyData.dataArr3);
            thirdRoad = new ItemRoad(data.historyData.dataArr4);
            fourthRoad = new ItemRoad(data.historyData.dataArr5);

            /*

            mainRoadAsk1 = new ItemRoad(data.historyData.dataArr1DragonAsk, 9);
            firstRoadAsk1 = new ItemRoad(data.historyData.dataArr2DragonAsk,"");
            secondRoadAsk1 = new ItemRoad(data.historyData.dataArr3DragonAsk,"");
            thirdRoadAsk1 = new ItemRoad(data.historyData.dataArr4DragonAsk,"");
            fourthRoadAsk1 = new ItemRoad(data.historyData.dataArr5DragonAsk,"");
            mainRoadAsk2 = new ItemRoad(data.historyData.dataArr1TigerAsk, 9);
            firstRoadAsk2 = new ItemRoad(data.historyData.dataArr2TigerAsk,"");
            secondRoadAsk2 = new ItemRoad(data.historyData.dataArr3TigerAsk,"");
            thirdRoadAsk2 = new ItemRoad(data.historyData.dataArr4TigerAsk,"");
            fourthRoadAsk2 = new ItemRoad(data.historyData.dataArr5TigerAsk,"");

            */

        }else if(gameID == 111){

            firstRoad = new ItemRoad(data.historyData.dataArr2);

          //  apeSkull();
         //   for(int val: data.historyArr) divide(val);
          //  mainRoad = new ItemRoad(bigRoad, 9);
          //  firstRoad = new ItemRoad(sortedRoad,"");
          //  Log.e("sheboon", Arrays.deepToString(data.historyData.dataArr2).replaceAll("],", "]," + System.getProperty("line.separator")) );

        }else if(gameID == 128){

          //  mainRoad =  new ItemRoad(data.historyArr, 9);
            firstRoad = new ItemRoad(data.historyData.dataArr2);
            secondRoad = new ItemRoad(data.historyData.dataArr3);
            thirdRoad = new ItemRoad(data.historyData.dataArr4);
            fourthRoad = new ItemRoad(data.historyData.dataArr5);

        } else {

            mainRoad =  new ItemRoad(data.historyArr, 9);
            firstRoad = new ItemRoad(data.historyData.dataArr2);
            secondRoad = new ItemRoad(data.historyData.dataArr3);
            thirdRoad = new ItemRoad(data.historyData.dataArr4);
            fourthRoad = new ItemRoad(data.historyData.dataArr5);

        }

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

       if(gameID == 101) Log.e("stubby",Arrays.deepToString(firstRoad.road));


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




    private void apeSkull(){
        sortedRoad = new ArrayList<>();
        bigRoad = new ArrayList<>();
        preRes = 0;
        preWin = 0;
    }

    private List<List<Integer>> sortedRoad;
    private List<Integer> bigRoad;
    private List<Integer> tempRoad;
    private int preWin = 0;
    private int preRes = 0;
    private void divide(int rawVal){
        List<Integer> powers = new ArrayList<>();
        for(int i = 10; i >= 0; i-- ){
            int boss = (int) Math.pow(2,i);
            if(rawVal >= boss){
                powers.add(0,boss);
                rawVal = rawVal - boss;
                if(rawVal <= 0){
                    packRes(powers);
                    break;
                }
            }
        }
    }

    private void packRes(List<Integer> twos){

        int curRes;
        Integer curWin = twos.get(0);
        // int curBigRes;

        int curBig;

        if(curWin == 1){
            curRes = Road.Bank;
            //  curBigRes = R.drawable.casino_roadbank;
            curBig = 1;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    curRes = Road.Bank_B;
                    //     curBigRes = R.drawable.casino_roadbank_1;
                    curBig =  2;
                    if(twos.size() > 2 && twos.get(2) == 16){
                        curRes = Road.Bank_P_B;
                        //   curBigRes = R.drawable.casino_roadbank_3;
                        curBig = 4;
                    }
                }else if(twos.get(1) == 16){
                    curRes = Road.Bank_P;
                    //   curBigRes = R.drawable.casino_roadbank_2;
                    curBig = 3;
                }
            }
            //   mainRoad.add(curBigRes);
            bigRoad.add(curBig);
        }else if(curWin == 2){
            curRes = Road.Play;
            // curBigRes = R.drawable.casino_roadplay;
            curBig = 5;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    curRes = Road.Play_B;
                    // curBigRes = R.drawable.casino_roadplay_1;
                    curBig = 6;
                    if(twos.size() > 2 && twos.get(2) == 16) {
                        curRes = Road.Play_P_B;
                        //   curBigRes = R.drawable.casino_roadplay_3;
                        curBig = 8;
                    }
                }else if(twos.get(1) == 16){
                    curRes = Road.Play_P;
                    //  curBigRes = R.drawable.casino_roadplay_2;
                    curBig = 7;
                }
            }
            //     mainRoad.add(curBigRes);
            bigRoad.add(curBig);
        }else{
            //  curBigRes = R.drawable.casino_roadtie;
            curBig = 9;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    //  curBigRes = R.drawable.casino_roadtie_1;
                    curBig = 10;
                    if(twos.size() > 2 && twos.get(2) == 16){
                        //   curBigRes = R.drawable.casino_roadtie_3;
                        curBig = 12;
                    }
                }else if(twos.get(1) == 16){
                    //   curBigRes = R.drawable.casino_roadtie_2;
                    curBig = 11;
                }
            }
            //   mainRoad.add(curBigRes);
            bigRoad.add(curBig);

            if(preWin == 0) return;

            switch(preRes) {
                case Road.Bank:
                    tempRoad.set(tempRoad.size() -1 ,Road.Bank_E);
                    break;
                case Road.Bank_B:
                    tempRoad.set(tempRoad.size() -1 ,Road.Bank_B_E);
                    break;
                case Road.Bank_P:
                    tempRoad.set(tempRoad.size() -1 ,Road.Bank_P_E);
                    break;
                case Road.Bank_P_B:
                    tempRoad.set(tempRoad.size() -1 ,Road.Bank_P_B_E);
                    break;
                case Road.Play:
                    tempRoad.set(tempRoad.size() -1 ,Road.Play_E);
                    break;
                case Road.Play_B:
                    tempRoad.set(tempRoad.size() -1 ,Road.Play_B_E);
                    break;
                case Road.Play_P:
                    tempRoad.set(tempRoad.size() -1 ,Road.Play_P_E);
                    break;
                case Road.Play_P_B:
                    tempRoad.set(tempRoad.size() -1 ,Road.Play_P_B_E);
                    break;
            }
            return;
        }

        if( (curWin - preWin) != 0){
            tempRoad = new ArrayList<>();
            sortedRoad.add(tempRoad);
        }
        tempRoad.add(curRes);
        preRes = curRes;
        preWin = curWin;
    }

}
