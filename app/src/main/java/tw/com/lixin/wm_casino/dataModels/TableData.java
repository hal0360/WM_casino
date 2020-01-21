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
    }
}
