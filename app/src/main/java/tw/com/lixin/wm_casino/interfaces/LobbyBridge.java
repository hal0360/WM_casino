package tw.com.lixin.wm_casino.interfaces;

public interface LobbyBridge {

    void wholeDataUpdated();
    void balanceUpdated();
    void peopleOnlineUpdate(int number);

    void nineUpdate();
}
