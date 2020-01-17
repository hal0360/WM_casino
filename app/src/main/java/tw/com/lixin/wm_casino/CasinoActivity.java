package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.global.Poker;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.popups.WinLossPopup;
import tw.com.lixin.wm_casino.tools.CardArea;
import tw.com.lixin.wm_casino.tools.CasinoArea;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public abstract class CasinoActivity extends RootActivity implements TableBridge, GameBridge {

    protected CasinoArea casinoArea;
    protected CardArea cardArea;
    protected GameSource source;
    protected SparseArray<ImageView> pokers;
    private List<ChipStack> stacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stacks = new ArrayList<>();
        source = GameSource.getInstance();
        casinoArea = findViewById(R.id.casino_area);
        cardArea = findViewById(R.id.card_area);
        pokers = new SparseArray<>();
        casinoArea.setUp();
    }

    public CasinoArea getArea(){
        return  casinoArea;
    }

    protected void addToArea(int stackId, int maxVal, int area){
        ChipStack stack = findViewById(stackId);
        stack.setUp(area, maxVal);
        stacks.add(stack);
    }

    protected void addToArea(ChipStack stack, int maxVal, int area){
        stack.setUp(area, maxVal);
        stacks.add(stack);
    }

    protected void addCard(int area, int card_id){
        ImageView img = findViewById(card_id);
        pokers.put(area,img);
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

        for(int p = 0; p < pokers.size(); p++) pokers.valueAt(p).setVisibility(View.INVISIBLE);
        if(source.table.stage == 1){
            cardArea.setVisibility(View.INVISIBLE);
            casinoArea.betting();
        }else {
            cardArea.setVisibility(View.VISIBLE);
            casinoArea.dealing();
            for(int i = 0; i < source.pokers.size(); i++) {
                int key = source.pokers.keyAt(i);
                ImageView pokerImg = pokers.get(key);
                pokerImg.setVisibility(View.VISIBLE);
                pokerImg.setImageResource(Poker.NUM(source.pokers.get(key)));
            }
        }

        casinoArea.updateBalance();
        casinoArea.setMember(User.userName());
        casinoArea.setPPLnum(source.pplOnline);
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
    public void onBackPressed() {
        source.tableLogout();
        finish();
    }

    public abstract void betStarted();

    @Override
    public void stageUpdate() {
        casinoArea.setPPLnum(source.pplOnline);
        if (source.table.stage == 1) {
            source.pokers.clear();
            casinoArea.betting();
            for(int p = 0; p < pokers.size(); p++) pokers.valueAt(p).setVisibility(View.INVISIBLE);
            betStarted();
            cardArea.setVisibility(View.INVISIBLE);
            for (ChipStack stack: stacks) stack.clearCoin();
        } else if (source.table.stage == 2) {
            casinoArea.dealing();
            cardArea.setVisibility(View.VISIBLE);
            for (ChipStack stack: stacks) stack.cancelBet();
            casinoArea.cancelBtn.disable(true);
            casinoArea.rebetBtn.disable(true);
            casinoArea.confirmBtn.disable(true);
        } else if (source.table.stage == 4) {

        }
    }

    @Override
    public void betCountdown(int sec) {
        casinoArea.setSecond(sec);
    }

    @Override
    public void tableUpdate() {
        casinoArea.setGyuShu(source.table.number,source.table.round);
    }

    @Override
    public void cardUpdate(int area, int img) {


        ImageView pokerImg = pokers.get(area);
        if(pokerImg != null){
            pokerImg.setVisibility(View.VISIBLE);
            pokerImg.setImageResource(Poker.NUM(img));
        }
    }

    @Override
    public void betUpdate(boolean betOK) {
        if(betOK){
            alert("bet succ!");
            for (ChipStack stack: stacks) stack.comfirmBet();
            casinoArea.cancelBtn.disable(true);
            casinoArea.rebetBtn.disable(false);
            casinoArea.confirmBtn.disable(true);
        }else{ alert("bet fail!"); }
    }

    @Override
    public void balanceUpdate() {
        casinoArea.updateBalance();
    }

    @Override
    public void winLossUpdate(float win) {
        showPopup(new WinLossPopup(win));
    }

    public void confirm(){
        Client22 client22 = new Client22();
        for (ChipStack stack: stacks) stack.addCoinToClient(client22);
        if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
        else alert("You haven't put any money!");
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }

    public void cancel(){
        for (ChipStack stack: stacks) stack.cancelBet();
        casinoArea.confirmBtn.disable(true);
        casinoArea.cancelBtn.disable(true);
    }

    public void rebet(){
        for (ChipStack stack: stacks) stack.repeatBet();
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }

    public void stackBet() {
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }
}
