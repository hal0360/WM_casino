package tw.com.lixin.wm_casino.dataModels.gameData;

import com.google.gson.JsonObject;

import java.util.List;

public class Game {
    public int gameID;
    public JsonObject dtOdds;
    public List<Group> groupArr;
}
