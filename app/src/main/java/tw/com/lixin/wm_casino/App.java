package tw.com.lixin.wm_casino;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import tw.com.atromoby.utils.RegisterApplication;

public class App extends RegisterApplication {

    private static MediaPlayer player, click, bet, control;
    public static boolean musicOn, effectOn;


    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.save_me);
        player.setLooping(true);
        musicOn = false;
        effectOn = true;

        click = MediaPlayer.create(this, R.raw.click);
        click.setLooping(false);

        bet = MediaPlayer.create(this, R.raw.bet);
        bet.setLooping(false);

        control = MediaPlayer.create(this, R.raw.control);
        control.setLooping(false);

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
        if(effectOn){
            click.seekTo(0);
            click.start();
        }
    }

    public static void betting(){
        if(effectOn){
            bet.seekTo(0);
            bet.start();
        }
    }

    public static void controlling(){
        if(effectOn){
            control.seekTo(0);
            control.start();
        }
    }

    public static int dpToPixel(float dp, Context context){
        return (int) dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
