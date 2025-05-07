package robedpixel.sdl.gamepad;

import java.lang.foreign.MemorySegment;

public interface SdlGamepadDeviceInterface {
  public MemorySegment getAddress();
}
