package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

public class ChipStackData {
    public int value = 0;
    public int maxValue = 999;

    public List<Chip> addedCoin = new ArrayList<>();
    public List<Chip> tempAddedCoin = new ArrayList<>();

    public void clear(){
        value = 0;
        addedCoin = new ArrayList<>();
        tempAddedCoin = new ArrayList<>();
    }

    public void comfirmBet(){
        addedCoin.addAll(tempAddedCoin);
        tempAddedCoin = new ArrayList<>();
    }


    public boolean add(Chip coin){
        value = value + coin.value;
        if(value > maxValue){
            value = value - coin.value;
            return false;
        }
        tempAddedCoin.add(coin);
        return true;
    }

    public void cancelBet(){
        value = 0;
        tempAddedCoin = new ArrayList<>();
        for(Chip coin: addedCoin) add(coin);
    }

    public void repeatBet(){
        for(Chip coin: addedCoin){
            add(coin);
        }
    }
}
