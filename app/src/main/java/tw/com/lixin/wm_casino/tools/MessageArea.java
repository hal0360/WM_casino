package tw.com.lixin.wm_casino.tools;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

import tw.com.atromoby.widgets.CollectionsView;
import tw.com.lixin.wm_casino.collections.MessageCollection;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.websocketSource.MessageSource;

public class MessageArea extends CollectionsView {

    private MessageSource source;
    public MessageArea(Context context) { super(context); }
    public MessageArea(Context context, AttributeSet attrs) { super(context, attrs); }

    public void login(int gameID, int groupID){
        source = MessageSource.getInstance();
        source.bind(this);
       // source.mssLogin(gameID, groupID);
    }

    public void logout(){
        source.unbind();
        source.close();
    }

    public void sendMessage(String mss){
        add(new MessageCollection(User.account(),mss,0));
    }

    public void sendEmoji(int imgRes, int arg){
        add(new MessageCollection(User.account(),"",imgRes));
    }

    public void failedToConnect(){
        add(new MessageCollection("","Error: connection failed",0));
    }

    public void failedToLogin(){
        add(new MessageCollection("","Error: login failed",0));
    }

    public void connected(){
        add(new MessageCollection("","Cconnection succesful",0));
    }

    public void mssBoxUpdated(List<MessageData.Data> datas ){
        for (MessageData.Data data: datas){
            add(new MessageCollection("",data.arguments,0));
        }
    }

    public void mssReceived(MessageData.Data data){
        add(new MessageCollection("",data.arguments,0));
    }
}
