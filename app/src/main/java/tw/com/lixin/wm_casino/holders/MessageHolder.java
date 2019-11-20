package tw.com.lixin.wm_casino.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;

public class MessageHolder extends ItemHolder {

    private String message, user;
    private int ingRes;

    public MessageHolder(String user, String content, int imgRes) {
        super(R.layout.message_item);
        message = content;
        this.user = user;
        ingRes = imgRes;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBind() {
        TextView mss = findViewById(R.id.message);
        if(!user.equals("")){
            mss.setText(user+ ": " +message);
            mss.setTextColor(0xff7CFC00);
           if (ingRes != 0) {
               ImageView icon = findViewById(R.id.icon);
               icon.setVisibility(View.VISIBLE);
               icon.setImageResource(ingRes);
           }
        }else {
            mss.setText(message);
        }

    }

    @Override
    public void onRecycle() {

    }
}
