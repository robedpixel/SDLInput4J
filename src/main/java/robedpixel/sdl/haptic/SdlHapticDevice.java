package robedpixel.sdl.haptic;

import java.lang.foreign.MemorySegment;
import lombok.Getter;

public class SdlHapticDevice implements AutoCloseable {
  private final MemorySegment address;
  private NativeSdlHapticFuncs funcs;

  public SdlHapticDevice(MemorySegment address, NativeSdlHapticFuncs funcs) {
    this.address = address;
    this.funcs = funcs;
  }
  public MemorySegment getAddress(){
    return address.asReadOnly();
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
