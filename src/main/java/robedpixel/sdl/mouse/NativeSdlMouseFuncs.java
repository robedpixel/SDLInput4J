package robedpixel.sdl.mouse;

// TODO: add nullablility annotations
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlMouseFuncs {
  private static volatile NativeSdlMouseFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_HasMouse;
  private final MethodHandle SDL_GetMice;
  private final MethodHandle SDL_GetMouseNameForID;
  private final MethodHandle SDL_GetMouseFocus;
  private final MethodHandle SDL_GetMouseState;
  private final MethodHandle SDL_GetGlobalMouseState;
  private final MethodHandle SDL_GetRelativeMouseState;
  private final MethodHandle SDL_WarpMouseInWindow;
  private final MethodHandle SDL_WarpMouseGlobal;
  private final MethodHandle SDL_SetRelativeMouseTransform;
  private final MethodHandle SDL_SetWindowRelativeMouseMode;
  private final MethodHandle SDL_GetWindowRelativeMouseMode;
  private final MethodHandle SDL_CaptureMouse;
  private final MethodHandle SDL_CreateCursor;
  // private final MethodHandle  SDL_CreateColorCursor;
  private final MethodHandle SDL_CreateSystemCursor;
  private final MethodHandle SDL_SetCursor;
  private final MethodHandle SDL_GetCursor;
  private final MethodHandle SDL_GetDefaultCursor;
  private final MethodHandle SDL_DestroyCursor;
  private final MethodHandle SDL_ShowCursor;
  private final MethodHandle SDL_HideCursor;
  private final MethodHandle SDL_CursorVisible;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
  private final MemorySegment tempFloatAddress = objectAllocator.allocate(ValueLayout.JAVA_FLOAT);
  private final MemorySegment temp2FloatAddress = objectAllocator.allocate(ValueLayout.JAVA_FLOAT);

  public NativeSdlMouseFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_HasMouse =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasMouse").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_GetMice =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMice").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetMouseNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMouseNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetMouseFocus =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMouseFocus").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_GetMouseState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMouseState").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetGlobalMouseState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGlobalMouseState").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetRelativeMouseState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetRelativeMouseState").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_WarpMouseInWindow =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_WarpMouseInWindow").orElseThrow(),
                FunctionDescriptor.ofVoid(
                    ValueLayout.ADDRESS, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT));
    SDL_WarpMouseGlobal =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_WarpMouseGlobal").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_FLOAT));
    SDL_SetRelativeMouseTransform =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetRelativeMouseTransform").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_SetWindowRelativeMouseMode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetWindowRelativeMouseMode").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN));
    SDL_GetWindowRelativeMouseMode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetWindowRelativeMouseMode").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_CaptureMouse =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CaptureMouse").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_BOOLEAN));
    SDL_CreateCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CreateCursor").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT));
    /*SDL_CreateColorCursor =
    Linker.nativeLinker()
            .downcallHandle(
                    library.find("SDL_CreateColorCursor").orElseThrow(),
                    FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS,ValueLayout.JAVA_INT,ValueLayout.JAVA_INT));*/
    SDL_CreateSystemCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CreateSystemCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_SetCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_GetCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_GetDefaultCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDefaultCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_DestroyCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_DestroyCursor").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    SDL_ShowCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ShowCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_HideCursor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HideCursor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_CursorVisible =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CursorVisible").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
  }

  public synchronized boolean hasMouse() throws Throwable {
    return (boolean) SDL_HasMouse.invoke();
  }

  public int[] getMice() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment intArray = (MemorySegment) SDL_GetMice.invoke(tempIntAddress);
      int numMice = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      if (numMice > 0) {
        int[] returnObject = new int[numMice];
        for (int i = 0; i < returnObject.length; i++) {
          returnObject[i] = intArray.getAtIndex(ValueLayout.JAVA_INT, i);
        }
        return returnObject;
      } else {
        return new int[0];
      }
    }
  }

  public synchronized String getMouseNameForID(int instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetMouseNameForID.invoke(instanceId);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized MemorySegment getMouseFocus() throws Throwable {
    return (MemorySegment) SDL_GetMouseFocus.invoke();
  }

  public void getMouseState(SdlMouseState state) throws Throwable {
    synchronized (addressMutex) {
      int mouseButtonFlags = (int) SDL_GetMouseState.invoke(tempFloatAddress, temp2FloatAddress);
      state.setX(tempFloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setY(temp2FloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setMouseButtonFlags(mouseButtonFlags);
    }
  }

  public synchronized void getGlobalMouseState(SdlMouseState state) throws Throwable {
    synchronized (addressMutex) {
      int mouseButtonFlags =
          (int) SDL_GetGlobalMouseState.invoke(tempFloatAddress, temp2FloatAddress);
      state.setX(tempFloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setY(temp2FloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setMouseButtonFlags(mouseButtonFlags);
    }
  }

  public void getRelativeMouseState(SdlMouseState state) throws Throwable {
    synchronized (addressMutex) {
      int mouseButtonFlags =
          (int) SDL_GetRelativeMouseState.invoke(tempFloatAddress, temp2FloatAddress);
      state.setX(tempFloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setY(temp2FloatAddress.get(ValueLayout.JAVA_FLOAT, 0));
      state.setMouseButtonFlags(mouseButtonFlags);
    }
  }

  public synchronized void warpMouseInWindow(MemorySegment window, float x, float y)
      throws Throwable {
    SDL_WarpMouseInWindow.invoke(window, x, y);
  }

  public synchronized boolean warpMouseGlobal(float x, float y) throws Throwable {
    return (boolean) SDL_WarpMouseGlobal.invoke(x, y);
  }

  public synchronized boolean setRelativeMouseTransform(
      MemorySegment callback, MemorySegment userdata) throws Throwable {
    return (boolean) SDL_SetRelativeMouseTransform.invoke(callback, userdata);
  }

  public synchronized boolean setWindowRelativeMouseMode(MemorySegment window, boolean enabled)
      throws Throwable {
    return (boolean) SDL_SetWindowRelativeMouseMode.invoke(window, enabled);
  }

  public synchronized boolean getWindowRelativeMouseMode(MemorySegment window) throws Throwable {
    return (boolean) SDL_GetWindowRelativeMouseMode.invoke(window);
  }

  public synchronized boolean captureMouse(boolean enabled) throws Throwable {
    return (boolean) SDL_CaptureMouse.invoke(enabled);
  }

  public synchronized MemorySegment createCursor(
      MemorySegment data, MemorySegment mask, int w, int h, int hot_x, int hot_y) throws Throwable {
    return (MemorySegment) SDL_CreateCursor.invoke(data, mask, w, h, hot_x, hot_y);
  }

  /*public synchronized MemorySegment  createColorCursor(MemorySegment surface,int hot_x, int hot_y){

  }*/
  public synchronized MemorySegment createSystemCursor(int instanceId) throws Throwable {
    return (MemorySegment) SDL_CreateSystemCursor.invoke(instanceId);
  }

  public synchronized boolean setCursor(MemorySegment cursor) throws Throwable {
    return (boolean) SDL_SetCursor.invoke(cursor);
  }

  public synchronized MemorySegment getCursor() throws Throwable {
    return (MemorySegment) SDL_GetCursor.invoke();
  }

  public synchronized MemorySegment getDefaultCursor() throws Throwable {
    return (MemorySegment) SDL_GetDefaultCursor.invoke();
  }

  public synchronized void destroyCursor(MemorySegment cursor) throws Throwable {
    SDL_DestroyCursor.invoke(cursor);
  }

  public synchronized boolean showCursor() throws Throwable {
    return (boolean) SDL_ShowCursor.invoke();
  }

  public synchronized boolean hideCursor() throws Throwable {
    return (boolean) SDL_HideCursor.invoke();
  }

  public synchronized boolean cursorVisible() throws Throwable {
    return (boolean) SDL_CursorVisible.invoke();
  }

  public static NativeSdlMouseFuncs getInstance(Arena allocator) {
    NativeSdlMouseFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlMouseFuncs(allocator);
      }
    }
    return result;
  }
}
