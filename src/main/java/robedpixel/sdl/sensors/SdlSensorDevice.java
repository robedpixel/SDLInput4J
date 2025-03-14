package robedpixel.sdl.sensors;

import java.lang.foreign.MemorySegment;
import lombok.Getter;

public class SdlSensorDevice implements AutoCloseable {
  @Getter private final MemorySegment address;
  private NativeSdlSensorsFuncs funcs;

  public SdlSensorDevice(MemorySegment address, NativeSdlSensorsFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  @Override
  public void close() throws Exception {
    try {
      funcs.closeSensor(address);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
