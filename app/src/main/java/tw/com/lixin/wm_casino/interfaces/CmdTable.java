package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.models.Table;

public interface CmdTable {
     Table exec(Group group);
}
