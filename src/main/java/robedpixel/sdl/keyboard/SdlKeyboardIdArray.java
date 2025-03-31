package robedpixel.sdl.keyboard;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlKeyboardIdArray {
  @Getter SdlKeyboardId[] data;

  public SdlKeyboardIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlKeyboardId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlKeyboardId();
      this.data[i].setValue(dataAddress.getAtIndex(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
