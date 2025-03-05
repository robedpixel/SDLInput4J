package robedpixel.Sdl.Hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

class NativeHintsFuncs {
    private final Arena allocator;
    private final MethodHandle SDL_SetHintWithPriority;
    private final MethodHandle SDL_SetHint;
    private final MethodHandle SDL_ResetHint;
    private final MethodHandle SDL_ResetHints;
    private final MethodHandle SDL_GetHint;
    private final MethodHandle SDL_GetHintBoolean;
    private final MethodHandle SDL_AddHintCallback;
    private final MethodHandle SDL_RemoveHintCallback;
    public NativeHintsFuncs(Arena allocator){
        this.allocator = allocator;
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
    public Boolean setHintWithPriority(String name, String value, HintPriority priority) throws Throwable {
        return (Boolean) SDL_SetHintWithPriority.invoke(allocator.allocateFrom(name),allocator.allocateFrom(value),priority);
    }
    public Boolean setHint(String name, String value) throws Throwable {
        return (Boolean) SDL_SetHint.invoke(allocator.allocateFrom(name),allocator.allocateFrom(value));
    }
    public Boolean resetHint(String name) throws Throwable {
        return (Boolean) SDL_ResetHint.invoke(allocator.allocateFrom(name));
    }
    public void resetHints() throws Throwable{
        SDL_ResetHints.invoke();
    }
    public String getHint(String name) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment)SDL_GetHint.invoke(allocator.allocateFrom(name));
        return charArrayAddress.getString(0);
    }
    public Boolean getHintBoolean(String name, boolean defaultValue) throws Throwable {
        return (Boolean) SDL_GetHintBoolean.invoke(allocator.allocateFrom(name),defaultValue);
    }
    public Boolean addHintCallback(String name, SdlHintCallback callbackUpcallStub,MemorySegment userData) throws Throwable {
        MethodHandle callbackHandle;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS);
        try {
            callbackHandle = MethodHandles.publicLookup().bind(callbackUpcallStub,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating method handle compareHandle", e);
        }
        MemorySegment callbackFunc = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                allocator);
        return (Boolean)SDL_AddHintCallback.invoke(name,callbackFunc,userData);
    }
    public Boolean removeHintCallback(String name, SdlHintCallback callbackUpcallStub,MemorySegment userData) throws Throwable {
        MethodHandle callbackHandle;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS);
        try {
            callbackHandle = MethodHandles.publicLookup().bind(callbackUpcallStub,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating method handle compareHandle", e);
        }
        MemorySegment callbackFunc = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                allocator);
        return (Boolean)SDL_RemoveHintCallback.invoke(name,callbackFunc,userData);
    }
}
