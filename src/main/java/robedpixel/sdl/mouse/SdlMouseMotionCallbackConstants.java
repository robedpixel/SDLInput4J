package robedpixel.sdl.mouse;

public class SdlMouseMotionCallbackConstants {
  public static final int SDL_BUTTON_LEFT = 1;
  public static final int SDL_BUTTON_MIDDLE = 2;
  public static final int SDL_BUTTON_RIGHT = 3;
  public static final int SDL_BUTTON_X1 = 4;
  public static final int SDL_BUTTON_X2 = 5;

  public static int SDL_BUTTON_MASK(int x) {
    return (1 << ((x) - 1));
  }

  public static final int SDL_BUTTON_LMASK = SDL_BUTTON_MASK(SDL_BUTTON_LEFT);
  public static final int SDL_BUTTON_MMASK = SDL_BUTTON_MASK(SDL_BUTTON_MIDDLE);
  public static final int SDL_BUTTON_RMASK = SDL_BUTTON_MASK(SDL_BUTTON_RIGHT);
  public static final int SDL_BUTTON_X1MASK = SDL_BUTTON_MASK(SDL_BUTTON_X1);
  public static final int SDL_BUTTON_X2MASK = SDL_BUTTON_MASK(SDL_BUTTON_X2);
}
