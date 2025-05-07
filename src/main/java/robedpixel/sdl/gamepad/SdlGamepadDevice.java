package robedpixel.sdl.gamepad;

import java.lang.foreign.MemorySegment;

public class SdlGamepadDevice implements AutoCloseable, SdlGamepadDeviceInterface {
  private final MemorySegment address;
  private NativeSdlGamepadFuncs funcs;

  public SdlGamepadDevice(MemorySegment address, NativeSdlGamepadFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  @Override
  public MemorySegment getAddress() {
    return address.asReadOnly();
  }

  @Override
  public void close() throws Exception {
    try {
      funcs.closeGamepad(address);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
