package robedpixel.sdl.guid;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class NativeSdlGuidModel {
  public static int DATA_LENGTH = 16;
  private final short[] data = new short[DATA_LENGTH];
  @Getter private MemorySegment dataAddress;
  private final Arena allocator = Arena.ofAuto();
  private static final VarHandle dataArray =
      NativeSdlGuidModel.getStructLayout()
          .varHandle(
              MemoryLayout.PathElement.groupElement("data"),
              MemoryLayout.PathElement.sequenceElement());

  /** StructLayout of SdlGuid in SDL C Library */
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_SHORT).withName("data"))
          .withName("SDL_GUID");

  public short getData(int index) {
    return data[index];
  }

  public void setData(int index, short value) {
    data[index] = value;
    dataArray.set(dataAddress, 0, index, data[index]);
  }

  public static NativeSdlGuidModel fromSegment(MemorySegment segment) {
    NativeSdlGuidModel returnObject = new NativeSdlGuidModel();
    returnObject.dataAddress = returnObject.allocator.allocate(structLayout);
    for (int i = 0; i < 16; i++) {
      returnObject.setData(i, (short) dataArray.get(segment, 0, i));
      dataArray.set(returnObject.dataAddress, 0, i, returnObject.getData(i));
    }
    return returnObject;
  }
}
