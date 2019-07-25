package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;




public class CoinStackData {
    public int hit = 0;
    public int value = 0;
    public int maxValue = 9999;
    public int minValue = 1;

    public List<Coin> addedCoin = new ArrayList<>();
    public List<Coin> tempAddedCoin = new ArrayList<>();
}
