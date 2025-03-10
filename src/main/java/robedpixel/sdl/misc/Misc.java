package robedpixel.sdl.misc;

import java.lang.foreign.Arena;

public class Misc {
  private final NativeMiscFuncs SdlFuncs;

  public Misc(Arena allocator) {
    SdlFuncs = NativeMiscFuncs.getInstance(allocator);
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
