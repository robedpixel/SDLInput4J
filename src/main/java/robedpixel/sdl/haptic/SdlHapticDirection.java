package robedpixel.sdl.haptic;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlHapticDirection {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_BYTE.withName("type"),
              MemoryLayout.paddingLayout(15),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_INT).withName("dir"))
          .withName("SDL_HapticDirection");
  @Getter byte type;
  @Getter int[] dir = new int[3];
  @Getter MemorySegment dataAddress;
  private Arena allocator = Arena.ofAuto();
  private static VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static VarHandle dirHandle =
      objectLayout.varHandle(
          MemoryLayout.PathElement.groupElement("dir"), MemoryLayout.PathElement.sequenceElement());

  private SdlHapticDirection() {}

  public SdlHapticDirection(byte type, int x, int y, int z) {
    dataAddress = allocator.allocate(objectLayout);
    this.type = type;
    dir[0] = x;
    dir[1] = y;
    dir[2] = z;
    typeHandle.set(dataAddress, 0, this.type);
    for (int i = 0; i < dir.length; i++) {
      dirHandle.set(dataAddress, 0, i, dir[i]);
    }
  }

  public static SdlHapticDirection getEventFromMemorySegment(MemorySegment segment) {
    SdlHapticDirection hapticDirection = new SdlHapticDirection();
    hapticDirection.type = (byte) typeHandle.get(segment, 0);
    for (int i = 0; i < hapticDirection.dir.length; i++) {
      hapticDirection.dir[i] = (byte) dirHandle.get(segment, i);
    }
    return hapticDirection;
  }
}
