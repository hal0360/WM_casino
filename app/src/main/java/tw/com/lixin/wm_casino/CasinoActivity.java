package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.RootActivity;
import tw.com.lixin.wm_casino.dataModels.Client22;
import tw.com.lixin.wm_casino.global.Poker;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.GameBridge;
import tw.com.lixin.wm_casino.interfaces.TableBridge;
import tw.com.lixin.wm_casino.popups.LimitPopup;
import tw.com.lixin.wm_casino.popups.WinLossPopup;
import tw.com.lixin.wm_casino.tools.CardArea;
import tw.com.lixin.wm_casino.tools.CardImage;
import tw.com.lixin.wm_casino.tools.CasinoArea;
import tw.com.lixin.wm_casino.tools.ChipStack;
import tw.com.lixin.wm_casino.tools.buttons.ArrowButton;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public abstract class CasinoActivity extends RootActivity implements TableBridge, GameBridge {

    protected CasinoArea casinoArea;
    protected CardArea cardArea;
    protected GameSource source;
    protected SparseArray<ImageView> pokers;
    private SparseArray<ChipStack> stacks;
    protected List<ConstraintLayout> pages;
    int curPage;
    protected List<View> grids;
    int curGrid;

    private ArrowButton arrowLeft, arrowRight, arrowGridLeft, arrowGridRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        curPage = 0;
        curGrid = 0;
        pages = new ArrayList<>();
        grids = new ArrayList<>();
        stacks = new SparseArray<>();
        source = GameSource.getInstance();
        casinoArea = findViewById(R.id.casino_area);
        cardArea = findViewById(R.id.card_area);
        pokers = new SparseArray<>();
        casinoArea.setUp();

        if(source.table.stage == 1){
            cardArea.setVisibility(View.INVISIBLE);
            casinoArea.betting();
        }else {
            cardArea.setVisibility(View.VISIBLE);
            casinoArea.dealing();
        }
        casinoArea.updateBalance();
        casinoArea.setMember(User.userName());
        casinoArea.setPPLnum(source.pplOnline);
        casinoArea.setGyuShu(source.table.number,source.table.round);
        casinoArea.updateBalance();
    }

    public CasinoArea getArea(){
        return  casinoArea;
    }

    public abstract void limitShows(LimitPopup limitPopup);

    protected void setStackAreaMax(int stack_id, long area, int maxVal){
        ChipStack stack = findViewById(stack_id);
        stack.setAreaMax(area,maxVal);
    }

    public void addToArea(ChipStack stack){
        stacks.put(stack.getId(), stack);
    }

    protected void addCard(int area, int card_id){

        ImageView img = findViewById(card_id);
        if(source.table.stage == 1){
            img.setVisibility(View.INVISIBLE);
        }else {
            img.setImageResource(Poker.NUM(source.pokers.get(area)));
        }

        pokers.put(area,img);
    }

    public void addCard(CardImage cardImage){
        if(source.table.stage == 1){
            cardImage.setVisibility(View.INVISIBLE);
        }else {
            cardImage.setImageResource(Poker.NUM(source.pokers.get(cardImage.area)));
        }
        pokers.put(cardImage.area,cardImage);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!source.isConnected()){
            alert("connection lost");
            finish();
            return;
        }

        gridUpdate();
        casinoArea.playVideo();
        source.table.bind(this);
        source.bind(this);
        casinoArea.login(source.table.gameID, source.table.groupID);
    }

    @Override
    public void onPause() {
        super.onPause();
        casinoArea.stopVideo();
        source.table.unBind();
        source.unbind();
        casinoArea.logout();
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
            for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).clearCoin();
        } else if (source.table.stage == 2) {
            casinoArea.dealing();
            cardArea.setVisibility(View.VISIBLE);
            for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).cancelBet();
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
            for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).comfirmBet();
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
        Client22 client22 = new Client22(source.table.gameID, source.table.groupID);
        for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).addCoinToClient(client22);
        if (client22.data.betArr.size() > 0) { source.send(Json.to(client22)); }
        else alert("You haven't put any money!");
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }

    public void cancel(){
        for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).cancelBet();
        casinoArea.confirmBtn.disable(true);
        casinoArea.cancelBtn.disable(true);
    }

    public void rebet(){
        for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).repeatBet();
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }

    public void stackBet() {
        casinoArea.confirmBtn.disable(false);
        casinoArea.cancelBtn.disable(false);
    }



    protected void addGrid(View grid){
        grids.add(grid);
    }
    protected void addPage(int page_id){
        ConstraintLayout page = findViewById(page_id);
        pages.add(page);
    }
    protected void setGridArrow(){
        arrowGridLeft = findViewById(R.id.arrow_left_grid);
        arrowGridRight = findViewById(R.id.arrow_right_grid);
        arrowGridRight.clicked(v->{
            curGrid++;
            if(curGrid>=grids.size()){
                curGrid = 0;
            }
            grids.get(curGrid).bringToFront();
            arrowGridLeft.bringToFront();
        });
        arrowGridLeft.clicked(v->{
            curGrid--;
            if(curGrid<0){
                curGrid = grids.size()-1;
            }
            grids.get(curGrid).bringToFront();
            arrowGridRight.bringToFront();
        });
    }
    protected void setPageArrow(){
        arrowLeft = findViewById(R.id.arrow_left);
        arrowRight = findViewById(R.id.arrow_right);
        arrowRight.clicked(v->{
            curPage++;
            if(curPage >= pages.size()){
                curPage = 0;
            }
            pages.get(curPage).bringToFront();
            arrowLeft.bringToFront();
        });
        arrowLeft.clicked(v->{
            curPage--;
            if(curPage<0){
                curPage = pages.size()-1;
            }
            pages.get(curPage).bringToFront();
            arrowRight.bringToFront();
        });
    }
}
