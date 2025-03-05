package robedpixel.Sdl.Guid;

import java.lang.foreign.Arena;


public class Guid {
    private final NativeGuidFuncs SdlFuncs;
    public Guid(Arena allocator){
        SdlFuncs = NativeGuidFuncs.getInstance(allocator);
    }
    public String guidToString(SdlGuid guid, int chGuid) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.guidToString(arena, guid, chGuid);
        }
    }

    public SdlGuid stringToGuid(String pchGuid) throws Throwable {
        try(Arena arena = Arena.ofConfined()) {
            return SdlFuncs.stringToGuid(arena, pchGuid);
        }
    }
}
