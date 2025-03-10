package robedpixel.sdl.mouse;


import robedpixel.sdl.power.NativeSdlPowerFuncs;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlMouseFuncs {
    private static volatile NativeSdlMouseFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_HasMouse;
    public NativeSdlMouseFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_HasMouse =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_HasMouse").orElseThrow(),
                                FunctionDescriptor.of(
                                        ValueLayout.JAVA_BOOLEAN));
    }
    public synchronized boolean hasMouse() throws Throwable {
        return (boolean)SDL_HasMouse.invoke();
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
