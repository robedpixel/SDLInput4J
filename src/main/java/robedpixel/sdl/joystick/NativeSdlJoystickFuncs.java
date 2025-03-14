package robedpixel.sdl.joystick;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlJoystickFuncs {
  private static volatile NativeSdlJoystickFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_LockJoysticks;
  private final MethodHandle SDL_UnlockJoysticks;
  private final MethodHandle SDL_HasJoystick;
  private final MethodHandle SDL_GetJoysticks;
  private final MethodHandle SDL_GetJoystickNameForID;
  private final MethodHandle SDL_GetJoystickPathForID;
  private final MethodHandle SDL_GetJoystickPlayerIndexForID;
  private final MethodHandle SDL_CloseJoystick;

  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlJoystickFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_LockJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_LockJoysticks").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_UnlockJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UnlockJoysticks").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_HasJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasJoystick").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_GetJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoysticks").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetJoystickNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickPathForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPathForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickPlayerIndexForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPlayerIndexForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_CloseJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CloseJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
  }

  public synchronized void lockJoysticks() throws Throwable {
    SDL_LockJoysticks.invoke();
  }

  public synchronized void unlockJoysticks() throws Throwable {
    SDL_UnlockJoysticks.invoke();
  }

  public synchronized boolean hasJoystick() throws Throwable {
    return (boolean) SDL_HasJoystick.invoke();
  }

  public synchronized SdlJoystickIdArray getJoysticks() throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetJoysticks.invoke(tempIntAddress);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      temp = temp.reinterpret(arraySize * ValueLayout.JAVA_INT.byteSize());
      return new SdlJoystickIdArray(temp, arraySize);
    }
  }

  public synchronized String getJoystickNameForId(int instanceId) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetJoystickNameForID.invoke(instanceId);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized String getJoystickPathForId(int instanceId) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetJoystickPathForID.invoke(instanceId);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getJoystickPlayerIndexForId(int instanceId) throws Throwable {
    return (int) SDL_GetJoystickNameForID.invoke(instanceId);
  }

  public synchronized void closeJoystick(MemorySegment sensor) throws Throwable {
    SDL_CloseJoystick.invoke(sensor);
  }

  public static NativeSdlJoystickFuncs getInstance(Arena allocator) {
    NativeSdlJoystickFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlJoystickFuncs(allocator);
      }
    }
    return result;
  }
}
