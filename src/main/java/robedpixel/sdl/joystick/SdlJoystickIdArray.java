package robedpixel.sdl.joystick;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlJoystickIdArray {
  @Getter SdlJoystickId[] data;

  public SdlJoystickIdArray(MemorySegment dataAddress, int count) throws Throwable {
    this.data = new SdlJoystickId[count];
    for (int i = 0; i < count; i++) {
      this.data[i] = new SdlJoystickId();
      this.data[i].setValue(dataAddress.getAtIndex(ValueLayout.JAVA_INT, i));
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
