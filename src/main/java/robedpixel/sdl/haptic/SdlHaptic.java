package robedpixel.sdl.haptic;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import robedpixel.sdl.haptic.effect.SdlHapticEffect;
import robedpixel.sdl.joystick.SdlJoystickDevice;

public class SdlHaptic {
  private final NativeSdlHapticFuncs SdlFuncs;

  public SdlHaptic(Arena allocator) {
    SdlFuncs = NativeSdlHapticFuncs.getInstance(allocator);
  }

  /**
   * Get an array of currently connected haptic devices.
   *
   * @return Returns an array of haptic device instance Ids or null on failure; call
   *     SdlError.getError() for more information. This should be freed with Sdlfree() when it is no
   *     longer needed.
   * @throws Throwable
   */
  public SdlHapticIdArray getHaptics() throws Throwable {
    return SdlFuncs.getHaptics();
  }

  /**
   * Get the implementation dependent name of a haptic device.
   *
   * @param instanceId the haptic device instance Id.
   * @return Returns the name of the selected haptic device. If no name can be found, this function
   *     returns null; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getHapticId(SdlHapticId instanceId) throws Throwable {
    return SdlFuncs.getHapticId(instanceId.getValue());
  }

  /**
   * Open a haptic device for use.
   *
   * @param instanceId the haptic device instance Id.
   * @return Returns the device identifier or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public SdlHapticDevice openHaptic(SdlHapticId instanceId) throws Throwable {
    MemorySegment address = SdlFuncs.openHaptic(instanceId.getValue());
    if (address == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlHapticDevice(address, this.SdlFuncs);
    }
  }

  /**
   * Get the SdlHapticDevice associated with an instance Id, if it has been opened.
   *
   * @param instanceId The instance Id to get the SdlHapticDevice for.
   * @return Returns an SdlHapticDevice on success or null on failure or if it hasn't been opened
   *     yet; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public SdlHapticDevice getHapticFromId(SdlHapticId instanceId) throws Throwable {
    MemorySegment address = SdlFuncs.getHapticFromId(instanceId.getValue());
    if (address == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlHapticDevice(address, this.SdlFuncs);
    }
  }

  /**
   * Get the instance Id of an opened haptic device.
   *
   * @param haptic The SdlHapticDevice to query
   * @return Returns the instance Id of the specified haptic device on success or 0 on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public SdlHapticId getHapticId(SdlHapticDevice haptic) throws Throwable {
    SdlHapticId hapticId = new SdlHapticId();
    hapticId.setValue(SdlFuncs.getHapticId(haptic.getAddress()));
    return hapticId;
  }

  /**
   * Get the implementation dependent name of a haptic device.
   *
   * @param haptic The SdlHapticDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the name of the selected haptic device. If no name can be found, this function
   *     returns NULL; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getHapticName(SdlHapticDevice haptic) throws Throwable {
    return SdlFuncs.getHapticName(haptic.getAddress());
  }

  /**
   * Query whether or not the current mouse has haptic capabilities.
   *
   * @return Returns true if the mouse is haptic or false if it isn't.
   * @throws Throwable
   */
  public boolean isMouseHaptic() throws Throwable {
    return SdlFuncs.isMouseHaptic();
  }

  /**
   * Try to open a haptic device from the current mouse.
   *
   * @return Returns the haptic device identifier or null on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public SdlHapticDevice openHapticFromMouse() throws Throwable {
    MemorySegment address = SdlFuncs.openHapticFromMouse();
    if (address == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlHapticDevice(address, this.SdlFuncs);
    }
  }

  /**
   * Query if a joystick has haptic features.
   *
   * @param joystick The SdlJoystickDevice to test for haptic capabilities.
   * @return Returns true if the joystick is haptic or false if it isn't.
   * @throws Throwable
   */
  public boolean isJoystickHaptic(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.isJoystickHaptic(joystick.getAddress());
  }

  /**
   * Open a haptic device for use from a joystick device.
   *
   * @param joystick the SdlJoystickDevice to create a haptic device from.
   * @return Returns a valid haptic device identifier on success or null on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public SdlHapticDevice openHapticFromJoystick(SdlJoystickDevice joystick) throws Throwable {
    MemorySegment address = SdlFuncs.openHapticFromJoystick(joystick.getAddress());
    if (address == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlHapticDevice(address, this.SdlFuncs);
    }
  }

  /**
   * Close a haptic device previously opened with openHaptic()
   *
   * @param haptic The SdlHapticDevice to close.
   * @throws Throwable
   */
  public void closeHaptic(SdlHapticDevice haptic) throws Throwable {
    SdlFuncs.closeHaptic(haptic.getAddress());
  }

  /**
   * Get the number of effects a haptic device can store.
   *
   * @param haptic The SdlHapticDevice to query.
   * @return Returns the number of effects the haptic device can store or a negative error code on
   *     failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getMaxHapticEffects(SdlHapticDevice haptic) throws Throwable {
    return SdlFuncs.getMaxHapticEffects(haptic.getAddress());
  }

  /**
   * Get the number of effects a haptic device can play at the same time.
   *
   * @param haptic The SdlHapticDevice to query.
   * @return Returns the number of effects the haptic device can play at the same time or -1 on
   *     failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getMaxHapticEffectsPlaying(SdlHapticDevice haptic) throws Throwable {
    return SdlFuncs.getMaxHapticEffectsPlaying(haptic.getAddress());
  }

  /**
   * Get the haptic device's supported features in bitwise manner.
   *
   * @param haptic The SdlHapticDevice to query.
   * @return Returns a list of supported haptic features in bitwise manner (OR'd), or 0 on failure;
   *     call SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getHapticFeatures(SdlHapticDevice haptic) throws Throwable {
    return SdlFuncs.getHapticFeatures(haptic.getAddress());
  }

  /**
   * Get the number of haptic axes the device has.
   *
   * @param haptic The SdlHapticDevice to query.
   * @return Returns the number of axes on success or -1 on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public int getNumHapticAxes(SdlHapticDevice haptic) throws Throwable {
    return SdlFuncs.getNumHapticAxes(haptic.getAddress());
  }

  /**
   * Check to see if an effect is supported by a haptic device.
   *
   * @param haptic The SdlHapticDevice to query.
   * @param effect The desired effect to query.
   * @return Returns true if the effect is supported or false if it isn't.
   * @throws Throwable
   */
  public boolean hapticEffectSupported(SdlHapticDevice haptic, SdlHapticEffect effect)
      throws Throwable {
    return SdlFuncs.hapticEffectSupported(haptic.getAddress(), effect.getMemorySegment());
  }

  /**
   * Create a new haptic effect on a specified device.
   * @param haptic An SdlHapticDevice to create the effect on.
   * @param effect An SdlHapticEffect object containing the properties of the effect to create.
   * @return Returns the Id of the effect on success or -1 on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public int createHapticEffect(SdlHapticDevice haptic, SdlHapticEffect effect) throws Throwable {
    return SdlFuncs.createHapticEffect(haptic.getAddress(), effect.getMemorySegment());
  }

  /**
   * Update the properties of an effect.
   * @param haptic The SdlHapticDevice that has the effect.
   * @param effect The identifier of the effect to update.
   * @param data An SdlHapticEffect object containing the new effect properties to use.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public boolean updateHapticEffect(SdlHapticDevice haptic, int effect, SdlHapticEffect data) throws Throwable{
    return SdlFuncs.updateHapticEffect(haptic.getAddress(),effect,data.getMemorySegment());
  }

  /**
   * Run the haptic effect on its associated haptic device.
   * @param haptic The SdlHapticDevice to run the effect on.
   * @param effect The Id of the haptic effect to run.
   * @param iterations The number of iterations to run the effect; use SDL_HAPTIC_INFINITY to repeat forever.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public boolean runHapticEffect(SdlHapticDevice haptic, int effect, int iterations) throws Throwable {
    return SdlFuncs.runHapticEffect(haptic.getAddress(),effect,iterations);
  }

  /**
   * Stop the haptic effect on its associated haptic device.
   * @param haptic The SdlHapticDevice to stop the effect on.
   * @param effect The Id of the haptic effect to stop.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public boolean stopHapticEffect(SdlHapticDevice haptic, int effect) throws Throwable{
    return SdlFuncs.stopHapticEffect(haptic.getAddress(),effect);
  }

  /**
   * Destroy a haptic effect on the device.
   * @param haptic the SdlHapticDevice to destroy the effect on.
   * @param effect the Id of the haptic effect to destroy.
   * @throws Throwable
   */
  public void destroyHapticEffect(SdlHapticDevice haptic, int effect) throws Throwable{
    SdlFuncs.destroyHapticEffect(haptic.getAddress(),effect);
  }

  /**
   * Get the status of the current effect on the specified haptic device.
   * @param haptic The SdlHapticDevice to query for the effect status on.
   * @param effect The Id of the haptic effect to query its status.
   * @return Returns true if it is playing, false if it isn't playing or haptic status isn't supported.
   * @throws Throwable
   */
  public synchronized boolean getHapticEffectStatus(SdlHapticDevice haptic, int effect) throws Throwable{
    return SdlFuncs.getHapticEffectStatus(haptic.getAddress(),effect);
  }

  /**
   * Set the global gain of the specified haptic device.
   * @param haptic The SdlHapticDevice to set the gain on.
   * @param gain value to set the gain to, should be between 0 and 100 (0 - 100).
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean setHapticGain(SdlHapticDevice haptic, int gain) throws Throwable{
    return SdlFuncs.setHapticGain(haptic.getAddress(),gain);
  }

  /**
   * Set the global autocenter of the device.
   * @param haptic The SdlHapticDevice to set autocentering on.
   * @param autocenter value to set autocenter to (0-100).
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean setHapticAutocenter(SdlHapticDevice haptic, int autocenter) throws Throwable{
    return SdlFuncs.setHapticAutocenter(haptic.getAddress(),autocenter);
  }

  /**
   * Pause a haptic device.
   * @param haptic The SdlHapticDevice to pause.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized  boolean pauseHaptic(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.pauseHaptic(haptic.getAddress());
  }

  /**
   * Resume a haptic device.
   * @param haptic The SdlHapticDevice to unpause.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean resumeHaptic(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.resumeHaptic(haptic.getAddress());
  }

  /**
   * Stop all the currently playing effects on a haptic device.
   * @param haptic The SdlHapticDevice to stop.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean stopHapticEffects(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.stopHapticEffects(haptic.getAddress());
  }

  /**
   * Check whether rumble is supported on a haptic device.
   * @param haptic haptic device to check for rumble support.
   * @return Returns true if the effect is supported or false if it isn't.
   * @throws Throwable
   */
  public synchronized boolean hapticRumbleSupported(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.hapticRumbleSupported(haptic.getAddress());
  }

  /**
   * Initialize a haptic device for simple rumble playback.
   * @param haptic The haptic device to initialize for simple rumble playback.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean initHapticRumble(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.initHapticRumble(haptic.getAddress());
  }

  /**
   * Run a simple rumble effect on a haptic device.
   * @param haptic The haptic device to play the rumble effect on.
   * @param strength Strength of the rumble to play as a 0-1 float value.
   * @param length Length of the rumble to play in milliseconds.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean playHapticRumble(SdlHapticDevice haptic, int strength, int length) throws Throwable{
    return SdlFuncs.playHapticRumble(haptic.getAddress(),strength,length);
  }

  /**
   * Stop the simple rumble on a haptic device.
   * @param haptic The haptic device to stop the rumble effect on.
   * @return Returns true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public synchronized boolean stopHapticRumble(SdlHapticDevice haptic) throws Throwable{
    return SdlFuncs.stopHapticRumble(haptic.getAddress());
  }
}
