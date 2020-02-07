package tw.com.lixin.wm_casino.dataModels;

import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.BaccaratActivity;

public class Client22 {

    public Data data;
    int protocol = 22;

    public Client22(int gameid, int groupid){
        data = new Data();
        data.gameID = gameid;
        data.groupID = groupid;
        data.commission = BaccaratActivity.comission;
    }

    public class Data{
        public int gameID;
        public int groupID;
        public List<BetStack> betArr = new ArrayList<>();
        int commission;
    }

    public void addBet(long area, int val){
        BetStack betStack = new BetStack();
        betStack.addBetMoney = val;
        betStack.betArea = area;
        data.betArr.add(betStack);
    }

    public class BetStack{
        long betArea;
        int addBetMoney;

    }


}
