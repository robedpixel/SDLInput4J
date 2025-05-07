package robedpixel.sdl.mouse;

import java.lang.foreign.MemorySegment;

public class SdlCursorInstance implements AutoCloseable, SdlCursor {
  private MemorySegment address;
  private NativeSdlMouseFuncs funcs;

  public SdlCursorInstance(MemorySegment address, NativeSdlMouseFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  @Override
  public void close() throws Exception {
    try {
      funcs.destroyCursor(address);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public MemorySegment getAddress() {
    return address.asReadOnly();
  }
}
