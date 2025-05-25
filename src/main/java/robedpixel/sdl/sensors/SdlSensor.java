package robedpixel.sdl.sensors;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import robedpixel.sdl.properties.SdlPropertiesId;

public class SdlSensor {
  private final NativeSdlSensorsFuncs SdlFuncs;

  public SdlSensor(Arena allocator) {
    SdlFuncs = NativeSdlSensorsFuncs.getInstance(allocator);
  }

  /**
   * Get a list of currently connected sensors.
   *
   * @return Returns an array of sensor instance IDs or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  @Nullable
  public SdlSensorIdArray getSensors() throws Throwable {
    return SdlFuncs.getSensors();
  }

  /**
   * Get the implementation dependent name of a sensor.
   *
   * @param instanceId The sensor instance ID
   * @return Returns the sensor name, or null if instance_id is not valid.
   * @throws Throwable
   */
  @Nullable
  public String getSensorNameForId(SdlSensorId instanceId) throws Throwable {
    return SdlFuncs.getSensorNameForId(instanceId.getValue());
  }

  /**
   * Get the type of sensor
   *
   * @param instanceId The sensor instance ID
   * @return Returns the SensorType , or SDL_SENSOR_INVALID if instance_id is not valid.
   * @throws Throwable
   */
  @NonNull
  public SdlSensorType getSensorTypeForId(SdlSensorId instanceId) throws Throwable {
    return SdlSensorType.fromInt(SdlFuncs.getSensorTypeForId(instanceId.getValue()));
  }

  /**
   * Get the platform dependent type of a sensor.
   *
   * @param instanceId The sensor instance ID
   * @return Returns the sensor platform dependent type, or -1 if instance_id is not valid.
   * @throws Throwable
   */
  public int getSensorNonPortableTypeForId(SdlSensorId instanceId) throws Throwable {
    return SdlFuncs.getSensorNonPortableTypeForId(instanceId.getValue());
  }

  /**
   * Open a sensor for use.
   *
   * @param instanceId The sensor instance ID
   * @return Returns an SDL_Sensor object or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  @Nullable
  public SdlSensorDevice openSensor(SdlSensorId instanceId) throws Throwable {
    MemorySegment address = SdlFuncs.openSensor(instanceId.getValue());
    if (address == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlSensorDevice(address, SdlFuncs);
    }
  }

  /**
   * Get the properties associated with a sensor.
   *
   * @param sensor The SdlSensorDevice object
   * @return Returns a valid property ID on success or 0 on failure; call sdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  @NonNull
  public SdlPropertiesId getSensorProperties(SdlSensorDevice sensor) throws Throwable {
    SdlPropertiesId returnedObject = new SdlPropertiesId();
    returnedObject.setValue(SdlFuncs.getSensorProperties(sensor.getAddress()));
    return returnedObject;
  }

  /**
   * Get the implementation dependent name of a sensor.
   *
   * @param sensor The SdlSensorDevice object
   * @return Returns the sensor name or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  @Nullable
  public String getSensorName(SdlSensorDevice sensor) throws Throwable {
    return SdlFuncs.getSensorName(sensor.getAddress());
  }

  /**
   * Get the type of a sensor.
   *
   * @param sensor The SdlSensorDevice object to inspect
   * @return Returns the SDLSensorType type, or SDL_SENSOR_INVALID if sensor is NULL.
   * @throws Throwable
   */
  public SdlSensorType getSensorType(SdlSensorDevice sensor) throws Throwable {
    return SdlSensorType.fromInt(SdlFuncs.getSensorType(sensor.getAddress()));
  }

  /**
   * Get the platform dependent type of a sensor.
   *
   * @param sensor The SdlSensorDevice object to inspect
   * @return Returns the sensor platform dependent type, or -1 if sensor is NULL.
   * @throws Throwable
   */
  public int getSensorNonPortableType(SdlSensorDevice sensor) throws Throwable {
    return SdlFuncs.getSensorNonPortableType(sensor.getAddress());
  }

  /**
   * Get the instance ID of a sensor.
   *
   * @param sensor The SdlSensorDevice object to inspect
   * @return Returns the sensor instance ID, or 0 on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public SdlSensorId getSensorId(SdlSensorDevice sensor) throws Throwable {
    SdlSensorId returnedObject = new SdlSensorId();
    returnedObject.setValue(SdlFuncs.getSensorId(sensor.getAddress()));
    return returnedObject;
  }

  /**
   * Get the current state of an opened sensor.
   *
   * @param sensor The SDL_Sensor object to query.
   * @param dataSnapshot SdlSensorDataSnapshot object to fill with data
   * @param numValues The number of values to write to the dataSnapshot
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean getSensorData(
      SdlSensorDevice sensor, SdlSensorDataSnapshot dataSnapshot, int numValues) throws Throwable {
    if (dataSnapshot.getData().length < numValues) {
      throw new IllegalArgumentException("Requested more data than size of snapshot!");
    }
    boolean result = false;
    try (Arena arena = Arena.ofConfined()) {
      result =
          SdlFuncs.getSensorData(sensor.getAddress(), dataSnapshot.getDataAddress(), numValues);
      if (result == true) {
        for (int i = 0; i < numValues; i++) {
          dataSnapshot.getData()[i] = dataSnapshot.getDataAddress().get(ValueLayout.JAVA_FLOAT, i);
        }
      }
    }
    return result;
  }

  /**
   * Update the current state of the open sensors.
   *
   * @throws Throwable
   */
  public void updateSensors() throws Throwable {
    SdlFuncs.updateSensors();
  }
}
