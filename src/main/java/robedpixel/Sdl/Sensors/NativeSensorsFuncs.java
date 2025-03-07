package robedpixel.Sdl.Sensors;

import robedpixel.Sdl.Power.NativePowerFuncs;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSensorsFuncs {
    private static volatile NativePowerFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GetSensors;
    private final MethodHandle SDL_GetSensorNameForID;
    private final Arena objectAllocator = Arena.ofAuto();
    private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
    private final MemorySegment tempFloatAddress = objectAllocator.allocate(ValueLayout.JAVA_FLOAT);
    private final MemorySegment tempSensorAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
    public NativeSensorsFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GetSensors = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetSensors").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS)
        );
        SDL_GetSensorNameForID = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetSensorNameForID").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT)
        );
    }
    public synchronized int getSensors(int count) throws Throwable {
        tempIntAddress.set(ValueLayout.JAVA_INT,0,count);
        return (int)SDL_GetSensors.invoke(tempIntAddress);
    }
    public synchronized String getSensorNameForId(int instanceId) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment) SDL_GetSensorNameForID.invoke(instanceId);
        return charArrayAddress.getString(0);
    }
}
