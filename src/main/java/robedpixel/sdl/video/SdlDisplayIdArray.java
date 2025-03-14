package robedpixel.sdl.video;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlDisplayIdArray {
  @Getter SdlDisplayId[] data;

  public SdlDisplayIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlDisplayId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlDisplayId();
      this.data[i].setValue(dataAddress.getAtIndex(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
