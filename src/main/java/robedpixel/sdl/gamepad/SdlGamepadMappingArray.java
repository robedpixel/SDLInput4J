package robedpixel.sdl.gamepad;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlGamepadMappingArray {
  @Getter String[] data;

  public SdlGamepadMappingArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new String[count];
    for (int i = 0; i < count; i++) {
      MemorySegment charAddress = dataAddress.getAtIndex(ValueLayout.ADDRESS, i);
      this.data[i] = charAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
