package robedpixel.sdl.rect;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlRectFuncs {
  private static volatile NativeSdlRectFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_HasRectIntersection;

  public NativeSdlRectFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_HasRectIntersection =
            Linker.nativeLinker()
                    .downcallHandle(
                            library.find("SDL_HasRectIntersection").orElseThrow(),
                            FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
  }
  public boolean hasRectIntersection(MemorySegment A, MemorySegment B) throws Throwable {
    return (boolean) SDL_HasRectIntersection.invoke(A,B);
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
