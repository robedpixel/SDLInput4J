package robedpixel.sdl.video;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;

class NativeSdlVideoFuncs {
  private static volatile NativeSdlVideoFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GetNumVideoDrivers;
  private final MethodHandle SDL_GetVideoDriver;
  private final MethodHandle SDL_GetCurrentVideoDriver;
  private final MethodHandle SDL_GetSystemTheme;
  private final MethodHandle SDL_GetDisplays;
  private final MethodHandle SDL_GetPrimaryDisplay;
  private final MethodHandle SDL_GetDisplayProperties;
  private final MethodHandle SDL_GetDisplayName;
  private final MethodHandle SDL_GetDisplayBounds;
  private final MethodHandle SDL_GetDisplayUsableBounds;
  private final MethodHandle SDL_GetNaturalDisplayOrientation;
  private final MethodHandle SDL_GetCurrentDisplayOrientation;
  private final MethodHandle SDL_GetDisplayContentScale;
  private final MethodHandle SDL_GetFullscreenDisplayModes;
  private final MethodHandle SDL_GetClosestFullscreenDisplayMode;
  private final MethodHandle SDL_GetDesktopDisplayMode;
  private final MethodHandle SDL_GetCurrentDisplayMode;
  private final MethodHandle SDL_GetDisplayForPoint;
  private final MethodHandle SDL_GetDisplayForRect;
  private final MethodHandle SDL_ScreenSaverEnabled;
  private final MethodHandle SDL_EnableScreenSaver;
  private final MethodHandle SDL_DisableScreenSaver;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlVideoFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetNumVideoDrivers =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumVideoDrivers").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    SDL_GetVideoDriver =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetVideoDriver").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetCurrentVideoDriver =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetCurrentVideoDriver").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_GetSystemTheme =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSystemTheme").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    SDL_GetDisplays =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplays").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetPrimaryDisplay =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetPrimaryDisplay").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    SDL_GetDisplayProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetDisplayName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetDisplayBounds =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayBounds").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetDisplayUsableBounds =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayUsableBounds").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNaturalDisplayOrientation =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNaturalDisplayOrientation").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetCurrentDisplayOrientation =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetCurrentDisplayOrientation").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetDisplayContentScale =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayContentScale").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_FLOAT, ValueLayout.JAVA_INT));
    SDL_GetFullscreenDisplayModes =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetFullscreenDisplayModes").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetClosestFullscreenDisplayMode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetClosestFullscreenDisplayMode").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_FLOAT,
                    ValueLayout.JAVA_BOOLEAN));
    SDL_GetDesktopDisplayMode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDesktopDisplayMode").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetCurrentDisplayMode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetCurrentDisplayMode").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetDisplayForPoint =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayForPoint").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetDisplayForRect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetDisplayForRect").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_ScreenSaverEnabled =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ScreenSaverEnabled").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout
                        .JAVA_BOOLEAN)); // Check whether the screensaver is currently enabled.
    SDL_EnableScreenSaver =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_EnableScreenSaver").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN)); // Allow the screen to be blanked by a screen saver.
    SDL_DisableScreenSaver =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_DisableScreenSaver").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
  }

  public synchronized int getNumVideoDrivers() throws Throwable {
    return (int) SDL_GetNumVideoDrivers.invoke();
  }

  public synchronized String getVideoDriver(int index) throws Throwable {
    MemorySegment charAddress = (MemorySegment) SDL_GetVideoDriver.invoke(index);
    return charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
  }

  public synchronized String getCurrentVideoDriver() throws Throwable {
    MemorySegment charAddress = (MemorySegment) SDL_GetCurrentVideoDriver.invoke();
    if (charAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getSystemTheme() throws Throwable {
    return (int) SDL_GetSystemTheme.invoke();
  }

  public synchronized SdlDisplayIdArray getDisplays() throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetDisplays.invoke(tempIntAddress);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      temp = temp.reinterpret(arraySize * ValueLayout.JAVA_INT.byteSize());
      return new SdlDisplayIdArray(temp, arraySize);
    }
  }

  public synchronized int getPrimaryDisplay() throws Throwable {
    return (int) SDL_GetPrimaryDisplay.invoke();
  }

  public synchronized int getDisplayProperties(int displayId) throws Throwable {
    return (int) SDL_GetDisplayProperties.invoke(displayId);
  }

  public synchronized String getDisplayName(int displayId) throws Throwable {
    MemorySegment charAddress = (MemorySegment) SDL_GetDisplayName.invoke(displayId);
    if (charAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charAddress.reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.UTF_8);
    }
  }

  public synchronized boolean getDisplayBounds(int displayId, MemorySegment rect) throws Throwable {
    return (boolean) SDL_GetDisplayBounds.invoke(displayId, rect);
  }

  public synchronized boolean getDisplayUsableBounds(int displayId, MemorySegment rect) throws Throwable {
    return (boolean) SDL_GetDisplayUsableBounds.invoke(displayId, rect);
  }

  public synchronized int getNaturalDisplayOrientation(int displayId) throws Throwable {
    return (int) SDL_GetNaturalDisplayOrientation.invoke(displayId);
  }

  public synchronized int getCurrentDisplayOrientation(int displayId) throws Throwable {
    return (int) SDL_GetCurrentDisplayOrientation.invoke(displayId);
  }

  public synchronized float getDisplayContentScale(int displayId) throws Throwable {
    return (float) SDL_GetDisplayContentScale.invoke(displayId);
  }

  // TODO: need test
  public synchronized SdlDisplayModeArray getFullscreenDisplayModes(int displayId) throws Throwable {
    MemorySegment temp =
        (MemorySegment) SDL_GetFullscreenDisplayModes.invoke(displayId, tempIntAddress);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      temp = temp.reinterpret(arraySize * ValueLayout.ADDRESS.byteSize());
      return new SdlDisplayModeArray(temp, arraySize);
    }
  }

  public synchronized boolean getClosestFullscreenDisplayMode(
      int displayId,
      int width,
      int height,
      float refreshRate,
      boolean includeHighDensityModes,
      MemorySegment closest)
      throws Throwable {
    return (boolean)
        SDL_GetClosestFullscreenDisplayMode.invoke(
            displayId, width, height, refreshRate, includeHighDensityModes, closest);
  }

  public synchronized MemorySegment getDesktopDisplayMode(int displayId) throws Throwable {
    return (MemorySegment) SDL_GetDesktopDisplayMode.invoke(displayId);
  }

  public synchronized MemorySegment getCurrentDisplayMode(int displayId) throws Throwable {
    return (MemorySegment) SDL_GetCurrentDisplayMode.invoke(displayId);
  }

  public synchronized int getDisplayForPoint(MemorySegment point) throws Throwable {
    return (int) SDL_GetDisplayForPoint.invoke(point);
  }

  public synchronized int getDisplayForRect(MemorySegment rect) throws Throwable {
    return (int) SDL_GetDisplayForRect.invoke(rect);
  }

  public synchronized boolean screenSaverEnabled() throws Throwable {
    return (boolean) SDL_ScreenSaverEnabled.invoke();
  }

  public synchronized boolean enableScreenSaver() throws Throwable {
    return (boolean) SDL_EnableScreenSaver.invoke();
  }

  public synchronized boolean disableScreenSaver() throws Throwable {
    return (boolean) SDL_DisableScreenSaver.invoke();
  }

  public static NativeSdlVideoFuncs getInstance(Arena allocator) {
    NativeSdlVideoFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlVideoFuncs(allocator);
      }
    }
    return result;
  }
}
