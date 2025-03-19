package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.audio.SdlAudioDeviceId;

public class SdlAudioDeviceEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_BOOLEAN.withName("recording"),
              ValueLayout.JAVA_BYTE.withName("padding1"),
              ValueLayout.JAVA_BYTE.withName("padding2"),
              ValueLayout.JAVA_BYTE.withName("padding3"))
          .withName("SDL_AudioDeviceEvent");

  /**
   * SDL_EVENT_AUDIO_DEVICE_ADDED, or SDL_EVENT_AUDIO_DEVICE_REMOVED, or
   * SDL_EVENT_AUDIO_DEVICE_FORMAT_CHANGED
   */
  @Getter int type;

  @Getter long reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** SDLAudioDeviceId for the device being added or removed or changing */
  @Getter SdlAudioDeviceId which;

  /** false if a playback device, true if a recording device. */
  @Getter boolean recording;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
  private static final VarHandle recordingHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("recording"));

  public static SdlAudioDeviceEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlAudioDeviceEvent retEvent = new SdlAudioDeviceEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlAudioDeviceId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.recording = (boolean) recordingHandle.get(segment, 0);
    return retEvent;
  }
}
