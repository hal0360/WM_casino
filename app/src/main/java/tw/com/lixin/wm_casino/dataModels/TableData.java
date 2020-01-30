package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

public class TableData {
    public int protocol;
    public Data data;

    public TableData(){
        data = new Data();
    }

    public class Data {
        public int gameID = -99;
        public int groupID = -99;
        public int gameStage;

        public List<Integer> historyArr;
        public HisData historyData;
        public int groupType;
        public int timeMillisecond;

        public int dealerID;
        public int gameNo;
        public int gameNoRound;
        public String dealerImage;
        public String dealerName;
    }

    public class HisData{
        public List<Object> dataArr2;
        public List<Object> dataArr3;
        public List<Object> dataArr4;
        public List<Object> dataArr5;
        public int bankerCount;
        public int playerCount;
        public int bankerPairCount;
        public int playerPairCount;
        public int dragonCount;
        public int tigerCount;
        public int tieCount;
        public List<Integer> dataArr1DragonAsk;
        public List<Object> dataArr2DragonAsk;
        public List<Object> dataArr3DragonAsk;
        public List<Object> dataArr4DragonAsk;
        public List<Object> dataArr5DragonAsk;
        public List<Integer> dataArr1TigerAsk;
        public List<Object> dataArr2TigerAsk;
        public List<Object> dataArr3TigerAsk;
        public List<Object> dataArr4TigerAsk;
        public List<Object> dataArr5TigerAsk;
        public int dragonAsk3;
        public int dragonAsk4;
        public int dragonAsk5;
        public int tigerAsk3;
        public int tigerAsk4;
        public int tigerAsk5;
        public List<Integer> dataArr1BankerAsk;
        public List<Object> dataArr2BankerAsk;
        public List<Object> dataArr3BankerAsk;
        public List<Object> dataArr4BankerAsk;
        public List<Object> dataArr5BankerAsk;
        public List<Integer> dataArr1PlayerAsk;
        public List<Object> dataArr2PlayerAsk;
        public List<Object> dataArr3PlayerAsk;
        public List<Object> dataArr4PlayerAsk;
        public List<Object> dataArr5PlayerAsk;
        public int bankerAsk3;
        public int bankerAsk4;
        public int bankerAsk5;
        public int playerAsk3;
        public int playerAsk4;
        public int playerAsk5;
    }
}
