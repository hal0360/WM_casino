package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.content.res.Configuration;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.popups.ProfilePopup;

public class VideoButton extends ClickConstraint {

    public VideoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_button, this);



        clicked(v->{
            RootActivity activity = (RootActivity) getContext();
            ProfilePopup popup = new ProfilePopup();
            popup.show(activity);

        });
    }


}
