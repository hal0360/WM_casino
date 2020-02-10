package tw.com.lixin.wm_casino.popups;

import android.view.View;
import android.widget.ScrollView;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.websocketSource.MessageSource;

public class MessagePopup extends PopupFragment {


    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.message_popup);

        MessageSource source = MessageSource.getInstance();

        ((ClickImage) dialog.findViewById(R.id.emo_1)).clicked(v-> source.sendEmoji( 1));
        ((ClickImage) dialog.findViewById(R.id.emo_2)).clicked(v-> source.sendEmoji(2));
        ((ClickImage) dialog.findViewById(R.id.emo_3)).clicked(v-> source.sendEmoji(3));
        ((ClickImage) dialog.findViewById(R.id.emo_4)).clicked(v-> source.sendEmoji( 4));
        ((ClickImage) dialog.findViewById(R.id.emo_5)).clicked(v-> source.sendEmoji(5));
        ((ClickImage) dialog.findViewById(R.id.emo_6)).clicked(v-> source.sendEmoji(6));
        ((ClickImage) dialog.findViewById(R.id.emo_7)).clicked(v-> source.sendEmoji(7));
        ((ClickImage) dialog.findViewById(R.id.emo_8)).clicked(v-> source.sendEmoji(8));
        ((ClickImage) dialog.findViewById(R.id.emo_9)).clicked(v-> source.sendEmoji( 9));
        ((ClickImage) dialog.findViewById(R.id.emo_10)).clicked(v-> source.sendEmoji( 10));
        ((ClickImage) dialog.findViewById(R.id.emo_11)).clicked(v-> source.sendEmoji(11));
        ((ClickImage) dialog.findViewById(R.id.emo_12)).clicked(v-> source.sendEmoji(12));
        ((ClickImage) dialog.findViewById(R.id.emo_13)).clicked(v-> source.sendEmoji(13));
        ((ClickImage) dialog.findViewById(R.id.emo_14)).clicked(v-> source.sendEmoji(14));
        ((ClickImage) dialog.findViewById(R.id.emo_15)).clicked(v-> source.sendEmoji(15));

        ((ClickText) dialog.findViewById(R.id.mss_1)).clicked(v-> source.sendMessage(1));
        ((ClickText) dialog.findViewById(R.id.mss_2)).clicked(v-> source.sendMessage(2));
        ((ClickText) dialog.findViewById(R.id.mss_3)).clicked(v-> source.sendMessage(3));
        ((ClickText) dialog.findViewById(R.id.mss_4)).clicked(v-> source.sendMessage(4));
        ((ClickText) dialog.findViewById(R.id.mss_5)).clicked(v-> source.sendMessage(5));
        ((ClickText) dialog.findViewById(R.id.mss_6)).clicked(v-> source.sendMessage(6));
        ((ClickText) dialog.findViewById(R.id.mss_7)).clicked(v-> source.sendMessage(7));
        ((ClickText) dialog.findViewById(R.id.mss_8)).clicked(v-> source.sendMessage(8));

        ScrollView textBox = dialog.findViewById(R.id.text_box);
        ScrollView emoBox = dialog.findViewById(R.id.emo_box);
        ((ClickImage) dialog.findViewById(R.id.smile_btn)).clicked(v-> {
            emoBox.setVisibility(View.VISIBLE);
            textBox.setVisibility(View.GONE);
        });
        ((ClickImage) dialog.findViewById(R.id.speaker_btn)).clicked(v-> {
            emoBox.setVisibility(View.GONE);
            textBox.setVisibility(View.VISIBLE);
        });
    }



   // public void sendMessage(String mss){
    //    mssList.add(new MessageCollection(User.account(),mss,0));
    //}

    //public void sendEmoji(int imgRes, int arg){
    //    mssList.add(new MessageCollection(User.account(),"",imgRes));
    //}



    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}
