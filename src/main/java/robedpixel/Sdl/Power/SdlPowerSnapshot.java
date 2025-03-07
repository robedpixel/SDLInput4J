package robedpixel.Sdl.Power;

import lombok.Getter;
import lombok.Setter;

/**
 * Container object to store each call of Power.getPowerInfo()
 */
public class SdlPowerSnapshot {
    /**
     * Seconds of battery life left
     */
    @Getter
    @Setter
    private int seconds;
    /**
     * Percentage of battery life left
     */
    @Getter
    @Setter
    private int percent;
    /**
     * State of system's power supply (See SdlPowerState)
     */
    @Getter
    @Setter
    private int powerState;
}
