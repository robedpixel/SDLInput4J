package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.touch.SdlFingerId;
import robedpixel.sdl.touch.SdlTouchId;
import robedpixel.sdl.video.SdlWindowId;

public class SdlTouchFingerEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("touchID"),
              ValueLayout.JAVA_LONG.withName("fingerID"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_FLOAT.withName("dx"),
              ValueLayout.JAVA_FLOAT.withName("dy"),
              ValueLayout.JAVA_INT.withName("windowID"))
          .withName("SDL_TouchFingerEvent");

  /**
   * SDL_EVENT_FINGER_DOWN, SDL_EVENT_FINGER_UP, SDL_EVENT_FINGER_MOTION, or
   * SDL_EVENT_FINGER_CANCELED *
   */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The touch device id */
  @Getter SdlTouchId touchId;

  @Getter SdlFingerId fingerId;

  /** Normalized in the range 0...1 */
  @Getter float x;

  /** Normalized in the range 0...1 */
  @Getter float y;

  /** Normalized in the range -1...1 */
  @Getter float dx;

  /** Normalized in the range -1...1 */
  @Getter float dy;

  /** Normalized in the range 0...1 */
  @Getter float pressure;

  /** The window underneath the finger, if any */
  @Getter SdlWindowId windowId;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle touchIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("touchID"));
  private static final VarHandle fingerIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("fingerID"));
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static final VarHandle dxHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("dx"));
  private static final VarHandle dyHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("dy"));
  private static final VarHandle pressureHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("pressure"));
  private static final VarHandle windowIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));

  public static SdlTouchFingerEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlTouchFingerEvent retEvent = new SdlTouchFingerEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.touchId = new SdlTouchId();
    retEvent.touchId.setValue((int) touchIdHandle.get(segment, 0));
    retEvent.fingerId = new SdlFingerId();
    retEvent.fingerId.setValue((long) fingerIdHandle.get(segment, 0));
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    retEvent.dx = (float) dxHandle.get(segment, 0);
    retEvent.dy = (float) dyHandle.get(segment, 0);
    retEvent.pressure = (float) pressureHandle.get(segment, 0);
    retEvent.windowId = new SdlWindowId();
    retEvent.windowId.setValue((int) windowIdHandle.get(segment, 0));
    return retEvent;
  }
}
