package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.mouse.SdlMouseId;

public class SdlMouseButtonEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("windowID"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_BYTE.withName("button"),
              ValueLayout.JAVA_BOOLEAN.withName("down"),
              ValueLayout.JAVA_BYTE.withName("clicks"),
              ValueLayout.JAVA_BYTE.withName("padding"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"))
          .withName("SDL_MouseButtonEvent");

  /** SDL_EVENT_MOUSE_BUTTON_DOWN or SDL_EVENT_MOUSE_BUTTON_UP */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The window with mouse focus, if any */
  @Getter int windowId;

  /** The mouse instance id in relative mode, SDL_TOUCH_MOUSEID for touch events, or 0 */
  @Getter SdlMouseId which;

  /** The mouse button index */
  @Getter byte button;

  /** true if the button is pressed */
  @Getter boolean down;

  /** 1 for single-click, 2 for double-click, etc. */
  @Getter byte clicks;

  /** X coordinate, relative to window */
  @Getter float x;

  /** Y coordinate, relative to window */
  @Getter float y;

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
  private static final VarHandle buttonHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("button"));
  private static final VarHandle downHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("down"));
  private static final VarHandle clicksHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("clicks"));
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));

  public static SdlMouseButtonEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlMouseButtonEvent retEvent = new SdlMouseButtonEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.windowId = (int) windowIdHandle.get(segment, 0);
    retEvent.which = new SdlMouseId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.button = (byte) buttonHandle.get(segment, 0);
    retEvent.down = (boolean) downHandle.get(segment, 0);
    retEvent.clicks = (byte) clicksHandle.get(segment, 0);
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    return retEvent;
  }
}
