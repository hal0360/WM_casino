package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.Road;

public class BacTable extends Table{

    public boolean comission = false;
    public CoinStackData stackLeft, stackRight, stackBTL, stackBTR, stackTop, stackSuper;
    public String tableRightScore, tableLeftScore, tableTopScore, tableBtlScore, tableBtrScore;
    public int pokerWin = -1;
    public int maxBetVal;
    public int playerScore, bankerScore;


    public int bankCount = 1;
    public int playCount = 1;
    public int tieCount = 1;
    public int bankPairCount = 1;
    public int playPairCount = 1;
    public List<List<Integer>> sortedRoad;
    public List<Integer> mainRoad;
    public GridRoad firstGrid;
    public GridRoad secGrid;
    public GridRoad thirdGrid;
    public GridRoad fourthGrid;
    private List<Integer> tempRoad;
    private int preWin = 0;
    private int preRes = 0;

    public BacTable(Group group) {
        super(group);
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
    public void loginSetup(TableData.Data data) {
        stackSuper = new CoinStackData();
        stackTop = new CoinStackData();
        stackBTR = new CoinStackData();
        stackRight = new CoinStackData();
        stackBTL = new CoinStackData();
        stackLeft = new CoinStackData();
        tableLeftScore = data.dtOdds.get(2);
        tableRightScore = data.dtOdds.get(1);
        tableBtlScore = data.dtOdds.get(5);
        tableBtrScore = data.dtOdds.get(4);
        tableTopScore = data.dtOdds.get(3);
        stackLeft.maxValue = data.maxBet02;
        stackBTL.maxValue = data.maxBet04;
        stackRight.maxValue = data.maxBet01;
        stackBTR.maxValue = data.maxBet04;
        stackTop.maxValue = data.maxBet03;
        stackSuper.maxValue = data.maxBet04;
        maxBetVal = data.maxBet01;
        if(maxBetVal < data.maxBet02) maxBetVal = data.maxBet02;
        if(maxBetVal < data.maxBet03) maxBetVal = data.maxBet03;
        if(maxBetVal < data.maxBet04) maxBetVal = data.maxBet04;
    }

    @Override
    public void resultUpdate(TableData.Data data) {
        //pokerWin = Move.divide(data.result);
        playerScore = data.playerScore;
        bankerScore = data.bankerScore;
    }

    @Override
    public void update(TableData.Data data){
        playCount = data.historyData.playerCount;
        bankCount = data.historyData.bankerCount;
        tieCount = data.historyData.tieCount;
        playPairCount = data.historyData.playerPairCount;
        bankPairCount = data.historyData.bankerPairCount;
        super.update(data);
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