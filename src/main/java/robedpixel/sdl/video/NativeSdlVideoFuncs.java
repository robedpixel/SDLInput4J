package robedpixel.sdl.video;


import robedpixel.sdl.touch.SdlTouchIdArray;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;

class NativeSdlVideoFuncs {
    private static volatile NativeSdlVideoFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetNumVideoDrivers;
    private final MethodHandle SDL_GetVideoDriver;
    private final MethodHandle  SDL_GetCurrentVideoDriver;
    private final MethodHandle SDL_GetSystemTheme;
    private final MethodHandle SDL_GetDisplays;
    private final MethodHandle SDL_GetPrimaryDisplay;
    private final MethodHandle SDL_GetDisplayProperties;
    private final MethodHandle SDL_GetDisplayName;
    private final Arena objectAllocator = Arena.ofAuto();
    private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
    public NativeSdlVideoFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GetNumVideoDrivers = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetNumVideoDrivers").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT));
        SDL_GetVideoDriver = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetVideoDriver").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.JAVA_INT));
        SDL_GetCurrentVideoDriver = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetCurrentVideoDriver").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS));
        SDL_GetSystemTheme = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetSystemTheme").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT));
        SDL_GetDisplays = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetDisplays").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS));
        SDL_GetPrimaryDisplay = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetPrimaryDisplay").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT));
        SDL_GetDisplayProperties = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetDisplayProperties").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.JAVA_INT,ValueLayout.JAVA_INT));
        SDL_GetDisplayName = Linker.nativeLinker()
                .downcallHandle(
                        library.find("SDL_GetDisplayName").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.JAVA_INT));
    }

    public int getNumVideoDrivers() throws Throwable {
        return (int) SDL_GetNumVideoDrivers.invoke();
    }
    public String getVideoDriver(int index) throws Throwable{
        MemorySegment charAddress = (MemorySegment) SDL_GetVideoDriver.invoke(index);
        return charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
    public String getCurrentVideoDriver() throws Throwable{
        MemorySegment charAddress = (MemorySegment) SDL_GetCurrentVideoDriver.invoke();
        if (charAddress == MemorySegment.NULL) {
            return null;
        } else {
            return charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
        }
    }
    public int getSystemTheme() throws Throwable {
        return (int) SDL_GetSystemTheme.invoke();
    }
    public SdlDisplayIdArray getDisplays() throws Throwable{
        MemorySegment temp = (MemorySegment) SDL_GetDisplays.invoke(tempIntAddress);
        if (temp == MemorySegment.NULL) {
            return null;
        } else {
            int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
            temp = temp.reinterpret(arraySize*ValueLayout.JAVA_INT.byteSize());
            return new SdlDisplayIdArray(temp, arraySize);
        }
    }
    public int getPrimaryDisplay() throws Throwable{
        return (int) SDL_GetPrimaryDisplay.invoke();
    }
    public int getDisplayProperties(int displayId) throws Throwable{
        return (int) SDL_GetDisplayProperties.invoke(displayId);
    }
    public String getDisplayName(int displayId) throws Throwable{
        MemorySegment charAddress = (MemorySegment) SDL_GetDisplayName.invoke(displayId);
        if (charAddress == MemorySegment.NULL) {
            return null;
        } else {
            return charAddress.reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.UTF_8);
        }
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
