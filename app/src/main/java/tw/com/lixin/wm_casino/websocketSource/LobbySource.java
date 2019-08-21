package tw.com.lixin.wm_casino.websocketSource;

import java.util.ArrayList;

import tw.com.atromoby.utils.Cmd;
import tw.com.atromoby.utils.Json;
import tw.com.lixin.wm_casino.dataModels.LobbyData;
import tw.com.lixin.wm_casino.dataModels.LoginResData;
import tw.com.lixin.wm_casino.dataModels.gameData.Game;
import tw.com.lixin.wm_casino.global.User;
import tw.com.lixin.wm_casino.interfaces.LobbyBridge;


public class LobbySource extends CasinoSource{

    private static LobbySource single_instance = null;
    public static LobbySource getInstance()
    {
        if (single_instance == null) single_instance = new LobbySource();
        return single_instance;
    }

    private LobbySource() {
        defineURL("ws://gameserver.a45.me:15109");
        games = new ArrayList<>();
    }

    private LobbyBridge bridge;
    public ArrayList<Game> games;
    public int totalOnline;
    public int bacOnline;

    public Game findGame(int id){
        for(Game game: games){
            if (game.gameID == id) return game;
        }
        return null;
    }

    public void bind(LobbyBridge bridge){
        this.bridge = bridge;
        binded(true);
    }


    public void unbind(){
        bridge = null;
        binded(false);
    }


    @Override
    public void onReceive(String text) {
        LobbyData lobbyData = Json.from(text, LobbyData.class);
        switch(lobbyData.protocol) {
            case 35:
                games.addAll(lobbyData.data.gameArr);
                handle(()-> bridge.wholeDataUpdated());
                break;
            case 30:
                User.balance(lobbyData.data.balance);
                handle(()-> bridge.balanceUpdated());
                break;
            case 34:
                if(lobbyData.data.gameID == 109){
                    totalOnline = lobbyData.data.onlinePeople;
                }else if(lobbyData.data.gameID == 101){
                    bacOnline = lobbyData.data.onlinePeople;
                }
                handle(()-> bridge.peopleOnlineUpdate(lobbyData.data.gameID, lobbyData.data.onlinePeople));
                break;
        }
    }

    @Override
    public void onLogin(LoginResData data) {

    }
}
