package robedpixel.sdl.events;


public enum SdlEventAction {
  /** Add events to the back of the queue. */
  SDL_ADDEVENT,
  /** Check but don't remove events from the queue front. */
  SDL_PEEKEVENT,
  /** Retrieve/remove events from the front of the queue. */
  SDL_GETEVENT
}
