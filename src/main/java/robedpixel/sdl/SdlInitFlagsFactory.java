package robedpixel.sdl;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;

public class SdlInitFlagsFactory {
  // define SDL_INIT_AUDIO      0x00000010u /**< `SDL_INIT_AUDIO` implies `SDL_INIT_EVENTS` */
  // define SDL_INIT_VIDEO      0x00000020u /**< `SDL_INIT_VIDEO` implies `SDL_INIT_EVENTS`, should
  // be initialized on the main thread */
  // define SDL_INIT_JOYSTICK   0x00000200u /**< `SDL_INIT_JOYSTICK` implies `SDL_INIT_EVENTS`,
  // should be initialized on the same thread as SDL_INIT_VIDEO on Windows if you don't set
  // SDL_HINT_JOYSTICK_THREAD */
  // define SDL_INIT_HAPTIC     0x00001000u
  // define SDL_INIT_GAMEPAD    0x00002000u /**< `SDL_INIT_GAMEPAD` implies `SDL_INIT_JOYSTICK` */
  // define SDL_INIT_EVENTS     0x00004000u
  // define SDL_INIT_SENSOR     0x00008000u /**< `SDL_INIT_SENSOR` implies `SDL_INIT_EVENTS` */
  // define SDL_INIT_CAMERA     0x00010000u /**< `SDL_INIT_CAMERA` implies `SDL_INIT_EVENTS` */
  public enum SDLFlagValue {
    SDL_INIT_AUDIO,
    SDL_INIT_VIDEO,
    SDL_INIT_JOYSTICK,
    SDL_INIT_HAPTIC,
    SDL_INIT_GAMEPAD,
    SDL_INIT_EVENTS,
    SDL_INIT_SENSOR,
    SDL_INIT_CAMERA
  }

  private static final EnumMap<SDLFlagValue, Integer> sdlFlagMap;

  static {
    sdlFlagMap = new EnumMap<>(SDLFlagValue.class);
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_AUDIO, Integer.parseUnsignedInt("00000010", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_VIDEO, Integer.parseUnsignedInt("00000020", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_JOYSTICK, Integer.parseUnsignedInt("00000200", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_HAPTIC, Integer.parseUnsignedInt("00001000", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_GAMEPAD, Integer.parseUnsignedInt("00002000", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_EVENTS, Integer.parseUnsignedInt("00004000", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_SENSOR, Integer.parseUnsignedInt("00008000", 16));
    sdlFlagMap.put(SDLFlagValue.SDL_INIT_CAMERA, Integer.parseUnsignedInt("00010000", 16));
  }

  public static SdlInitFlags getSdlInitFlag(SDLFlagValue value) {
    return new SdlInitFlags(sdlFlagMap.get(value));
  }

  public static SdlInitFlags orSdlInitFlag(SDLFlagValue... value) {
    int flag = Integer.parseUnsignedInt("0");
    for (SDLFlagValue i : value) {
      flag = flag | sdlFlagMap.get(i);
    }
    return new SdlInitFlags(flag);
  }

  public static SdlInitFlags andSdlInitFlag(SDLFlagValue... value) {
    Iterator<SDLFlagValue> iterator = Arrays.stream(value).iterator();
    int flag;
    if (iterator.hasNext()) {
      flag = sdlFlagMap.get(iterator.next());
      while (iterator.hasNext()) {
        flag = flag & sdlFlagMap.get(iterator.next());
      }
      return new SdlInitFlags(flag);
    } else {
      throw new IllegalArgumentException("No Flags provided to andSdlInitFlag method!");
    }
  }
}
