package tw.com.lixin.wm_casino.models;

public class RoadItem {

    public RoadItem(int x, int y, int res){
        this.x = x;
        this.y = y;
        this.res = res;
    }

    public int res;
    public int x;
    public int y;
    public int color = 0xFFFFFFFF;
    public String word = "";
    public int strID = 0;
}
