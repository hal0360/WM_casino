package tw.com.lixin.wm_casino;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.utils.RegisterApplication;
import tw.com.lixin.wm_casino.collections.AndaCollection;
import tw.com.lixin.wm_casino.collections.BacCollection;
import tw.com.lixin.wm_casino.collections.DragonTigerCollection;
import tw.com.lixin.wm_casino.collections.FantanCollection;
import tw.com.lixin.wm_casino.collections.FishPrawnCollection;
import tw.com.lixin.wm_casino.collections.GoldenFlowerCollection;
import tw.com.lixin.wm_casino.collections.NiuCollection;
import tw.com.lixin.wm_casino.collections.RouletteCollection;
import tw.com.lixin.wm_casino.collections.SamgongCollection;
import tw.com.lixin.wm_casino.collections.SeDieCollection;
import tw.com.lixin.wm_casino.collections.SicBoCollection;
import tw.com.lixin.wm_casino.interfaces.CmdCollection;
import tw.com.lixin.wm_casino.interfaces.CmdTable;
import tw.com.lixin.wm_casino.tools.LocaleUtils;
import tw.com.lixin.wm_casino.websocketSource.GameSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;
import tw.com.lixin.wm_casino.websocketSource.MessageSource;

public class App extends RegisterApplication {

    private static MediaPlayer player, click, bet, control;
    public static boolean musicOn, effectOn;
    public static List<Integer> flush;
    public static List<Integer> niu;
    public static SparseArray<String> sam;
    private static App app;
    public static SparseIntArray appNames;
    public static SparseArray<CmdCollection> collectionProvider;
    public static SparseArray<CmdTable> tableProvider;
    public static SparseIntArray emos;
    public static SparseIntArray examples;



    public static void switchLanguage(Locale locale){
        LocaleUtils.setLocale(locale);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        LocaleUtils.setLocale(Locale.US);
        examples = new SparseIntArray();
        examples.put(1,R.string.mss_1);
        examples.put(2,R.string.mss_2);
        examples.put(3,R.string.mss_3);
        examples.put(4,R.string.mss_4);
        examples.put(5,R.string.mss_5);
        examples.put(6,R.string.mss_6);
        examples.put(7,R.string.mss_7);
        examples.put(8,R.string.mss_8);

        emos = new SparseIntArray();
        emos.put(1,R.drawable.emo1);
        emos.put(2,R.drawable.emo2);
        emos.put(3,R.drawable.emo3);
        emos.put(4,R.drawable.emo4);
        emos.put(5,R.drawable.emo5);
        emos.put(6,R.drawable.emo6);
        emos.put(7,R.drawable.emo7);
        emos.put(8,R.drawable.emo8);
        emos.put(9,R.drawable.emo9);
        emos.put(10,R.drawable.emo10);
        emos.put(11,R.drawable.emo11);
        emos.put(12,R.drawable.emo12);
        emos.put(13,R.drawable.emo13);
        emos.put(14,R.drawable.emo14);
        emos.put(15,R.drawable.emo15);

        tableProvider = new SparseArray<>();
        tableProvider.put(101, BacCollection::new);
        tableProvider.put(102, DragonTigerCollection::new);
        tableProvider.put(103, RouletteCollection::new);
        tableProvider.put(104, SicBoCollection::new);
        tableProvider.put(105, NiuCollection::new);
        tableProvider.put(106, SamgongCollection::new);
        tableProvider.put(107, FantanCollection::new);
        tableProvider.put(108, SeDieCollection::new);
        tableProvider.put(110, FishPrawnCollection::new);
        tableProvider.put(111, GoldenFlowerCollection::new);
        tableProvider.put(128, AndaCollection::new);

        collectionProvider = new SparseArray<>();
        collectionProvider.put(101, BacCollection::new);
        collectionProvider.put(102, DragonTigerCollection::new);
        collectionProvider.put(103, RouletteCollection::new);
        collectionProvider.put(104, SicBoCollection::new);
        collectionProvider.put(105, NiuCollection::new);
        collectionProvider.put(106, SamgongCollection::new);
        collectionProvider.put(107, FantanCollection::new);
        collectionProvider.put(108, SeDieCollection::new);
        collectionProvider.put(110, FishPrawnCollection::new);
        collectionProvider.put(111, GoldenFlowerCollection::new);
        collectionProvider.put(128, AndaCollection::new);

        appNames = new SparseIntArray();
        appNames.put(101,R.string.baccarat);
        appNames.put(102,R.string.dragon_tiger);
        appNames.put(103,R.string.roulette);
        appNames.put(104,R.string.sic_bo);
        appNames.put(105,R.string.niuniu);
        appNames.put(106,R.string.samgong);
        appNames.put(107,R.string.fantan);
        appNames.put(108,R.string.se_die);
        appNames.put(110,R.string.fish_prawn_crab);
        appNames.put(111,R.string.golden_flower);
        appNames.put(128,R.string.anda_baha);

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

    public static App getThisApp(){
        return app;
    }

    public void lobbyFail(){
        Kit.alert(this,"Lobby disconnected");
        LobbySource.getInstance().logout();
        GameSource.getInstance().tableLogout();
        MessageSource.getInstance().logout();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
