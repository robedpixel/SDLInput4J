package robedpixel.sdl.events;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import robedpixel.sdl.CharBuffer;
import robedpixel.sdl.events.sdlevent.SdlEvent;
import robedpixel.sdl.video.SdlWindowId;

public class SdlEvents {
  private final NativeEventsFuncs sdlFuncs;

  public SdlEvents(Arena allocator) {
    sdlFuncs = NativeEventsFuncs.getInstance(allocator);
  }

  /**
   * Pump the event loop, gathering events from the input devices.
   *
   * @throws Throwable
   */
  public void pumpEvents() throws Throwable {
    sdlFuncs.pumpEvents();
  }

  /**
   * Check the event queue for messages and optionally return them.
   *
   * @param event Destination buffer for the retrieved events, may be null to leave the events in
   *     the queue and return the number of events that would have been stored.
   * @param numEvents If action is SDL_ADDEVENT, the number of events to add back to the event
   *     queue; if action is SDL_PEEKEVENT or SDL_GETEVENT, the maximum number of events to
   *     retrieve.
   * @param action Action to take;
   * @param minType Minimum value of the event type to be considered; SDL_EVENT_FIRST is a safe
   *     choice.
   * @param maxType Maximum value of the event type to be considered; SDL_EVENT_LAST is a safe
   *     choice.
   * @return Returns the number of events actually stored or -1 on failure; call
   *     SdlError.getError()) for more information.
   * @throws Throwable
   */
  public int peepEvents(
      SdlEventBuffer event, int numEvents, SdlEventAction action, int minType, int maxType)
      throws Throwable {
    return sdlFuncs.peepEvents(event, numEvents, action, minType, maxType);
  }

  /**
   * Check for the existence of a certain event type in the event queue.
   *
   * @param type The type of event to be queried; see SDLEventType for details.
   * @return Returns true if events matching type are present, or false if events matching type are
   *     not present.
   * @throws Throwable
   */
  public boolean hasEvent(SdlEventType type) throws Throwable {
    return sdlFuncs.hasEvent(type);
  }

  /**
   * Check for the existence of certain event types in the event queue.
   *
   * @param minType The low end of event type to be queried, inclusive; see SDLEventType for
   *     details.
   * @param maxType The high end of event type to be queried, inclusive; see SDLEventType for
   *     details.
   * @return
   * @throws Throwable
   */
  public boolean hasEvents(SdlEventType minType, SdlEventType maxType) throws Throwable {
    return sdlFuncs.hasEvents(minType.ordinal(), maxType.ordinal());
  }

  /**
   * Clear events of a specific type from the event queue.
   *
   * @param type The type of event to be cleared; see SDLEventType for details.
   * @throws Throwable
   */
  public void flushEvent(SdlEventType type) throws Throwable {
    sdlFuncs.flushEvent(type.ordinal());
  }

  /**
   * Clear events of a range of types from the event queue.
   *
   * @param minType The low end of event type to be cleared, inclusive; see SDLEventType for
   *     details.
   * @param maxType The high end of event type to be cleared, inclusive; see SDLEventType for
   *     details.
   * @throws Throwable
   */
  public void flushEvents(SdlEventType minType, SdlEventType maxType) throws Throwable {
    sdlFuncs.flushEvents(minType.ordinal(), maxType.ordinal());
  }

  /**
   * Poll for currently pending events.
   *
   * @param event The SDL_Event structure to be filled with the next event from the queue, or null.
   * @return Returns true if this got an event or false if there are none available.
   * @throws Throwable
   */
  public boolean pollEvent(SdlEvent event) throws Throwable {
    return sdlFuncs.pollEvent(event);
  }

  /**
   * Wait indefinitely for the next available event.
   *
   * @param event The SDLEvent structure to be filled in with the next event from the queue, or
   *     null.
   * @return Returns true on success or false if there was an error while waiting for events; call
   *     SDLError.GetError() for more information.
   * @throws Throwable
   */
  public boolean waitEvent(SdlEvent event) throws Throwable {
    return sdlFuncs.waitEvent(event);
  }

  /**
   * Wait until the specified timeout (in milliseconds) for the next available event.
   *
   * @param event The SDL_Event structure to be filled in with the next event from the queue, or
   *     null.
   * @param timeoutMs The maximum number of milliseconds to wait for the next available event.
   * @return Returns true if this got an event or false if the timeout elapsed without any events
   *     available.
   * @throws Throwable
   */
  public boolean waitEventTimeout(SdlEvent event, int timeoutMs) throws Throwable {
    return sdlFuncs.waitEventTimeout(event, timeoutMs);
  }

  /**
   * Add an event to the event queue.
   *
   * @param event The SDL_Event to be added to the queue.
   * @return Returns true on success, false if the event was filtered or on failure; call
   *     SDLError.GetError() for more information. A common reason for error is the event queue
   *     being full.
   * @throws Throwable
   */
  public boolean pushEvent(SdlEvent event) throws Throwable {
    return sdlFuncs.pushEvent(event);
  }

  /**
   * Set up a filter to process all events before they are added to the internal event queue.
   *
   * @param filter An SDLEventFilterCallback function to call when an event happens.
   * @param userdata A pointer that is passed to filter.
   * @return
   * @throws Throwable
   */
  public void setEventFilter(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    sdlFuncs.setEventFilter(filter, userdata);
  }

  /**
   * Query the current event filter.
   *
   * @param sdlEventFilterCallbackPointer The current callback function will be stored here.
   * @param userdataPointer The pointer that is passed to the current event filter will be stored
   *     here.
   * @return Returns true on success or false if there is no event filter set.
   * @throws Throwable
   */
  public boolean getEventFilter(
      MemorySegment sdlEventFilterCallbackPointer, MemorySegment userdataPointer) throws Throwable {
    return sdlFuncs.getEventFilter(sdlEventFilterCallbackPointer, userdataPointer);
  }

  /**
   * Add a callback to be triggered when an event is added to the event queue.
   *
   * @param filter An SDLEventFilterCallback function to call when an event happens
   * @param userdata A pointer that is passed to filter.
   * @return Returns true on success or false on failure; call SDLError.GetError() for more
   *     information.
   * @throws Throwable
   */
  public boolean addEventWatch(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    return sdlFuncs.addEventWatch(filter, userdata);
  }

  /**
   * Remove an event watch callback added with addEventWatch().
   *
   * @param filter The function originally passed to addEventWatch().
   * @param userdata The pointer originally passed to addEventWatch().
   * @throws Throwable
   */
  public void removeEventWatch(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    sdlFuncs.removeEventWatch(filter, userdata);
  }

  /**
   * Run a specific filter function on the current event queue, removing any events for which the
   * filter returns false.
   *
   * @param filter The SDL_EventFilter function to call when an event happens.
   * @param userdata A pointer that is passed to filter.
   * @return
   * @throws Throwable
   */
  public void filterEvents(SdlEventFilterCallback filter, MemorySegment userdata) throws Throwable {
    sdlFuncs.filterEvents(filter, userdata);
  }

  /**
   * Set the state of processing events by type.
   *
   * @param type The type of event; see SDLEventType for details.
   * @param enabled Whether to process the event or not.
   * @throws Throwable
   */
  public void setEventEnabled(SdlEventType type, boolean enabled) throws Throwable {
    sdlFuncs.setEventEnabled(type.ordinal(), enabled);
  }

  /**
   * Query the state of processing events by type.
   *
   * @param type The type of event; see SDLEventType for details.
   * @return Returns true if the event is being processed, false otherwise.
   * @throws Throwable
   */
  public boolean eventEnabled(SdlEventType type) throws Throwable {
    return sdlFuncs.eventEnabled(type.ordinal());
  }

  /**
   * Allocate a set of user-defined events, and return the beginning event number for that set of
   * events.
   *
   * @param numEvents The number of events to be allocated.
   * @return Returns the beginning event number, or 0 if numevents is invalid or if there are not
   *     enough user-defined events left.
   * @throws Throwable
   */
  public int registerEvents(int numEvents) throws Throwable {
    return sdlFuncs.registerEvents(numEvents);
  }

  /**
   * Get window associated with an event.
   *
   * @param event An event containing a windowId.
   * @return Returns the associated window on success or null if there is none.
   * @throws Throwable
   */
  public SdlWindowId getWindowFromEvent(SdlEvent event) throws Throwable {
    return sdlFuncs.getWindowFromEvent(event);
  }

  /**
   * Generate a human-readable description of an event.
   *
   * @param event An event to describe. May be NULL.
   * @param buf The buffer to fill with the description string. May be NULL.
   * @return Returns number of bytes needed for the full string, not counting the null-terminator
   *     byte.
   * @throws Throwable
   */
  public int getEventDescription(SdlEvent event, CharBuffer buf) throws Throwable {
    return sdlFuncs.getEventDescription(event, buf.getSegment(), buf.getLength());
  }
}
