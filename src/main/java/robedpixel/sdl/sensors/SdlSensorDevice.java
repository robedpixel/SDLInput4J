package robedpixel.sdl.sensors;

import java.lang.foreign.MemorySegment;
import lombok.Getter;

public class SdlSensorDevice {
  @Getter private MemorySegment address;

  public SdlSensorDevice(MemorySegment address) {
    this.address = address;
  }
}
