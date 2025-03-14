package robedpixel.sdl.events;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlDisplayEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("displayID"),
              ValueLayout.JAVA_INT.withName("data1"),
              ValueLayout.JAVA_INT.withName("data2"))
          .withName("SDL_DisplayEvent");
  @Getter int type;
  @Getter int reserved;
  @Getter long timestamp;
  @Getter SdlDisplayId displayId;
  @Getter int data1;
  @Getter int data2;
  private static VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static VarHandle displayIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("displayID"));
  private static VarHandle data1Handle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("data1"));
  private static VarHandle data2Handle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("data2"));

  public static SdlDisplayEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlDisplayEvent retEvent = new SdlDisplayEvent();
    retEvent.type = (int) typeHandle.get(segment);
    retEvent.reserved = (int) reservedHandle.get(segment);
    retEvent.timestamp = (long) timestampHandle.get(segment);
    retEvent.displayId = new SdlDisplayId();
    retEvent.displayId.setValue((int) displayIdHandle.get(segment));
    retEvent.data1 = (int) data1Handle.get(segment);
    retEvent.data2 = (int) data2Handle.get(segment);
    return retEvent;
  }
}
