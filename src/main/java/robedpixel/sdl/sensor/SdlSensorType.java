package robedpixel.sdl.sensors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlSensorType {
  /** < Returned for an invalid sensor */
  SDL_SENSOR_INVALID(-1),
  /** < Unknown sensor type */
  SDL_SENSOR_UNKNOWN(0),
  /** < Accelerometer */
  SDL_SENSOR_ACCEL(1),
  /** < Gyroscope */
  SDL_SENSOR_GYRO(2),
  /** < Accelerometer for left Joy-Con controller and Wii nunchuk */
  SDL_SENSOR_ACCEL_L(3),
  /** < Gyroscope for left Joy-Con controller */
  SDL_SENSOR_GYRO_L(4),
  /** < Accelerometer for right Joy-Con controller */
  SDL_SENSOR_ACCEL_R(5),
  /** < Gyroscope for right Joy-Con controller */
  SDL_SENSOR_GYRO_R(6);

  @Getter private final int value;

  SdlSensorType(final int value) {
    this.value = value;
  }

  private static final ImmutableMap<Integer, SdlSensorType> reverseLookup =
      Maps.uniqueIndex(List.of(SdlSensorType.values()), SdlSensorType::getValue);

  public static SdlSensorType fromInt(final int id) {
    return reverseLookup.get(id);
  }
}
