package tw.com.lixin.wm_casino.interfaces;

public interface TableBridge {
    void statusUpdate();
    void gridUpdate();
    void betCountdown(int sec);

    void resultUpadte();
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void tableLogin(boolean logOk);
    void cardUpdate(int area, int img);
}
