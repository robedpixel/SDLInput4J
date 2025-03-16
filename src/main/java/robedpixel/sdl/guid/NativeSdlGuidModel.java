package robedpixel.sdl.guid;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import lombok.Setter;

public class NativeSdlGuidModel {
  @Getter @Setter private short[] data = new short[16];
  @Getter private final MemorySegment funcDesc = MemorySegment.ofArray(data);

  /** StructLayout of SdlGuid in SDL C Library */
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              MemoryLayout.sequenceLayout(16, ValueLayout.JAVA_SHORT).withName("data"))
          .withName("SDL_GUID");

  public static NativeSdlGuidModel fromSegment(MemorySegment segment) {
    NativeSdlGuidModel returnObject = new NativeSdlGuidModel();
    VarHandle dataArray =
        NativeSdlGuidModel.getStructLayout()
            .arrayElementVarHandle(
                MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
    for (int i = 0; i < 16; i++) {
      returnObject.getData()[i] = (short) dataArray.get(segment, i);
    }
    return returnObject;
  }
}
