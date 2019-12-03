package tw.com.lixin.wm_casino.popups;

import android.view.View;
import android.widget.ScrollView;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickImage;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.tools.gameComponents.MessageArea;

public class MessagePopup extends PopupFragment {

    private MessageArea area;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.message_popup);

        ((ClickImage) dialog.findViewById(R.id.emo_1)).clicked(v-> area.sendEmoji(R.drawable.emo1, 1));
        ((ClickImage) dialog.findViewById(R.id.emo_2)).clicked(v-> area.sendEmoji(R.drawable.emo2, 2));
        ((ClickImage) dialog.findViewById(R.id.emo_3)).clicked(v-> area.sendEmoji(R.drawable.emo3, 3));
        ((ClickImage) dialog.findViewById(R.id.emo_4)).clicked(v-> area.sendEmoji(R.drawable.emo4, 4));
        ((ClickImage) dialog.findViewById(R.id.emo_5)).clicked(v-> area.sendEmoji(R.drawable.emo5, 5));
        ((ClickImage) dialog.findViewById(R.id.emo_6)).clicked(v-> area.sendEmoji(R.drawable.emo6, 6));
        ((ClickImage) dialog.findViewById(R.id.emo_7)).clicked(v-> area.sendEmoji(R.drawable.emo7, 7));
        ((ClickImage) dialog.findViewById(R.id.emo_8)).clicked(v-> area.sendEmoji(R.drawable.emo8, 8));
        ((ClickImage) dialog.findViewById(R.id.emo_9)).clicked(v-> area.sendEmoji(R.drawable.emo9, 9));
        ((ClickImage) dialog.findViewById(R.id.emo_10)).clicked(v-> area.sendEmoji(R.drawable.emo10, 10));
        ((ClickImage) dialog.findViewById(R.id.emo_11)).clicked(v-> area.sendEmoji(R.drawable.emo11, 11));
        ((ClickImage) dialog.findViewById(R.id.emo_12)).clicked(v-> area.sendEmoji(R.drawable.emo12, 12));
        ((ClickImage) dialog.findViewById(R.id.emo_13)).clicked(v-> area.sendEmoji(R.drawable.emo13, 13));
        ((ClickImage) dialog.findViewById(R.id.emo_14)).clicked(v-> area.sendEmoji(R.drawable.emo14, 14));
        ((ClickImage) dialog.findViewById(R.id.emo_15)).clicked(v-> area.sendEmoji(R.drawable.emo15, 15));

        ((ClickText) dialog.findViewById(R.id.mss_1)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_2)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_3)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_4)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_5)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_6)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_7)).clicked(v-> area.sendMessage(v.getRawText()));
        ((ClickText) dialog.findViewById(R.id.mss_8)).clicked(v-> area.sendMessage(v.getRawText()));

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

    public void connectMessage(MessageArea area){
        this.area = area;
    }
}
