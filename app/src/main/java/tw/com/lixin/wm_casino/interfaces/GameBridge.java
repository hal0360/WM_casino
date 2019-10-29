package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.dataModels.TableData;

public interface GameBridge {
    void balanceUpdate(float value);
    void betUpdate(boolean betOK);
    void winLossUpdate(TableData.Data data);
}
