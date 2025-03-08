package robedpixel.Sdl.Power;


import java.lang.foreign.Arena;

public class SdlPower {
    private final NativeSdlPowerFuncs SdlFuncs;
    public SdlPower(Arena allocator){
        SdlFuncs = NativeSdlPowerFuncs.getInstance(allocator);
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
