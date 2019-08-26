package tw.com.lixin.wm_casino.websocketSource;

import tw.com.lixin.wm_casino.dataModels.TableData;
import tw.com.lixin.wm_casino.models.CoinStackData;

public class BacSource extends GameSource{

    private static BacSource single_instance = null;
    public static BacSource getInstance()
    {
        if (single_instance == null) single_instance = new BacSource();
        return single_instance;
    }
    private BacSource() {
        defineURL("ws://gameserver.a45.me:15101");
    }

    public boolean comission = false;
    public CoinStackData stackLeft, stackRight, stackBTL, stackBTR, stackTop, stackSuper;
    public String tableRightScore;
    public String tableLeftScore;
    public String tableTopScore;
    public String tableBtlScore;
    public String tableBtrScore;
    public int pokerWin = -1;
    public int maxBetVal;
    public int playerScore, bankerScore;

    @Override
    public void onData(int protocol, TableData.Data data) {
        if(protocol == 10){
            if (data.bOk) {
                stackSuper = new CoinStackData();
                stackTop = new CoinStackData();
                stackBTR = new CoinStackData();
                stackRight = new CoinStackData();
                stackBTL = new CoinStackData();
                stackLeft = new CoinStackData();
                tableLeftScore = data.dtOdds.get(2);
                tableRightScore = data.dtOdds.get(1);
                tableBtlScore = data.dtOdds.get(5);
                tableBtrScore = data.dtOdds.get(4);
                tableTopScore = data.dtOdds.get(3);
                stackLeft.maxValue = data.maxBet02;
                stackBTL.maxValue = data.maxBet04;
                stackRight.maxValue = data.maxBet01;
                stackBTR.maxValue = data.maxBet04;
                stackTop.maxValue = data.maxBet03;
                stackSuper.maxValue = data.maxBet04;
                maxBetVal = data.maxBet01;
                if(maxBetVal < data.maxBet02) maxBetVal = data.maxBet02;
                if(maxBetVal < data.maxBet03) maxBetVal = data.maxBet03;
                if(maxBetVal < data.maxBet04) maxBetVal = data.maxBet04;
            }
        }else if(protocol == 25){
            //pokerWin = Move.divide(bacData.data.result);
            playerScore = data.playerScore;
            bankerScore = data.bankerScore;
        }
    }
}

