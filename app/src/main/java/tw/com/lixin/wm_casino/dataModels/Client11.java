package tw.com.lixin.wm_casino.dataModels;

public class Client11 {
    private Data data = new Data();
    int protocol = 11;

    public Client11(int game, int group){
        data.groupID = group;
        data.gameID = game;
    }

    private class Data{
        int groupID ;
        int gameID;
        int areaID = -1;
    }


}
