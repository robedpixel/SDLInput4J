package robedpixel.Sdl.Power;


import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class Power {
    private final NativePowerFuncs SdlFuncs;
    public Power(Arena allocator){
        SdlFuncs = NativePowerFuncs.getInstance(allocator);
    }

    /**
     * Get the current power supply details
     * @return Snapshot of power info at the time of call
     * @throws Throwable
     */
    public SdlPowerSnapshot getPowerInfo() throws Throwable {
        SdlPowerSnapshot returnObject = new SdlPowerSnapshot();
        SdlFuncs.getPowerInfo(returnObject);
        return returnObject;
    }
}
