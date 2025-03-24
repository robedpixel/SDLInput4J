package robedpixel.sdl.joystick;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlJoystickConnectionState {
  SDL_JOYSTICK_CONNECTION_INVALID(-1),
  SDL_JOYSTICK_CONNECTION_UNKNOWN(0),
  SDL_JOYSTICK_CONNECTION_WIRED(1),
  SDL_JOYSTICK_CONNECTION_WIRELESS(2);
  @Getter private final int value;

  SdlJoystickConnectionState(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlJoystickConnectionState> reverseLookup =
      Maps.uniqueIndex(
          List.of(SdlJoystickConnectionState.values()), SdlJoystickConnectionState::getValue);

  public static SdlJoystickConnectionState fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
