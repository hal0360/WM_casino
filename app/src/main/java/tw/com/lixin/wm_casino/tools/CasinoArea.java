package tw.com.lixin.wm_casino.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.CollectionsView;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.StackCallBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.Chip;
import tw.com.lixin.wm_casino.models.Table;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.popups.NumberPadDialog;
import tw.com.lixin.wm_casino.popups.ProfilePopup;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.tools.chips.ChipView;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class CasinoArea extends ConstraintLayout implements View.OnClickListener{

    private ProfileBar bar;
    private TextView gyuShu, countdown, member, cusChipTxt;
    private ImageView dealImg;
    private GameSource source;
    private Table table;
    private ChipView selectedChip;
    private CollectionsView mssList;
    private ConstraintLayout cusChip;
    private CasinoActivity activity;

    private static Chip curChip;

    private List<BetStack> stacks;

    public CasinoArea(Context context) {super(context);}

    @SuppressLint("SetTextI18n")
    public CasinoArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.casino_area, this);

       // source = GameSource.getInstance();
       // table = source.table;

        stacks = new ArrayList<>();
        member = findViewById(R.id.member);
        bar = findViewById(R.id.profile);
        countdown = findViewById(R.id.count_txt);
        dealImg = findViewById(R.id.dealer_img);
        mssList = findViewById(R.id.mss_list);
        cusChip = findViewById(R.id.custom_chip);
        cusChipTxt = findViewById(R.id.custom_num_txt);

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


        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CasinoArea);

        a.recycle();

        gyuShu = findViewById(R.id.gyu_shu);
        ClickText limitBtn = findViewById(R.id.limit_btn);
        limitBtn.clicked(v->{
            LimitPopup popup = new LimitPopup();
          //  activity.showPopup(popup);
        });

        cusChip.setOnClickListener(v->{
            selectedChip.turnOff();
            curChip = new Chip(10, R.drawable.chipcustom,-99);
            cusChipTxt.setText(10+"");
            activity.showPopup(new NumberPadDialog());
        });

        post(this::soourceUp);

    }

    public void addToStack(BetStack stack){
        stacks.add(stack);
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

    @SuppressLint("SetTextI18n")
    private void soourceUp(){

        activity = (CasinoActivity) getContext();
        /*
        bar.setTitle(bar.getTitle() + table.groupID);
        member.setText(User.userName());
        bar.updateBalance();

        table.onAreaStage(stage->{
            if (table.stage == 1) {
                dealImg.setVisibility(INVISIBLE);
            } else if (table.stage == 2) {
                dealImg.setVisibility(VISIBLE);
            }
        });
        table.onSecond(sec-> countdown.setText(sec+""));
        table.onTable(()-> gyuShu.setText(table.number + "/" + table.round));
*/
    }


    public void setTitle(String txt){
        bar.setTitle(txt);
    }


    @Override
    public void onClick(View v) {
        selectedChip.turnOff();
        selectedChip = (ChipView) v;
        selectedChip.turnOn();
        curChip = selectedChip.getChip();
    }
}
