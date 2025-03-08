package robedpixel.sdl;

import lombok.Getter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public abstract class SdlMainThreadCallback implements AutoCloseable{
    @Getter
    private Arena callbackAllocator = Arena.ofConfined();
    @Getter
    private MemorySegment userData;
    @Getter
    MemorySegment callbackAddress;
    public SdlMainThreadCallback(MemorySegment userData){
        this.userData = userData;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS);
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
     * @param userData A MemorySegment for a Java object of any structure for user data
     */
    void callback(MemorySegment userData) {

    }
    @Override
    public void close(){
        callbackAllocator.close();
    }
}
