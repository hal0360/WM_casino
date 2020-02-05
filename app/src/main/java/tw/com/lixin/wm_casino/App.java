package tw.com.lixin.wm_casino;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.RegisterApplication;

public class App extends RegisterApplication {

    private static MediaPlayer player, click, bet, control;
    public static boolean musicOn, effectOn;


    public static List<Integer> flush;
    public static List<Integer> niu;


    public static SparseArray<String> sam;


    @Override
    public void onCreate() {
        super.onCreate();

        sam = new SparseArray<>();
        sam.put(1,"0");
        sam.put(2,"p0");
        sam.put(3,"2p0");
        sam.put(4,"1");
        sam.put(5,"p1");
        sam.put(6,"2p1");
        sam.put(7,"2");
        sam.put(8,"p2");
        sam.put(9,"2p2");
        sam.put(10,"3");
        sam.put(11,"p3");
        sam.put(12,"2p3");
        sam.put(13,"4");
        sam.put(14,"p4");
        sam.put(15,"2p4");
        sam.put(16,"5");
        sam.put(17,"p5");
        sam.put(18,"2p5");
        sam.put(19,"6");
        sam.put(20,"p6");
        sam.put(21,"2p6");
        sam.put(22,"7");
        sam.put(23,"p7");
        sam.put(24,"2p7");
        sam.put(25,"8");
        sam.put(26,"p8");
        sam.put(27,"2p8");
        sam.put(28,"9");
        sam.put(29,"p9");
        sam.put(30,"2p9");
        sam.put(31,"3p");


        niu = new ArrayList<>();
        niu.add(20);
        niu.add(0);

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


    public static int[] getNiutemp(int res){
        for(int q = 1; q < 32; q++){
            for(int qf: niu){
                if((q + qf) == res) return new int[]{q,qf};
            }
        }
        return new int[]{0,0};
    }

    public static List<int[]> getNiuniu( int result){
        int remP3 = result%1000000;
        int remP2 = remP3%10000;
        int remP1 = remP2%100;
        int qp3 = result/1000000;
        int qp2 = remP3/10000;
        int qp1 = remP2/100;

        List<int[]> raw = new ArrayList<>();
        raw.add(getNiutemp(qp1));
        raw.add(getNiutemp(qp2));
        raw.add(getNiutemp(qp3));
        raw.add(getNiutemp(remP1));

        return raw;
    }

    public static int[] getSamtemp(int res){
        for(int q = 1; q < 32; q++){
            for(int qf: flush){
                if((q + qf) == res) return new int[]{q,qf};
            }
        }
        return new int[]{0,0};
    }

    public static List<int[]> getSamgong( int result){
        int remP3 = result%8000000;
        int remP2 = remP3%40000;
        int remP1 = remP2%200;
        int qp3 = result/8000000;
        int qp2 = remP3/40000;
        int qp1 = remP2/200;

        List<int[]> raw = new ArrayList<>();
        raw.add(getSamtemp(qp1));
        raw.add(getSamtemp(qp2));
        raw.add(getSamtemp(qp3));
        raw.add(getSamtemp(remP1));

        return raw;
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
