package tw.com.lixin.wm_casino.interfaces;

public interface LobbyBridge {
    void wholeDataUpdated();
    void peopleOnlineUpdate(int gameID ,int number);
}
