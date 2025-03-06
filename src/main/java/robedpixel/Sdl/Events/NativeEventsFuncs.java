package robedpixel.Sdl.Events;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeEventsFuncs {
    private static volatile NativeEventsFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_PumpEvents;
    private final MethodHandle SDL_PeepEvents;
    public NativeEventsFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_PumpEvents = Linker.nativeLinker().downcallHandle(
                library.find("SDL_PumpEvents").orElseThrow(),

                FunctionDescriptor.ofVoid()
        );
        SDL_PeepEvents = Linker.nativeLinker().downcallHandle(
                library.find("SDL_PeepEvents").orElseThrow(),

                FunctionDescriptor.ofVoid()
        );
    }
    public void pumpEvents() throws Throwable {
        SDL_PumpEvents.invoke();
    }
    public static NativeEventsFuncs getInstance(Arena allocator) {
        NativeEventsFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeEventsFuncs(allocator);
            }
        }
        return result;
    }
}
