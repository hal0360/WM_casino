package tw.com.lixin.wm_casino.models;


public class Chip{
    public int value, image, chipId;
    public static boolean isCustom = false;

    public static Chip curChip;

    public Chip(int val, int img, int resId) {
        value = val;
        image = img;
        chipId = resId;
    }

}
