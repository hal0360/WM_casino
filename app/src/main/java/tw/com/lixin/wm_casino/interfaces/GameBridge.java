package tw.com.lixin.wm_casino.interfaces;


public interface GameBridge {
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void winLossUpdate(float moneyWin );
}
