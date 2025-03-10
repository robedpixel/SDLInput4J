package robedpixel.sdl.sensors;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import lombok.Getter;

public enum SdlSensorType {
  SDL_SENSOR_INVALID(-1),
  /** < Returned for an invalid sensor */
  SDL_SENSOR_UNKNOWN(0),
  /** < Unknown sensor type */
  SDL_SENSOR_ACCEL(1),
  /** < Accelerometer */
  SDL_SENSOR_GYRO(2),
  /** < Gyroscope */
  SDL_SENSOR_ACCEL_L(3),
  /** < Accelerometer for left Joy-Con controller and Wii nunchuk */
  SDL_SENSOR_GYRO_L(4),
  /** < Gyroscope for left Joy-Con controller */
  SDL_SENSOR_ACCEL_R(5),
  /** < Accelerometer for right Joy-Con controller */
  SDL_SENSOR_GYRO_R(6);

  /** < Gyroscope for right Joy-Con controller */
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
