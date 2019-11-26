package tw.com.lixin.wm_casino.tools.gameComponents;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.holders.MessageHolder;
import tw.com.lixin.wm_casino.websocketSource.MessageSource;

import android.util.AttributeSet;

import java.util.List;

public class MessageArea extends ItemsView {

    private MessageSource source;
    public MessageArea(Context context) { super(context); }
    public MessageArea(Context context, AttributeSet attrs) { super(context, attrs); }

    public void login(int gameID, int groupID){
        source = MessageSource.getInstance();
        source.bind(this);
        source.mssLogin(gameID, groupID);
    }

    public void logout(){
        source.unbind();
        source.close();
    }

    public void sendMessage(String mss){
        add(new MessageHolder(User.account(),mss,0));
    }

    public void sendEmoji(int imgRes, int arg){
        add(new MessageHolder(User.account(),"",imgRes));
    }

    public void failedToConnect(){
        add(new MessageHolder("","Error: connection failed",0));
    }

    public void failedToLogin(){
        add(new MessageHolder("","Error: login failed",0));
    }

    public void connected(){
        add(new MessageHolder("","Cconnection succesful",0));
    }

    public void mssBoxUpdated(List<MessageData.Data> datas ){
        for (MessageData.Data data: datas){
            add(new MessageHolder("",data.arguments,0));
        }
    }

    public void mssReceived(MessageData.Data data){
        add(new MessageHolder("",data.arguments,0));
    }
}
