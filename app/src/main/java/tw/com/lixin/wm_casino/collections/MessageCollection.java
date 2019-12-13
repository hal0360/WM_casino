package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;

public class MessageCollection extends Collection {

    private String message, user;
    private int ingRes;

    public MessageCollection(String user, String content, int imgRes) {
        super(R.layout.message_collection);
        message = content;
        this.user = user;
        ingRes = imgRes;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        TextView mss = holder.findViewById(R.id.message);
        if(!user.equals("")){
            mss.setText(user+ ": " +message);
            mss.setTextColor(0xff7CFC00);
            if (ingRes != 0) {
                ImageView icon = holder.findViewById(R.id.icon);
                icon.setVisibility(View.VISIBLE);
                icon.setImageResource(ingRes);
            }
        }else {
            mss.setText(message);
        }
    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
