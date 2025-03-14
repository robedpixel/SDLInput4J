package robedpixel.sdl.touch;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.sensors.SdlSensorId;

/**
 * Contains an array of SdlTouchIds
 */
public class SdlTouchIdArray {
  @Getter SdlTouchId[] data;

  public SdlTouchIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlTouchId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlTouchId();
      this.data[i].setValue(dataAddress.getAtIndex(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
