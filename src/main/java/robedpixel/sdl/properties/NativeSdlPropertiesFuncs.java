package robedpixel.sdl.properties;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlPropertiesFuncs {
    private static volatile NativeSdlPropertiesFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetGlobalProperties;
    public NativeSdlPropertiesFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GetGlobalProperties =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGlobalProperties").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    }
    public int getGlobalProperties() throws Throwable {
        return (int) SDL_GetGlobalProperties.invoke();
    }
    public static NativeSdlPropertiesFuncs getInstance(Arena allocator) {
        NativeSdlPropertiesFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null) INSTANCE = result = new NativeSdlPropertiesFuncs(allocator);
            }
        }
        return result;
    }
}
