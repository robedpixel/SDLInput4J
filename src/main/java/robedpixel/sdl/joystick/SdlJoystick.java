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
}
