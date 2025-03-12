package robedpixel.sdl.properties;

import lombok.Getter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public abstract class SdlPropertyCleanupCallback implements AutoCloseable{
    @Getter
    private final Arena callbackAllocator = Arena.ofConfined();
    @Getter
    private final MemorySegment userData;
    @Getter
    MemorySegment callbackAddress;

    public SdlPropertyCleanupCallback(MemorySegment userData){
        this.userData = userData;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS, ValueLayout.ADDRESS);
        MethodHandle callbackHandle;
        try {
            callbackHandle = MethodHandles.publicLookup().bind(this,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating callback handle", e);
        }
        callbackAddress = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                callbackAllocator);
    }

    /**
     * @param userData An app-defined pointer passed to the callback.
     * @param value The pointer assigned to the property to clean up.
     */
    void callback(MemorySegment userData,MemorySegment value) {

    }
    @Override
    public void close(){
        callbackAllocator.close();
    }
}

