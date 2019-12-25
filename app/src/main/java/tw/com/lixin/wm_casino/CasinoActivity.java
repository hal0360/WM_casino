package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.models.ChipStackData;
import tw.com.lixin.wm_casino.popups.WinLossPopup;
import tw.com.lixin.wm_casino.tools.CardArea;
import tw.com.lixin.wm_casino.tools.CasinoArea;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public class CasinoActivity extends RootActivity implements TableBridge, GameBridge {

    protected CasinoArea casinoArea;
    protected CardArea cardArea;
    protected GameSource source;

    private List<ChipStack> stacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        stacks = new ArrayList<>();
        source = GameSource.getInstance();
        casinoArea = findViewById(R.id.casino_area);
        cardArea = findViewById(R.id.card_area);
    }

    public CasinoArea getArea(){
        return  casinoArea;
    }

    public void AddToArea(int stackId, int maxVal, int area){
        ChipStack stack = findViewById(stackId);
        stack.setUp(area, maxVal);
        stacks.add(stack);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()){
            alert("connection lost");
            finish();
            return;
        }
        casinoArea.setGyuShu(source.table.number,source.table.round);
        casinoArea.updateBalance();
        casinoArea.playVideo();
        source.table.bind(this);
        source.bind(this);

        if(source.table.stage == 1){
            cardArea.setVisibility(View.INVISIBLE);
            casinoArea.betting();
        }else {
            cardArea.setVisibility(View.VISIBLE);
           // if(source.table.result != -99) cardArea.showScore(table.playerScore, table.bankerScore);
            casinoArea.dealing();
        }
        casinoArea.updateBalance();
        casinoArea.setMember(User.userName());
        gridUpdate();
    }

    @Override
    public void onPause() {
        super.onPause();
        casinoArea.stopVideo();
        source.table.unBind();
        source.unbind();
    }

    @Override
    public void stageUpdate() {
        if (source.table.stage == 1) {
            casinoArea.betting();
            cardArea.setVisibility(View.VISIBLE);
            for (ChipStack stack: stacks) stack.clearCoin();
        } else if (source.table.stage == 2) {
            casinoArea.dealing();
            cardArea.setVisibility(View.VISIBLE);
            for (ChipStack stack: stacks) stack.cancelBet();

            cancelBtn.disable(true);
            rebetBtn.disable(true);
            betBtn.disable(true);
        } else if (source.table.stage == 4) {

        }
    }

    @Override
    public void gridUpdate() {

    }

    @Override
    public void betCountdown(int sec) {
        casinoArea.setSecond(sec);
    }

    @Override
    public void resultUpdate() {

    }

    @Override
    public void tableUpdate() {
        casinoArea.setGyuShu(source.table.number,source.table.round);
    }

    @Override
    public void cardUpdate(int area, int img) {

    }

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            for (ChipStack stack: stacks) stack.comfirmBet();

            cancelBtn.disable(true);
            rebetBtn.disable(false);
            betBtn.disable(true);
        }else{ alert("bet fail!"); }
    }

    @Override
    public void balanceUpdate() {
        casinoArea.updateBalance();
    }

    @Override
    public void winLossUpdate(float win) {
        WinLossPopup popup = new WinLossPopup();
        popup.setPay(win);
        showPopup(new WinLossPopup());
    }


    public void confirm(){

    }

    public void cancel(){

    }

    public void rebet(){

    }
}
