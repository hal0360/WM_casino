package tw.com.lixin.wm_casino.dataModels;

import java.util.List;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;

public class Server35 {

    // public int protocol;

    public interface CmdData {
        void exec(Data data);
    }
    public Data data;

    public class Data{
        public List<Game> gameArr;
    }


}
