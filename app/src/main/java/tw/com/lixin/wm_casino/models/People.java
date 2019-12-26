package tw.com.lixin.wm_casino.models;

public class People {
    public String name;
    public int winRate, memberID;

    public People(String name, int winRate, int memberID){
        this.name = name;
        this.winRate = winRate;
        this.memberID = memberID;
    }
}
