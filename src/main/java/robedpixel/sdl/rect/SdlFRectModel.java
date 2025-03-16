package robedpixel.sdl.rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
  @Getter
  private Rectangle2D.Float data;

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

  public SdlFRectModel() {
    data = new Rectangle2D.Float();
    dataAddress = allocator.allocate(structLayout);
    xHandle.set(dataAddress, 0,data.x);
    yHandle.set(dataAddress, 0,data.y);
    wHandle.set(dataAddress, 0,data.width);
    hHandle.set(dataAddress, 0,data.height);
  }

  public static SdlFRectModel fromMemorySegment(MemorySegment segment) {
    SdlFRectModel model = new SdlFRectModel();
    model.dataAddress = segment;
    model.data = new Rectangle2D.Float();
    model.data.x = (float)xHandle.get(model.dataAddress, 0);
    model.data.y = (float)yHandle.get(model.dataAddress, 0);
    model.data.width = (float)wHandle.get(model.dataAddress, 0);
    model.data.height = (float)hHandle.get(model.dataAddress, 0);
    return model;
  }

  public float getX() {
    return data.x;
  }

  public float getY() {
    return data.y;
  }

  public float getW() {
    return data.width;
  }

  public float getH() {
    return data.height;
  }

  public void setX(float newValue) {
    data.x = newValue;
    xHandle.set(dataAddress, 0,data.x);
  }

  public void setY(float newValue) {
    data.y = newValue;
    yHandle.set(dataAddress, 0,data.y);
  }

  public void setW(float newValue) {
    data.width = newValue;
    wHandle.set(dataAddress, 0,data.width);
  }

  public void setH(float newValue) {
    data.height = newValue;
    hHandle.set(dataAddress, 0,data.height);
  }
}
