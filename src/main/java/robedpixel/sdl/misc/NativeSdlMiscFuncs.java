package robedpixel.sdl.misc;

// TODO: add nullablility annotations
import org.jspecify.annotations.NonNull;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlMiscFuncs {
  private static volatile NativeSdlMiscFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_OpenURL;

  public NativeSdlMiscFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_OpenURL =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenURL").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
  }

  public synchronized boolean openUrl(Arena localAllocator, String url) throws Throwable {
    return (boolean) SDL_OpenURL.invoke(localAllocator.allocateFrom(url));
  }
  @NonNull

  public static NativeSdlMiscFuncs getInstance(Arena allocator) {
    NativeSdlMiscFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlMiscFuncs(allocator);
      }
    }
    return result;
  }
}
