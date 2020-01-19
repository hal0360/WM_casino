package tw.com.lixin.wm_casino.collections;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.Collection;
import tw.com.atromoby.widgets.CollectionHolder;
import tw.com.lixin.wm_casino.R;

public class MessageCollection extends Collection {

    private String message, user, arg;

    public MessageCollection(String user, String content, String arg) {
        super(R.layout.message_collection);
        message = content;
        this.user = user;
        this.arg = arg;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind(CollectionHolder holder) {
        TextView mss = holder.findViewById(R.id.message);

        mss.setText(user+ ": " + arg + " " + message);
       // mss.setTextColor(0xff7CFC00);
    }

    @Override
    public void onRecycle(CollectionHolder holder) {

    }
}
