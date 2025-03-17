package robedpixel.sdl.haptic;

import java.lang.foreign.MemorySegment;
import lombok.Getter;

public class SdlHapticDevice implements AutoCloseable {
  @Getter private final MemorySegment address;
  private NativeSdlHapticFuncs funcs;

  public SdlHapticDevice(MemorySegment address, NativeSdlHapticFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }

  @Override
  public void close() throws Exception {
    try {
      funcs.closeHaptic(address);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
