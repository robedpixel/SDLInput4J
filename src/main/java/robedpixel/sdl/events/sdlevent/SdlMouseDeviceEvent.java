package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.keyboard.SdlKeyboardId;
import robedpixel.sdl.mouse.SdlMouseId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlMouseDeviceEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("which"))
                    .withName("SDL_MouseDeviceEvent");
    /** SDL_EVENT_MOUSE_ADDED or SDL_EVENT_MOUSE_REMOVED* */
    @Getter
    int type;

    @Getter int reserved;

    /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
    @Getter long timestamp;

    /** The mouse instance id */
    @Getter
    SdlMouseId which;

    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));

    public static SdlMouseDeviceEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlMouseDeviceEvent retEvent = new SdlMouseDeviceEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.which = new SdlMouseId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        return retEvent;
    }
}
