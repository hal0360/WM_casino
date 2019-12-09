package tw.com.lixin.wm_casino.websocketSource;

import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.Client11;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.tools.gameComponents.MessageArea;

public class MessageSource extends CasinoSource{

    private static MessageSource single_instance = null;
    public static MessageSource getInstance()
    {
        if (single_instance == null) single_instance = new MessageSource();
        return single_instance;
    }

    private MessageArea area;

    public void bind(MessageArea bridge){
        area = bridge;
        binded(true);
    }

    public void unbind(){
        area = null;
        binded(false);
    }

    public MessageArea getArea(){
        return area;
    }

    public final void mssLogin(int gameid, int groupid){
        defineURL("ws://gameserver.a45.me:15801");

        login(User.sid(),data->{
            Client11 client = new Client11(gameid, groupid);
            send(Json.to(client));
        }, fail-> handle(() -> area.failedToConnect()));
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
