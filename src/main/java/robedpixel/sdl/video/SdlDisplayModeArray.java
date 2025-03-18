package robedpixel.sdl.video;

import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class SdlDisplayModeArray {
    @Getter
    SdlDisplayMode[] data;
    public SdlDisplayModeArray(MemorySegment dataAddress, int count) throws Throwable {
        this.data = new SdlDisplayMode[count];
        for (int i = 0; i < count; i++) {
            this.data[i] = SdlDisplayMode.fromMemorySegment(dataAddress.getAtIndex(ValueLayout.ADDRESS, i));
        }
        NativeSdlLib.sdlFree(dataAddress);
    }
}
