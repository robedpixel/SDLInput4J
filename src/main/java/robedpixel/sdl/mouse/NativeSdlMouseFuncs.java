package robedpixel.sdl.mouse;


import robedpixel.sdl.power.NativeSdlPowerFuncs;
import robedpixel.sdl.sensors.SdlSensorIdArray;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlMouseFuncs {
    private static volatile NativeSdlMouseFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_HasMouse;
    private final MethodHandle SDL_GetMice;
    private final Arena objectAllocator = Arena.ofAuto();
    private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
    public NativeSdlMouseFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_HasMouse =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_HasMouse").orElseThrow(),
                                FunctionDescriptor.of(
                                        ValueLayout.JAVA_BOOLEAN));
        SDL_GetMice =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetMice").orElseThrow(),
                                FunctionDescriptor.of(
                                        ValueLayout.ADDRESS,ValueLayout.ADDRESS));
    }
    public synchronized boolean hasMouse() throws Throwable {
        return (boolean)SDL_HasMouse.invoke();
    }
    public synchronized  SdlMouseIdArray getMice() throws Throwable{
        MemorySegment temp = (MemorySegment) SDL_GetMice.invoke(tempIntAddress);
        if (temp == MemorySegment.NULL) {
            return null;
        } else {
            int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
            return new SdlMouseIdArray(temp, arraySize);
        }
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
