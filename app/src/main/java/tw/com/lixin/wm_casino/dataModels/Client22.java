package tw.com.lixin.wm_casino.dataModels;

import java.util.ArrayList;
import java.util.List;

public class Client22 {

    public Data data;
    int protocol = 22;

    public Client22(int gameid, int groupid){
        data = new Data();
        data.gameID = gameid;
        data.groupID = groupid;
    }

    public class Data{
        public int gameID;
        public int groupID;
        public List<BetStack> betArr = new ArrayList<>();
        public int commission = 0;
    }

    public void addBet(long area, int val){
        BetStack betStack = new BetStack();
        betStack.addBetMoney = val;
        betStack.betArea = area;
        data.betArr.add(betStack);
    }

    public class BetStack{
        public long betArea;
        public int addBetMoney;

    }


}
