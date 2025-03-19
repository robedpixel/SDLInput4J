package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.pen.SdlPenId;
import robedpixel.sdl.video.SdlWindowId;

public class SdlPenTouchEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("windowID"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_INT.withName("pen_state"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_BOOLEAN.withName("eraser"),
              ValueLayout.JAVA_BOOLEAN.withName("down"))
          .withName("SDL_PenTouchEvent");

  /** SDL_EVENT_PEN_DOWN or SDL_EVENT_PEN_UP */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The window with pen focus, if any */
  @Getter SdlWindowId windowId;

  /** The pen instance id */
  @Getter SdlPenId which;

  /** Complete pen input state at time of event, check with pen.SdlPenInputFlag */
  @Getter int penState;

  /** X coordinate, relative to window */
  @Getter float x;

  /** Y coordinate, relative to window */
  @Getter float y;

  /** True if eraser end is used (not all pens support this). */
  @Getter boolean eraser;

  /** True if the pen is touching or false if the pen is lifted off */
  @Getter boolean down;

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
  private static final VarHandle penStateHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("pen_state"));
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static final VarHandle eraserHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("eraser"));
  private static final VarHandle downHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("down"));

  public static SdlPenTouchEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlPenTouchEvent retEvent = new SdlPenTouchEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.windowId = new SdlWindowId();
    retEvent.windowId.setValue((int) windowIdHandle.get(segment, 0));
    retEvent.which = new SdlPenId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.penState = (int) penStateHandle.get(segment, 0);
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    retEvent.eraser = (boolean) eraserHandle.get(segment, 0);
    retEvent.down = (boolean) downHandle.get(segment, 0);
    return retEvent;
  }
}
