package tw.com.lixin.wm_casino.tools;

import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import tw.com.atromoby.utils.Json;
import tw.com.atromoby.widgets.Cmd;
import tw.com.lixin.wm_casino.dataModels.GlobalData;
import tw.com.lixin.wm_casino.interfaces.CmdData;
import tw.com.lixin.wm_casino.interfaces.CmdStr;

public class WMSocket extends WebSocketListener {

    private WebSocket webSocket = null;
    private CmdStr cmdSocket;
    private Cmd cmdOpen, cmdFail;
    private Handler handler;
    public boolean connected = false;
    private SparseArray<CmdData> cmds = new SparseArray<>();

    public WMSocket(){
        handler = new Handler();
    }

    public void start(String url){
        close();
        OkHttpClient client = new OkHttpClient();
        webSocket = client.newWebSocket(new Request.Builder().url(url).build(), this);
        client.dispatcher().executorService().shutdown();
    }

    public void onSuccess(Cmd cmd){
        cmdOpen = cmd;
    }

    public void onFail(Cmd cmd){
        cmdFail = cmd;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        connected = true;
        if(cmdOpen != null){
            handler.post(() -> cmdOpen.exec());
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        GlobalData dataa = Json.from(text, GlobalData.class);

        CmdData cccmmdd = cmds.get(dataa.protocol);
        if(cccmmdd != null){
            handler.post(() -> cccmmdd.exec(dataa.data));
        }
    }

    public void send(String message){
        webSocket.send(message);
    }

    public void onReceive(int protocol, CmdData cmd){
        cmds.put(protocol,cmd);
    }

    public void close(){
        Log.e("onclose", "caleed");
        if(webSocket == null) return;
        webSocket.close(1000,null);
        webSocket = null;
        cleanCallbacks();
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
        Log.e("failed", t.toString());
        this.webSocket = null;
        if(cmdFail != null){
            handler.post(() -> cmdFail.exec());
        }
    }

    public void cleanCallbacks(){
        cmdFail = null;
        cmdOpen = null;
        cmdSocket = null;
        cmds = new SparseArray<>();
        handler.removeCallbacksAndMessages(null);
    }
}
