package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.dataModels.TableLogData;

public interface CmdTableLog {
    void exec(TableLogData.Data data);
}
