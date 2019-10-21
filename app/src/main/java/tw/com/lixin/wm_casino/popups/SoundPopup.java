package tw.com.lixin.wm_casino.popups;

import android.app.Dialog;
import androidx.appcompat.widget.SwitchCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.App;
import tw.com.lixin.wm_casino.R;

public class SoundPopup extends PopupFragment {

    @Override
    public void dialogCreated(Dialog dialog) {
        dialog.setContentView(R.layout.sound_popup);
        setGravity(Gravity.TOP | Gravity.END);
        setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        SwitchCompat background = dialog.findViewById(R.id.back_music_swich);
        SwitchCompat efffect = dialog.findViewById(R.id.effect_switch);

        if(App.musicOn) background.setChecked(true);
        if(App.effectOn) efffect.setChecked(true);

        background.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) App.music_on();
            else App.music_off();
        });

        efffect.setOnCheckedChangeListener((buttonView, isChecked) -> App.effectOn = isChecked);
    }

}
