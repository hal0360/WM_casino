package tw.com.lixin.wm_casino.websocketSource;

import android.os.Handler;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import tw.com.atromoby.utils.Cmd;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.CheckData;
import tw.com.lixin.wm_casino.dataModels.LoginData;
import tw.com.lixin.wm_casino.dataModels.LoginResData;
import tw.com.lixin.wm_casino.interfaces.CmdLog;
import tw.com.lixin.wm_casino.interfaces.CmdStr;


public abstract class CasinoSource extends WebSocketListener{

    private WebSocket webSocket = null;
    private Handler genHandler = new Handler();

    private Handler logHandler = new Handler();

    private boolean connected = false;
    private String loginDataStr;
    private String webUrl;
    private CmdLog cmdLogOpen;
    private CmdStr cmdLogFail;

    private boolean isBinded = false;

    public void binded(boolean bid){
        isBinded = bid;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        send(loginDataStr);
    }

    public final void login(String user, String pass, CmdLog logOK, CmdStr logFail){
        close();
        cmdLogOpen = logOK;
        cmdLogFail = logFail;
        logHandler.postDelayed(()-> {
            cmdLogOpen = null;
            if(cmdLogFail != null) cmdLogFail.exec("Websocket login timeout");
            cmdLogFail = null;
            close();
        },6000);
        loginDataStr = Json.to(new LoginData( user, pass));
        OkHttpClient client = new OkHttpClient();
        webSocket = client.newWebSocket(new Request.Builder().url(webUrl).build(), this);
        client.dispatcher().executorService().shutdown();
    }

    public final void login(String sid, CmdLog logOK, CmdStr logFail){
        close();
        cmdLogOpen = logOK;
        cmdLogFail = logFail;
        logHandler.postDelayed(()-> {
            cmdLogOpen = null;
            if(cmdLogFail != null) cmdLogFail.exec("Websocket login timeout");
            cmdLogFail = null;
            close();
        },6000);
        loginDataStr = Json.to(new CheckData(sid));
        OkHttpClient client = new OkHttpClient();
        webSocket = client.newWebSocket(new Request.Builder().url(webUrl).build(), this);
        client.dispatcher().executorService().shutdown();
    }


    public void defineURL(String url){
        webUrl = url;
    }

    public boolean isConnected() {
        return connected;
    }

    public abstract void onReceive(String text);

    public abstract void onLogin(LoginResData data);

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        if(!connected){
            LoginResData logRespend = Json.from(text, LoginResData.class);
            if(logRespend.protocol == 0){
                logHandler.removeCallbacksAndMessages(null);
                if(logRespend.data.bOk){
                    onLogin(logRespend);
                    connected = true;
                    cmdLogFail = null;
                    genHandler.post(() -> {
                        if(cmdLogOpen != null) cmdLogOpen.exec(logRespend.data);
                        cmdLogOpen = null;
                    });
                }else{
                    genHandler.post(() -> {
                        if(cmdLogFail != null) cmdLogFail.exec("Password incorrect!!");
                        close();
                    });
                }
            }
        }else {
            onReceive(text);
        }
    }

    public void handle(Cmd cmd){
        if(isBinded) genHandler.post(()->{ if(isBinded) cmd.exec(); });
    }

    public void handleSimple(Cmd cmd){
        genHandler.post(cmd::exec);
    }

    public void send(String message){
        webSocket.send(message);
    }

    public void close(){
        Log.e("onclose", "caleed");
        connected = false;
        cleanCallbacks();
        if(webSocket == null) return;
        webSocket.close(1000,null);
        webSocket = null;
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        //   Log.e("onMessageByte", bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Log.e("onClosing", "bye bye");
        //webSocket.close(1000, null);
        connected = false;
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.e("onClosing", "god bye");
        connected = false;
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.e(getClass().getSimpleName() + " failed", t.toString());
        genHandler.post(() -> {
            if(cmdLogFail != null) cmdLogFail.exec(t.toString());
            close();
        });
    }

    public void cleanCallbacks(){
        cmdLogOpen = null;
        cmdLogFail = null;
        genHandler.removeCallbacksAndMessages(null);
        logHandler.removeCallbacksAndMessages(null);
    }
}
