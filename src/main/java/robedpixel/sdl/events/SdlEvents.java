package robedpixel.sdl.events;

import java.lang.foreign.Arena;

public class SdlEvents {
    private final NativeEventsFuncs sdlFuncs;
    public SdlEvents(Arena allocator) {
        sdlFuncs = NativeEventsFuncs.getInstance(allocator);
    }
}
