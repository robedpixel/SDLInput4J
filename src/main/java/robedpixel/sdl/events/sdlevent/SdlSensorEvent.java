package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.sensors.SdlSensorId;

public class SdlSensorEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              MemoryLayout.sequenceLayout(6, ValueLayout.JAVA_FLOAT).withName("data"),
              ValueLayout.JAVA_LONG.withName("sensor_timestamp"))
          .withName("SDL_SensorEvent");

  /** SDL_EVENT_SENSOR_UPDATE */
  @Getter int type;

  @Getter long reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The instance ID of the sensor */
  @Getter SdlSensorId which;

  @Getter float[] data;

  /**
   * (Unisgned Int64) The timestamp of the sensor reading in nanoseconds, not necessarily
   * synchronized with the system clock
   */
  @Getter long sensorTimestamp;

  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle reservedHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
  private static final VarHandle timestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
  private static final VarHandle whichHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("which"));
  private static final VarHandle dataHandle =
      objectLayout.varHandle(
          MemoryLayout.PathElement.groupElement("data"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle sensorTimestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("sensor_timestamp"));

  public static SdlSensorEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlSensorEvent retEvent = new SdlSensorEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlSensorId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.data = new float[6];
    for (int i = 0; i < retEvent.data.length; i++) {
      retEvent.data[i] = (float) dataHandle.get(segment, 0, i);
    }
    retEvent.sensorTimestamp = (long) sensorTimestampHandle.get(segment, 0);
    return retEvent;
  }
}
