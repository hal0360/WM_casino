package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

public class MessageData {
    public int protocol;
    public Data data;

    public class Data {
        public List<Data> messageBoxArr;
        public int gameID = -99;
        public int groupID = -99;
        public String arguments;
        public String contents;
        public String messageType;
        public String dateTime;
        public String sender;
        public String senderName;
        public int senderID;
        public boolean bOk;
    }
}
