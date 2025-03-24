package robedpixel.sdl.joystick;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;

/** The structure that describes a virtual joystick touchpad. */
public class SdlVirtualJoystickTouchpadDesc {
  public static final MemoryLayout objectLayout =
      MemoryLayout.structLayout(
          ValueLayout.JAVA_SHORT.withName("nfingers"),
          MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_SHORT).withName("padding"));
  @Getter private short uNFingers;
  @Getter private MemorySegment dataAddress;
  private static VarHandle fingersHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("nfingers"));
  private Arena allocator = Arena.ofAuto();

  public SdlVirtualJoystickTouchpadDesc(short uNFingers) {
    this.uNFingers = uNFingers;
    dataAddress = allocator.allocate(objectLayout);
    fingersHandle.set(dataAddress, 0, this.uNFingers);
  }
}
