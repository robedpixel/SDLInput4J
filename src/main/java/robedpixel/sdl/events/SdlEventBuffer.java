package robedpixel.sdl.events;

import java.lang.foreign.*;
import lombok.Getter;
import robedpixel.sdl.events.sdlevent.SdlEvent;

public class SdlEventBuffer implements AutoCloseable {
  @Getter private final MemorySegment buffer;
  private final Arena arena = Arena.ofConfined();

  public SdlEventBuffer(int size) {
    buffer = arena.allocate(SdlEvent.objectLayout.byteSize() * size);
  }

  @Override
  public void close() throws Exception {
    arena.close();
  }
}
