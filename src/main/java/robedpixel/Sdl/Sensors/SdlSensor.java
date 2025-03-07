package robedpixel.Sdl.Sensors;

import lombok.Getter;

import java.lang.foreign.MemorySegment;

public class SdlSensor {
    @Getter
    private MemorySegment address;
    public SdlSensor(MemorySegment address){
        this.address = address;
    }
}
