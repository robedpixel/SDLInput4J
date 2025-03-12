package robedpixel.sdl.touch;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlTouchFuncs {
  private static volatile NativeSdlTouchFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GetTouchDevices;
  private final MethodHandle SDL_GetTouchDeviceName;
  private final MethodHandle SDL_GetTouchDeviceType;
  private final MethodHandle SDL_GetTouchFingers;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlTouchFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetTouchDevices =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetTouchDevices").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetTouchDeviceName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetTouchDeviceName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetTouchDeviceType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetTouchDeviceType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetTouchFingers =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetTouchFingers").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
  }

  public synchronized SdlTouchIdArray getTouchDevices() throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetTouchDevices.invoke(tempIntAddress);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      return new SdlTouchIdArray(temp, arraySize);
    }
  }

  public synchronized String getTouchDeviceName(int touchId) throws Throwable {
    MemorySegment charAddress = (MemorySegment) SDL_GetTouchDeviceName.invoke(touchId);
    if (charAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charAddress.getString(0);
    }
  }

  public synchronized int getTouchDeviceType(int touchId) throws Throwable {
    return (int) SDL_GetTouchDeviceType.invoke(touchId);
  }

  // assume return 2d array
  public synchronized SdlFinger2dArray getTouchFingers(int touchId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetTouchFingers.invoke(touchId, tempIntAddress);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      return new SdlFinger2dArray(temp, arraySize);
    }
  }

  public static NativeSdlTouchFuncs getInstance(Arena allocator) {
    NativeSdlTouchFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlTouchFuncs(allocator);
      }
    }
    return result;
  }
}
