package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

public class TableData {
    public int protocol;
    public Data data;

    public class Data {
        public int gameID = -99;
        public int groupID = -99;

        public int gameStage;
        public int cardArea;
        public int cardID;
        public int bankerScore;
        public int playerScore;
        public int result;
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
