package tw.com.lixin.wm_casino.dataModels;

public class LoginResData {
    public int protocol;
    public Data data;

    public class Data {
        public Boolean bOk;
        public int gameID;
        public String account;
        public int memberID;
        public String userName;
        public int headID;
        public String sid;
    }

}
