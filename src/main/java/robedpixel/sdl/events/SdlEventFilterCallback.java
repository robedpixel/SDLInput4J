package robedpixel.sdl.events;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import lombok.Getter;

public class SdlEventFilterCallback implements AutoCloseable {
  @Getter private final Arena callbackAllocator = Arena.ofConfined();
  private final MemorySegment callbackAddress;

  public SdlEventFilterCallback() {
    FunctionDescriptor callbackHandleDescriptor =
        FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
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

  public MemorySegment getCallbackAddress() {
    return callbackAddress.asReadOnly();
  }

  /**
   * @param userData what was passed as userdata to SDL_SetEventFilter() or SDL_AddEventWatch, etc
   * @param event The event that triggered the callback.
   * @return Returns true to permit event to be added to the queue, and false to disallow it. When
   *     used with SDL_AddEventWatch, the return value is ignored.
   */
  boolean callback(MemorySegment userData, MemorySegment event) {
    return false;
  }

  @Override
  public void close() {
    callbackAllocator.close();
  }
}
