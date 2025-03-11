package robedpixel.sdl.sensors;

import robedpixel.sdl.SdlDevice;

import java.lang.foreign.MemorySegment;

public class SdlSensorDevice extends SdlDevice {
  public SdlSensorDevice(MemorySegment address) {
    super(address);
  }
}
