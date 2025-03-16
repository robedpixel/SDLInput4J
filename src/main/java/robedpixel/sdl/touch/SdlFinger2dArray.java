package robedpixel.sdl.touch;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlFinger2dArray {
  @Getter SdlFinger[][] data;

  public SdlFinger2dArray(MemorySegment dataAddress, int count) throws Throwable {
    int width = 0;
    for (int i = 0; i < count; i++) {
      if (dataAddress.getAtIndex(ValueLayout.ADDRESS, i) != MemorySegment.NULL) {
        width++;
      } else {
        break;
      }
    }
    int length = count / width;
    data = new SdlFinger[width][length];
    VarHandle idHandle =
        SdlFinger.objectLayout.varHandle(MemoryLayout.PathElement.groupElement("id"));
    VarHandle xHandle =
        SdlFinger.objectLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
    VarHandle yHandle =
        SdlFinger.objectLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
    VarHandle pressureHandle =
        SdlFinger.objectLayout.varHandle(MemoryLayout.PathElement.groupElement("pressure"));
    for (int i = 0; i < width; i++) {
      MemorySegment secondAddress = dataAddress.getAtIndex(ValueLayout.ADDRESS, i);
      for (int j = 0; j < length; j++) {
        long id = (long) idHandle.get(secondAddress,0);
        float x = (float) xHandle.get(secondAddress,0);
        float y = (float) yHandle.get(secondAddress,0);
        float pressure = (float) pressureHandle.get(secondAddress,0);
        data[i][j] = new SdlFinger(id, x, y, pressure);
      }
    }
    NativeSdlLib.sdlFree(dataAddress);
  }
}
