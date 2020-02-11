package tw.com.lixin.wm_casino.popups;

import android.view.View;

import tw.com.atromoby.rtmplayer.IjkVideoView;
import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.R;

public class LiveVideoPopup extends PopupFragment {

    private IjkVideoView video;
    private static int curID = R.id.live1_btn;

    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.live_video_popup);

        video = dialog.findViewById(R.id.live_video);

        setVideo(dialog,curID);

        dialog.clicked(R.id.live1_btn, v-> setVideo(dialog, R.id.live1_btn));

        dialog.clicked(R.id.live2_btn, v-> setVideo(dialog, R.id.live2_btn));

        dialog.clicked(R.id.live3_btn, v-> setVideo(dialog, R.id.live3_btn));

        dialog.clicked(R.id.live4_btn, v-> setVideo(dialog, R.id.live4_btn));

        dialog.clicked(R.id.live5_btn, v-> setVideo(dialog, R.id.live5_btn));

        dialog.clicked(R.id.live6_btn, v-> setVideo(dialog, R.id.live6_btn));

        dialog.clicked(R.id.close_btn,v-> dismiss());
    }

    private void setVideo(FragDialog dialog, int idd){
        View cur = dialog.findViewById(curID);
        cur.setBackgroundResource(R.drawable.live_button_border);
        video.stopPlayback();
        if(idd == R.id.live1_btn){
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/view360/stream1");
        }else if(idd == R.id.live2_btn){
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/fullview02/stream1");
        }else if(idd == R.id.live3_btn){
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/fullview03/stream1");
        }else if(idd == R.id.live4_btn){
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/fullview04/stream1");
        }else if(idd == R.id.live5_btn){
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/fullview05/stream1");
        }else{
            video.setVideoPath("rtmp://wmvdo.nicejj.cn/fullview06/stream1");
        }
        video.start();
        View cur2 = dialog.findViewById(idd);
        cur2.setBackgroundResource(R.drawable.live_button_border_on);
        curID = idd;
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}

