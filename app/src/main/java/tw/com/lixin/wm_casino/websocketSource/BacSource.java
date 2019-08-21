package tw.com.lixin.wm_casino.websocketSource;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Cmd;
import tw.com.atromoby.utils.CountDown;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.BacData;
import tw.com.lixin.wm_casino.dataModels.Client10;
import tw.com.lixin.wm_casino.dataModels.LoginResData;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
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
        tables = new ArrayList<>();
        defineURL("ws://gameserver.a45.me:15101");
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
        Log.e("BacSource", text);
        BacData bacData = Json.from(text, BacData.class);
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

        }else if(bacData.protocol == 38){

        //    BacTable tt = findTable(bacData.data.groupID);
          //  if(tt != null)tt.update(bacData);

        }

    }

    @Override
    public void onLogin(LoginResData data) {
        Game bacGame = LobbySource.getInstance().findGame(101);
        if(bacGame == null) return;
        for(Group group: bacGame.groupArr) {
            if (group.gameStage != 4) {
                BacTable table = new BacTable();
                table.setUp(group.historyArr);
                table.stage = group.gameStage;
                table.groupID = group.groupID;
                table.groupType = group.groupType;
                table.score = group.bankerScore;
                table.round = group.gameNoRound;
                table.number = group.gameNo;
                table.dealerImageUrl = group.dealerImage;
                table.dealerName = group.dealerName;
                try {
                    table.dealerImage = BitmapFactory.decodeStream(new URL(table.dealerImageUrl).openStream());
                    Log.e(table.dealerName + " succ", "okokk");
                } catch (IOException e) {
                    Log.e(table.dealerName + " BitError", e.getMessage());
                }
                tables.add(table);
            }
        }
    }
}

