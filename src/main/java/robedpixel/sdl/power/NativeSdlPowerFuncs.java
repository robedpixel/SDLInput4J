package robedpixel.sdl.power;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import org.jspecify.annotations.NonNull;

class NativeSdlPowerFuncs {
  private static volatile NativeSdlPowerFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_GetPowerInfo;
  private final Arena localAllocator = Arena.ofAuto();
  private final MemorySegment secondAddress = localAllocator.allocate(ValueLayout.JAVA_INT);
  private final MemorySegment percentAddress = localAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlPowerFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetPowerInfo =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetPowerInfo").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
  }

  public void getPowerInfo(SdlPowerSnapshot sdlPowerSnapshot) throws Throwable {
    synchronized (addressMutex) {
      int powerState = (int) SDL_GetPowerInfo.invoke(secondAddress, percentAddress);
      sdlPowerSnapshot.setPowerState(powerState);
      sdlPowerSnapshot.setSeconds(secondAddress.get(ValueLayout.JAVA_INT, 0));
      sdlPowerSnapshot.setPercent(percentAddress.get(ValueLayout.JAVA_INT, 0));
    }
  }

  @NonNull
  public static NativeSdlPowerFuncs getInstance(Arena allocator) {
    NativeSdlPowerFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlPowerFuncs(allocator);
      }
    }
    return result;
  }
}
