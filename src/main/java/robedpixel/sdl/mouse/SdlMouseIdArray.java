package robedpixel.sdl.mouse;

import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class SdlMouseIdArray implements AutoCloseable {
    @Getter
    SdlMouseId[] data;
    MemorySegment dataAddress;

    public SdlMouseIdArray(MemorySegment dataAddress, int count) {
        this.data = new SdlMouseId[count];
        for (int i = 0; i < count; i++) {
            this.data[i] = new SdlMouseId();
            this.data[i].setValue(dataAddress.get(ValueLayout.JAVA_INT, i));
        }
        this.dataAddress = dataAddress;
    }

    @Override
    public void close() throws Exception {
        try {
            NativeSdlLib.sdlFree(dataAddress);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
