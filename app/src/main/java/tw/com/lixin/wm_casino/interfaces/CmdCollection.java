package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.collections.GameCollection;
import tw.com.lixin.wm_casino.models.Table;

public interface CmdCollection {
    GameCollection exec(Table t);
}
