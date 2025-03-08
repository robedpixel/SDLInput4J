package robedpixel.sdl.error;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlErrorFuncs {
    private static volatile NativeSdlErrorFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetError;
    private final MethodHandle SDL_ClearError;
    public NativeSdlErrorFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GetError = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetError").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.ADDRESS)
        );
        SDL_ClearError = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetError").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
        );
    }
    public synchronized String getError() throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment) SDL_GetError.invoke();
        return charArrayAddress.getString(0);
    }
    public synchronized Boolean clearError() throws Throwable {
        return (Boolean) SDL_ClearError.invoke();
    }
    public static NativeSdlErrorFuncs getInstance(Arena allocator) {
        NativeSdlErrorFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeSdlErrorFuncs(allocator);
            }
        }
        return result;
    }
}
