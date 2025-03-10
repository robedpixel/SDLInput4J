package robedpixel.sdl.touch;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.NativeSdlLib;

public class SdlFinger2dArray implements AutoCloseable {
  @Getter SdlFinger[][] data;
  MemorySegment dataAddress;

  public SdlFinger2dArray(MemorySegment dataAddress, int count) {
    // TODO: find out how to get length and width of array that is null terminated
    int width = 0;
    for (int i = 0; i < count; i++) {
      if (dataAddress.get(ValueLayout.ADDRESS, i) != MemorySegment.NULL) {
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
      MemorySegment secondAddress = dataAddress.get(ValueLayout.ADDRESS, i);
      for (int j = 0; j < length; j++) {
        long id = (long) idHandle.get(secondAddress);
        float x = (float) xHandle.get(secondAddress);
        float y = (float) yHandle.get(secondAddress);
        float pressure = (float) pressureHandle.get(secondAddress);
        data[i][j] = new SdlFinger(id, x, y, pressure);
      }
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
