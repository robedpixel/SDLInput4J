package robedpixel.sdl.rect;

import java.awt.*;
import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public class SdlPointModel {
  @Getter
  private static final StructLayout structLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("x"), ValueLayout.JAVA_INT.withName("y"))
          .withName("SDL_Point");

  @Getter private Point data;
  private static VarHandle xHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("x"));
  private static VarHandle yHandle =
      structLayout.varHandle(MemoryLayout.PathElement.groupElement("y"));
  @Getter private MemorySegment dataAddress;
  Arena allocator = Arena.ofAuto();

  public SdlPointModel() {
    data = new Point();
    dataAddress = allocator.allocate(structLayout);
    xHandle.set(dataAddress, 0, data.x);
    yHandle.set(dataAddress, 0, data.y);
  }

  public static SdlPointModel fromMemorySegment(MemorySegment segment) {
    SdlPointModel model = new SdlPointModel();
    model.dataAddress = segment;
    model.data = new Point();
    model.data.x = (int) xHandle.get(model.dataAddress, 0);
    model.data.y = (int) yHandle.get(model.dataAddress, 0);
    return model;
  }

  public int getX() {
    return data.x;
  }

  public int getY() {
    return data.y;
  }

  public void setX(int newValue) {
    data.x = newValue;
    xHandle.set(dataAddress, 0, data.x);
  }

  public void setY(int newValue) {
    data.y = newValue;
    yHandle.set(dataAddress, 0, data.y);
  }
}
