package robedpixel.sdl.mouse;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;

public class SdlCursorBitmap {
  private final MemorySegment data;
  private final MemorySegment mask;
  @Getter private final int hotX;
  @Getter private final int hotY;
  @Getter private final int width;
  @Getter private final int height;
  private final Arena allocator = Arena.ofAuto();

  public SdlCursorBitmap(int width, int height, int hotX, int hotY) {
    if ((width % 8) != 0) {
      throw new IllegalArgumentException("Width must be a multiple of 8!");
    }
    int numBytes = (width / 8) * height;
    data = allocator.allocate(ValueLayout.JAVA_BYTE, numBytes);
    mask = allocator.allocate(ValueLayout.JAVA_BYTE, numBytes);
    this.width = width;
    this.height = height;
    this.hotX = hotX;
    this.hotY = hotY;
  }

  public byte getMaskValueAtIndex(long index) {
    return mask.getAtIndex(ValueLayout.JAVA_BYTE, index);
  }

  public byte getDataValueAtIndex(long index) {
    return data.getAtIndex(ValueLayout.JAVA_BYTE, index);
  }

  public void setMaskValueAtIndex(long index, byte value) {
    mask.setAtIndex(ValueLayout.JAVA_BYTE, index, value);
  }

  public void setDataValueAtIndex(long index, byte value) {
    data.setAtIndex(ValueLayout.JAVA_BYTE, index, value);
  }

  public MemorySegment getData() {
    return data.asReadOnly();
  }

  public MemorySegment getMask() {
    return mask.asReadOnly();
  }
}
