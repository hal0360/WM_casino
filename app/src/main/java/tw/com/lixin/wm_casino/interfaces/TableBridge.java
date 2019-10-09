package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.dataModels.TableData;

public interface TableBridge {
    void statusUpdate();
    void gridUpdate();
    void betCountdown(int sec);
    void tableLogin(TableData.Data data);
}
