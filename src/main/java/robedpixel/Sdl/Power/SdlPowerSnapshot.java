package robedpixel.Sdl.Power;

import lombok.Getter;
import lombok.Setter;

public class SdlPowerSnapshot {
    @Getter
    @Setter
    private int seconds;
    @Getter
    @Setter
    private int percent;
    @Getter
    @Setter
    private int powerState;
}
