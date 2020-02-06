package tw.com.lixin.wm_casino.dataModels;

public class GameData {
    public int protocol;
    public Data data;

    public class Data {
        public int memberID;
        public int gameID = -99;
        public int groupID = -99;
        public Boolean bOk;
        public float moneyWin;
        public float balance;
        public int gameStage;

        public String userName;
        public int winRate;
        public int userCount;

        public int cardArea;
        public int cardID;
        public int bankerScore;
        public int playerScore;
        public int result;
    }
}
