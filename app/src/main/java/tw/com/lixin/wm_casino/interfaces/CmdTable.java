package tw.com.lixin.wm_casino.interfaces;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.lixin.wm_casino.collections.GameCollection;
import tw.com.lixin.wm_casino.models.Table;

public interface CmdTable {
     GameCollection exec(Table t, FragDialog d);
}
