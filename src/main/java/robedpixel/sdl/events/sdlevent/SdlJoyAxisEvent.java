package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

public class SdlJoyAxisEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_BYTE.withName("axis"),
              ValueLayout.JAVA_BYTE.withName("padding1"),
              ValueLayout.JAVA_BYTE.withName("padding2"),
              ValueLayout.JAVA_BYTE.withName("padding3"),
              ValueLayout.JAVA_SHORT.withName("value"),
              ValueLayout.JAVA_SHORT.withName("padding4"))
          .withName("SDL_JoyBallEvent");

  /** SDL_EVENT_JOYSTICK_AXIS_MOTION */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The joystick instance id */
  @Getter SdlJoystickId which;

  /** The joystick axis index */
  @Getter byte axis;

  /** The axis value (range: -32768 to 32767) */
  @Getter short value;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
  private static final VarHandle axisHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("axis"));
  private static final VarHandle valueHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("value"));

  public static SdlJoyAxisEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlJoyAxisEvent retEvent = new SdlJoyAxisEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlJoystickId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.axis = (byte) axisHandle.get(segment, 0);
    retEvent.value = (short) valueHandle.get(segment, 0);
    return retEvent;
  }
}
