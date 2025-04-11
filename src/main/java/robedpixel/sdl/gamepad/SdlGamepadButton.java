package robedpixel.sdl.gamepad;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlGamepadButton {
  SDL_GAMEPAD_BUTTON_INVALID(-1),
  /** Bottom face button (e.g. Xbox A button) */
  SDL_GAMEPAD_BUTTON_SOUTH(0),
  /** Right face button (e.g. Xbox B button) */
  SDL_GAMEPAD_BUTTON_EAST(1),
  /** Left face button (e.g. Xbox X button) */
  SDL_GAMEPAD_BUTTON_WEST(2),
  /** Top face button (e.g. Xbox Y button) */
  SDL_GAMEPAD_BUTTON_NORTH(3),
  SDL_GAMEPAD_BUTTON_BACK(4),
  SDL_GAMEPAD_BUTTON_GUIDE(5),
  SDL_GAMEPAD_BUTTON_START(6),
  SDL_GAMEPAD_BUTTON_LEFT_STICK(7),
  SDL_GAMEPAD_BUTTON_RIGHT_STICK(8),
  SDL_GAMEPAD_BUTTON_LEFT_SHOULDER(9),
  SDL_GAMEPAD_BUTTON_RIGHT_SHOULDER(10),
  SDL_GAMEPAD_BUTTON_DPAD_UP(11),
  SDL_GAMEPAD_BUTTON_DPAD_DOWN(12),
  SDL_GAMEPAD_BUTTON_DPAD_LEFT(13),
  SDL_GAMEPAD_BUTTON_DPAD_RIGHT(14),
  /**
   * Additional button (e.g. Xbox Series X share button, PS5 microphone button, Nintendo Switch Pro
   * capture button, Amazon Luna microphone button, Google Stadia capture button)
   */
  SDL_GAMEPAD_BUTTON_MISC1(15),
  /** Upper or primary paddle, under your right hand (e.g. Xbox Elite paddle P1) */
  SDL_GAMEPAD_BUTTON_RIGHT_PADDLE1(16),
  /** Upper or primary paddle, under your left hand (e.g. Xbox Elite paddle P3) */
  SDL_GAMEPAD_BUTTON_LEFT_PADDLE1(17),
  /** Lower or secondary paddle, under your right hand (e.g. Xbox Elite paddle P2) */
  SDL_GAMEPAD_BUTTON_RIGHT_PADDLE2(18),
  /** Lower or secondary paddle, under your left hand (e.g. Xbox Elite paddle P4) */
  SDL_GAMEPAD_BUTTON_LEFT_PADDLE2(19),
  /** PS4/PS5 touchpad button */
  SDL_GAMEPAD_BUTTON_TOUCHPAD(20),
  /** Additional button */
  SDL_GAMEPAD_BUTTON_MISC2(21),
  /** Additional button */
  SDL_GAMEPAD_BUTTON_MISC3(22),
  /** Additional button */
  SDL_GAMEPAD_BUTTON_MISC4(23),
  /** Additional button */
  SDL_GAMEPAD_BUTTON_MISC5(24),
  /** Additional button */
  SDL_GAMEPAD_BUTTON_MISC6(25),
  SDL_GAMEPAD_BUTTON_COUNT(26);

  @Getter private final int value;

  SdlGamepadButton(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlGamepadButton> reverseLookup =
      Maps.uniqueIndex(List.of(SdlGamepadButton.values()), SdlGamepadButton::getValue);

  public static SdlGamepadButton fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
