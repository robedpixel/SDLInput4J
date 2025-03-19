package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.video.SdlWindowId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;

public class SdlTextInputEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("windowID"),
                            ValueLayout.ADDRESS.withName("text"))
                    .withName("SDL_TextInputEvent");
    /**
     * SDL_EVENT_TEXT_INPUT
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
     * The input text, UTF-8 encoded
     */
    @Getter
    String text;
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
    public static SdlTextInputEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlTextInputEvent retEvent = new SdlTextInputEvent();
        retEvent.type = (int) typeHandle.get(segment,0);
        retEvent.reserved = (int) reservedHandle.get(segment,0);
        retEvent.timestamp = (long) timestampHandle.get(segment,0);
        retEvent.windowId = new SdlWindowId();
        retEvent.windowId.setValue((int) windowIdHandle.get(segment,0));
        retEvent.text = ((MemorySegment) textHandle.get(segment,0)).reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.UTF_8);
        return retEvent;
    }
}
