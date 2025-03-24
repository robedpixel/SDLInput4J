package robedpixel.sdl.joystick;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.sensors.SdlSensorType;

/** The structure that describes a virtual joystick sensor. */
public class SdlVirtualJoystickSensorDesc {
  public static final MemoryLayout objectLayout =
      MemoryLayout.structLayout(
          ValueLayout.JAVA_INT.withName("type"), ValueLayout.JAVA_FLOAT.withName("rate"));
  @Getter private SdlSensorType type;
  @Getter private float rate;
  @Getter private MemorySegment dataAddress;
  private static VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static VarHandle rateHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("rate"));
  private Arena allocator = Arena.ofAuto();

  public SdlVirtualJoystickSensorDesc(SdlSensorType type, float rate) {
    this.type = type;
    this.rate = rate;
    dataAddress = allocator.allocate(objectLayout);
    typeHandle.set(dataAddress, 0, this.type.getValue());
    rateHandle.set(dataAddress, 0, this.rate);
  }
}
