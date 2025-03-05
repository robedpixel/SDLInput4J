package robedpixel.Sdl.Misc;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeMiscFuncs {
    private static volatile NativeMiscFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_OpenURL;
    public NativeMiscFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_OpenURL = Linker.nativeLinker().downcallHandle(
                library.find("SDL_OpenURL").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS)
        );
    }
    public Boolean openUrl(Arena localAllocator, String url) throws Throwable {
        return (Boolean)SDL_OpenURL.invoke(localAllocator.allocateFrom(url));
    }
    public static NativeMiscFuncs getInstance(Arena allocator) {
        NativeMiscFuncs  result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeMiscFuncs (allocator);
            }
        }
        return result;
    }
}
