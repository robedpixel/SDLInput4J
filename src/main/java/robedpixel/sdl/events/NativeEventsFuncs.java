package robedpixel.sdl.events;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeEventsFuncs {
  private static volatile NativeEventsFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_PumpEvents;
  private final MethodHandle SDL_PeepEvents;
    private final MethodHandle SDL_HasEvent;

  public NativeEventsFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_PumpEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PumpEvents").orElseThrow(), FunctionDescriptor.ofVoid());

    SDL_PeepEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PeepEvents").orElseThrow(), FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.JAVA_INT,ValueLayout.JAVA_INT,ValueLayout.JAVA_INT,ValueLayout.JAVA_INT));
  }

  public void pumpEvents() throws Throwable {
    SDL_PumpEvents.invoke();
  }
  //public int peepEvents()

  public static NativeEventsFuncs getInstance(Arena allocator) {
    NativeEventsFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeEventsFuncs(allocator);
      }
    }
    return result;
  }
}
