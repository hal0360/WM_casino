package tw.com.lixin.wm_casino.dataModels;


public class TableLogData {

    public int protocol;
    public Data data;

    public class Data {
        public int memberID;
        public int gameID = -99;
        public int groupID = -99;
        public Boolean bOk;
        public float balance;
       // public Map<Long, String> dtOdds;
        public int maxBet01;
        public int maxBet02;
        public int maxBet03;
        public int maxBet04;
        public int minBet01;
        public int minBet02;
        public int minBet03;
        public int minBet04;
    }

}
