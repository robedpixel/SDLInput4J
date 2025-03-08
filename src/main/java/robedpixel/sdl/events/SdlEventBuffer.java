package robedpixel.sdl.events;

import java.lang.foreign.*;

public class SdlEventBuffer {
    private MemorySegment buffer;
    //TODO: enumerate all layouts of events
    private UnionLayout SdlEventLayout = MemoryLayout.unionLayout(ValueLayout.JAVA_INT,SdlCommonEvent.objectLayout);
    public SdlEventBuffer(int size){

    }
}
