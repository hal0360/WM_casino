package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class SignalPopup extends PopupFragment {

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.signal_popup);

        GameSource source = GameSource.getInstance();
        ClickConstraint sig = dialog.findViewById(R.id.signal1_btn);
        sig.clicked(v->{
            source.videoSignal = "wmvdo.gtgdd";
            dismiss();
        });
        sig = dialog.findViewById(R.id.signal2_btn);
        sig.clicked(v->{
            source.videoSignal = "wmvdo.nicejj";
            dismiss();
        });
        sig = dialog.findViewById(R.id.signal3_btn);
        sig.clicked(v->{
            source.videoSignal = "wmvdo3.nicejj";
            dismiss();
        });
        sig = dialog.findViewById(R.id.signal4_btn);
        sig.clicked(v->{
            source.videoSignal = "wmvdo.dkbix";
            dismiss();
        });

        ClickText close = dialog.findViewById(R.id.close_btn);
        close.clicked(v-> dismiss());
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}

