package tw.com.lixin.wm_casino.interfaces;

public interface BacBridge {
    void statusUpdate();
    void cardUpdate(int area, int img);
    void resultUpadte();
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void gridUpdate();
    void betCountdown(int sec);

}
