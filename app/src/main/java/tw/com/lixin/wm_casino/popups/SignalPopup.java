package tw.com.lixin.wm_casino.popups;

import tw.com.atromoby.utils.Kit;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.CasinoActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;
import tw.com.lixin.wm_casino.tools.buttons.ClickText;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class SignalPopup extends PopupFragment {

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.signal_popup);

        GameSource source = GameSource.getInstance();
        CasinoActivity activity = (CasinoActivity) dialog.getRootActivity();

        ClickConstraint sig = dialog.findViewById(R.id.signal1_btn);
        if(source.videoSignal.equals("wmvdo.gtgdd")){
            sig.setBackgroundResource(R.drawable.message_butt_border);
        }
        sig.clicked(v->{
            source.videoSignal = "wmvdo.gtgdd";
            activity.resetVideo();
            dismiss();
        });

        sig = dialog.findViewById(R.id.signal2_btn);
        if(source.videoSignal.equals("wmvdo.nicejj")){
            sig.setBackgroundResource(R.drawable.message_butt_border);
        }
        sig.clicked(v->{
            source.videoSignal = "wmvdo.nicejj";
            activity.resetVideo();
            dismiss();
        });

        sig = dialog.findViewById(R.id.signal3_btn);
        if(source.videoSignal.equals("wmvdo3.nicejj")){
            sig.setBackgroundResource(R.drawable.message_butt_border);
        }
        sig.clicked(v->{
            /*
            source.videoSignal = "wmvdo3.nicejj";
            curButt.setBackgroundResource(R.drawable.message_border);
            curButt = (ClickConstraint) v;
            curButt.setBackgroundResource(R.drawable.message_butt_border);
            activity.resetVideo();
            dismiss(); */
            Kit.alert(dialog.getContext(),"Invalid video");
        });

        sig = dialog.findViewById(R.id.signal4_btn);
        if(source.videoSignal.equals("wmvdo.dkbix")){
            sig.setBackgroundResource(R.drawable.message_butt_border);
        }
        sig.clicked(v->{
            source.videoSignal = "wmvdo.dkbix";
            activity.resetVideo();
            dismiss();
        });

        ClickText close = dialog.findViewById(R.id.close_btn);
        close.clicked(v-> dismiss());
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}

