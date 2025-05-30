package robedpixel.sdl.touch;

import java.lang.foreign.Arena;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SdlTouch {
  private final NativeSdlTouchFuncs SdlFuncs;

  public SdlTouch(Arena allocator) {
    SdlFuncs = NativeSdlTouchFuncs.getInstance(allocator);
  }

  // TODO: NEEDS BETTER MEMORY MANAGEMENT
  /**
   * Get a list of registered touch devices.
   *
   * @return An array of SdlTouchIds or null on failure,
   */
  @Nullable
  public SdlTouchIdArray getTouchDevices() throws Throwable {
    return SdlFuncs.getTouchDevices();
  }

  /**
   * Get the touch device name as reported from the driver.
   *
   * @param touchId the touch device instance ID.
   * @return Returns touch device name, or null on failure; call SdlError.getError() for more
   *     information.
   */
  @Nullable
  public String getTouchDeviceName(SdlTouchId touchId) throws Throwable {
    return SdlFuncs.getTouchDeviceName(touchId.getValue());
  }

  /**
   * Get the type of the given touch device.
   *
   * @param touchId the ID of a touch device.
   * @return Returns touch device type.
   */
  @NonNull
  public SdlTouchDeviceType getTouchDeviceType(SdlTouchId touchId) throws Throwable {
    return SdlTouchDeviceType.fromInt(SdlFuncs.getTouchDeviceType(touchId.getValue()));
  }

  /**
   * Get a list of active fingers for a given touch device.
   *
   * @param touchId The ID of a touch device.
   * @return Returns a 2d array of SdlFinger objects or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  @Nullable
  public SdlFinger2dArray getTouchFingers(SdlTouchId touchId) throws Throwable {
    return SdlFuncs.getTouchFingers(touchId.getValue());
  }
}
