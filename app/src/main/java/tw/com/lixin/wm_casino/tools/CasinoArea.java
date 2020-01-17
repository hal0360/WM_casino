package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.popups.MessagePopup;
import tw.com.lixin.wm_casino.popups.NumberPadDialog;
import tw.com.lixin.wm_casino.popups.PeoplePopup;
import tw.com.lixin.wm_casino.popups.SignalPopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.tools.buttons.ControlButton;
import tw.com.lixin.wm_casino.tools.chips.ChipView;

public class CasinoArea extends ConstraintLayout implements View.OnClickListener{

    private ProfileBar bar;
    private TextView gyuShu, countdown, member, cusChipTxt, pplTxt;
    private ImageView dealImg;
    private ChipView selectedChip;
    private CollectionsView mssList;
    private CasinoActivity activity;
    private IjkVideoView video;
    public ControlButton confirmBtn, cancelBtn, rebetBtn;

    public static Chip curChip;


    public CasinoArea(Context context) {super(context);}

    @SuppressLint("SetTextI18n")
    public CasinoArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.casino_area, this);

        member = findViewById(R.id.member);
        bar = findViewById(R.id.profile);
        countdown = findViewById(R.id.count_txt);
        dealImg = findViewById(R.id.dealer_img);
        mssList = findViewById(R.id.mss_list);
        ConstraintLayout cusChip = findViewById(R.id.custom_chip);
        cusChipTxt = findViewById(R.id.custom_num_txt);
        gyuShu = findViewById(R.id.gyu_shu);
        confirmBtn =  findViewById(R.id.confirm_btn);
        cancelBtn =  findViewById(R.id.cancel_btn);
        rebetBtn =  findViewById(R.id.rebet_btn);
        pplTxt = findViewById(R.id.ppl_num_txt);

        findViewById(R.id.chip1).setOnClickListener(this);
        findViewById(R.id.chip5).setOnClickListener(this);
        findViewById(R.id.chip50).setOnClickListener(this);
        findViewById(R.id.chip100).setOnClickListener(this);
        findViewById(R.id.chip10).setOnClickListener(this);
        findViewById(R.id.chip20).setOnClickListener(this);
        findViewById(R.id.chip500).setOnClickListener(this);
        findViewById(R.id.chip1000).setOnClickListener(this);
        findViewById(R.id.chip5000).setOnClickListener(this);
        findViewById(R.id.chip10000).setOnClickListener(this);
        if(curChip != null){
            if(curChip.resId == -99) {
                selectedChip = findViewById(R.id.chip10);
                cusChipTxt.setText(curChip.value+"");
            }else {
                selectedChip = findViewById(curChip.resId);
                selectedChip.turnOn();
            }
        }else {
            selectedChip = findViewById(R.id.chip10);
            selectedChip.turnOn();
            curChip = selectedChip.getChip();
        }

        ClickText limitBtn = findViewById(R.id.limit_btn);
        limitBtn.clicked(v-> activity.showPopup( new LimitPopup()));
        ClickImage mssBtn = findViewById(R.id.mss_btn);
        mssBtn.clicked(v-> activity.showPopup(new MessagePopup()));
        ClickImage peopleBtn = findViewById(R.id.people_btn);
        peopleBtn.clicked(v-> activity.showPopup(new PeoplePopup()));
        ClickImage signalBtn = findViewById(R.id.signal_btn);
        signalBtn.clicked(v-> activity.showPopup(new SignalPopup()));

        cusChip.setOnClickListener(v->{
            App.betting();
            selectedChip.turnOff();
            curChip = new Chip(10, R.drawable.chipcustom,-99);
            cusChipTxt.setText(10+"");
            activity.showPopup(new NumberPadDialog());
        });

        confirmBtn.clicked(v-> activity.confirm());

        cancelBtn.clicked(v-> activity.cancel());

        rebetBtn.clicked(v-> activity.rebet());

    }

    public void setUp(){
        video = findViewById(R.id.video);
        activity = (CasinoActivity) getContext();
    }

    @SuppressLint("SetTextI18n")
    public void setPPLnum(int num){
        pplTxt.setText(""+num);
    }

    public void setMember(String mem){
        member.setText(mem);
    }

    public void dealing(){
        dealImg.setVisibility(VISIBLE);
    }

    public void betting(){
        dealImg.setVisibility(INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void setSecond(int sec){
        countdown.setText(sec+"");
    }

    @SuppressLint("SetTextI18n")
    public void setGyuShu(int num, int round){
        gyuShu.setText(num + "/" + round);
    }

    public void setVideo(String url){
        video.setVideoPath(url);
    }

    public void playVideo(){
        video.start();
    }

    public void stopVideo(){
        video.stopPlayback();
    }

    @SuppressLint("SetTextI18n")
    public void setCusChip(int val){
        String cusStr = cusChipTxt.getText().toString();
        cusStr = cusStr + val;
        curChip.value = Integer.parseInt(cusStr);
        cusChipTxt.setText(cusStr);
    }

    public void setCusBack(){
        String cusStr = cusChipTxt.getText().toString();
        cusStr = cusStr.substring(0, cusStr.length() - 1);
        if (cusStr.equals("")) {
            curChip.value = 1;
            cusChipTxt.setText("1");
        }
        curChip.value = Integer.parseInt(cusStr);
        cusChipTxt.setText(cusStr);
    }

    public void setTitle(String txt){
        bar.setTitle(txt);
    }

    @SuppressLint("SetTextI18n")
    public void updateBalance() {
        bar.updateBalance();
    }

    @Override
    public void onClick(View v) {
        App.betting();
        selectedChip.turnOff();
        selectedChip = (ChipView) v;
        selectedChip.turnOn();
        curChip = selectedChip.getChip();
    }
}
