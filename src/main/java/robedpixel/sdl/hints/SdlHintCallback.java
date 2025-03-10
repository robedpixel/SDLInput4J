package robedpixel.sdl.hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import lombok.Getter;

/** A callback used to send notifications of hint value changes. */
public abstract class SdlHintCallback implements AutoCloseable {
  @Getter private Arena callbackAllocator = Arena.ofConfined();
  @Getter private MemorySegment nameAddress;
  @Getter private String name;
  @Getter private MemorySegment userData;
  @Getter MemorySegment callbackAddress;

  public SdlHintCallback(MemorySegment userData, String name) {
    this.userData = userData;
    this.name = name;
    MethodHandle callbackHandle;
    FunctionDescriptor callbackHandleDescriptor =
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    try {
      callbackHandle =
          MethodHandles.publicLookup()
              .bind(this, "callback", callbackHandleDescriptor.toMethodType());
    } catch (Exception e) {
      throw new AssertionError("Problem creating callback handle", e);
    }
    nameAddress = this.getCallbackAllocator().allocateFrom(this.name);
    callbackAddress =
        Linker.nativeLinker()
            .upcallStub(callbackHandle, callbackHandleDescriptor, callbackAllocator);
  }

  /**
   * @param userData A MemorySegment for a Java object of any structure for user data passed to
   *     addHintCallback
   * @param name MemorySegment of a null terminated C-String passed to addHintCallback
   * @param oldValue MemorySegment of a null terminated C-String of the previous hint value
   * @param newValue MemorySegment of a null terminated C-String of the new hint value
   */
  void callback(
      MemorySegment userData, MemorySegment name, MemorySegment oldValue, MemorySegment newValue) {}

  @Override
  public final void close() {
    callbackAllocator.close();
  }
}
