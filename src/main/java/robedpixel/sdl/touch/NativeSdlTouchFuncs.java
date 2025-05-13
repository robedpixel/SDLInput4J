package robedpixel.sdl.touch;


import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlTouchFuncs {
  private static volatile NativeSdlTouchFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
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
  @Nullable

  public SdlTouchIdArray getTouchDevices() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetTouchDevices.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        temp = temp.reinterpret(arraySize * ValueLayout.JAVA_INT.byteSize());
        return new SdlTouchIdArray(temp, arraySize);
      }
    }
  }
  @Nullable
  public synchronized String getTouchDeviceName(int touchId) throws Throwable {
    MemorySegment charAddress = (MemorySegment) SDL_GetTouchDeviceName.invoke(touchId);
    if (charAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getTouchDeviceType(int touchId) throws Throwable {
    return (int) SDL_GetTouchDeviceType.invoke(touchId);
  }
  @Nullable
  // assume return 2d array
  public SdlFinger2dArray getTouchFingers(int touchId) throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetTouchFingers.invoke(touchId, tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        temp = temp.reinterpret(arraySize * ValueLayout.ADDRESS.byteSize());
        return new SdlFinger2dArray(temp, arraySize);
      }
    }
  }
@NonNull
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
