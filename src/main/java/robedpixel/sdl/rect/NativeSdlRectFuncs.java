package robedpixel.sdl.rect;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlRectFuncs {
  private static volatile NativeSdlRectFuncs INSTANCE;
  private static final Object mutex = new Object();

  public NativeSdlRectFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
  }

  public static NativeSdlRectFuncs getInstance(Arena allocator) {
    NativeSdlRectFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlRectFuncs(allocator);
      }
    }
    return result;
  }
}
