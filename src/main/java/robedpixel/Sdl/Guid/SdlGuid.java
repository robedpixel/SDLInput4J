package robedpixel.Sdl.Guid;

import lombok.Getter;
import lombok.Setter;


import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;

public class SdlGuid {
    @Getter
    @Setter
    private short[] data = new short[16];
    @Getter
    private final MemorySegment funcDesc = MemorySegment.ofArray(data);
    @Getter
    private static final StructLayout structLayout = MemoryLayout.structLayout(MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_SHORT).withName("data")).withName("SDL_GUID");
}
