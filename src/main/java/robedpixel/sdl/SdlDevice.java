package robedpixel.sdl;

import lombok.Getter;

import java.lang.foreign.MemorySegment;

public abstract class SdlDevice{
    @Getter
    private final MemorySegment address;

    public SdlDevice(MemorySegment address) {
        this.address = address;
    }
}
