package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.keyboard.SdlKeyboardId;

public class SdlKeyboardEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("windowID"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_INT.withName("scancode"),
              ValueLayout.JAVA_INT.withName("key"),
              ValueLayout.JAVA_SHORT.withName("mod"),
              ValueLayout.JAVA_SHORT.withName("raw"),
              ValueLayout.JAVA_BOOLEAN.withName("down"),
              ValueLayout.JAVA_BOOLEAN.withName("repeat"))
          .withName("SDL_KeyboardEvent");

  /** SDL_EVENT_KEY_DOWN or SDL_EVENT_KEY_UP */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The window with keyboard focus, if any */
  @Getter int windowId;

  /** The keyboard instance id, or 0 if unknown or virtual */
  @Getter SdlKeyboardId which;

  /** SDL physical key code */
  @Getter int scanCode;

  /** SDL virtual key code */
  @Getter int key;

  /** current key modifiers */
  @Getter short mod;

  /** The platform dependent scancode for this event */
  @Getter short raw;

  /** true if the key is pressed */
  @Getter boolean down;

  /** true if this is a key repeat */
  @Getter boolean repeat;

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
  private static final VarHandle scanCodeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("scancode"));
  private static final VarHandle keyHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("key"));
  private static final VarHandle modHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("mod"));
  private static final VarHandle rawHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("raw"));
  private static final VarHandle downHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("down"));
  private static final VarHandle repeatHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("repeat"));

  public static SdlKeyboardEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlKeyboardEvent retEvent = new SdlKeyboardEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.windowId = (int) windowIdHandle.get(segment, 0);
    retEvent.which = new SdlKeyboardId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.scanCode = (int) scanCodeHandle.get(segment, 0);
    retEvent.key = (int) keyHandle.get(segment, 0);
    retEvent.mod = (short) modHandle.get(segment, 0);
    retEvent.raw = (short) rawHandle.get(segment, 0);
    retEvent.down = (boolean) downHandle.get(segment, 0);
    retEvent.repeat = (boolean) repeatHandle.get(segment, 0);
    return retEvent;
  }
}
