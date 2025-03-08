package robedpixel.sdl.events;

import lombok.Getter;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlDisplayEvent {
    public static final StructLayout objectLayout = MemoryLayout.structLayout(ValueLayout.JAVA_INT.withName("type"),ValueLayout.JAVA_LONG.withName("reserved"),ValueLayout.JAVA_LONG.withName("timestamp")).withName("SDL_CommonEvent");
    @Getter
    int name;
    @Getter
    long reserved;
    @Getter
    long timestamp;
    public static SdlDisplayEvent getEventFromMemorySegment(MemorySegment segment){
        SdlDisplayEvent retEvent = new SdlDisplayEvent();
        VarHandle nameHandle = objectLayout.varHandle(MemoryLayout.PathElement.groupElement("name"));
        VarHandle reservedHandle = objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
        VarHandle timestampHandle = objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
        retEvent.name = (int)nameHandle.get(segment);
        retEvent.reserved = (long)reservedHandle.get(segment);
        retEvent.timestamp = (long)timestampHandle.get(segment);
        return retEvent;
    }
}
