package tw.com.lixin.wm_casino.models;

import java.util.ArrayList;
import java.util.List;

public class ChipStackData {
    public int hit = 0;
    public int value = 0;
    public int maxValue = 9999;
    public int minValue = 1;

    public List<Chip> addedChip = new ArrayList<>();
    public List<Chip> tempAddedChip = new ArrayList<>();
}
