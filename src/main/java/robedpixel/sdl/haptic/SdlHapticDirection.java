package robedpixel.sdl.haptic;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlHapticDirection {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_BYTE.withName("type"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_INT).withName("dir"))
          .withName("SDL_HapticDirection");
  @Getter byte type;
  @Getter int[] dir = new int[3];

  public static SdlHapticDirection getEventFromMemorySegment(MemorySegment segment) {
    SdlHapticDirection hapticDirection = new SdlHapticDirection();
    VarHandle typeHandle = objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    VarHandle dirHandle =
        objectLayout.varHandle(
            MemoryLayout.PathElement.sequenceElement(),
            MemoryLayout.PathElement.groupElement("dir"));
    hapticDirection.type = (byte) typeHandle.get(segment, 0);
    for (int i = 0; i < hapticDirection.dir.length; i++) {
      hapticDirection.dir[i] = (byte) dirHandle.get(segment, i);
    }
    return hapticDirection;
  }
}
