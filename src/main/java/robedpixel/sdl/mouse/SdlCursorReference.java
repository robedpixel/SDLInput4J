package robedpixel.sdl.mouse;

import java.lang.foreign.MemorySegment;

public class SdlCursorReference implements SdlCursor {
  private MemorySegment address;

  public SdlCursorReference(MemorySegment address) {
    this.address = address;
  }

  @Override
  public MemorySegment getAddress() {
    return address.asReadOnly();
  }
}
