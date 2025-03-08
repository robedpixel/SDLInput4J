package robedpixel.sdl.hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlHintsFuncs {
    private static volatile NativeSdlHintsFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_SetHintWithPriority;
    private final MethodHandle SDL_SetHint;
    private final MethodHandle SDL_ResetHint;
    private final MethodHandle SDL_ResetHints;
    private final MethodHandle SDL_GetHint;
    private final MethodHandle SDL_GetHintBoolean;
    private final MethodHandle SDL_AddHintCallback;
    private final MethodHandle SDL_RemoveHintCallback;
    public NativeSdlHintsFuncs(Arena allocator){
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

    public synchronized Boolean setHintWithPriority(Arena localAllocator,String name, String value, HintPriority priority) throws Throwable {
        return (Boolean) SDL_SetHintWithPriority.invoke(localAllocator.allocateFrom(name),localAllocator.allocateFrom(value),priority);
    }
    public synchronized Boolean setHint(Arena localAllocator,String name, String value) throws Throwable {
        return (Boolean) SDL_SetHint.invoke(localAllocator.allocateFrom(name),localAllocator.allocateFrom(value));
    }
    public synchronized Boolean resetHint(Arena localAllocator,String name) throws Throwable {
        return (Boolean) SDL_ResetHint.invoke(localAllocator.allocateFrom(name));
    }
    public synchronized void resetHints() throws Throwable{
        SDL_ResetHints.invoke();
    }
    public synchronized String getHint(Arena localAllocator,String name) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment)SDL_GetHint.invoke(localAllocator.allocateFrom(name));
        if (charArrayAddress == MemorySegment.NULL){
            return null;
        }else {
            return charArrayAddress.getString(0);
        }
    }
    public synchronized Boolean getHintBoolean(Arena localAllocator,String name, boolean defaultValue) throws Throwable {
        return (Boolean) SDL_GetHintBoolean.invoke(localAllocator.allocateFrom(name),defaultValue);
    }
    public synchronized Boolean addHintCallback(MemorySegment name, MemorySegment callbackFunc,MemorySegment userData) throws Throwable {
        return (Boolean)SDL_AddHintCallback.invoke(name,callbackFunc,userData);
    }
    public synchronized Boolean removeHintCallback(MemorySegment name, MemorySegment callbackFunc,MemorySegment userData) throws Throwable {

        return (Boolean)SDL_RemoveHintCallback.invoke(name,callbackFunc,userData);
    }
    public static NativeSdlHintsFuncs getInstance(Arena allocator) {
        NativeSdlHintsFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeSdlHintsFuncs(allocator);
            }
        }
        return result;
    }
}
