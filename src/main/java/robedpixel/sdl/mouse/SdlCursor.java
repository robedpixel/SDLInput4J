package robedpixel.sdl.mouse;

import java.lang.foreign.MemorySegment;

public interface SdlCursor {
    MemorySegment getAddress();
}
