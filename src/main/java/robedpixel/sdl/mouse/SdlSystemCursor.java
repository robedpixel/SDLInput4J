package robedpixel.sdl.mouse;

public enum SdlSystemCursor {
  /** Default cursor. Usually an arrow. */
  SDL_SYSTEM_CURSOR_DEFAULT,
  /** Text selection. Usually an I-beam. */
  SDL_SYSTEM_CURSOR_TEXT,
  /** Wait. Usually an hourglass or watch or spinning ball. */
  SDL_SYSTEM_CURSOR_WAIT,
  /** Crosshair. */
  SDL_SYSTEM_CURSOR_CROSSHAIR,
  /** Program is busy but still interactive. Usually it's WAIT with an arrow. */
  SDL_SYSTEM_CURSOR_PROGRESS,
  /** Double arrow pointing northwest and southeast. */
  SDL_SYSTEM_CURSOR_NWSE_RESIZE,
  /** Double arrow pointing northeast and southwest. */
  SDL_SYSTEM_CURSOR_NESW_RESIZE,
  /** Double arrow pointing west and east. */
  SDL_SYSTEM_CURSOR_EW_RESIZE,
  /** Double arrow pointing north and south. */
  SDL_SYSTEM_CURSOR_NS_RESIZE,
  /** Four pointed arrow pointing north, south, east, and west. */
  SDL_SYSTEM_CURSOR_MOVE,
  /** Not permitted. Usually a slashed circle or crossbones. */
  SDL_SYSTEM_CURSOR_NOT_ALLOWED,
  /** Pointer that indicates a link. Usually a pointing hand. */
  SDL_SYSTEM_CURSOR_POINTER,
  /** Window resize top-left. This may be a single arrow or a double arrow like NWSE_RESIZE. */
  SDL_SYSTEM_CURSOR_NW_RESIZE,
  /** Window resize top. May be NS_RESIZE. */
  SDL_SYSTEM_CURSOR_N_RESIZE,
  /** Window resize top-right. May be NESW_RESIZE. */
  SDL_SYSTEM_CURSOR_NE_RESIZE,
  /** Window resize right. May be EW_RESIZE. */
  SDL_SYSTEM_CURSOR_E_RESIZE,
  /** Window resize bottom-right. May be NWSE_RESIZE. */
  SDL_SYSTEM_CURSOR_SE_RESIZE,
  /** Window resize bottom. May be NS_RESIZE. */
  SDL_SYSTEM_CURSOR_S_RESIZE,
  /** Window resize bottom-left. May be NESW_RESIZE. */
  SDL_SYSTEM_CURSOR_SW_RESIZE,
  /** Window resize left. May be EW_RESIZE. */
  SDL_SYSTEM_CURSOR_W_RESIZE,
  SDL_SYSTEM_CURSOR_COUNT
}
