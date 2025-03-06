package robedpixel.Sdl.Power;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativePowerFuncs {
    private static volatile NativePowerFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetPowerInfo;
    public NativePowerFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GetPowerInfo = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetPowerInfo").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_INT,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
    }
    public int getPowerInfo(MemorySegment seconds, MemorySegment percent) throws Throwable {
        return (int)SDL_GetPowerInfo.invoke(seconds,percent);
    }
    public static NativePowerFuncs getInstance(Arena allocator) {
        NativePowerFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativePowerFuncs (allocator);
            }
        }
        return result;
    }
}
