package robedpixel.sdl.sensors;

import java.lang.foreign.MemorySegment;

public class SdlSensorDevice implements AutoCloseable {
  private final MemorySegment address;
  private NativeSdlSensorsFuncs funcs;

  public SdlSensorDevice(MemorySegment address, NativeSdlSensorsFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  public MemorySegment getAddress() {
    return address.asReadOnly();
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
