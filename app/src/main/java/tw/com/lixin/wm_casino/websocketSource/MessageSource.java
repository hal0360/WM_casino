package tw.com.lixin.wm_casino.websocketSource;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client11;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.dataModels.MssSendData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.tools.CasinoArea;

public class MessageSource extends CasinoSource{

    private static MessageSource single_instance = null;

    private int groupID, gameID;

    public List<MessageData.Data> mssDataList;

    private String errorMss;

    public static MessageSource getInstance()
    {
        if (single_instance == null) single_instance = new MessageSource();
        return single_instance;
    }

    private MessageSource() {
        mssDataList = new ArrayList<>();
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

    public void logout(){
        unbind();
        close();
    }

    final void mssLogin(int gameid, int groupid){
        defineURL("wss://a45gs-t.dartspharm.com/15801");
        gameID = gameid;
        groupID = groupid;
        login(User.sid(),data->{
            Client11 client = new Client11(gameid, groupid);
            send(Json.to(client));
        }, fail-> errorMss = "Failed to connect");
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
                    errorMss = "Connection successful";
                }
                else{
                    errorMss = "Failed to login";
                }
                break;
            case 102:
                mssDataList.addAll(mdata.data.messageBoxArr);
                handle(() -> area.mssBoxUpdated(mdata.data.messageBoxArr));
                break;
            case 101:
                mssDataList.add(mdata.data);
                handle(() -> area.mssReceived(mdata.data));
                break;
        }
    }
}
