package robedpixel.sdl.rect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlFRectModel {
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_FLOAT.withName("w"),
              ValueLayout.JAVA_FLOAT.withName("h"))
          .withName("SDL_FRect");

  private static VarHandle xHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static VarHandle yHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static VarHandle wHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("w"));
  private static VarHandle hHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("h"));
  @Getter MemorySegment dataAddress;
  Arena allocator = Arena.ofAuto();

  public SdlFRectModel() {
    dataAddress = allocator.allocate(structLayout);
    xHandle.set(dataAddress, 0,0f);
    yHandle.set(dataAddress, 0,0f);
    wHandle.set(dataAddress, 0,0f);
    hHandle.set(dataAddress, 0,0f);
  }

  public static SdlFRectModel fromMemorySegment(MemorySegment segment) {
    SdlFRectModel model = new SdlFRectModel();
    model.dataAddress = segment;
    return model;
  }

  public float getX() {
    return (float) xHandle.get(dataAddress,0);
  }

  public float getY() {
    return (float) yHandle.get(dataAddress,0);
  }

  public float getW() {
    return (float) wHandle.get(dataAddress,0);
  }

  public float getH() {
    return (float) hHandle.get(dataAddress,0);
  }

  public void setX(float newValue) {
    xHandle.set(dataAddress, 0,newValue);
  }

  public void setY(float newValue) {
    yHandle.set(dataAddress, 0,newValue);
  }

  public void setW(float newValue) {
    wHandle.set(dataAddress, 0,newValue);
  }

  public void setH(float newValue) {
    hHandle.set(dataAddress, 0,newValue);
  }
}
