package robedpixel.sdl.joystick;

import java.lang.foreign.Arena;

public class SdlJoystick {
  private NativeSdlJoystickFuncs SdlFuncs;

  public SdlJoystick(Arena allocator) {
    SdlFuncs = NativeSdlJoystickFuncs.getInstance(allocator);
  }

  /**
   * Locking for atomic access to the joystick API.
   *
   * @throws Throwable
   */
  public void lockJoysticks() throws Throwable {
    SdlFuncs.lockJoysticks();
  }

  /**
   * Unlocking for atomic access to the joystick API.
   *
   * @throws Throwable
   */
  public void unlockJoysticks() throws Throwable {
    SdlFuncs.unlockJoysticks();
  }

  /**
   * Return whether a joystick is currently connected.
   *
   * @return Returns true if a joystick is connected, false otherwise.
   * @throws Throwable
   */
  public boolean hasJoystick() throws Throwable {
    return SdlFuncs.hasJoystick();
  }

  /**
   * Get a list of currently connected joysticks.
   *
   * @return Returns an array of joystick instance IDs or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public SdlJoystickIdArray getJoysticks() throws Throwable {
    return SdlFuncs.getJoysticks();
  }

  /**
   * Get the implementation dependent name of a joystick.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the name of the selected joystick. If no name can be found, this function
   *     returns null; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getJoystickNameForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickNameForId(instanceId.getValue());
  }

  /**
   * Get the implementation dependent path of a joystick.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the path of the selected joystick. If no path can be found, this function
   *     returns null; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getJoystickPathForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickPathForId(instanceId.getValue());
  }

  /**
   * Get the player index of a joystick.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the player index of a joystick, or -1 if it's not available.
   * @throws Throwable
   */
  public int getJoystickPlayerIndexForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickPlayerIndexForId(instanceId.getValue());
  }
}
