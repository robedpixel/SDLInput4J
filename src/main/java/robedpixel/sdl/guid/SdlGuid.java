package robedpixel.sdl.guid;

import org.jspecify.annotations.NonNull;

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
   * @param buffer ByteArray to store the buffer
   * @return The converted ASCII string
   * @throws Throwable
   */
  @NonNull
  public String guidToString(NativeSdlGuidModel guid, SdlGuidByteArray buffer) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.guidToString(arena, guid, buffer);
    }
  }

  /**
   * Convert a GUID string into a SDL_GUID structure.
   *
   * @param pchGuid string containing an ASCII representation of a GUID.
   * @return Returns a SDL_GUID structure.
   * @throws Throwable
   */
  @NonNull
  public NativeSdlGuidModel stringToGuid(String pchGuid) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.stringToGuid(arena, pchGuid);
    }
  }
}
