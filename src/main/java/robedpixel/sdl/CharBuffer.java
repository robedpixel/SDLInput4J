package robedpixel.sdl;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.Getter;

public class CharBuffer implements AutoCloseable {
  private Arena arena = Arena.ofConfined();
  @Getter private MemorySegment segment;
  @Getter private int length;

  public CharBuffer(int length) {
    segment = arena.allocate(ValueLayout.JAVA_BYTE, length);
    this.length = length;
  }

  public String getString() {
    return segment.reinterpret(ValueLayout.JAVA_BYTE.byteSize() * length).getString(0);
  }

  @Override
  public void close() throws Exception {
    arena.close();
  }
}
