package robedpixel.sdl.events;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlCommonEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"))
          .withName("SDL_CommonEvent");
  @Getter int type;
  @Getter long reserved;
  @Getter long timestamp;
  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));

  public static SdlCommonEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlCommonEvent retEvent = new SdlCommonEvent();
    retEvent.type = (int) typeHandle.get(segment);
    retEvent.reserved = (int) reservedHandle.get(segment);
    retEvent.timestamp = (long) timestampHandle.get(segment);
    return retEvent;
  }
}
