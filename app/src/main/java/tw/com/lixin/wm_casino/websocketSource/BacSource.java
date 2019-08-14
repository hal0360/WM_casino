package tw.com.lixin.wm_casino.websocketSource;

import android.content.Context;
import android.util.SparseIntArray;
import android.widget.TextView;

import java.util.List;

import tw.com.atromoby.utils.Cmd;
import tw.com.atromoby.utils.CountDown;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.BacData;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.BacBridge;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.models.CoinStackData;
import tw.com.lixin.wm_casino.models.Table;

public class BacSource extends CasinoSource{

    private static BacSource single_instance = null;
    public static BacSource getInstance()
    {
        if (single_instance == null) single_instance = new BacSource();
        return single_instance;
    }
    private BacSource() {
        defineURL("");
    }

    private BacBridge bridge;
    public boolean comission = false;
    public boolean cardIsOpening = true;
    public boolean isBettingNow = true;
    public int groupID = -1;
    public int gameID = 101;
    public int areaID;
    public CountDown countDownTimer;
    public CoinStackData stackLeft, stackRight, stackBTL, stackBTR, stackTop, stackSuper;
    public SparseIntArray pokers;
    public int cardStatus = 0;
    public boolean displayCard = false;
    public String tableRightScore;
    public String tableLeftScore;
    public String tableTopScore;
    public String tableBtlScore;
    public String tableBtrScore;
    public int pokerWin = -1;
    public int maxBetVal;
    public int playerScore, bankerScore;
    public BacTable table;
   // private Popup winPopup;
    public List<BacTable> tables;

    private BacTable findTable(int id){
        for(BacTable tTable: tables) if(id == tTable.groupID) return tTable;
        return null;
    }

    public void bind(BacBridge bridge){
        this.bridge = bridge;
        binded(true);
    }

    public void unbind(){
        this.bridge = null;
        binded(false);
    }

    public void tableLogin(BacTable table){
        this.table = table;
        groupID = table.groupID;
        Client10 client = new Client10(table.groupID);
        send(Json.to(client));
    }

    @Override
    public void onReceive(String text) {
        BacData bacData = Json.from(text, BacData.class);
        if(bacData.data.gameID != gameID || bacData.data.groupID != groupID) return;
        if(bacData.protocol == 10){
            if (bacData.data.bOk) {
                stackSuper = new CoinStackData();
                stackTop = new CoinStackData();
                stackBTR = new CoinStackData();
                stackRight = new CoinStackData();
                stackBTL = new CoinStackData();
                stackLeft = new CoinStackData();
                pokers = new SparseIntArray();
                tableLeftScore = bacData.data.dtOdds.get(2);
                tableRightScore = bacData.data.dtOdds.get(1);
                tableBtlScore = bacData.data.dtOdds.get(5);
                tableBtrScore = bacData.data.dtOdds.get(4);
                tableTopScore = bacData.data.dtOdds.get(3);
                stackLeft.maxValue = bacData.data.maxBet02;
                stackBTL.maxValue = bacData.data.maxBet04;
                stackRight.maxValue = bacData.data.maxBet01;
                stackBTR.maxValue = bacData.data.maxBet04;
                stackTop.maxValue = bacData.data.maxBet03;
                stackSuper.maxValue = bacData.data.maxBet04;
                maxBetVal = bacData.data.maxBet01;
                if(maxBetVal < bacData.data.maxBet02) maxBetVal = bacData.data.maxBet02;
                if(maxBetVal < bacData.data.maxBet03) maxBetVal = bacData.data.maxBet03;
                if(maxBetVal < bacData.data.maxBet04) maxBetVal = bacData.data.maxBet04;
            }
            handle(() -> bridge.tableLogin(bacData.data.bOk));

        }else if(bacData.protocol == 20){
            BacTable tt = findTable(bacData.data.groupID);
            if(tt != null)tt.statusUpdate(bacData.data.gameStage);
        }else if(bacData.protocol == 22){
            handle(() -> bridge.betUpdate(bacData.data.bOk));
        }else if(bacData.protocol == 23){
            handle(() -> bridge.balanceUpdate(bacData.data.balance));
        }else if(bacData.protocol == 24){
            BacTable tt = findTable(bacData.data.groupID);
            if(tt != null)tt.cardUpdate(bacData.data.cardArea, bacData.data.cardID);
        }else if(bacData.protocol == 25){
            //pokerWin = Move.divide(bacData.data.result);
            playerScore = bacData.data.playerScore;
            bankerScore = bacData.data.bankerScore;
            handle(() -> bridge.resultUpadte());
        }else if(bacData.protocol == 26){
            BacTable tt = findTable(bacData.data.groupID);
            if(tt != null)tt.update(bacData);
        }else if(bacData.protocol == 31){
            /*
            if(User.memberID() != bacData.data.memberID || bridge == null) return;
            Context context = (Context) bridge;
            winPopup = new Popup(context, R.layout.win_loss_popup);
            TextView mText = winPopup.findViewById(R.id.player_bet);
            mText.setText(stackLeft.value + "");
            mText = winPopup.findViewById(R.id.banker_bet);
            mText.setText(stackRight.value + "");
            mText = winPopup.findViewById(R.id.player_pair_bet);
            mText.setText(stackBTL.value + "");
            mText = winPopup.findViewById(R.id.banker_pair_bet);
            mText.setText(stackBTR.value + "");
            mText = winPopup.findViewById(R.id.tie_bet);
            mText.setText(stackTop.value + "");
            mText = winPopup.findViewById(R.id.super_bet);
            mText.setText(stackSuper.value + "");
            mText = winPopup.findViewById(R.id.player_win);
            if (bacData.data.dtMoneyWin.get(2) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(2) + "");
            }
            mText = winPopup.findViewById(R.id.banker_win);
            if (bacData.data.dtMoneyWin.get(1) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(1) + "");
            }
            mText = winPopup.findViewById(R.id.player_pair_win);
            if (bacData.data.dtMoneyWin.get(5) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(5) + "");
            }
            mText = winPopup.findViewById(R.id.banker_pair_win);
            if (bacData.data.dtMoneyWin.get(4) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(4) + "");
            }
            mText = winPopup.findViewById(R.id.tie_win);
            if (bacData.data.dtMoneyWin.get(3) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(3) + "");
            }
            mText = winPopup.findViewById(R.id.super_win);
            if (bacData.data.dtMoneyWin.get(8) == null) {
                mText.setText("");
            } else {
                mText.setText(bacData.data.dtMoneyWin.get(8) + "");
            }
            mText = winPopup.findViewById(R.id.total_win_money);
            mText.setText(bacData.data.moneyWin + "");
            winPopup.show(); */
        }else if(bacData.protocol == 38){

            BacTable tt = findTable(bacData.data.groupID);
            if(tt != null)tt.update(bacData);

            handle(() -> countDownTimer.start(bacData.data.timeMillisecond, i->{
                if(!cardIsOpening){
                    if(bridge != null) bridge.betCountdown(i);
                }
            }));
        }

    }
}

