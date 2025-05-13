package robedpixel.sdl.sensors;


import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlSensorsFuncs {
  private static volatile NativeSdlSensorsFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_GetSensors;
  private final MethodHandle SDL_GetSensorNameForID;
  private final MethodHandle SDL_GetSensorTypeForID;
  private final MethodHandle SDL_GetSensorNonPortableTypeForID;
  private final MethodHandle SDL_OpenSensor;
  private final MethodHandle SDL_GetSensorFromID;
  private final MethodHandle SDL_GetSensorProperties;
  private final MethodHandle SDL_GetSensorName;
  private final MethodHandle SDL_GetSensorType;
  private final MethodHandle SDL_GetSensorNonPortableType;
  private final MethodHandle SDL_GetSensorID;
  private final MethodHandle SDL_GetSensorData;
  private final MethodHandle SDL_CloseSensor;
  private final MethodHandle SDL_UpdateSensors;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlSensorsFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetSensors =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensors").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetSensorNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetSensorTypeForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorTypeForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetSensorNonPortableTypeForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorNonPortableTypeForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_OpenSensor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenSensor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetSensorFromID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorFromID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetSensorProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetSensorName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetSensorType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetSensorNonPortableType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorNonPortableType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetSensorID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetSensorData =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetSensorData").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
    SDL_CloseSensor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CloseSensor").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    SDL_UpdateSensors =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UpdateSensors").orElseThrow(), FunctionDescriptor.ofVoid());
  }
  @Nullable
  public SdlSensorIdArray getSensors() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetSensors.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        temp = temp.reinterpret(arraySize * ValueLayout.JAVA_INT.byteSize());
        return new SdlSensorIdArray(temp, arraySize);
      }
    }
  }
  @Nullable
  public synchronized String getSensorNameForId(int instanceId) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetSensorNameForID.invoke(instanceId);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getSensorTypeForId(int instanceId) throws Throwable {
    return (int) SDL_GetSensorTypeForID.invoke(instanceId);
  }

  public synchronized int getSensorNonPortableTypeForId(int instanceId) throws Throwable {
    return (int) SDL_GetSensorNonPortableTypeForID.invoke(instanceId);
  }

  public synchronized MemorySegment openSensor(int instanceId) throws Throwable {
    return (MemorySegment) SDL_OpenSensor.invoke(instanceId);
  }

  public synchronized MemorySegment getSensorFromId(int instanceId) throws Throwable {
    return (MemorySegment) SDL_GetSensorFromID.invoke(instanceId);
  }

  public synchronized int getSensorProperties(MemorySegment sensor) throws Throwable {
    return (int) SDL_GetSensorProperties.invoke(sensor);
  }
  @Nullable
  public synchronized String getSensorName(MemorySegment sensor) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetSensorName.invoke(sensor);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getSensorType(MemorySegment sensor) throws Throwable {
    return (int) SDL_GetSensorType.invoke(sensor);
  }

  public synchronized int getSensorNonPortableType(MemorySegment sensor) throws Throwable {
    return (int) SDL_GetSensorNonPortableType.invoke(sensor);
  }

  public synchronized int getSensorId(MemorySegment sensor) throws Throwable {
    return (int) SDL_GetSensorID.invoke(sensor);
  }

  public synchronized boolean getSensorData(MemorySegment sensor, MemorySegment data, int numValues)
      throws Throwable {
    return (boolean) SDL_GetSensorData.invoke(sensor, data, numValues);
  }

  public synchronized void closeSensor(MemorySegment sensor) throws Throwable {
    SDL_CloseSensor.invoke(sensor);
  }

  public synchronized void updateSensors() throws Throwable {
    SDL_UpdateSensors.invoke();
  }
@NonNull
  public static NativeSdlSensorsFuncs getInstance(Arena allocator) {
    NativeSdlSensorsFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlSensorsFuncs(allocator);
      }
    }
    return result;
  }
}
