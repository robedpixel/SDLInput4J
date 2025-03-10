package robedpixel.sdl.touch;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.sensors.SdlSensorId;

/**
 * Contains an array of SdlTouchIds, the close function must be called during object destruction to
 * prevent memory leaks
 */
public class SdlTouchIdArray implements AutoCloseable {
  @Getter SdlTouchId[] data;
  MemorySegment dataAddress;

  public SdlTouchIdArray(MemorySegment dataAddress, int count) {
    this.data = new SdlTouchId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlTouchId();
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
