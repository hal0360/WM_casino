package tw.com.lixin.wm_casino.interfaces;

public interface BacTableBridge {
    void statusUpdate();
    void cardUpdate(int area, int img);
    void gridUpdate();
    void betCountdown(int sec);
}
