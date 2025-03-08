package robedpixel.sdl.sensors;

import lombok.Getter;

import java.lang.foreign.MemorySegment;

public class SdlSensorDevice {
    @Getter
    private MemorySegment address;
    public SdlSensorDevice(MemorySegment address){
        this.address = address;
    }
}
