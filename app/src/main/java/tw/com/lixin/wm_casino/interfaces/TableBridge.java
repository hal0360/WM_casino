package tw.com.lixin.wm_casino.interfaces;


public interface TableBridge {
    void stageUpdate();
    void gridUpdate();
    void betCountdown(int sec);
    void resultUpdate();
    void cardUpdate(int area, int img);
    void tableUpdate();
}
