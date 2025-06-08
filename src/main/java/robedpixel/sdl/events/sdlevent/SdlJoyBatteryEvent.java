package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;
import robedpixel.sdl.power.SdlPowerState;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlJoyBatteryEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("which"),
                            ValueLayout.JAVA_INT.withName("state"),
                            ValueLayout.JAVA_INT.withName("percent"))
                    .withName("SDL_JoyBatteryEvent");

    /**
     * SDL_EVENT_JOYSTICK_BATTERY_UPDATED
     */
    @Getter
    int type;

    @Getter int reserved;

    /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
    @Getter long timestamp;

    /** The joystick instance id */
    @Getter
    SdlJoystickId which;
    /** The joystick battery state */
    @Getter
    SdlPowerState state;
    /** The joystick battery percent charge remaining */
    @Getter
    int percent;

    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
    private static final VarHandle stateHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("state"));
    private static final VarHandle percentHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("percent"));

    public static SdlJoyBatteryEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlJoyBatteryEvent retEvent = new SdlJoyBatteryEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.which = new SdlJoystickId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        retEvent.state = SdlPowerState.fromInt((int) stateHandle.get(segment, 0));
        retEvent.percent = (int) percentHandle.get(segment, 0);
        return retEvent;
    }
}
