package tw.com.lixin.wm_casino.popups;

import android.content.Context;
import android.widget.TextView;

import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.BacActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.TableData;


public class WinLossPopup extends Popup {

    TextView player_bet, banker_bet, player_pair_bet, banker_pair_bet, tie_bet, player_win, banker_win, player_pair_win, banker_pair_win, tie_win, total_win_money;

    public WinLossPopup(Context context) {
        super(context, R.layout.win_loss_popup);

        player_bet = findViewById(R.id.player_bet);
        banker_bet = findViewById(R.id.banker_bet);
        player_pair_bet = findViewById(R.id.player_pair_bet);
        banker_pair_bet = findViewById(R.id.banker_pair_bet);
        tie_bet = findViewById(R.id.tie_bet);
        player_win = findViewById(R.id.player_win);
        banker_win = findViewById(R.id.banker_win);
        player_pair_win = findViewById(R.id.player_pair_win);
        banker_pair_win = findViewById(R.id.banker_pair_win);
        tie_win = findViewById(R.id.tie_win);
        total_win_money = findViewById(R.id.total_win_money);
    }

    public void setUp(TableData.Data data){

        player_bet.setText(BacActivity.playStackData.value + "");
        banker_bet.setText(BacActivity.bankStackData.value + "");
        player_pair_bet.setText(BacActivity.playPairStackData.value + "");
        banker_pair_bet.setText(BacActivity.bankPairStackData.value + "");
        tie_bet.setText(BacActivity.tieStackData.value + "");

        if (data.dtMoneyWin.get(2) == null) {
            player_win.setText("");
        } else {
            player_win.setText(data.dtMoneyWin.get(2) + "");
        }
        if (data.dtMoneyWin.get(1) == null) {
            banker_win.setText("");
        } else {
            banker_win.setText(data.dtMoneyWin.get(1) + "");
        }
        if (data.dtMoneyWin.get(5) == null) {
            player_pair_win.setText("");
        } else {
            player_pair_win.setText(data.dtMoneyWin.get(5) + "");
        }
        if (data.dtMoneyWin.get(4) == null) {
            banker_pair_win.setText("");
        } else {
            banker_pair_win.setText(data.dtMoneyWin.get(4) + "");
        }
        if (data.dtMoneyWin.get(3) == null) {
            tie_win.setText("");
        } else {
            tie_win.setText(data.dtMoneyWin.get(3) + "");
        }
        total_win_money.setText(data.moneyWin + "");
    }
}
