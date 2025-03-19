package robedpixel.sdl.video;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlDisplayOrientation {
  SDL_ORIENTATION_UNKNOWN(0),
  /** < The display orientation can't be determined */
  SDL_ORIENTATION_LANDSCAPE(1),
  /** < The display is in landscape mode, with the right side up, relative to portrait mode */
  SDL_ORIENTATION_LANDSCAPE_FLIPPED(2),
  /** < The display is in landscape mode, with the left side up, relative to portrait mode */
  SDL_ORIENTATION_PORTRAIT(3),
  /** < The display is in portrait mode */
  SDL_ORIENTATION_PORTRAIT_FLIPPED(4);

  /** < The display is in portrait mode, upside down */
  @Getter private final int value;

  SdlDisplayOrientation(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlDisplayOrientation> reverseLookup =
      Maps.uniqueIndex(List.of(SdlDisplayOrientation.values()), SdlDisplayOrientation::getValue);

  public static SdlDisplayOrientation fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
