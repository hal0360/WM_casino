package tw.com.lixin.wm_casino;

import java.util.List;

import tw.com.atromoby.utils.RegisterApplication;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.tools.CasinoSocket;

public class App extends RegisterApplication {

    public static CasinoSocket socket;
    public static List<Game> games;

    @Override
    public void onCreate() {
        super.onCreate();

        socket = new CasinoSocket();
    }

    public static void logout(){


    }

    public static void cleanSocketCalls(){

    }


}
