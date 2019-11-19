package tw.com.lixin.wm_casino.tools.gameComponents;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.widgets.ItemsView;
import tw.com.lixin.wm_casino.dataModels.MessageData;

import android.util.AttributeSet;

public class MessageArea extends ItemsView {
    public MessageArea(Context context) { super(context); }



    public MessageArea(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public void failedToConnect(){

    }

    public void failedToLogin(){

    }

    public void connected(){

    }

    public void mssBoxUpdated(MessageData.Data data ){

    }

    public void mssReceived(MessageData.Data data){

    }
}
