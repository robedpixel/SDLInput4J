package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlJoyButtonEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("which"),
                            ValueLayout.JAVA_BYTE.withName("button"),
                            ValueLayout.JAVA_BOOLEAN.withName("down"),
                            ValueLayout.JAVA_BYTE.withName("padding1"),
                            ValueLayout.JAVA_BYTE.withName("padding2"))
                    .withName("SDL_JoyButtonEvent");
    /** SDL_EVENT_JOYSTICK_BUTTON_DOWN or SDL_EVENT_JOYSTICK_BUTTON_UP*/
    @Getter
    int type;

    @Getter int reserved;

    /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
    @Getter long timestamp;

    /** The joystick instance id */
    @Getter
    SdlJoystickId which;
    /** The joystick button index */
    @Getter
    byte button;
    /** true if the button is pressed */
    @Getter
    boolean down;

    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
    private static final VarHandle buttonHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("button"));
    private static final VarHandle downHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("down"));
    public static SdlJoyButtonEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlJoyButtonEvent retEvent = new SdlJoyButtonEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.which = new SdlJoystickId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        retEvent.button = (byte) buttonHandle.get(segment, 0);
        retEvent.down = (boolean) downHandle.get(segment, 0);
        return retEvent;
    }
}
