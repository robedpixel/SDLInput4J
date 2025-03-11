package robedpixel.sdl.joystick;

import robedpixel.sdl.SdlDevice;

import java.lang.foreign.MemorySegment;

public class SdlJoystickDevice extends SdlDevice {
    public SdlJoystickDevice(MemorySegment address) {
        super(address);
    }
}
