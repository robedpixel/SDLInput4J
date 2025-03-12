package robedpixel.sdl.properties;

import lombok.Getter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class SdlEnumeratePropertiesCallback implements AutoCloseable{
    @Getter
    private final Arena callbackAllocator = Arena.ofConfined();
    @Getter
    private final MemorySegment userData;
    @Getter
    MemorySegment callbackAddress;

    public SdlEnumeratePropertiesCallback(MemorySegment userData){
        this.userData = userData;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS);
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
     * @param props The SdlPropertiesId that is being enumerated.
     * @param name The next property name in the enumeration.
     */
    void callback(MemorySegment userData,int props, MemorySegment name) {

    }
    @Override
    public void close(){
        callbackAllocator.close();
    }
}
