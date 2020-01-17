package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

public class TableData {
    public int protocol;
    public Data data;

    public TableData(){
        data = new Data();
        data.historyData = new HisData();
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
        public int bankerCount;
        public int playerCount;
        public int bankerPairCount;
        public int playerPairCount;
        public int dragonCount;
        public int tigerCount;
        public int tieCount;
    }
}
