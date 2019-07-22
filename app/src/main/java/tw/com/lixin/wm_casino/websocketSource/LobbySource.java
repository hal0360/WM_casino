package tw.com.lixin.wm_casino.websocketSource;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Cmd;
import tw.com.atromoby.utils.CountDown;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.Popup;
import tw.com.lixin.wm_casino.global.Url;
import tw.com.lixin.wm_casino.interfaces.BacBridge;
import tw.com.lixin.wm_casino.interfaces.CmdBool;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;
import tw.com.lixin.wm_casino.models.Table;


public class LobbySource extends CasinoSource{

    private static LobbySource single_instance = null;
    public static LobbySource getInstance()
    {
        if (single_instance == null) single_instance = new LobbySource();
        return single_instance;
    }

    private LobbySource() {

        defineURL(Url.Lobby);
    }

    private LobbyBridge lobbyBridge;



    public void unbind(){
        lobbyBridge = null;
    }


    @Override
    public void onReceive(String text) {

    }
}
