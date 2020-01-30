package tw.com.lixin.wm_casino.models;

import android.util.Log;

import java.util.List;

public class ItemRoad {

    public int posX = -1;
    public int[][] road;

    /*
    public ItemRoad(List<List<Integer>> arrs, int g){
        road = new int[300][6];

        for(List<Integer> arr : arrs){
            next++;
            posX = next;
            posY = -1;
            for(int res: arr){
                posY++;
                if(posY > 5 || road[posX][posY] != 0) posY--;
                while (road[posX][posY] != 0) posX++;
                road[posX][posY] = res;
            }
        }

    }*/

    @SuppressWarnings("unchecked")
    ItemRoad(List<Object> arrs){
        road = new int[300][6];
        int res;
        List<Double> arr;
        int next = -1;
        boolean horiMode = false;
        try {
            for (Object obj: arrs){
                next++;
                posX = next;
                int posY = -1;
                arr = (List<Double>) obj;
                for(double douRes: arr){
                    res =  (int) douRes;
                    posY++;
                    if(posY > 5 || road[posX][posY] != 0) {
                        posY--;
                        horiMode = true;
                    }
                    while (road[posX][posY] != 0) posX++;
                    road[posX][posY] = res;
                }
            }
        }catch (Exception e){

        }
    }
}
