package robedpixel.sdl.rect;

import java.awt.Rectangle;
import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlRectModel {
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("x"),
              ValueLayout.JAVA_INT.withName("y"),
              ValueLayout.JAVA_INT.withName("w"),
              ValueLayout.JAVA_INT.withName("h"))
          .withName("SDL_Rect");

  @Getter private Rectangle data;

  private static VarHandle xHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static VarHandle yHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  private static VarHandle wHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("w"));
  private static VarHandle hHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("h"));
  @Getter private MemorySegment dataAddress;
  Arena allocator = Arena.ofAuto();

  public SdlRectModel() {
    dataAddress = allocator.allocate(structLayout);
    data = new Rectangle();
    xHandle.set(dataAddress, 0, data.x);
    yHandle.set(dataAddress, 0, data.y);
    wHandle.set(dataAddress, 0, data.width);
    hHandle.set(dataAddress, 0, data.height);
  }

  public static SdlRectModel fromMemorySegment(MemorySegment segment) {
    SdlRectModel model = new SdlRectModel();
    model.dataAddress = segment;
    model.data = new Rectangle();
    model.data.x = (int) xHandle.get(model.dataAddress, 0);
    model.data.y = (int) yHandle.get(model.dataAddress, 0);
    model.data.width = (int) wHandle.get(model.dataAddress, 0);
    model.data.height = (int) hHandle.get(model.dataAddress, 0);
    return model;
  }

  void updateValues() {
    data.x = (int) xHandle.get(dataAddress, 0);
    data.y = (int) yHandle.get(dataAddress, 0);
    data.width = (int) wHandle.get(dataAddress, 0);
    data.height = (int) hHandle.get(dataAddress, 0);
  }

  public int getX() {
    return data.x;
  }

  public int getY() {
    return data.y;
  }

  public int getW() {
    return data.width;
  }

  public int getH() {
    return data.height;
  }

  public void setX(int newValue) {
    data.x = newValue;
    xHandle.set(dataAddress, 0, data.x);
  }

  public void setY(int newValue) {
    data.y = newValue;
    yHandle.set(dataAddress, 0, data.y);
  }

  public void setW(int newValue) {
    data.width = newValue;
    wHandle.set(dataAddress, 0, data.width);
  }

  public void setH(int newValue) {
    data.height = newValue;
    hHandle.set(dataAddress, 0, data.height);
  }
}
