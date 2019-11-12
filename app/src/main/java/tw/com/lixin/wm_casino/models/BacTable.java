package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.TableLogData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.Road;

public class BacTable extends Table{

    public static ChipStackData playStackData, playPairStackData, tieStackData, bankStackData, bankPairStackData;
    public static boolean comission;
    public static void bacStarted(TableLogData.Data data){
        bankPairStackData = new ChipStackData();
        bankStackData = new ChipStackData();
        playPairStackData = new ChipStackData();
        tieStackData = new ChipStackData();
        playStackData = new ChipStackData();
        playStackData.score = data.dtOdds.get(2);
        bankStackData.score = data.dtOdds.get(1);
        playPairStackData.score = data.dtOdds.get(5);
        bankPairStackData.score = data.dtOdds.get(4);
        tieStackData.score = data.dtOdds.get(3);
        playStackData.maxValue = data.maxBet02;
        tieStackData.maxValue = data.maxBet03;
        playPairStackData.maxValue = data.maxBet04;
        bankStackData.maxValue = data.maxBet01;
        bankPairStackData.maxValue = data.maxBet04;
        comission = false;
    }

    public int bankCount = 0;
    public int playCount = 0;
    public int tieCount = 0;
    public int bankPairCount = 0;
    public int playPairCount = 0;
    public List<List<Integer>> sortedRoad;
    public List<Integer> mainRoad;
    public GridRoad firstGrid;
    public GridRoad secGrid;
    public GridRoad thirdGrid;
    public GridRoad fourthGrid;
    private List<Integer> tempRoad;
    private int preWin = 0;
    private int preRes = 0;
    public int playerScore, bankerScore;


    public BacTable(Group group) {
        super(group);
        gameID = 101;
    }

    public void cancelBets(){
        playPairStackData.cancelBet();
        playStackData.cancelBet();
        bankPairStackData.cancelBet();
        tieStackData.cancelBet();
        bankStackData.cancelBet();
    }

    public void rebetBets(){
        playPairStackData.repeatBet();
        playStackData.repeatBet();
        bankPairStackData.repeatBet();
        tieStackData.repeatBet();
        bankStackData.repeatBet();
    }

    public void confirmBets(){
        playPairStackData.comfirmBet();
        playStackData.comfirmBet();
        bankPairStackData.comfirmBet();
        tieStackData.comfirmBet();
        bankStackData.comfirmBet();
    }

    @Override
    public void historySetup(List<Integer> histories) {
        mainRoad = new ArrayList<>();
        sortedRoad = new ArrayList<>();
        for(int val: histories) divide(val);
        firstGrid = new GridRoad();
        firstGrid.setFirst(sortedRoad);
        secGrid = new GridRoad();
        secGrid.setSec(sortedRoad);
        thirdGrid = new GridRoad();
        thirdGrid.setThird(sortedRoad);
        fourthGrid = new GridRoad();
        fourthGrid.setFourth(sortedRoad);
    }

    @Override
    public void historyUpdate(TableData.Data data) {
        playCount = data.historyData.playerCount;
        bankCount = data.historyData.bankerCount;
        tieCount = data.historyData.tieCount;
        playPairCount = data.historyData.playerPairCount;
        bankPairCount = data.historyData.bankerPairCount;
    }

    @Override
    public void resultUpdate(TableData.Data data) {
        playerScore = data.playerScore;
        bankerScore = data.bankerScore;
    }

    @Override
    public void stageUpdate() {
        if(!gameBinded()) return;
        if(stage == 1){
            playPairStackData.clear();
            playStackData.clear();
            bankPairStackData.clear();
            tieStackData.clear();
            bankStackData.clear();
        }else if(stage == 2){
            playPairStackData.cancelBet();
            playStackData.cancelBet();
            bankPairStackData.cancelBet();
            tieStackData.cancelBet();
            bankStackData.cancelBet();
        }
    }





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
        int curBigRes;

        if(curWin == 1){
            curRes = Road.Bank;
            curBigRes = R.drawable.casino_roadbank;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    curRes = Road.Bank_B;
                    curBigRes = R.drawable.casino_roadbank_1;
                    if(twos.size() > 2 && twos.get(2) == 16){
                        curRes = Road.Bank_P_B;
                        curBigRes = R.drawable.casino_roadbank_3;
                    }
                }else if(twos.get(1) == 16){
                    curRes = Road.Bank_P;
                    curBigRes = R.drawable.casino_roadbank_2;
                }
            }
            mainRoad.add(curBigRes);
        }else if(curWin == 2){
            curRes = Road.Play;
            curBigRes = R.drawable.casino_roadplay;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    curRes = Road.Play_B;
                    curBigRes = R.drawable.casino_roadplay_1;
                    if(twos.size() > 2 && twos.get(2) == 16) {
                        curRes = Road.Play_P_B;
                        curBigRes = R.drawable.casino_roadplay_3;
                    }
                }else if(twos.get(1) == 16){
                    curRes = Road.Play_P;
                    curBigRes = R.drawable.casino_roadplay_2;
                }
            }
            mainRoad.add(curBigRes);
        }else{
            curBigRes = R.drawable.casino_roadtie;
            if(twos.size() > 1){
                if(twos.get(1) == 8){
                    curBigRes = R.drawable.casino_roadtie_1;
                    if(twos.size() > 2 && twos.get(2) == 16) curBigRes = R.drawable.casino_roadtie_3;
                }else if(twos.get(1) == 16) curBigRes = R.drawable.casino_roadtie_2;
            }
            mainRoad.add(curBigRes);

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

    public void askRoadFirst(int askWin){
        if(askWin == 1){
            firstGrid.drawRealAskFirst(preWin, askWin, Road.Bank);
        }else{
            firstGrid.drawRealAskFirst(preWin, askWin, Road.Play);
        }

    }

    public void askRoadSec(int askWin){
        if(sortedRoad.size()>1){
            if(askWin ==  preWin) {
                if(secGrid.blueWillWin){
                    secGrid.drawRealAsk(Road.Play);
                }else{
                    secGrid.drawRealAsk(Road.Bank);
                }
            }else{
                if(secGrid.blueWillWin){
                    secGrid.drawRealAsk(Road.Bank);
                }else{
                    secGrid.drawRealAsk(Road.Play);
                }
            }
        }
    }

    public void askRoadThird(int askWin){
        if(sortedRoad.size()>2){
            if(askWin ==  preWin) {
                if(thirdGrid.blueWillWin){
                    thirdGrid.drawRealAsk(Road.Play_S);
                }else{
                    thirdGrid.drawRealAsk(Road.Bank_S);
                }
            }else{
                if(thirdGrid.blueWillWin){
                    thirdGrid.drawRealAsk(Road.Bank_S);
                }else{
                    thirdGrid.drawRealAsk(Road.Play_S);
                }
            }
        }
    }

    public void askRoadFourth(int askWin){
        if(sortedRoad.size()>3){
            if(askWin ==  preWin) {
                if(fourthGrid.blueWillWin){
                    fourthGrid.drawRealAsk(Road.Play_I);
                }else{
                    fourthGrid.drawRealAsk(Road.Bank_I);
                }
            }else{
                if(fourthGrid.blueWillWin){
                    fourthGrid.drawRealAsk(Road.Bank_I);
                }else{
                    fourthGrid.drawRealAsk(Road.Play_I);
                }
            }
        }
    }

}