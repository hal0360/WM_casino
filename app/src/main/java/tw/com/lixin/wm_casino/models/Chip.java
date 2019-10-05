package tw.com.lixin.wm_casino.models;


public class Chip{
    public int value, image;

    public static Chip curChip;

    public Chip(int val, int img) {
        value = val;
        image = img;
    }

}
