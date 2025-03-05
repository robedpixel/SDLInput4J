package robedpixel.Sdl.Hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class Hints {
    private NativeHintsFuncs SdlFuncs;
    public Hints(Arena allocator){
        SdlFuncs = new NativeHintsFuncs(allocator);
    }
    public Boolean setHintWithPriority(String name, String value, HintPriority priority) throws Throwable {
        return SdlFuncs.setHintWithPriority(name,value,priority);
    }
    public Boolean setHint(String name, String value) throws Throwable {
        return SdlFuncs.setHint(name, value);
    }
    public Boolean resetHint(String name) throws Throwable {
        return SdlFuncs.resetHint(name);
    }
    public void resetHints() throws Throwable{
        SdlFuncs.resetHints();
    }
    public String getHint(String name) throws Throwable {
        return SdlFuncs.getHint(name);
    }
    public Boolean getHintBoolean(String name, boolean defaultValue) throws Throwable {
        return SdlFuncs.getHintBoolean(name, defaultValue);
    }
    public Boolean addHintCallback(String name, SdlHintCallback callbackUpcallStub,MemorySegment userData) throws Throwable {
        return SdlFuncs.addHintCallback(name, callbackUpcallStub, userData);
    }
    public Boolean removeHintCallback(String name, SdlHintCallback callbackUpcallStub,MemorySegment userData) throws Throwable {
        return SdlFuncs.removeHintCallback(name, callbackUpcallStub, userData);
    }
}
