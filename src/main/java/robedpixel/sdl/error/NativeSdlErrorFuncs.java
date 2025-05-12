package robedpixel.sdl.error;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import org.jspecify.annotations.NonNull;

class NativeSdlErrorFuncs {
  private static volatile NativeSdlErrorFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GetError;
  private final MethodHandle SDL_ClearError;

  public NativeSdlErrorFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetError =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetError").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_ClearError =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ClearError").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
  }

  @NonNull
  public synchronized String getError() throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetError.invoke();
    return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
  }

  public synchronized boolean clearError() throws Throwable {
    return (boolean) SDL_ClearError.invoke();
  }

  @NonNull
  public static NativeSdlErrorFuncs getInstance(Arena allocator) {
    NativeSdlErrorFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlErrorFuncs(allocator);
      }
    }
    return result;
  }
}
