package robedpixel.sdl.power;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

/** The basic state for the system's power supply */
public enum SdlPowerState {
  /** < error determining power status */
  SDL_POWERSTATE_ERROR(-1),
  /** < cannot determine power status */
  SDL_POWERSTATE_UNKNOWN(0),
  /** < Not plugged in, running on the battery */
  SDL_POWERSTATE_ON_BATTERY(1),
  /** < Plugged in, no battery available */
  SDL_POWERSTATE_NO_BATTERY(2),
  /** < Plugged in, charging battery */
  SDL_POWERSTATE_CHARGING(3),
  /** < Plugged in, battery charged */
  SDL_POWERSTATE_CHARGED(4);

  @Getter private final int value;

  SdlPowerState(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlPowerState> reverseLookup =
      Maps.uniqueIndex(List.of(SdlPowerState.values()), SdlPowerState::getValue);

  public static SdlPowerState fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
