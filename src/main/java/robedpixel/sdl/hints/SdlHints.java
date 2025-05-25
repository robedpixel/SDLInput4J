package robedpixel.sdl.hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import org.jspecify.annotations.Nullable;

public class SdlHints {
  private NativeSdlHintsFuncs SdlFuncs;

  public SdlHints(Arena allocator) {
    SdlFuncs = NativeSdlHintsFuncs.getInstance(allocator);
  }

  /**
   * Set a hint with a specific priority
   *
   * @param name The hint to set
   * @param value The value of the hint variable
   * @param priority The HintPriority of the hint
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setHint(String name, String value, HintPriority priority) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setHintWithPriority(arena, name, value, priority);
    }
  }

  /**
   * Set a hint with normal priority.
   *
   * @param name The hint to set
   * @param value The value of the hint variable
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setHint(String name, String value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setHint(arena, name, value);
    }
  }

  /**
   * Reset a hint to the default value.
   *
   * @param name The hint to reset
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean resetHint(String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.resetHint(arena, name);
    }
  }

  /**
   * Reset all hints to the default values.
   *
   * @throws Throwable
   */
  public void resetHints() throws Throwable {
    SdlFuncs.resetHints();
  }

  /**
   * Get the value of a hint.
   *
   * @param name The hint to set.
   * @return Returns the string value of a hint or null if the hint isn't set.
   * @throws Throwable
   */
  @Nullable
  public String getHint(String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getHint(arena, name);
    }
  }

  /**
   * Get the boolean value of a hint variable.
   *
   * @param name The name of the hint to get the boolean value from.
   * @param defaultValue The value to return if the hint does not exist.
   * @return Returns the boolean value of a hint or the provided default value if the hint does not
   *     exist.
   * @throws Throwable
   */
  public boolean getHintBoolean(String name, boolean defaultValue) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getHintBoolean(arena, name, defaultValue);
    }
  }

  /**
   * Add a function to watch a particular hint.
   *
   * @param callbackUpcallStub Callback to callwhen a change happens to the hint
   * @return Returns true on success or false on failure; call SDL_GetError() for more information.
   * @throws Throwable
   */
  public boolean addHintCallback(SdlHintCallback callbackUpcallStub) throws Throwable {
    MethodHandle callbackHandle;
    FunctionDescriptor callbackHandleDescriptor =
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    try {
      callbackHandle =
          MethodHandles.publicLookup()
              .bind(callbackUpcallStub, "callback", callbackHandleDescriptor.toMethodType());
    } catch (Exception e) {
      throw new AssertionError("Problem creating method handle compareHandle", e);
    }
    MemorySegment nameAddress =
        callbackUpcallStub.getCallbackAllocator().allocateFrom(callbackUpcallStub.getName());
    MemorySegment callbackFunc =
        Linker.nativeLinker()
            .upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                callbackUpcallStub.getCallbackAllocator());
    return SdlFuncs.addHintCallback(nameAddress, callbackFunc, callbackUpcallStub.getUserData());
  }

  /**
   * Remove a function watching a particular hint.
   *
   * @param callbackUpcallStub Callback to remove
   * @return Returns true on success or false on failure; call SDL_GetError() for more information.
   * @throws Throwable
   */
  public boolean removeHintCallback(SdlHintCallback callbackUpcallStub) throws Throwable {
    MethodHandle callbackHandle;
    FunctionDescriptor callbackHandleDescriptor =
        FunctionDescriptor.ofVoid(
            ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    try {
      callbackHandle =
          MethodHandles.publicLookup()
              .bind(callbackUpcallStub, "callback", callbackHandleDescriptor.toMethodType());
    } catch (Exception e) {
      throw new AssertionError("Problem creating method handle compareHandle", e);
    }
    MemorySegment nameAddress =
        callbackUpcallStub.getCallbackAllocator().allocateFrom(callbackUpcallStub.getName());
    MemorySegment callbackFunc =
        Linker.nativeLinker()
            .upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                callbackUpcallStub.getCallbackAllocator());
    return SdlFuncs.removeHintCallback(nameAddress, callbackFunc, callbackUpcallStub.getUserData());
  }
}
