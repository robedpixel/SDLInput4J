package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.mouse.SdlMouseId;

public class SdlMouseWheelEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("windowID"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_INT.withName("direction"),
              ValueLayout.JAVA_FLOAT.withName("mouse_x"),
              ValueLayout.JAVA_FLOAT.withName("mouse_y"),
              ValueLayout.JAVA_INT.withName("integer_x"),
              ValueLayout.JAVA_INT.withName("integer_y"))
          .withName("SDL_MouseWheelEvent");

  /** SDL_EVENT_MOUSE_WHEEL */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The window with keyboard focus, if any */
  @Getter int windowId;

  /** The mouse instance id in relative mode or 0 */
  @Getter SdlMouseId which;

  /** The amount scrolled horizontally, positive to the right and negative to the left */
  @Getter float x;

  /** The amount scrolled vertically, positive away from the user and negative toward the user */
  @Getter float y;

  /**
   * (SdlMouseWheelDirection) Set to one of the SDL_MOUSEWHEEL_* defines. When FLIPPED the values in
   * X and Y will be opposite. Multiply by -1 to change them back
   */
  @Getter int direction;

  /** X coordinate, relative to window */
  @Getter float mouseX;

  /** Y coordinate, relative to window */
  @Getter float mouseY;

  /** The amount scrolled horizontally, accumulated to whole scroll "ticks" (added in 3.2.12) */
  @Getter int integerX;

  /** The amount scrolled vertically, accumulated to whole scroll "ticks" (added in 3.2.12) */
  @Getter int integerY;

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
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static final VarHandle directionHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("direction"));
  private static final VarHandle mouseXHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("mouse_x"));
  private static final VarHandle mouseYHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("mouse_y"));
  private static final VarHandle integerXHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("integer_x"));
  private static final VarHandle integerYHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("integer_y"));

  public static SdlMouseWheelEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlMouseWheelEvent retEvent = new SdlMouseWheelEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.windowId = (int) windowIdHandle.get(segment, 0);
    retEvent.which = new SdlMouseId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    retEvent.direction = (int) directionHandle.get(segment, 0);
    retEvent.mouseX = (float) mouseXHandle.get(segment, 0);
    retEvent.mouseY = (float) mouseYHandle.get(segment, 0);
    retEvent.integerX = (int) integerXHandle.get(segment, 0);
    retEvent.integerY = (int) integerYHandle.get(segment, 0);
    return retEvent;
  }
}
