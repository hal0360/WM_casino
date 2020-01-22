package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;

public class RouletteTable extends Table {

    public List<RoadItem> firstRoad;
    public List<RoadItem> secondRoad;
    public List<RoadItem> thirdRoad;
    public List<RoadItem> fourthRoad;

    public RouletteTable(Group group) {
        super(group);
        gameID = 103;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void historyUpdate(TableData.Data data) {
        firstRoad = new ArrayList<>();
        secondRoad = new ArrayList<>();
        thirdRoad = new ArrayList<>();
        fourthRoad = new ArrayList<>();

        int posX = 0;
        int posY = 0;
        for(int num: data.historyArr){
            if(posY > 5) {
                posX++;
                posY = 0;
            }
            RoadItem item = new RoadItem();
            item.x = posX;
            item.y = posY;
            item.word = num + "";
            if(num == 0) item.resID = R.drawable.green_ball;
            else if ( (num & 1) == 0 ) item.resID = R.drawable.black_ball;
            else item.resID = R.drawable.dark_red_ball;
            firstRoad.add(0, item);
            posY++;
        }

        int next = -1;
        int[][] road = new int[200][6];
        int res;
        List<Double> arr;
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
                if(res == 1){
                    item.strID = R.string.red;
                    item.color = 0xFFFF0000;
                }
                else if(res == 2) {
                    item.strID = R.string.black;
                    item.color = 0xFF000000;
                }
                else {
                    item.strID = R.string.green;
                    item.color = 0xFF7CFC00;
                }
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
                if(res == 1){
                    item.strID = R.string.big;
                    item.color = 0xFFFF0000;
                }
                else if(res == 2) {
                    item.strID = R.string.small;
                    item.color = 0xFF000000;
                }
                else {
                    item.strID = R.string.oh;
                    item.color = 0xFF7CFC00;
                }
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
                if(res == 1){
                    item.strID = R.string.odd;
                    item.color = 0xFFFF0000;
                }
                else if(res == 2) {
                    item.strID = R.string.even;
                    item.color = 0xFF000000;
                }
                else {
                    item.strID = R.string.oh;
                    item.color = 0xFF7CFC00;
                }
                fourthRoad.add(0,item);
            }
        }
    }
}
