package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.keyboard.SdlKeyboardId;
import robedpixel.sdl.mouse.SdlMouseId;
import robedpixel.sdl.mouse.SdlMouseMotionCallbackConstants;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlMouseMotionEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("windowID"),
                            ValueLayout.JAVA_INT.withName("which"),
                    /**
                     * SDLMouseMotionCallbackConstants
                     */
                            ValueLayout.JAVA_INT.withName("state"),
                            ValueLayout.JAVA_FLOAT.withName("x"),
                            ValueLayout.JAVA_FLOAT.withName("y"),
                            ValueLayout.JAVA_FLOAT.withName("xrel"),
                            ValueLayout.JAVA_FLOAT.withName("yrel"))
                    .withName("SDL_MouseMotionEvent");
    /** SDL_EVENT_MOUSE_MOTION */
    @Getter
    int type;

    @Getter int reserved;

    /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
    @Getter long timestamp;
    /** The window with keyboard focus, if any */
    @Getter int windowId;
    /** The mouse instance id in relative mode, SDL_TOUCH_MOUSEID for touch events, or 0 */
    @Getter
    SdlMouseId which;
    /** (SdlMouseMotionCallbackConstants) The current button state */
    @Getter
    int state;
    /** X coordinate, relative to window */
    @Getter
    float x;
    /** Y coordinate, relative to window */
    @Getter
    float y;
    /** The relative motion in the X direction */
    @Getter
    float xrel;
    /** The relative motion in the Y direction */
    @Getter
    float yrel;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle windowIdHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
    private static final VarHandle stateHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("state"));
    private static final VarHandle xHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
    private static final VarHandle yHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
    private static final VarHandle xRelHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("xrel"));
    private static final VarHandle yRelHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("yrel"));
    public static SdlMouseMotionEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlMouseMotionEvent retEvent = new SdlMouseMotionEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.windowId = (int) windowIdHandle.get(segment, 0);
        retEvent.which = new SdlMouseId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        retEvent.state = (int) stateHandle.get(segment, 0);
        retEvent.x = (float) xHandle.get(segment, 0);
        retEvent.y = (float) yHandle.get(segment, 0);
        retEvent.xrel = (float) xRelHandle.get(segment, 0);
        retEvent.yrel = (float) yRelHandle.get(segment, 0);
        return retEvent;
    }
}
