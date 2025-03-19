package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.video.SdlWindowId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlUserEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("windowID"),
                            ValueLayout.JAVA_INT.withName("code"),
                            ValueLayout.JAVA_INT.withName("data1"),
                            ValueLayout.JAVA_INT.withName("data2"))
                    .withName("SDL_UserEvent");
    /**
     * SDL_EVENT_USER through SDL_EVENT_LAST-1
     */
    @Getter
    int type;
    @Getter int reserved;
    /**
     * (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS()
     */
    @Getter long timestamp;
    /**
     * The associated window if any
     */
    @Getter
    SdlWindowId windowId;
    /**
     * User defined event code
     */
    @Getter int code;
    /**
     * User defined data pointer
     */
    @Getter int data1;
    /**
     * User defined data pointer
     */
    @Getter int data2;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle windowIdHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));
    private static final VarHandle codeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("code"));
    private static final VarHandle data1Handle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("data1"));
    private static final VarHandle data2Handle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("data2"));
    public static SdlUserEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlUserEvent retEvent = new SdlUserEvent();
        retEvent.type = (int) typeHandle.get(segment,0);
        retEvent.reserved = (int) reservedHandle.get(segment,0);
        retEvent.timestamp = (long) timestampHandle.get(segment,0);
        retEvent.code = (int) codeHandle.get(segment,0);
        retEvent.windowId = new SdlWindowId();
        retEvent.windowId.setValue((int) windowIdHandle.get(segment,0));
        retEvent.data1 = (int) data1Handle.get(segment,0);
        retEvent.data2 = (int) data2Handle.get(segment,0);
        return retEvent;
    }
}
