package robedpixel.sdl.gamepad;

import java.lang.foreign.MemorySegment;

public class SdlGamepadDeviceInstance implements SdlGamepadDeviceInterface {
  private final MemorySegment address;

  public SdlGamepadDeviceInstance(MemorySegment address) {
    this.address = address;
  }

  @Override
  public MemorySegment getAddress() {
    return address.asReadOnly();
  }
}
