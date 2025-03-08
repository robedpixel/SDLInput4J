package robedpixel.sdl.sensors;


import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class SdlSensor {
    private final NativeSdlSensorsFuncs SdlFuncs;
    public SdlSensor(Arena allocator){
        SdlFuncs = NativeSdlSensorsFuncs.getInstance(allocator);
    }

    /**
     * Get a list of currently connected sensors.
     * @param count Number of sensors to retrieve
     * @return Array containing number of sensors retrieved, elements may be null if there are less sensors than amount requested
     * @throws Throwable
     */
    public SdlSensorId[] getSensor(int count) throws Throwable {
        SdlSensorId[] sensorArray = new SdlSensorId[count];
        MemorySegment intArray = SdlFuncs.getSensors(count);
        for (int i = 0;i<count;i++){
            int arrayValue = intArray.get(ValueLayout.JAVA_INT,i);
            if (arrayValue == 0){
                break;
            }
            SdlSensorId sensorId = new SdlSensorId();
            sensorId.setValue(arrayValue);
            sensorArray[i] = sensorId;
        }
        return sensorArray;
    }

    /**
     * Get the implementation dependent name of a sensor.
     * @param instanceId The sensor instance ID
     * @return Returns the sensor name, or null if instance_id is not valid.
     * @throws Throwable
     */
    public String getSensorNameForId(SdlSensorId instanceId) throws Throwable {
        return SdlFuncs.getSensorNameForId(instanceId.getValue());
    }

    /**
     * Get the type of sensor
     * @param instanceId The sensor instance ID
     * @return Returns the SensorType , or SDL_SENSOR_INVALID if instance_id is not valid.
     * @throws Throwable
     */
    public SdlSensorType getSensorTypeForId (SdlSensorId instanceId) throws Throwable{
        return SdlSensorType.fromInt(SdlFuncs.getSensorTypeForId(instanceId.getValue()));
    }

    /**
     * Get the platform dependent type of a sensor.
     * @param instanceId The sensor instance ID
     * @return Returns the sensor platform dependent type, or -1 if instance_id is not valid.
     * @throws Throwable
     */
    public int getSensorNonPortableTypeForId(SdlSensorId instanceId) throws Throwable{
        return SdlFuncs.getSensorNonPortableTypeForId(instanceId.getValue());
    }

    /**
     * Open a sensor for use.
     * @param instanceId The sensor instance ID
     * @return Returns an SDL_Sensor object or NULL on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public SdlSensorDevice openSensor(SdlSensorId instanceId) throws Throwable {
        MemorySegment address = SdlFuncs.openSensor(instanceId.getValue());
        if (address == MemorySegment.NULL){
            return null;
        }else {
            return new SdlSensorDevice(SdlFuncs.openSensor(instanceId.getValue()));
        }
    }
    public SdlSensorDevice getSensorFromId(SdlSensorId instanceId) throws Throwable{
        return new SdlSensorDevice(SdlFuncs.getSensorFromId(instanceId.getValue()));
    }
}
