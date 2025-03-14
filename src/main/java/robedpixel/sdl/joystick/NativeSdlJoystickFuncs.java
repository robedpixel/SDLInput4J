package robedpixel.sdl.joystick;



import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlJoystickFuncs {
    private static volatile NativeSdlJoystickFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_CloseJoystick;
    public NativeSdlJoystickFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_CloseJoystick =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_CloseJoystick").orElseThrow(),
                                FunctionDescriptor.ofVoid(
                                        ValueLayout.ADDRESS));
    }
    public synchronized void closeJoystick(MemorySegment sensor) throws Throwable {
        SDL_CloseJoystick.invoke(sensor);
    }
    public static NativeSdlJoystickFuncs getInstance(Arena allocator) {
        NativeSdlJoystickFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null) INSTANCE = result = new NativeSdlJoystickFuncs(allocator);
            }
        }
        return result;
    }
}
