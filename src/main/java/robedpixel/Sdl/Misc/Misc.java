package robedpixel.Sdl.Misc;

import java.lang.foreign.Arena;

public class Misc {
    private final NativeMiscFuncs SdlFuncs;
    public Misc(Arena allocator){
        SdlFuncs = NativeMiscFuncs.getInstance(allocator);
    }
    public Boolean openUrl(String url) throws Throwable {
        try (Arena arena = Arena.ofConfined()){
            return SdlFuncs.openUrl(arena,url);
        }
    }
}
