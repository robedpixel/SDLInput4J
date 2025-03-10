package robedpixel.sdl.mouse;

import robedpixel.sdl.power.NativeSdlPowerFuncs;

import java.lang.foreign.Arena;

public class SdlMouse {
    private final NativeSdlMouseFuncs SdlFuncs;

    public SdlMouse(Arena allocator) {
        SdlFuncs = NativeSdlMouseFuncs.getInstance(allocator);
    }

    /**
     * Return whether a mouse is currently connected.
     * @return Returns true if a mouse is connected, false otherwise.
     * @throws Throwable
     */
    public boolean hasMouse() throws Throwable{
        return SdlFuncs.hasMouse();
    }
}
