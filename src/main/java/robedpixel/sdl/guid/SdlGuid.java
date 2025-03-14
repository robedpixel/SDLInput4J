package robedpixel.sdl.guid;

import java.lang.foreign.Arena;

public class SdlGuid {
  private final NativeSdlGuidFuncs SdlFuncs;

  public SdlGuid(Arena allocator) {
    SdlFuncs = NativeSdlGuidFuncs.getInstance(allocator);
  }

  /**
   * Get an ASCII string representation for a given SDL_GUID.
   *
   * @param guid The SdlGuid you wish to convert to string.
   * @param chGuid The length of the returned String, should be at least 33
   * @return The converted ASCII string
   * @throws Throwable
   */
  public String guidToString(NativeSdlGuidModel guid, int chGuid) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.guidToString(arena, guid, chGuid);
    }
  }

  /**
   * Convert a GUID string into a SDL_GUID structure.
   *
   * @param pchGuid string containing an ASCII representation of a GUID.
   * @return Returns a SDL_GUID structure.
   * @throws Throwable
   */
  public NativeSdlGuidModel stringToGuid(String pchGuid) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.stringToGuid(arena, pchGuid);
    }
  }
}
