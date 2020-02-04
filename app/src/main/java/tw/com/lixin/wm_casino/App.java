package tw.com.lixin.wm_casino;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.RegisterApplication;

public class App extends RegisterApplication {

    private static MediaPlayer player, click, bet, control;
    public static boolean musicOn, effectOn;


    public static List<Integer> flush;

    @Override
    public void onCreate() {
        super.onCreate();

        flush = new ArrayList<>();
        flush.add(160);
        flush.add(128);
        flush.add(96);
        flush.add(64);
        flush.add(32);
        flush.add(0);

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


    public static int[] getSamgong( int result){
        for(int b = 1; b < 32; b++){
            for(int bf: flush){
                for(int p1 = 1; p1 < 32; p1++){
                    for(int p1f: flush){
                        for(int p2 = 1; p2 < 32; p2++){
                            for(int p2f: flush){
                                for(int p3 = 1; p3 < 32; p3++){
                                    for(int p3f: flush){
                                        if((b+bf + (p1+p1f)*200 + (p2+p2f)*40000 + (p3+p3f)*8000000) == result) return new int[]{b, p1, p2, p3};
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new int[]{0, 0, 0, 0};
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
