package tw.com.lixin.wm_casino.tools.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.popups.TableSwitchPopup;

public class TableSwitchButton extends ClickConstraint {

    private TableSwitchPopup popup;


    public TableSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_button, this);
        ImageView btnImg = findViewById(R.id.btn_img);
        btnImg.setImageResource(R.drawable.changetable);

        post(()-> popup = new TableSwitchPopup());

        clicked(v->{
            RootActivity activity = (RootActivity) getContext();
            activity.showPopup(popup);
        });

    }

}

