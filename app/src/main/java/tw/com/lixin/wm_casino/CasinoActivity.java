package tw.com.lixin.wm_casino;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import tw.com.atromoby.utils.Json;
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
import tw.com.lixin.wm_casino.tools.txtViews.ResultText;
import tw.com.lixin.wm_casino.websocketSource.GameSource;

public abstract class CasinoActivity extends WMActivity implements TableBridge, GameBridge {

    protected CasinoArea casinoArea;
    protected CardArea cardArea;
    protected GameSource source;
    protected SparseArray<ImageView> pokers;
    private SparseArray<ChipStack> stacks;
    protected List<ConstraintLayout> pages;
    int curPage;
    protected List<View> grids;
    int curGrid;

    protected SparseArray<ResultText> resultTexts;

    private ArrowButton arrowLeft, arrowRight, arrowGridLeft, arrowGridRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        curPage = 0;
        curGrid = 0;
        pages = new ArrayList<>();
        resultTexts = new SparseArray<>();
        grids = new ArrayList<>();
        stacks = new SparseArray<>();
        source = GameSource.getInstance();
        casinoArea = findViewById(R.id.casino_area);
        cardArea = findViewById(R.id.card_area);
        pokers = new SparseArray<>();
        casinoArea.setUp();
        casinoArea.setBetTxt(source.totalBet);

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
        casinoArea.setTitle(getString(App.appNames.get(source.table.gameID)) + source.table.groupID);

    }

    public void addTxt(ResultText resultText){
        resultText.setVisibility(View.INVISIBLE);
        resultTexts.put(resultText.getId(),resultText);
    }

    public ResultText getTxt(int rid){
        ResultText resultText = resultTexts.get(rid);
        if(resultText == null) resultText = findViewById(rid);
        resultText.setVisibility(View.VISIBLE);
        return resultText;
    }

    public void resetVideo(){
        casinoArea.resetVideo();
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
        if(source.stage == 1){
            img.setVisibility(View.INVISIBLE);
        }else {
            img.setImageResource(Poker.NUM(source.pokers.get(area)));
        }

        pokers.put(area,img);
    }

    public void addCard(CardImage cardImage){
        if(source.stage == 1){
            cardImage.setVisibility(View.INVISIBLE);
        }else {
            cardImage.setImageResource(Poker.NUM(source.pokers.get(cardImage.area)));
        }
        pokers.put(cardImage.area,cardImage);
    }

    @Override
    public void serverFailed() {
        alert("Game disconnected");
        onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        gridUpdate();
        casinoArea.playVideo();
        source.table.bind(this);
        source.bind(this);
        casinoArea.bindMss();

        if (source.stage == 4) {
            alert("Table ended");
            onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        casinoArea.stopVideo();
        source.table.unBind();
        source.unbind();
        casinoArea.unBindMss();
    }

    @Override
    public void onBackPressed() {
        source.tableLogout();
        toActivity(LobbyActivity.class);
    }

    public void setResultText(int rid, String text){
        ResultText rTs = resultTexts.get(rid);
        if(rTs != null){
            rTs.setVisibility(View.VISIBLE);
            rTs.setText(text);
        }
    }

    public void showResultText(int rid){
        ResultText rTs = resultTexts.get(rid);
        if(rTs != null){
            rTs.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void statusUpdate() {
        casinoArea.setPPLnum(source.pplOnline);
        if (source.stage == 1) {
            source.pokers.clear();
            casinoArea.setBetTxt(source.totalBet);
            casinoArea.betting();
            for(int p = 0; p < pokers.size(); p++) pokers.valueAt(p).setVisibility(View.INVISIBLE);
            for(int t = 0; t < resultTexts.size(); t++) resultTexts.valueAt(t).setVisibility(View.INVISIBLE);
            cardArea.setVisibility(View.INVISIBLE);
            for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).refresh();
        } else if (source.stage == 2) {
            casinoArea.dealing();
            cardArea.setVisibility(View.VISIBLE);
            for(int s = 0; s < stacks.size(); s++) stacks.valueAt(s).refresh();
            casinoArea.cancelBtn.disable(true);
            casinoArea.rebetBtn.disable(true);
            casinoArea.confirmBtn.disable(true);
        } else if (source.stage == 4) {
            alert("Table ended");
            onBackPressed();
        }
    }
    @Override
    public void stageUpdate() { }

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
            int sumBet = 0;
            for(int s = 0; s < stacks.size(); s++){
                stacks.valueAt(s).comfirmBet();
                sumBet = sumBet + stacks.valueAt(s).data.value;
            }
            casinoArea.setBetTxt(sumBet);
            source.totalBet = sumBet;
            float curBal = User.balance();
            User.balance(curBal-sumBet);
            casinoArea.updateBalance();
            casinoArea.cancelBtn.disable(true);
            casinoArea.rebetBtn.disable(false);
            casinoArea.confirmBtn.disable(true);
        }else{
            //casinoArea.setBetTxt(0);
            alert("bet fail!");
        }
    }

    @Override
    public void balanceUpdate() {
        casinoArea.updateBalance();
    }

    @Override
    public void winLossUpdate(float win) {
        showPopup(new WinLossPopup());
    }

    public void confirm(){
        Client22 client22 = new Client22(source.table.gameID, source.table.groupID);
        for(int s = 0; s < stacks.size(); s++) {
            stacks.valueAt(s).addCoinToClient(client22);
        }
        if (client22.data.betArr.size() > 0) {
            source.send(Json.to(client22));
        }
        else alert("You haven't put any money!");
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
