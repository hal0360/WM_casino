package tw.com.lixin.wm_casino.websocketSource;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client11;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.dataModels.MssSendData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.tools.CasinoArea;

public class MessageSource extends CasinoSource{

    private static MessageSource single_instance = null;

    private int groupID, gameID;

    public static MessageSource getInstance()
    {
        if (single_instance == null) single_instance = new MessageSource();
        return single_instance;
    }

    private CasinoArea area;

    public void bind(CasinoArea bridge){
        area = bridge;
        binded(true);
    }

    public void unbind(){
        area = null;
        binded(false);
    }

    public final void mssLogin(int gameid, int groupid){
        defineURL("ws://gameserver.a45.me:15801");
        gameID = gameid;
        groupID = groupid;
        login(User.sid(),data->{
            Client11 client = new Client11(gameid, groupid);
            send(Json.to(client));
        }, fail-> area.failedToConnect());
    }

    public void sendEmoji(int arg){
        MssSendData mssData = new MssSendData();
        mssData.data.gameID = gameID;
        mssData.data.groupID = groupID;
        mssData.data.arguments = arg;
        mssData.data.messageType = "Emoji";
        send(Json.to(mssData));
    }

    public void sendMessage(int arg){
        MssSendData mssData = new MssSendData();
        mssData.data.gameID = gameID;
        mssData.data.groupID = groupID;
        mssData.data.arguments = arg;
        mssData.data.messageType = "Example";
        send(Json.to(mssData));
    }


    @Override
    public void onReceive(String text) {
        if(area == null) return;
        MessageData mdata = Json.from(text, MessageData.class);
        switch(mdata.protocol) {
            case 10:
                if(mdata.data.bOk){
                    handle(() -> area.connected());
                }
                else{
                    handle(() -> area.failedToLogin());
                }
                break;
            case 102:
                handle(() -> area.mssBoxUpdated(mdata.data.messageBoxArr));
                break;
            case 101:
                handle(() -> area.mssReceived(mdata.data));
                break;
        }
    }
}
