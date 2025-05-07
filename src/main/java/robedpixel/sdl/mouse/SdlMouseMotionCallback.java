package robedpixel.sdl.mouse;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import lombok.Getter;

public class SdlMouseMotionCallback implements AutoCloseable {
  @Getter private final Arena callbackAllocator = Arena.ofConfined();
  @Getter private final MemorySegment userData;
  @Getter MemorySegment callbackAddress;
  private static final FunctionDescriptor callbackHandleDescriptor =
      FunctionDescriptor.ofVoid(
          ValueLayout.ADDRESS,
          ValueLayout.JAVA_LONG,
          ValueLayout.ADDRESS,
          ValueLayout.JAVA_INT,
          ValueLayout.ADDRESS,
          ValueLayout.ADDRESS);
  private final MethodHandle callbackHandle;

  public SdlMouseMotionCallback(MemorySegment userData) {
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
   * A callback used to transform mouse motion delta from raw values.
   *
   * @param userData what was passed as userdata to setRelativeMouseTransform()
   * @param utimeStamp (unsigned long) The associated time at which this mouse motion event was
   *     received.
   * @param window The associated window to which this mouse motion event was addressed.
   * @param mouseId The associated mouse from which this mouse motion event was emitted.
   * @param x pointer to a variable that will be treated as the resulting x-axis motion.
   * @param y pointer to a variable that will be treated as the resulting y-axis motion.
   */
  void callback(
      MemorySegment userData,
      long utimeStamp,
      MemorySegment window,
      int mouseId,
      MemorySegment x,
      MemorySegment y) {}

  @Override
  public void close() {
    callbackAllocator.close();
  }
}
