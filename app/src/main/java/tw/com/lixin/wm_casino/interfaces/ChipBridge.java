package tw.com.lixin.wm_casino.interfaces;

import tw.com.lixin.wm_casino.models.Chip;

public interface ChipBridge {

    public void turnOn();

    public void turnOff();

    public Chip getChip();
}
