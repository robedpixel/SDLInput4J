package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.video.SdlWindowId;

public class SdlDropEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("windowID"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.ADDRESS.withName("source"),
              ValueLayout.ADDRESS.withName("data"))
          .withName("SDL_DropEvent");

  /**
   * SDL_EVENT_DROP_BEGIN or SDL_EVENT_DROP_FILE or SDL_EVENT_DROP_TEXT or SDL_EVENT_DROP_COMPLETE
   * or SDL_EVENT_DROP_POSITION
   */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The window that was dropped on, if any */
  @Getter SdlWindowId windowId;

  /** X coordinate, relative to window (not on begin) */
  @Getter float x;

  /** Y coordinate, relative to window (not on begin) */
  @Getter float y;

  /** The source app that sent this drop event, or NULL if that isn't available */
  @Getter String source;

  /**
   * The text for SDL_EVENT_DROP_TEXT and the file name for SDL_EVENT_DROP_FILE, NULL for other
   * events
   */
  @Getter String data;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle windowIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));
  private static final VarHandle xHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static final VarHandle yHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static final VarHandle sourceHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("source"));
  private static final VarHandle dataHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("data"));

  public static SdlDropEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlDropEvent retEvent = new SdlDropEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.windowId = new SdlWindowId();
    retEvent.windowId.setValue((int) windowIdHandle.get(segment, 0));
    retEvent.x = (float) xHandle.get(segment, 0);
    retEvent.y = (float) yHandle.get(segment, 0);
    MemorySegment tempSegment = (MemorySegment) sourceHandle.get(segment, 0);
    retEvent.source = tempSegment.reinterpret(Integer.MAX_VALUE).getString(0);
    tempSegment = (MemorySegment) dataHandle.get(segment, 0);
    retEvent.data = tempSegment.reinterpret(Integer.MAX_VALUE).getString(0);
    return retEvent;
  }
}
