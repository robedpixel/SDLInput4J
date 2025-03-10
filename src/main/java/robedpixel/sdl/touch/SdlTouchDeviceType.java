package robedpixel.sdl.touch;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlTouchDeviceType {
  SDL_TOUCH_DEVICE_INVALID(-1),
  SDL_TOUCH_DEVICE_DIRECT(0),
  /** < touch screen with window-relative coordinates */
  SDL_TOUCH_DEVICE_INDIRECT_ABSOLUTE(1),
  /** < trackpad with absolute device coordinates */
  SDL_TOUCH_DEVICE_INDIRECT_RELATIVE(2);

  /** < trackpad with screen cursor-relative coordinates */
  @Getter private final int value;

  SdlTouchDeviceType(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlTouchDeviceType> reverseLookup =
      Maps.uniqueIndex(List.of(SdlTouchDeviceType.values()), SdlTouchDeviceType::getValue);

  public static SdlTouchDeviceType fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
