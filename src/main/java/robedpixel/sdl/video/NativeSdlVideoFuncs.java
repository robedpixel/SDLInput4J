package robedpixel.sdl.video;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlVideoFuncs {
    private static volatile NativeSdlVideoFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetNumVideoDrivers;
    private final MethodHandle SDL_GetVideoDriver;
    private final MethodHandle  SDL_GetCurrentVideoDriver;
    private final MethodHandle SDL_GetSystemTheme;
    private final MethodHandle SDL_GetDisplays;
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
                        library.find("SDL_GetSystemTheme").orElseThrow(),
                        FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS));
    }

    public int getNumVideoDrivers() throws Throwable {
        return (int) SDL_GetNumVideoDrivers.invoke();
    }
    public String getVideoDriver(int index) throws Throwable{
        MemorySegment charAddress = (MemorySegment) SDL_GetVideoDriver.invoke(index);
        return charAddress.getString(0);
    }
    public String getCurrentVideoDriver() throws Throwable{
        MemorySegment charAddress = (MemorySegment) SDL_GetCurrentVideoDriver.invoke();
        if (charAddress == MemorySegment.NULL) {
            return null;
        } else {
            return charAddress.getString(0);
        }
    }
    public int getSystemTheme() throws Throwable {
        return (int) SDL_GetSystemTheme.invoke();
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
