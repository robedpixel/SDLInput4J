package robedpixel.sdl.events;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import robedpixel.sdl.events.sdlevent.SdlEvent;
import robedpixel.sdl.video.SdlWindowId;

class NativeEventsFuncs {
  private static volatile NativeEventsFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_PumpEvents;
  private final MethodHandle SDL_PeepEvents;
  private final MethodHandle SDL_HasEvent;
  private final MethodHandle SDL_HasEvents;
  private final MethodHandle SDL_FlushEvent;
  private final MethodHandle SDL_FlushEvents;
  private final MethodHandle SDL_PollEvent;
  private final MethodHandle SDL_WaitEvent;
  private final MethodHandle SDL_WaitEventTimeout;
  private final MethodHandle SDL_PushEvent;
  private final MethodHandle SDL_SetEventFilter;
  private final MethodHandle SDL_GetEventFilter;
  private final MethodHandle SDL_AddEventWatch;
  private final MethodHandle SDL_RemoveEventWatch;
  private final MethodHandle SDL_FilterEvents;
  private final MethodHandle SDL_SetEventEnabled;
  private final MethodHandle SDL_EventEnabled;
  private final MethodHandle SDL_RegisterEvents;
  private final MethodHandle SDL_GetWindowFromEvent;
  private final MethodHandle SDL_GetEventDescription;

  public NativeEventsFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_PumpEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PumpEvents").orElseThrow(), FunctionDescriptor.ofVoid());

    SDL_PeepEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PeepEvents").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT));
    SDL_HasEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasEvent").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_HasEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasEvents").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_FlushEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_FlushEvent").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
    SDL_FlushEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_FlushEvents").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_PollEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PollEvent").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_WaitEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_WaitEvent").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_WaitEventTimeout =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_WaitEventTimeout").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_PushEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PushEvent").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_SetEventFilter =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetEventFilter").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetEventFilter =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetEventFilter").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_AddEventWatch =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_AddEventWatch").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_RemoveEventWatch =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_RemoveEventWatch").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_FilterEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_FilterEvents").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_SetEventEnabled =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetEventEnabled").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT, ValueLayout.JAVA_BOOLEAN));
    SDL_EventEnabled =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_EventEnabled").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_RegisterEvents =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_RegisterEvents").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetWindowFromEvent =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetWindowFromEvent").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetEventDescription =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetEventDescription").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
  }

  public void pumpEvents() throws Throwable {
    SDL_PumpEvents.invoke();
  }

  public int peepEvents(
      SdlEventBuffer event, int numEvents, SdlEventAction action, int minType, int maxType)
      throws Throwable {
    if (event == null) {
      return (int)
          SDL_PeepEvents.invoke(MemorySegment.NULL, numEvents, action.ordinal(), minType, maxType);
    }
    return (int)
        SDL_PeepEvents.invoke(event.getBuffer(), numEvents, action.ordinal(), minType, maxType);
  }

  public boolean hasEvent(SdlEventType type) throws Throwable {
    return (boolean) SDL_HasEvent.invoke(type.ordinal());
  }

  public boolean hasEvents(int minType, int maxType) throws Throwable {
    return (boolean) SDL_HasEvents.invoke(minType, maxType);
  }

  public void flushEvent(int type) throws Throwable {
    SDL_FlushEvent.invoke(type);
  }

  public void flushEvents(int minType, int maxType) throws Throwable {
    SDL_FlushEvents.invoke(minType, maxType);
  }

  public boolean pollEvent(SdlEvent event) throws Throwable {
    if (event == null) {
      return (boolean) SDL_PollEvent.invoke(MemorySegment.NULL);
    }
    return (boolean) SDL_PollEvent.invoke(event.getSegment());
  }

  public boolean waitEvent(SdlEvent event) throws Throwable {
    if (event == null) {
      return (boolean) SDL_WaitEvent.invoke(MemorySegment.NULL);
    }
    return (boolean) SDL_WaitEvent.invoke(event.getSegment());
  }

  public boolean waitEventTimeout(SdlEvent event, int timeoutMs) throws Throwable {
    if (event == null) {
      return (boolean) SDL_WaitEventTimeout.invoke(MemorySegment.NULL, timeoutMs);
    }
    return (boolean) SDL_WaitEventTimeout.invoke(event.getSegment(), timeoutMs);
  }

  public boolean pushEvent(SdlEvent event) throws Throwable {
    return (boolean) SDL_PushEvent.invoke(event.getSegment());
  }

  public void setEventFilter(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    SDL_SetEventFilter.invoke(filter.getCallbackAddress(), userdata);
  }

  public boolean getEventFilter(MemorySegment sdlEventFilterPointer, MemorySegment userdataPointer)
      throws Throwable {
    return (boolean) SDL_GetEventFilter.invoke(sdlEventFilterPointer, userdataPointer);
  }

  public boolean addEventWatch(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    return (boolean) SDL_AddEventWatch.invoke(filter, userdata);
  }

  public void removeEventWatch(SdlEventFilterCallback filter, MemorySegment userdata)
      throws Throwable {
    SDL_RemoveEventWatch.invoke(filter, userdata);
  }

  public void filterEvents(SdlEventFilterCallback filter, MemorySegment userdata) throws Throwable {
    SDL_FilterEvents.invoke(filter, userdata);
  }

  public void setEventEnabled(int type, boolean enabled) throws Throwable {
    SDL_SetEventEnabled.invoke(type, enabled);
  }

  public boolean eventEnabled(int type) throws Throwable {
    return (boolean) SDL_EventEnabled.invoke(type);
  }

  public int registerEvents(int numEvents) throws Throwable {
    return (int) SDL_RegisterEvents.invoke(numEvents);
  }

  public SdlWindowId getWindowFromEvent(SdlEvent event) throws Throwable {
    Object object = SDL_GetWindowFromEvent.invoke(event.getSegment());
    MemorySegment segment = (MemorySegment) object;
    if (segment == MemorySegment.NULL) {
      return null;
    }
    SdlWindowId window = new SdlWindowId();
    window.setValue((int) object);
    return window;
  }

  public int getEventDescription(SdlEvent event, MemorySegment buf, int bufLen) throws Throwable {
    return (int) SDL_GetEventDescription.invoke(event, buf, bufLen);
  }

  public static NativeEventsFuncs getInstance(Arena allocator) {
    NativeEventsFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeEventsFuncs(allocator);
      }
    }
    return result;
  }
}
