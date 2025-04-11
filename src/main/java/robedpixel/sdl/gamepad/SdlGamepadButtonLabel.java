package robedpixel.sdl.gamepad;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlGamepadButtonLabel {
  SDL_GAMEPAD_BUTTON_LABEL_UNKNOWN(0),
  SDL_GAMEPAD_BUTTON_LABEL_A(1),
  SDL_GAMEPAD_BUTTON_LABEL_B(2),
  SDL_GAMEPAD_BUTTON_LABEL_X(3),
  SDL_GAMEPAD_BUTTON_LABEL_Y(4),
  SDL_GAMEPAD_BUTTON_LABEL_CROSS(5),
  SDL_GAMEPAD_BUTTON_LABEL_CIRCLE(6),
  SDL_GAMEPAD_BUTTON_LABEL_SQUARE(7),
  SDL_GAMEPAD_BUTTON_LABEL_TRIANGLE(8);

  @Getter private final int value;

  SdlGamepadButtonLabel(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlGamepadButtonLabel> reverseLookup =
      Maps.uniqueIndex(List.of(SdlGamepadButtonLabel.values()), SdlGamepadButtonLabel::getValue);

  public static SdlGamepadButtonLabel fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
