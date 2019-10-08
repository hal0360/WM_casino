package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.dataModels.TableData;

public interface GameBridge {
    void resultUpdate(TableData.Data data);
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void tableLogin(TableData.Data data);
    void cardUpdate(int area, int img);

    void statusUpdate(int stage);
    void gridUpdate();
    void winLossUpdate(TableData.Data data);

    void startCountDown(int milli);
}
