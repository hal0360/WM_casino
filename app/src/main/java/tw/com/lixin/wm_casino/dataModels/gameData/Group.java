package tw.com.lixin.wm_casino.dataModels.gameData;

import java.util.List;

import tw.com.lixin.wm_casino.dataModels.TableData;

public class Group {
    public int dealerID;
    public int gameNo;
    public int gameNoRound;
    public int gameStage;
    public int groupID;
    public int groupType;
    public String dealerImage;
    public String dealerName;
    public List<Integer> historyArr;
    public int timeMillisecond;
    public TableData.HisData historyData;


}

