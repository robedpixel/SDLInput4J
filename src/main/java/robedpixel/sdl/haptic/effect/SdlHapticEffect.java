package robedpixel.sdl.haptic.effect;

import java.lang.foreign.MemorySegment;

public interface SdlHapticEffect {
  public MemorySegment getMemorySegment();
}
