package robedpixel.sdl.touch;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import lombok.Getter;

public class SdlFinger {
  @Getter private final SdlFingerId id;
  @Getter private final float x;
  @Getter private final float y;
  @Getter private final float pressure;
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_LONG.withName("id"),
              ValueLayout.JAVA_FLOAT.withName("x"),
              ValueLayout.JAVA_FLOAT.withName("y"),
              ValueLayout.JAVA_FLOAT.withName("pressure"))
          .withName("SDL_Finger");

  public SdlFinger(long id, float x, float y, float pressure) {
    this.id = new SdlFingerId();
    this.id.setValue(id);
    this.x = x;
    this.y = y;
    this.pressure = pressure;
  }
}
