package tw.com.lixin.wm_casino.interfaces;

public interface TableBridge {
    void statusUpdate();
    void cardUpdate(int area, int img);
    void gridUpdate();
    void resultUpdate();
    void betCountdown(int sec);
}
