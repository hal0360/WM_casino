package tw.com.lixin.wm_casino;

import android.media.MediaPlayer;

import java.util.List;

import tw.com.atromoby.utils.RegisterApplication;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.tools.CasinoSocket;

public class App extends RegisterApplication {


    public static MediaPlayer player;
    public static boolean musicOn;

    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.save_me);
        player.setLooping(true);
        musicOn = false;
    }

    public static void music_on(){
        player.seekTo(0);
        player.start();
        musicOn = true;
    }

    public static void music_off(){
        player.stop();
        musicOn = false;
    }

    public static void logout(){


    }

    public static void cleanSocketCalls(){

    }


}
