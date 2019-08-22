package tw.com.lixin.wm_casino.interfaces;

public interface GameBridge {
    void resultUpadte();
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void tableLogin(boolean logOk);
}
