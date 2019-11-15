package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.Road;

public class TigerDragonTable extends Table{

    public int tigerCount = 0;
    public int dragonCount = 0;
    public int tieCount = 0;
    public List<List<Integer>> sortedRoad;
    public List<Integer> mainRoad;
    public GridRoad firstGrid;
    public GridRoad secGrid;
    public GridRoad thirdGrid;
    public GridRoad fourthGrid;
    private List<Integer> tempRoad;
    private int preWin = 0;
    public int tigerScore, dragonScore, pokerWin = -1;


    public TigerDragonTable(Group group) {
        super(group);
        gameID = 102;
    }

    private void packRes(int curWin){

        int curRes = 0;
        int curBigRes;

        if(curWin == 1){
            curRes = Road.Bank;
            curBigRes = R.drawable.dragon_dragon;
            mainRoad.add(curBigRes);
        }else if(curWin == 2){
            curRes = Road.Play;
            curBigRes = R.drawable.dragon_tiger;
            mainRoad.add(curBigRes);
        }else{
            curBigRes = R.drawable.dragon_tie;
            mainRoad.add(curBigRes);
            switch(preWin) {
                case 0:
                    return;
                case 1:
                    tempRoad.set(tempRoad.size() -1 ,Road.Bank_E);
                    return;
                case 2:
                    tempRoad.set(tempRoad.size() -1 ,Road.Play_E);
                    return;
            }
        }

        if( (curWin - preWin) != 0){
            tempRoad = new ArrayList<>();
            sortedRoad.add(tempRoad);
        }
        tempRoad.add(curRes);
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

    @Override
    public void historyUpdate(TableData.Data data) {
        mainRoad = new ArrayList<>();
        sortedRoad = new ArrayList<>();
        for(int val: data.historyArr) packRes(val);
        firstGrid = new GridRoad();
        firstGrid.setFirst(sortedRoad);
        secGrid = new GridRoad();
        secGrid.setSec(sortedRoad);
        thirdGrid = new GridRoad();
        thirdGrid.setThird(sortedRoad);
        fourthGrid = new GridRoad();
        fourthGrid.setFourth(sortedRoad);
        tigerCount = data.historyData.tigerCount;
        dragonCount = data.historyData.dragonCount;
        tieCount = data.historyData.tieCount;
    }

    @Override
    public void resultUpdate(TableData.Data data) {
        pokerWin = Table.resDivide(data.result);
        tigerScore = data.playerScore;
        dragonScore = data.bankerScore;
    }

}
