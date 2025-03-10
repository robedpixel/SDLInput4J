package robedpixel.sdl.sensors;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;

public class SdlSensorDataSnapshot {
  @Getter float[] data;
  Arena allocator = Arena.ofAuto();
  @Getter MemorySegment dataAddress;

  SdlSensorDataSnapshot(float[] data) {
    this.data = data;
    dataAddress = allocator.allocate(ValueLayout.JAVA_FLOAT, data.length);
  }
}
