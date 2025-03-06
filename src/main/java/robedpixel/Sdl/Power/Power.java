package robedpixel.Sdl.Power;


import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class Power {
    private final NativePowerFuncs SdlFuncs;
    public Power(Arena allocator){
        SdlFuncs = NativePowerFuncs.getInstance(allocator);
    }
    public SdlPowerSnapshot getPowerInfo(Arena localAllocator) throws Throwable {
        SdlPowerSnapshot returnObject = new SdlPowerSnapshot();
        try(Arena arena = Arena.ofConfined()) {
            MemorySegment secondAddress = arena.allocate(ValueLayout.JAVA_INT);
            MemorySegment percentAddress = arena.allocate(ValueLayout.JAVA_INT);
            int powerState = (int) SdlFuncs.getPowerInfo(secondAddress, percentAddress);
            returnObject.setPowerState(powerState);
            returnObject.setSeconds(secondAddress.get(ValueLayout.JAVA_INT,0));
            returnObject.setPercent(percentAddress.get(ValueLayout.JAVA_INT,9));
        }
        return returnObject;
    }
}
