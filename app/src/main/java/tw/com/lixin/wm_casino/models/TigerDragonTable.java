package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.global.Road;

public class TigerDragonTable extends Table{

    public int tigerCount = 0;
    public int dragonCount = 0;
    public int tieCount = 0;

    public List<Integer> mainRoad;
    public List<RoadItem> firstRoad;
    public List<RoadItem> secondRoad;
    public List<RoadItem> thirdRoad;
    public List<RoadItem> fourthRoad;


    public TigerDragonTable(Group group) {
        super(group);
        gameID = 102;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void historyUpdate(TableData.Data data) {
        firstRoad = new ArrayList<>();
        secondRoad = new ArrayList<>();
        thirdRoad = new ArrayList<>();
        fourthRoad = new ArrayList<>();
        mainRoad = data.historyArr;
        int[][] road = new int[200][6];
        int posX;
        int posY;
        int next = -1;
        int res;
        List<Double> arr;
        for (Object obj: data.historyData.dataArr2){
            next++;
            posX = next;
            posY = -1;
            arr = (List<Double>) obj;
            for(double douRes: arr){
                res =  (int) douRes;
                posY++;
                if(posY > 5 || road[posX][posY] != 0) posY--;
                while (road[posX][posY] != 0) posX++;
                road[posX][posY] = res;
                RoadItem item = new RoadItem();
                item.x = posX;
                item.y = posY;
                if(res == 1) item.resID = Road.Bank;
                else if(res == 2) item.resID = Road.Play;
                else if(res == 5) item.resID = Road.Bank_E;
                else item.resID = Road.Play_E;
                firstRoad.add(0,item);
            }
        }
        next = -1;
        road = new int[200][6];
        for (Object obj: data.historyData.dataArr3){
            next++;
            posX = next;
            posY = -1;
            arr = (List<Double>) obj;
            for(double douRes: arr){
                res =  (int) douRes;
                posY++;
                if(posY > 5 || road[posX][posY] != 0) posY--;
                while (road[posX][posY] != 0) posX++;
                road[posX][posY] = res;
                RoadItem item = new RoadItem();
                item.x = posX;
                item.y = posY;
                if(res == 1) item.resID = Road.Bank;
                else if(res == 2) item.resID = Road.Play;
                secondRoad.add(0,item);
            }
        }
        next = -1;
        road = new int[200][6];
        for (Object obj: data.historyData.dataArr4){
            next++;
            posX = next;
            posY = -1;
            arr = (List<Double>) obj;
            for(double douRes: arr){
                res =  (int) douRes;
                posY++;
                if(posY > 5 || road[posX][posY] != 0) posY--;
                while (road[posX][posY] != 0) posX++;
                road[posX][posY] = res;
                RoadItem item = new RoadItem();
                item.x = posX;
                item.y = posY;
                if(res == 1) item.resID = Road.Bank_S;
                else if(res == 2) item.resID = Road.Play_S;
                thirdRoad.add(0,item);
            }
        }
        next = -1;
        road = new int[200][6];
        for (Object obj: data.historyData.dataArr5){
            next++;
            posX = next;
            posY = -1;
            arr = (List<Double>) obj;
            for(double douRes: arr){
                res =  (int) douRes;
                posY++;
                if(posY > 5 || road[posX][posY] != 0) posY--;
                while (road[posX][posY] != 0) posX++;
                road[posX][posY] = res;
                RoadItem item = new RoadItem();
                item.x = posX;
                item.y = posY;
                if(res == 1) item.resID = Road.Bank_I;
                else if(res == 2) item.resID = Road.Play_I;
                fourthRoad.add(0,item);
            }
        }
        tigerCount = data.historyData.tigerCount;
        dragonCount = data.historyData.dragonCount;
        tieCount = data.historyData.tieCount;
    }
}
