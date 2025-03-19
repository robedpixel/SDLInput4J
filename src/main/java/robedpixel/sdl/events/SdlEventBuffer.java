package robedpixel.sdl.events;

import java.lang.foreign.*;
import robedpixel.sdl.events.sdlevent.SdlCommonEvent;

public class SdlEventBuffer {
  private MemorySegment buffer;
  // TODO: enumerate all layouts of events
  private UnionLayout SdlEventLayout =
      MemoryLayout.unionLayout(
              ValueLayout.JAVA_INT.withName("type"), SdlCommonEvent.objectLayout.withName("common"))
          .withName("SDL_Event");

  public SdlEventBuffer(int size) {}
}
