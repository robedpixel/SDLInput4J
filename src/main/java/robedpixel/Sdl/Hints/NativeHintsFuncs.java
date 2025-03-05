package robedpixel.Sdl.Hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeHintsFuncs {
    private static volatile NativeHintsFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_SetHintWithPriority;
    private final MethodHandle SDL_SetHint;
    private final MethodHandle SDL_ResetHint;
    private final MethodHandle SDL_ResetHints;
    private final MethodHandle SDL_GetHint;
    private final MethodHandle SDL_GetHintBoolean;
    private final MethodHandle SDL_AddHintCallback;
    private final MethodHandle SDL_RemoveHintCallback;
    public NativeHintsFuncs(Arena allocator){
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_SetHintWithPriority = Linker.nativeLinker().downcallHandle(
                library.find("SDL_SetHintWithPriority").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
        );
        SDL_SetHint = Linker.nativeLinker().downcallHandle(
                library.find("SDL_SetHint").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
        SDL_ResetHint = Linker.nativeLinker().downcallHandle(
                library.find("SDL_ResetHint").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS)
        );
        SDL_ResetHints = Linker.nativeLinker().downcallHandle(
                library.find("SDL_ResetHints").orElseThrow(),

                FunctionDescriptor.ofVoid()
        );
        SDL_GetHint = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetHint").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
        SDL_GetHintBoolean = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetHintBoolean").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.JAVA_BOOLEAN)
        );
        SDL_AddHintCallback = Linker.nativeLinker().downcallHandle(
                library.find("SDL_AddHintCallback").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
        SDL_RemoveHintCallback = Linker.nativeLinker().downcallHandle(
                library.find("SDL_RemoveHintCallback").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
    }

    public Boolean setHintWithPriority(Arena localAllocator,String name, String value, HintPriority priority) throws Throwable {
        return (Boolean) SDL_SetHintWithPriority.invoke(localAllocator.allocateFrom(name),localAllocator.allocateFrom(value),priority);
    }
    public Boolean setHint(Arena localAllocator,String name, String value) throws Throwable {
        return (Boolean) SDL_SetHint.invoke(localAllocator.allocateFrom(name),localAllocator.allocateFrom(value));
    }
    public Boolean resetHint(Arena localAllocator,String name) throws Throwable {
        return (Boolean) SDL_ResetHint.invoke(localAllocator.allocateFrom(name));
    }
    public void resetHints() throws Throwable{
        SDL_ResetHints.invoke();
    }
    public String getHint(Arena localAllocator,String name) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment)SDL_GetHint.invoke(localAllocator.allocateFrom(name));
        return charArrayAddress.getString(0);
    }
    public Boolean getHintBoolean(Arena localAllocator,String name, boolean defaultValue) throws Throwable {
        return (Boolean) SDL_GetHintBoolean.invoke(localAllocator.allocateFrom(name),defaultValue);
    }
    public Boolean addHintCallback(MemorySegment name, MemorySegment callbackFunc,MemorySegment userData) throws Throwable {
        return (Boolean)SDL_AddHintCallback.invoke(name,callbackFunc,userData);
    }
    public Boolean removeHintCallback(MemorySegment name, MemorySegment callbackFunc,MemorySegment userData) throws Throwable {

        return (Boolean)SDL_RemoveHintCallback.invoke(name,callbackFunc,userData);
    }
    public static NativeHintsFuncs getInstance(Arena allocator) {
        NativeHintsFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeHintsFuncs(allocator);
            }
        }
        return result;
    }
}
