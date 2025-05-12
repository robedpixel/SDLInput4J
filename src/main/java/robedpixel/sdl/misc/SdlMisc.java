package robedpixel.sdl.misc;

// TODO: add nullablility annotations
import java.lang.foreign.Arena;

public class SdlMisc {
  private final NativeSdlMiscFuncs SdlFuncs;

  public SdlMisc(Arena allocator) {
    SdlFuncs = NativeSdlMiscFuncs.getInstance(allocator);
  }

  /**
   * Open a URL/URI in the browser or other appropriate external application.
   *
   * @param url a valid URL/URI to open. Use file:///full/path/to/file for local files, if
   *     supported.
   * @return Returns true on success or false on failure; call SDL_GetError() for more information.
   * @throws Throwable
   */
  public boolean openUrl(String url) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.openUrl(arena, url);
    }
  }
}
