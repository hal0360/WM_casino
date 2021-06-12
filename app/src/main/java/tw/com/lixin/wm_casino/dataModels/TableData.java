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
        public int oddCount;
        public int evenCount;
        public int bigCount;
        public int smallCount;
        public int player1Count;
        public int player2Count;
        public int player3Count;
        public int number1Count;
        public int number2Count;
        public int number3Count;
        public int zeroCount;
        public int phoenixCount;
        public int andarCount;
        public int baharCount;

        public List<Integer> dataArr1DragonAsk;
        public List<List<Integer>> dataArr2DragonAsk;
        public List<List<Integer>> dataArr3DragonAsk;
        public List<List<Integer>> dataArr4DragonAsk;
        public List<List<Integer>> dataArr5DragonAsk;
        public List<Integer> dataArr1TigerAsk;
        public List<List<Integer>> dataArr2TigerAsk;
        public List<List<Integer>> dataArr3TigerAsk;
        public List<List<Integer>> dataArr4TigerAsk;
        public List<List<Integer>> dataArr5TigerAsk;
        public int dragonAsk3;
        public int dragonAsk4;
        public int dragonAsk5;
        public int tigerAsk3;
        public int tigerAsk4;
        public int tigerAsk5;
        public List<Integer> dataArr1BankerAsk;
        public List<List<Integer>> dataArr3BankerAsk;
        public List<List<Integer>> dataArr4BankerAsk;
        public List<List<Integer>> dataArr5BankerAsk;
        public List<Integer> dataArr1PlayerAsk;
        public List<List<Integer>> dataArr3PlayerAsk;
        public List<List<Integer>> dataArr4PlayerAsk;
        public List<List<Integer>> dataArr5PlayerAsk;
        public int bankerAsk3;
        public int bankerAsk4;
        public int bankerAsk5;
        public int playerAsk3;
        public int playerAsk4;
        public int playerAsk5;
    }
}
