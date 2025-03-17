package robedpixel.sdl.haptic;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlHapticIdArray {
  @Getter SdlHapticId[] data;

  public SdlHapticIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlHapticId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlHapticId();
      this.data[i].setValue(dataAddress.get(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
