package robedpixel.sdl.gamepad;

import lombok.Getter;

public enum SdlGamepadAxis {
  SDL_GAMEPAD_AXIS_INVALID(-1),
  SDL_GAMEPAD_AXIS_LEFTX(0),
  SDL_GAMEPAD_AXIS_LEFTY(1),
  SDL_GAMEPAD_AXIS_RIGHTX(2),
  SDL_GAMEPAD_AXIS_RIGHTY(3),
  SDL_GAMEPAD_AXIS_LEFT_TRIGGER(4),
  SDL_GAMEPAD_AXIS_RIGHT_TRIGGER(5),
  SDL_GAMEPAD_AXIS_COUNT(6);
  @Getter private final int value;

  SdlGamepadAxis(final int value) {
    this.value = value;
  }
}
