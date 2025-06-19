package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

public class SdlGamepadTouchpadEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_INT.withName("touchpad"),
              ValueLayout.JAVA_INT.withName("finger"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_FLOAT.withName("pressure"))
          .withName("SDL_GamepadTouchpadEvent");

  /**
   * SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN or SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION or
   * SDL_EVENT_GAMEPAD_TOUCHPAD_UP
   */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The joystick instance id */
  @Getter SdlJoystickId which;

  /** The index of the touchpad */
  @Getter int touchpad;

  /** The index of the finger on the touchpad */
  @Getter int finger;

  /** Normalized in the range 0...1 with 0 being on the left */
  @Getter float x;

  /** Normalized in the range 0...1 with 0 being at the top */
  @Getter float y;

  /** Normalized in the range 0...1 */
  @Getter float pressure;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
  private static final VarHandle touchpadHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("touchpad"));
  private static final VarHandle fingerHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("finger"));
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static final VarHandle pressureHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("pressure"));

  public static SdlGamepadTouchpadEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlGamepadTouchpadEvent retEvent = new SdlGamepadTouchpadEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlJoystickId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.touchpad = (int) touchpadHandle.get(segment, 0);
    retEvent.finger = (int) fingerHandle.get(segment, 0);
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    retEvent.pressure = (float) pressureHandle.get(segment, 0);
    return retEvent;
  }
}
