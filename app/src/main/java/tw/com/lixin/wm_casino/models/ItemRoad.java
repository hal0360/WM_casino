package tw.com.lixin.wm_casino.models;


import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.global.Road;

public class ItemRoad {

    public int[][] road;
    public int maxX;


    ItemRoad(List<List<Integer>> arrs, String fuk){
        road = new int[300][6];

        int next = -1;
        int posX, posY;
        boolean horiMode;
        for (List<Integer> arr: arrs){
            horiMode = false;
            next++;
            posY = -1;
            while (road[next][0] != 0) next++;
            posX = next;
            for(int res: arr){

                if(horiMode) {
                    posX++;
                }else {
                    posY++;
                    if(posY > 5 || road[posX][posY] != 0) {
                        posY--;
                        posX++;
                        horiMode = true;
                    }
                }
                if(posX > maxX) maxX = posX;
                road[posX][posY] = res;

            }
        }
    }

    ItemRoad(List<Integer> arrs, int g){
        road = new int[300][6];

        int posX = 0;
        int posY = 0;
        for(int res: arrs){
            if(posY > 5) {
                posY = 0;
                posX++;
                if(posX > maxX) maxX = posX;
            }
            road[posX][posY] = res;
            posY++;
        }

    }

    @SuppressWarnings("unchecked")
    ItemRoad(List<Object> arrs){
        road = new int[300][6];
        int res;
        List<Double> arr;
        int next = -1;
        int posX, posY;
        boolean horiMode;
        try {
            for (Object obj: arrs){
                horiMode = false;
                next++;
                posY = -1;
                while (road[next][0] != 0) next++;
                posX = next;
                arr = (List<Double>) obj;
                for(double douRes: arr){
                    res =  (int) douRes;

                    if(horiMode) {
                        posX++;
                    }else {
                        posY++;
                        if(posY > 5 || road[posX][posY] != 0) {
                            posY--;
                            posX++;
                            horiMode = true;
                        }
                    }
                    if(posX > maxX) maxX = posX;
                    road[posX][posY] = res;
                }
            }
        }catch (Exception e){

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
                   // packRes(powers);
                    break;
                }
            }
        }
    }




}
