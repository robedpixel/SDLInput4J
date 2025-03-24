package robedpixel.sdl.keyboard;

// TODO: fill with SDL3 enums
public class SdlKeycode {
  public static int SDLK_EXTENDED_MASK = (1 << 29);
  public static int SDLK_SCANCODE_MASK = (1 << 30);

  public static int SDL_SCANCODE_TO_KEYCODE(int x) {
    return x | SDLK_EXTENDED_MASK;
  }

  /** < 0 */
  public static int SDLK_UNKNOWN = 0x00000000;
}
