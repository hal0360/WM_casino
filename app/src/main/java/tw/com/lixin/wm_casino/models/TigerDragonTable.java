package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.Road;

public class TigerDragonTable {

    public List<List<Integer>> sortedRoad;
    public List<Integer> mainRoad;
    public GridRoad firstGrid;
    public GridRoad secGrid;
    public GridRoad thirdGrid;
    public GridRoad fourthGrid;
    private List<Integer> tempRoad;
    private int preWin = 0;
    private int preRes = 0;



    private void packRes(int curWin){

        int curRes;

        int curBigRes;

        if(curWin == 1){
            curRes = Road.Bank;
            curBigRes = R.drawable.casino_roadbank;
            mainRoad.add(curBigRes);
        }else if(curWin == 2){
            curRes = Road.Play;
            curBigRes = R.drawable.casino_roadplay;
            mainRoad.add(curBigRes);
        }else{
            curBigRes = R.drawable.casino_roadtie;
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
      //  tempRoad.add(curRes);
       // preRes = curRes;
        preWin = curWin;
    }

}
