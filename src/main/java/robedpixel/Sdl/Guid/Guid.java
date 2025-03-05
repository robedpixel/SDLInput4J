package robedpixel.Sdl.Guid;

import java.lang.foreign.Arena;


public class Guid {
    private final NativeGuidFuncs SdlFuncs;
    public Guid(Arena allocator){
        SdlFuncs = new NativeGuidFuncs(allocator);
    }
    public String guidToString(SdlGuid guid, int chGuid) throws Throwable {
        return SdlFuncs.guidToString(guid, chGuid);
    }
    //TODO: find a way to extract struct value to function
    public SdlGuid stringToGuid(String pchGuid) throws Throwable {
        return SdlFuncs.stringToGuid(pchGuid);
    }
}
