package robedpixel.sdl.video;

import java.lang.foreign.MemorySegment;

public class SdlWindow {
  private final MemorySegment address;

  public SdlWindow(MemorySegment address) {
    this.address = address;
  }

  public MemorySegment getAddress() {
    return address.asReadOnly();
  }
}
