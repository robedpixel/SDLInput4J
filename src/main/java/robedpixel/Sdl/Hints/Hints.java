package robedpixel.Sdl.Hints;

import robedpixel.Sdl.NativeSdlLib;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class Hints {
    private NativeHintsFuncs SdlFuncs;
    public Hints(Arena allocator){
        SdlFuncs = NativeHintsFuncs.getInstance(allocator);
    }
    public Boolean setHintWithPriority(String name, String value, HintPriority priority) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.setHintWithPriority(arena,name, value, priority);
        }
    }
    public Boolean setHint(String name, String value) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.setHint(arena,name, value);
        }
    }
    public Boolean resetHint(String name) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.resetHint(arena,name);
        }
    }
    public void resetHints() throws Throwable{
        SdlFuncs.resetHints();
    }
    public String getHint(String name) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.getHint(arena,name);
        }
    }
    public Boolean getHintBoolean(String name, boolean defaultValue) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.getHintBoolean(arena,name, defaultValue);
        }
    }
    public Boolean addHintCallback(SdlHintCallback callbackUpcallStub) throws Throwable {
        MethodHandle callbackHandle;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS);
        try {
            callbackHandle = MethodHandles.publicLookup().bind(callbackUpcallStub,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating method handle compareHandle", e);
        }
        MemorySegment nameAddress = callbackUpcallStub.getCallbackAllocator().allocateFrom(callbackUpcallStub.getName());
        MemorySegment callbackFunc = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                callbackUpcallStub.getCallbackAllocator());
        return SdlFuncs.addHintCallback(nameAddress, callbackFunc, callbackUpcallStub.getUserData());
    }
    public Boolean removeHintCallback(SdlHintCallback callbackUpcallStub) throws Throwable {
        MethodHandle callbackHandle;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS);
        try {
            callbackHandle = MethodHandles.publicLookup().bind(callbackUpcallStub,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating method handle compareHandle", e);
        }
        MemorySegment nameAddress = callbackUpcallStub.getCallbackAllocator().allocateFrom(callbackUpcallStub.getName());
        MemorySegment callbackFunc = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                callbackUpcallStub.getCallbackAllocator());
        return SdlFuncs.removeHintCallback(nameAddress, callbackFunc, callbackUpcallStub.getUserData());
    }
}
