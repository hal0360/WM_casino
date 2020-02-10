package tw.com.lixin.wm_casino.interfaces;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.dataModels.gameData.Group;
import tw.com.lixin.wm_casino.models.Table;

public interface CmdTable {
     Collection exec(Table t, FragDialog d);
}
