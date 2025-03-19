package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.video.SdlWindowId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;

public class SdlTextEditingEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                    ValueLayout.JAVA_INT.withName("type"),
                    ValueLayout.JAVA_INT.withName("reserved"),
                    ValueLayout.JAVA_LONG.withName("timestamp"),
                    ValueLayout.JAVA_INT.withName("windowID"),
                    ValueLayout.ADDRESS.withName("text"),
                    ValueLayout.JAVA_INT.withName("start"),
                    ValueLayout.JAVA_INT.withName("length")
            ).withName("SDL_TextEditingEvent");
    /**
     * SDL_EVENT_TEXT_EDITING
     */
    @Getter
    int type;
    @Getter int reserved;
    /**
     * (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS()
     */
    @Getter long timestamp;
    /**
     * The window with keyboard focus, if any
     */
    @Getter
    SdlWindowId windowId;
    /**
     * The editing text
     */
    @Getter
    String text;
    /**
     * The start cursor of selected editing text, or -1 if not set
     */
    @Getter
    int start;
    /**
     * The length of selected editing text, or -1 if not set
     */
    @Getter
    int length;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle windowIdHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));
    private static final VarHandle textHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("text"));
    private static final VarHandle startHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("start"));
    private static final VarHandle lengthHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("length"));
    public static SdlTextEditingEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlTextEditingEvent retEvent = new SdlTextEditingEvent();
        retEvent.type = (int) typeHandle.get(segment,0);
        retEvent.reserved = (int) reservedHandle.get(segment,0);
        retEvent.timestamp = (long) timestampHandle.get(segment,0);
        retEvent.windowId = new SdlWindowId();
        retEvent.windowId.setValue((int) windowIdHandle.get(segment,0));
        retEvent.text = ((MemorySegment) textHandle.get(segment,0)).reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.US_ASCII);
        retEvent.start = (int) startHandle.get(segment,0);
        retEvent.length = (int) lengthHandle.get(segment,0);
        return retEvent;
    }
}
