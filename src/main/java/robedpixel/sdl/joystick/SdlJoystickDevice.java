package robedpixel.sdl.joystick;

import java.lang.foreign.MemorySegment;

public class SdlJoystickDevice implements AutoCloseable {
  private final MemorySegment address;
  private NativeSdlJoystickFuncs funcs;

  public SdlJoystickDevice(MemorySegment address, NativeSdlJoystickFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  public MemorySegment getAddress() {
    return address.asReadOnly();
  }

  @Override
  public void close() throws Exception {
    try {
      funcs.closeJoystick(address);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
