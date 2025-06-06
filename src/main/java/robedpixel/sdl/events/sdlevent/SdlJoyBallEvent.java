package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

public class SdlJoyBallEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_BYTE.withName("ball"),
              ValueLayout.JAVA_BYTE.withName("padding1"),
              ValueLayout.JAVA_BYTE.withName("padding2"),
              ValueLayout.JAVA_BYTE.withName("padding3"),
              ValueLayout.JAVA_SHORT.withName("xrel"),
              ValueLayout.JAVA_SHORT.withName("yrel"))
          .withName("SDL_JoyBallEvent");

  /** SDL_EVENT_JOYSTICK_BALL_MOTION */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The joystick instance id */
  @Getter SdlJoystickId which;

  /** The joystick trackball index */
  @Getter byte ball;

  /** The relative motion in the X direction */
  @Getter short xRel;

  /** The relative motion in the Y direction */
  @Getter short yRel;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
  private static final VarHandle ballHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("ball"));
  private static final VarHandle xRelHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("xrel"));
  private static final VarHandle yRelHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("yrel"));

  public static SdlJoyBallEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlJoyBallEvent retEvent = new SdlJoyBallEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlJoystickId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.ball = (byte) ballHandle.get(segment, 0);
    retEvent.xRel = (short) xRelHandle.get(segment, 0);
    retEvent.yRel = (short) yRelHandle.get(segment, 0);
    return retEvent;
  }
}
