package robedpixel.sdl.gamepad;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlGamepadType {
  SDL_GAMEPAD_TYPE_UNKNOWN(0),
  SDL_GAMEPAD_TYPE_STANDARD(1),
  SDL_GAMEPAD_TYPE_XBOX360(2),
  SDL_GAMEPAD_TYPE_XBOXONE(3),
  SDL_GAMEPAD_TYPE_PS3(4),
  SDL_GAMEPAD_TYPE_PS4(5),
  SDL_GAMEPAD_TYPE_PS5(6),
  SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_PRO(7),
  SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_LEFT(8),
  SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_RIGHT(9),
  SDL_GAMEPAD_TYPE_NINTENDO_SWITCH_JOYCON_PAIR(10),
  SDL_GAMEPAD_TYPE_COUNT(11);

  @Getter private final int value;

  SdlGamepadType(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlGamepadType> reverseLookup =
      Maps.uniqueIndex(List.of(SdlGamepadType.values()), SdlGamepadType::getValue);

  public static SdlGamepadType fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
