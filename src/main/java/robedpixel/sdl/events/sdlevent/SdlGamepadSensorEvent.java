package robedpixel.sdl.events.sdlevent;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.joystick.SdlJoystickId;

public class SdlGamepadSensorEvent {
  public static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("type"),
              ValueLayout.JAVA_INT.withName("reserved"),
              ValueLayout.JAVA_LONG.withName("timestamp"),
              ValueLayout.JAVA_INT.withName("which"),
              ValueLayout.JAVA_INT.withName("sensor"),
              MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_FLOAT).withName("data"),
              ValueLayout.JAVA_LONG.withName("sensor_timestamp"))
          .withName("SDL_GamepadSensorEvent");

  /** SDL_EVENT_GAMEPAD_SENSOR_UPDATE */
  @Getter int type;

  @Getter int reserved;

  /** (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS() */
  @Getter long timestamp;

  /** The joystick instance id */
  @Getter SdlJoystickId which;

  /** The type of the sensor, one of the values of SDL_SensorType */
  @Getter int sensor;

  /** Up to 3 values from the sensor, as defined in SDL_sensor.h */
  @Getter float[] data;

  /**
   * The timestamp of the sensor reading in nanoseconds, not necessarily synchronized with the
   * system clock
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
  private static final VarHandle sensorHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("sensor"));
  private static final VarHandle dataHandle =
      objectLayout.varHandle(
          MemoryLayout.PathElement.groupElement("data"),
          MemoryLayout.PathElement.sequenceElement());
  private static final VarHandle sensorTimestampHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("sensor_timestamp"));

  public static SdlGamepadSensorEvent getEventFromMemorySegment(MemorySegment segment) {
    SdlGamepadSensorEvent retEvent = new SdlGamepadSensorEvent();
    retEvent.type = (int) typeHandle.get(segment, 0);
    retEvent.reserved = (int) reservedHandle.get(segment, 0);
    retEvent.timestamp = (long) timestampHandle.get(segment, 0);
    retEvent.which = new SdlJoystickId();
    retEvent.which.setValue((int) whichHandle.get(segment, 0));
    retEvent.sensor = (int) sensorHandle.get(segment, 0);
    retEvent.data = new float[3];
    for (int i = 0; i < 3; i++) {
      retEvent.data[i] = (float) dataHandle.get(segment, 0, i);
    }
    retEvent.sensorTimestamp = (long) sensorTimestampHandle.get(segment, 0);
    return retEvent;
  }
}
