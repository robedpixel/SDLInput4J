package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

public class SdlGamepadDeviceEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"))
          .withName("SDL_GamepadDeviceEvent");

  /**
   * SDL_EVENT_GAMEPAD_ADDED, SDL_EVENT_GAMEPAD_REMOVED, or SDL_EVENT_GAMEPAD_REMAPPED,
   * SDL_EVENT_GAMEPAD_UPDATE_COMPLETE or SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED
   */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The joystick instance id */
  @Getter SdlJoystickId which;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));

  public static SdlGamepadDeviceEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlGamepadDeviceEvent retEvent = new SdlGamepadDeviceEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlJoystickId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    return retEvent;
  }
}
