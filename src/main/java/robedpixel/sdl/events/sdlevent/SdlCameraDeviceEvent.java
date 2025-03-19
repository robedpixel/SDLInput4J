package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.audio.SdlAudioDeviceId;
import robedpixel.sdl.camera.SdlCameraId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

public class SdlCameraDeviceEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                    ValueLayout.JAVA_INT.withName("type"),
                    ValueLayout.JAVA_INT.withName("reserved"),
                    ValueLayout.JAVA_LONG.withName("timestamp"),
                    ValueLayout.JAVA_INT.withName("which")
            ).withName("SDL_CameraDeviceEvent");
    /**
     * SDL_EVENT_CAMERA_DEVICE_ADDED, SDL_EVENT_CAMERA_DEVICE_REMOVED, SDL_EVENT_CAMERA_DEVICE_APPROVED, SDL_EVENT_CAMERA_DEVICE_DENIED
     */
    @Getter
    int type;
    @Getter long reserved;
    /**
     * (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS()
     */
    @Getter long timestamp;
    /**
     * SdlCameraId for the device being added or removed or changing
     */
    @Getter
    SdlCameraId which;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle whichHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
    public static SdlCameraDeviceEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlCameraDeviceEvent retEvent = new SdlCameraDeviceEvent();
        retEvent.type = (int) typeHandle.get(segment, 0);
        retEvent.reserved = (int) reservedHandle.get(segment, 0);
        retEvent.timestamp = (long) timestampHandle.get(segment, 0);
        retEvent.which = new SdlCameraId();
        retEvent.which.setValue((int) whichHandle.get(segment, 0));
        return retEvent;
    }
}
