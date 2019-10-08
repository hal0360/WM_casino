package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.models.Table;

public class RatePanel extends ConstraintLayout{

    private Context context;
    private int orientation;
    private View sec1, sec2, sec3;
    private TextView sec1Num, sec2Num, sec3Num, sec1Per, sec2Per, sec3Per, fin1Num, fin2Num, fin3Num, fin1Per, fin2Per, fin3Per;

    public RatePanel(Context context) {
        super(context);
        init(context);
    }

    public RatePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        View.inflate(context, R.layout.rate_panel, this);
        orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            findViewById(R.id.collapse_btn).setOnClickListener(v->{
                findViewById(R.id.expanded_panel).setVisibility(GONE);
                findViewById(R.id.expanded_buttons).setVisibility(GONE);
                findViewById(R.id.collapsed_panel).setVisibility(VISIBLE);
                findViewById(R.id.expand_btn).setVisibility(VISIBLE);
            });
            findViewById(R.id.expand_btn).setOnClickListener(v->{
                findViewById(R.id.expanded_panel).setVisibility(VISIBLE);
                findViewById(R.id.expanded_buttons).setVisibility(VISIBLE);
                findViewById(R.id.collapsed_panel).setVisibility(GONE);
                findViewById(R.id.expand_btn).setVisibility(GONE);
            });

            sec1 = findViewById(R.id.sec_1);
            sec1Num = findViewById(R.id.sec_1_num);
            sec1Per = findViewById(R.id.sec_1_per);
            fin1Num = findViewById(R.id.fin_1_num);
            fin1Per = findViewById(R.id.fin_1_per);
            sec2 = findViewById(R.id.sec_2);
            sec2Num = findViewById(R.id.sec_2_num);
            sec2Per = findViewById(R.id.sec_2_per);
            fin2Num = findViewById(R.id.fin_2_num);
            fin2Per = findViewById(R.id.fin_2_per);
            sec3 = findViewById(R.id.sec_3);
            sec3Num = findViewById(R.id.sec_3_num);
            sec3Per = findViewById(R.id.sec_3_per);
            fin3Num = findViewById(R.id.fin_3_num);
            fin3Per = findViewById(R.id.fin_3_per);

            findViewById(R.id.expanded_panel).setVisibility(VISIBLE);
            findViewById(R.id.expanded_buttons).setVisibility(VISIBLE);
            findViewById(R.id.collapsed_panel).setVisibility(GONE);
            findViewById(R.id.expand_btn).setVisibility(GONE);
        }else{
            setBackgroundColor(Color.parseColor("#0A2A1C"));
        }
    }

    public void setUp(Table table){
        TextView gyu = findViewById(R.id.gyu_shu);
        gyu.setText(table.number + "/" + table.round);
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            TextView dealName = findViewById(R.id.dealer_name);
            dealName.setText(table.dealerName);
            TextView tableName = findViewById(R.id.table_name);

            tableName.setText("baccarat" + table.groupID);

        }else{
            TextView member = findViewById(R.id.member);
            member.setText(User.account());
        }

    }

    public void percentUpdate(int round, int val1, int val2,int val3){
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            int per1, per2, per3;
            if(round == 0){
                per1 = 0;
                per2 = 0;
                per3 = 0;
            }else {
                per1 = val1*100/round;
                per2 = val2*100/round;
                per3 = val3*100/round;
            }
            sec1Num.setText(val1+"");
            sec2Num.setText(val2+"");
            sec3Num.setText(val3+"");
            fin1Num.setText(val1+"");
            fin2Num.setText(val2+"");
            fin3Num.setText(val3+"");

            sec1Per.setText(per1+"%");
            sec2Per.setText(per2+"%");
            sec3Per.setText(per3+"%");
            fin1Per.setText(per1+"%");
            fin2Per.setText(per2+"%");
            fin3Per.setText(per3+"%");

            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT,
                    val1
            );
            sec1.setLayoutParams(param1);
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT,
                    val2
            );
            sec2.setLayoutParams(param2);
            LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT,
                    val3
            );
            sec3.setLayoutParams(param3);
        }
    }
}
