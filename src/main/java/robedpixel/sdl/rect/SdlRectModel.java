package robedpixel.sdl.rect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

// TODO:
public class SdlRectModel {
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("x"),
              ValueLayout.JAVA_INT.withName("y"),
              ValueLayout.JAVA_INT.withName("w"),
              ValueLayout.JAVA_INT.withName("h"))
          .withName("SDL_Rect");

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

  public SdlRectModel() {
    dataAddress = allocator.allocate(structLayout);
    xHandle.set(dataAddress, 0,0);
    yHandle.set(dataAddress, 0,0);
    wHandle.set(dataAddress, 0,0);
    hHandle.set(dataAddress, 0,0);
  }

  public static SdlFRectModel fromMemorySegment(MemorySegment segment) {
    SdlFRectModel model = new SdlFRectModel();
    model.dataAddress = segment;
    return model;
  }

  public int getX() {
    return (int) xHandle.get(dataAddress,0);
  }

  public int getY() {
    return (int) yHandle.get(dataAddress,0);
  }

  public int getW() {
    return (int) wHandle.get(dataAddress,0);
  }

  public int getH() {
    return (int) hHandle.get(dataAddress,0);
  }

  public void setX(int newValue) {
    xHandle.set(dataAddress, 0,newValue);
  }

  public void setY(int newValue) {
    yHandle.set(dataAddress, 0,newValue);
  }

  public void setW(int newValue) {
    wHandle.set(dataAddress, 0,newValue);
  }

  public void setH(int newValue) {
    hHandle.set(dataAddress, 0,newValue);
  }
}
