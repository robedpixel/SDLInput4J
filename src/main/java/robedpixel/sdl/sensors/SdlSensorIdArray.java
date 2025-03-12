package robedpixel.sdl.sensors;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

/**
 * Contains an array of SdlSensorIds
 */
public class SdlSensorIdArray {
  @Getter SdlSensorId[] data;

  public SdlSensorIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlSensorId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlSensorId();
      this.data[i].setValue(dataAddress.get(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
