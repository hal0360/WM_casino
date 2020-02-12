package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

import tw.com.lixin.wm_casino.dataModels.gameData.Game;


public class LobbyData {
    public int protocol;
    public Data data;

    public class Data {
        public List<Game> gameArr;
        public float balance;
        public int gameID;
        public int onlinePeople;
    }
}
