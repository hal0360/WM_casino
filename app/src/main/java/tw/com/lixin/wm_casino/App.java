package tw.com.lixin.wm_casino;

import android.media.MediaPlayer;

import tw.com.atromoby.utils.RegisterApplication;

public class App extends RegisterApplication {


    public static MediaPlayer player, click;
    public static boolean musicOn;

    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.save_me);
        player.setLooping(true);
        musicOn = false;

        click = MediaPlayer.create(this, R.raw.click);
        click.setLooping(false);
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

    public static void clicking(){
        click.seekTo(0);
        click.start();
    }



}
