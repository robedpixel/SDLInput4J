package robedpixel.sdl;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import lombok.Getter;

public abstract class SdlMainThreadCallback implements AutoCloseable {
  @Getter private final Arena callbackAllocator = Arena.ofConfined();
  @Getter private final MemorySegment userData;
  @Getter MemorySegment callbackAddress;
  private static final FunctionDescriptor callbackHandleDescriptor =
      FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);
  private final MethodHandle callbackHandle;

  public SdlMainThreadCallback(MemorySegment userData) {
    this.userData = userData;
    try {
      callbackHandle =
          MethodHandles.publicLookup()
              .bind(this, "callback", callbackHandleDescriptor.toMethodType());
    } catch (Exception e) {
      throw new AssertionError("Error creating callback handle", e);
    }
    callbackAddress =
        Linker.nativeLinker()
            .upcallStub(callbackHandle, callbackHandleDescriptor, callbackAllocator);
  }

  /**
   * @param userData A MemorySegment for a Java object of any structure for user data
   */
  void callback(MemorySegment userData) {}

  @Override
  public void close() {
    callbackAllocator.close();
  }
}
