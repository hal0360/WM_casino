package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.dataModels.MessageData;
import tw.com.lixin.wm_casino.global.User;

public class MessageCollection extends Collection {

    private MessageData.Data data;

    public MessageCollection(MessageData.Data data) {
        super(R.layout.message_collection);
        this.data = data;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        TextView mss = holder.findViewById(R.id.message);

        ImageView icon = holder.findViewById(R.id.icon);

        if(data.sender.equals("Player")){
            if(data.senderID == User.memberID()) mss.setTextColor(0xff7CFC00);
            else mss.setTextColor(0xffFF0000);
            if(data.messageType.equals("Emoji")){
                int nun = Integer.parseInt(data.arguments);
                icon.setVisibility(View.VISIBLE);
                icon.setImageResource(App.emos.get(nun));
                mss.setText(data.senderName+ ": ");
            }else if(data.messageType.equals("Example")){
                int nun = Integer.parseInt(data.arguments);
                mss.setText(data.senderName+ ": " + holder.getContex().getString(App.examples.get(nun)));
            }
        }else {
            mss.setTextColor(0xffFFD700);
            mss.setText(data.arguments);
        }


    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
