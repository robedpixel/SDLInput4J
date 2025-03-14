package robedpixel.sdl.joystick;

import lombok.Getter;

import java.lang.foreign.MemorySegment;

public class SdlJoystickDevice implements AutoCloseable{
    @Getter
    private final MemorySegment address;
    private NativeSdlJoystickFuncs funcs;
    public SdlJoystickDevice(MemorySegment address, NativeSdlJoystickFuncs funcs) {
        this.address = address;
        this.funcs = funcs;
    }

    @Override
    public void close() throws Exception {
        try {
            funcs.closeJoystick(address);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
