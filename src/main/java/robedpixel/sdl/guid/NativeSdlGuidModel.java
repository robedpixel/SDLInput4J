package robedpixel.sdl.guid;

import lombok.Getter;
import lombok.Setter;


import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;

public class NativeSdlGuidModel {
    @Getter
    @Setter
    private short[] data = new short[16];
    @Getter
    private final MemorySegment funcDesc = MemorySegment.ofArray(data);
    /**
     * StructLayout of SdlGuid in SDL C Library
     */
    @Getter
    private static final StructLayout structLayout = MemoryLayout.structLayout(MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_SHORT).withName("data")).withName("SDL_GUID");
}
