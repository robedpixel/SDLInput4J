package robedpixel.sdl.haptic;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;
import robedpixel.sdl.touch.SdlTouchDeviceType;

public enum SdlHapticTypeFlag {
  SDL_HAPTIC_CONSTANT(1),
  /**
   * Sine wave effect supported.
   *
   * <p>Periodic haptic effect that simulates sine waves.
   *
   * <p>\sa SDL_HapticPeriodic
   */
  SDL_HAPTIC_SINE(1 << 1),
  /**
   * Left/Right effect supported.
   *
   * <p>Haptic effect for direct control over high/low frequency motors.
   *
   * <p>\sa SDL_HapticLeftRight
   */
  SDL_HAPTIC_LEFTRIGHT(1 << 2),
  /**
   * Triangle wave effect supported.
   *
   * <p>Periodic haptic effect that simulates triangular waves.
   *
   * <p>\sa SDL_HapticPeriodic
   */
  SDL_HAPTIC_TRIANGLE(1 << 3),
  /**
   * Sawtoothup wave effect supported.
   *
   * <p>Periodic haptic effect that simulates saw tooth up waves.
   *
   * <p>\sa SDL_HapticPeriodic
   */
  SDL_HAPTIC_SAWTOOTHUP(1 << 4),
  /**
   * Sawtoothdown wave effect supported.
   *
   * <p>Periodic haptic effect that simulates saw tooth down waves.
   *
   * <p>\sa SDL_HapticPeriodic
   */
  SDL_HAPTIC_SAWTOOTHDOWN(1 << 5),
  /**
   * Ramp effect supported.
   *
   * <p>Ramp haptic effect.
   *
   * <p>\sa SDL_HapticRamp
   */
  SDL_HAPTIC_RAMP(1 << 6),
  /**
   * Spring effect supported - uses axes position.
   *
   * <p>Condition haptic effect that simulates a spring. Effect is based on the axes position.
   *
   * <p>\sa SDL_HapticCondition
   */
  SDL_HAPTIC_SPRING(1 << 7),
  /**
   * Damper effect supported - uses axes velocity.
   *
   * <p>Condition haptic effect that simulates dampening. Effect is based on the axes velocity.
   *
   * <p>\sa SDL_HapticCondition
   */
  SDL_HAPTIC_DAMPER(1 << 8),
  /**
   * Inertia effect supported - uses axes acceleration.
   *
   * <p>Condition haptic effect that simulates inertia. Effect is based on the axes acceleration.
   *
   * <p>\sa SDL_HapticCondition
   */
  SDL_HAPTIC_INERTIA(1 << 9),
  /**
   * Friction effect supported - uses axes movement.
   *
   * <p>Condition haptic effect that simulates friction. Effect is based on the axes movement.
   *
   * <p>\sa SDL_HapticCondition
   */
  SDL_HAPTIC_FRICTION(1 << 10),
  /**
   * Custom effect is supported.
   *
   * <p>User defined custom haptic effect.
   */
  SDL_HAPTIC_CUSTOM(1 << 11),
  /**
   * Device can set global gain.
   *
   * <p>Device supports setting the global gain.
   *
   * <p>\sa SDL_HapticSetGain
   */
  SDL_HAPTIC_GAIN(1 << 12),
  /**
   * Device can set autocenter.
   *
   * <p>Device supports setting autocenter.
   *
   * <p>\sa SDL_HapticSetAutocenter
   */
  SDL_HAPTIC_AUTOCENTER(1 << 13),
  /**
   * Device can be queried for effect status.
   *
   * <p>Device supports querying effect status.
   *
   * <p>\sa SDL_HapticGetEffectStatus
   */
  SDL_HAPTIC_STATUS(1 << 14),
  /**
   * Device can be paused.
   *
   * <p>Devices supports being paused.
   *
   * <p>\sa SDL_HapticPause \sa SDL_HapticUnpause
   */
  SDL_HAPTIC_PAUSE(1 << 15);

  @Getter private final int value;

  SdlHapticTypeFlag(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlTouchDeviceType> reverseLookup =
      Maps.uniqueIndex(List.of(SdlTouchDeviceType.values()), SdlTouchDeviceType::getValue);

  public static SdlTouchDeviceType fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
