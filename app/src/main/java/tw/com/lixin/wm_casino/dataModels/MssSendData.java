package tw.com.lixin.wm_casino.dataModels;

import java.util.List;

public class MssSendData {

    public Data data = new Data();
    int protocol = 22;

    public MssSendData(){
    }

    public class Data{
        public int gameID = -99;
        public int groupID = -99;
        public int areaID = -1;
        public int arguments;
        public String contents = "";
        public String messageType;

    }


}
