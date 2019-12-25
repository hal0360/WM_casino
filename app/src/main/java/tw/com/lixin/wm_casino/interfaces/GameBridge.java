package tw.com.lixin.wm_casino.interfaces;


public interface GameBridge {
    void betUpdate(boolean betOK);
    void balanceUpdate();
    void winLossUpdate(float win);
}
