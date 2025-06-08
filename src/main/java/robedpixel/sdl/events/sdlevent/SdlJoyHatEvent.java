package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlJoyHatEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("which"),
                            ValueLayout.JAVA_BYTE.withName("hat"),
                            ValueLayout.JAVA_BYTE.withName("value"),
                            ValueLayout.JAVA_BYTE.withName("padding1"),
                            ValueLayout.JAVA_BYTE.withName("padding2"))
                    .withName("SDL_JoyHatEvent");
    /** SDL_EVENT_JOYSTICK_HAT_MOTION */
    @Getter
    int type;

    @Getter int reserved;

    /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
    @Getter long timestamp;

    /** The joystick instance id */
    @Getter
    SdlJoystickId which;
    /** The joystick hat index */
    @Getter
    byte hat;
    /** The hat position value.
     *   SDL_HAT_LEFTUP SDL_HAT_UP SDL_HAT_RIGHTUP
     *   SDL_HAT_LEFT SDL_HAT_CENTERED SDL_HAT_RIGHT
     *   SDL_HAT_LEFTDOWN SDL_HAT_DOWN SDL_HAT_RIGHTDOWN
     * <p>
     *   Note that zero means the POV is centered.
     */
    @Getter
    byte value;

    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
    private static final VarHandle hatHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("hat"));
    private static final VarHandle valueHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("value"));
    public static SdlJoyHatEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlJoyHatEvent retEvent = new SdlJoyHatEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.which = new SdlJoystickId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        retEvent.hat = (byte) hatHandle.get(segment, 0);
        retEvent.value = (byte) valueHandle.get(segment, 0);
        return retEvent;
    }
}
