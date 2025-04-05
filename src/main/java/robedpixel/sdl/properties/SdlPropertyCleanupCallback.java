package robedpixel.sdl.properties;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import lombok.Getter;

public abstract class SdlPropertyCleanupCallback implements AutoCloseable {
  @Getter private final Arena callbackAllocator = Arena.ofConfined();
  private final MemorySegment userData;
  private final MemorySegment callbackAddress;

  public SdlPropertyCleanupCallback(MemorySegment userData) {
    this.userData = userData;
    FunctionDescriptor callbackHandleDescriptor =
        FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    MethodHandle callbackHandle;
    try {
      callbackHandle =
          MethodHandles.publicLookup()
              .bind(this, "callback", callbackHandleDescriptor.toMethodType());
    } catch (Exception e) {
      throw new AssertionError("Problem creating callback handle", e);
    }
    callbackAddress =
        Linker.nativeLinker()
            .upcallStub(callbackHandle, callbackHandleDescriptor, callbackAllocator);
  }
  public MemorySegment getCallbackAddress(){
    return callbackAddress.asReadOnly();
  }
  public MemorySegment getUserData(){
    return userData.asReadOnly();
  }

  /**
   * @param userData An app-defined pointer passed to the callback.
   * @param value The pointer assigned to the property to clean up.
   */
  void callback(MemorySegment userData, MemorySegment value) {}

  @Override
  public void close() {
    callbackAllocator.close();
  }
}
