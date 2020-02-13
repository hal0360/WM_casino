package tw.com.lixin.wm_casino.popups;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.com.atromoby.widgets.FragDialog;
import tw.com.atromoby.widgets.PopupFragment;
import tw.com.lixin.wm_casino.LoginActivity;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.tools.buttons.ClickConstraint;
import tw.com.lixin.wm_casino.websocketSource.GameSource;
import tw.com.lixin.wm_casino.websocketSource.LobbySource;
import tw.com.lixin.wm_casino.websocketSource.MessageSource;

public class ProfilePopup extends PopupFragment {


    @SuppressLint("SetTextI18n")
    @Override
    public void dialogCreated(FragDialog dialog) {
        dialog.setContentView(R.layout.profile_popup);

        TextView balance = dialog.findViewById(R.id.balance_txt);
        balance.setText(User.balance()+"");
        TextView member = dialog.findViewById(R.id.member_txt);
        member.setText(User.userName());


        dialog.setGravity(Gravity.TOP | Gravity.END);
        dialog.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ((ClickConstraint) dialog.findViewById(R.id.music_btn)).clicked(v-> dialog.getRootActivity().showPopup(new SoundPopup()));

        ((ClickConstraint) dialog.findViewById(R.id.language_btn)).clicked(v-> dialog.getRootActivity().showPopup(new LanguagePopup()));

        ((ClickConstraint) dialog.findViewById(R.id.rule_btn)).clicked(v-> dialog.getRootActivity().showPopup(new RulePopup()));

        ((ClickConstraint) dialog.findViewById(R.id.logout_btn)).clicked(v-> {
            LobbySource.getInstance().logout();
            GameSource.getInstance().tableLogout();
            MessageSource.getInstance().logout();
            Intent intent = new Intent(dialog.getRootActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    @Override
    public void dialogClosed(FragDialog dialog) {

    }

}
