package robedpixel.sdl.sensors;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

/**
 * Contains an array of SdlSensorIds, the close function must be called during object destruction to
 * prevent memory leaks
 */
public class SdlSensorIdArray implements AutoCloseable {
  @Getter SdlSensorId[] data;
  MemorySegment dataAddress;

  public SdlSensorIdArray(MemorySegment dataAddress, int count) {
    this.data = new SdlSensorId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlSensorId();
      this.data[i].setValue(dataAddress.get(ValueLayout.JAVA_INT, i));
    }
    this.dataAddress = dataAddress;
  }

  @Override
  public void close() throws Exception {
    try {
      NativeSdlLib.sdlFree(dataAddress);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
