package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.popups.MessagePopup;
import tw.com.lixin.wm_casino.tools.gameComponents.MessageArea;

public class MessageButton extends ClickConstraint {

    private MessagePopup popup;

    public MessageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_button, this);
        ImageView btnImg = findViewById(R.id.btn_img);
        btnImg.setImageResource(R.drawable.message);

        post(()-> popup = new MessagePopup());

        clicked(v->{
            RootActivity activity = (RootActivity) getContext();
            activity.showPopup(popup);
        });

    }


    public void connectMessage(MessageArea area){
        popup.connectMessage(area);
    }
}
