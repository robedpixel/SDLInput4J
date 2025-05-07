package robedpixel.sdl.joystick;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import robedpixel.sdl.guid.NativeSdlGuidModel;
import robedpixel.sdl.properties.SdlPropertiesId;

// TODO: add rest of javadoc

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
   * Get the implementation-dependent GUID of a joystick.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the GUID of the selected joystick. If called with an invalid instanceId, this
   *     function returns a zero GUID.
   * @throws Throwable
   */
  public NativeSdlGuidModel getJoystickGuidForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickGuidForId(instanceId.getValue());
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

  /**
   * Get the USB vendor ID of a joystick, if available.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the USB vendor ID of the selected joystick. If called with an invalid
   *     instanceId, this function returns 0.
   * @throws Throwable
   */
  public short getJoystickVendorForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickVendorForId(instanceId.getValue());
  }

  /**
   * Get the USB product ID of a joystick, if available.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the USB product ID of the selected joystick. If called with an invalid
   *     instanceId, this function returns 0.
   * @throws Throwable
   */
  public short getJoystickProductForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickProductForId(instanceId.getValue());
  }

  /**
   * Get the USB product version of a joystick, if available.
   *
   * @param instanceId The joystick instance ID.
   * @return Returns the USB product version of the selected joystick. If called with an invalid
   *     instanceId, this function returns 0.
   * @throws Throwable
   */
  public short getJoystickProductVersionForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickProductVersionForId(instanceId.getValue());
  }

  /**
   * Get the type of a joystick, if available.
   *
   * @param instanceId the joystick instance ID.
   * @return Returns the SdlJoystickType ordinal value of the selected joystick. If called with an
   *     invalid instance_id, this function returns SDL_JOYSTICK_TYPE_UNKNOWN ordinal value.
   * @throws Throwable
   */
  public int getJoystickTypeForId(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.getJoystickTypeForId(instanceId.getValue());
  }

  /**
   * Open a joystick for use.
   *
   * @param instanceId the joystick instance ID.
   * @return Returns a SdlJoystickDevice or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public SdlJoystickDevice openJoystick(SdlJoystickId instanceId) throws Throwable {
    MemorySegment joystickAddress = SdlFuncs.openJoystick(instanceId.getValue());
    if (joystickAddress == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlJoystickDevice(joystickAddress, this.SdlFuncs);
    }
  }

  /**
   * Get the SDlJoystickDevice associated with an instance ID, if it has been opened.
   *
   * @param instanceId The instance ID to get the SdlJoystickDevice for.
   * @return Returns an SdlJoystickDevice on success or null on failure or if it hasn't been opened
   *     yet; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public SdlJoystickDevice getJoystickFromId(SdlJoystickId instanceId) throws Throwable {
    MemorySegment joystickAddress = SdlFuncs.getJoystickFromId(instanceId.getValue());
    if (joystickAddress == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlJoystickDevice(joystickAddress, this.SdlFuncs);
    }
  }

  /**
   * Get the SdlJoystickDevice associated with a player index.
   *
   * @param instanceId The player index to get the SdlJoystickDevice for.
   * @return Returns an SdlJoystickDevice on success or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public SdlJoystickDevice getJoystickFromPlayerIndex(SdlJoystickId instanceId) throws Throwable {
    MemorySegment joystickAddress = SdlFuncs.getJoystickFromPlayerIndex(instanceId.getValue());
    if (joystickAddress == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlJoystickDevice(joystickAddress, this.SdlFuncs);
    }
  }

  /**
   * Attach a new virtual joystick.
   *
   * @param desc joystick description
   * @return Returns the joystick instance ID, or 0 on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public SdlJoystickId attachVirtualJoystick(SdlVirtualJoystickDesc desc) throws Throwable {
    SdlJoystickId returnObject = new SdlJoystickId();
    returnObject.setValue(SdlFuncs.attachVirtualJoystick(desc.getDataAddress()));
    return returnObject;
  }

  /**
   * Detach a virtual joystick.
   *
   * @param instanceId the joystick instance ID, previously returned from
   *     SdlJoystick.attachVirtualJoystick().
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean detachVirtualJoystick(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.detachVirtualJoystick(instanceId.getValue());
  }

  /**
   * Query whether or not a joystick is virtual.
   *
   * @param instanceId The joystick instance ID
   * @return Returns true if joystick is virtual, false otherwise
   * @throws Throwable
   */
  public boolean isJoystickVirtual(SdlJoystickId instanceId) throws Throwable {
    return SdlFuncs.isJoystickVirtual(instanceId.getValue());
  }

  /**
   * Set the state of an axis on an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param axis The index of the axis on the virtual joystick to update.
   * @param value The new value for the specified axis.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickVirtualAxis(SdlJoystickDevice joystick, int axis, short value)
      throws Throwable {
    return SdlFuncs.setJoystickVirtualAxis(joystick.getAddress(), axis, value);
  }

  /**
   * Generate ball motion on an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param ball The index of the ball on the virtual joystick to update.
   * @param xrel The relative motion on the X axis.
   * @param yrel The relative motion on the Y axis.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickVirtualBall(
      SdlJoystickDevice joystick, int ball, short xrel, short yrel) throws Throwable {
    return SdlFuncs.setJoystickVirtualBall(joystick.getAddress(), ball, xrel, yrel);
  }

  /**
   * Set the state of a button on an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param button The index of the button on the virtual joystick to update.
   * @param down True if the button is pressed, false otherwise.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickVirtualButton(SdlJoystickDevice joystick, int button, boolean down)
      throws Throwable {
    return SdlFuncs.setJoystickVirtualButton(joystick.getAddress(), button, down);
  }

  /**
   * Set the state of a hat on an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param hat The index of the hat on the virtual joystick to update.
   * @param uValue (Unsigned Byte) The new value for the specified hat.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickVirtualHat(SdlJoystickDevice joystick, int hat, byte uValue)
      throws Throwable {
    return SdlFuncs.setJoystickVirtualHat(joystick.getAddress(), hat, uValue);
  }

  /**
   * Set the state of a hat on an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param touchpad The index of the touchpad on the virtual joystick to update.
   * @param finger The index of the finger on the touchpad to set.
   * @param down True if the finger is pressed, false if the finger is released.
   * @param x The x coordinate of the finger on the touchpad, normalized 0 to 1, with the origin in
   *     the upper left.
   * @param y The y coordinate of the finger on the touchpad, normalized 0 to 1, with the origin in
   *     the upper left.
   * @param pressure The pressure of the finger.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickVirtualTouchpad(
      SdlJoystickDevice joystick,
      int touchpad,
      int finger,
      boolean down,
      float x,
      float y,
      float pressure)
      throws Throwable {
    return SdlFuncs.setJoystickVirtualTouchpad(
        joystick.getAddress(), touchpad, finger, down, x, y, pressure);
  }

  /**
   * Send a sensor update for an opened virtual joystick.
   *
   * @param joystick The virtual joystick on which to set state.
   * @param type The type of the sensor on the virtual joystick to update.
   * @param uSensorTimestamp (Unsigned Long) A 64-bit timestamp in nanoseconds associated with the
   *     sensor reading.
   * @param data The data associated with the sensor reading.
   * @param numValues The number of values pointed to by data.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean sendJoystickVirtualSensorData(
      SdlJoystickDevice joystick,
      int type,
      long uSensorTimestamp,
      MemorySegment data,
      int numValues)
      throws Throwable {
    return SdlFuncs.sendJoystickVirtualSensorData(
        joystick.getAddress(), type, uSensorTimestamp, data, numValues);
  }

  /**
   * Get the properties associated with a joystick.
   *
   * @param joystick the SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns a valid property ID on success or 0 on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public SdlPropertiesId getJoystickProperties(SdlJoystickDevice joystick) throws Throwable {
    SdlPropertiesId propertiesId = new SdlPropertiesId();
    propertiesId.setValue(SdlFuncs.getJoystickProperties(joystick.getAddress()));
    return propertiesId;
  }

  /**
   * Get the implementation dependent name of a joystick.
   *
   * @param joystick the SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the name of the selected joystick. If no name can be found, this function
   *     returns null; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getJoystickName(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickName(joystick.getAddress());
  }

  /**
   * Get the implementation dependent path of a joystick.
   *
   * @param joystick the SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the path of the selected joystick. If no path can be found, this function
   *     returns null; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public String getJoystickPath(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickPath(joystick.getAddress());
  }

  /**
   * Get the player index of an opened joystick.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the player index, or -1 if it's not available.
   * @throws Throwable
   */
  public int getJoystickPlayerIndex(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickPlayerIndex(joystick.getAddress());
  }

  /**
   * Set the player index of an opened joystick.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @param playerIndex player index to assign to this joystick, or -1 to clear the player index and
   *     turn off player LEDs.
   * @return Returns the player index, or -1 if it's not available.
   * @throws Throwable
   */
  public boolean setJoystickPlayerIndex(SdlJoystickDevice joystick, int playerIndex)
      throws Throwable {
    return SdlFuncs.setJoystickPlayerIndex(joystick.getAddress(), playerIndex);
  }

  /**
   * Get the implementation-dependent GUID for the joystick.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the GUID of the given joystick. If called on an invalid index, this function
   *     returns a zero GUID; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public NativeSdlGuidModel getJoystickGuid(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickGuid(joystick.getAddress());
  }

  /**
   * Get the USB vendor ID of an opened joystick, if available.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the USB vendor ID of the selected joystick, or 0 if unavailable.
   * @throws Throwable
   */
  public short getJoystickVendor(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickVendor(joystick.getAddress());
  }

  /**
   * Get the USB product ID of an opened joystick, if available.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the USB product ID of the selected joystick, or 0 if unavailable.
   * @throws Throwable
   */
  public short getJoystickProduct(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickProduct(joystick.getAddress());
  }

  /**
   * Get the USB product version of an opened joystick, if available.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the USB product version of the selected joystick, or 0 if unavailable.
   * @throws Throwable
   */
  public short getJoystickProductVersion(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickProductVersion(joystick.getAddress());
  }

  /**
   * Get the firmware version of an opened joystick, if available.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the firmware version of the selected joystick, or 0 if unavailable.
   * @throws Throwable
   */
  public short getJoystickFirmwareVersion(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickFirmwareVersion(joystick.getAddress());
  }

  /**
   * Get the serial number of an opened joystick, if available.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the serial number of the selected joystick, or null if unavailable.
   * @throws Throwable
   */
  public String getJoystickSerial(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickSerial(joystick.getAddress());
  }

  /**
   * Get the type of an opened joystick.
   *
   * @param joystick The SdlJoystickDevice obtained from SdlJoystick.openJoystick().
   * @return Returns the SdlJoystickType ordinal value of the selected joystick.
   * @throws Throwable
   */
  public int getJoystickType(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickType(joystick.getAddress());
  }

  /**
   * Get the device information encoded in a NativeSdlGuidModel structure.
   *
   * @param guid The NativeSdlGuidModel you wish to get info about.
   * @param guidInfo The object to store the extracted information in
   * @throws Throwable
   */
  public void getJoystickGuidInfo(NativeSdlGuidModel guid, SdlJoystickGuidInfo guidInfo)
      throws Throwable {
    SdlFuncs.getJoystickGuidInfo(guid.getDataAddress(), guidInfo);
  }

  /**
   * Get the status of a specified joystick.
   *
   * @param joystick The joystick to query.
   * @return Returns true if the joystick has been opened, false if it has not; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public boolean joystickConnected(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.joystickConnected(joystick.getAddress());
  }

  /**
   * Get the instance ID of an opened joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns the instance ID of the specified joystick on success or 0 on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getJoystickID(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickID(joystick.getAddress());
  }

  /**
   * Get the number of general axis controls on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns the number of axis controls/number of axes on success or -1 on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getNumJoystickAxes(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getNumJoystickAxes(joystick.getAddress());
  }

  /**
   * Get the number of trackballs on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns the number of trackballs on success or -1 on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public int getNumJoystickBalls(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getNumJoystickBalls(joystick.getAddress());
  }

  /**
   * Get the number of POV hats on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns the number of POV hats on success or -1 on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public int getNumJoystickHats(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getNumJoystickHats(joystick.getAddress());
  }

  /**
   * Get the number of buttons on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns the number of buttons on success or -1 on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public int getNumJoystickButtons(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getNumJoystickButtons(joystick.getAddress());
  }

  /**
   * Set the state of joystick event processing.
   *
   * @param enabled whether to process joystick events or not.
   * @throws Throwable
   */
  public void setJoystickEventsEnabled(boolean enabled) throws Throwable {
    SdlFuncs.setJoystickEventsEnabled(enabled);
  }

  /**
   * Query the state of joystick event processing.
   *
   * @return Returns true if joystick events are being processed, false otherwise.
   * @throws Throwable
   */
  public boolean joystickEventsEnabled() throws Throwable {
    return SdlFuncs.joystickEventsEnabled();
  }

  /**
   * Update the current state of the open joysticks.
   *
   * @throws Throwable
   */
  public void updateJoysticks() throws Throwable {
    SdlFuncs.updateJoysticks();
  }

  /**
   * Get the current state of an axis control on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @param axis The axis to query; the axis indices start at index 0.
   * @return Returns a 16-bit signed integer representing the current position of the axis or 0 on
   *     failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public short getJoystickAxis(SdlJoystickDevice joystick, int axis) throws Throwable {
    return SdlFuncs.getJoystickAxis(joystick.getAddress(), axis);
  }

  /**
   * Get the initial state of all axis controls on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @return Returns an array of the initial values for the axis of the joystick, or a zero length
   *     array if no values are returned
   * @throws Throwable
   */
  public SdlJoystickAxisReading[] getAllJoystickAxisInitialState(SdlJoystickDevice joystick)
      throws Throwable {
    int numaxes = SdlFuncs.getNumJoystickAxes(joystick.getAddress());
    if (numaxes < 1) {
      return new SdlJoystickAxisReading[0];
    }
    SdlJoystickAxisReading[] returnArray = new SdlJoystickAxisReading[numaxes];
    try (Arena arena = Arena.ofConfined()) {
      MemorySegment axisAddress = arena.allocate(ValueLayout.JAVA_SHORT);
      for (int i = 0; i < numaxes; i++) {
        SdlJoystickAxisReading reading = new SdlJoystickAxisReading();
        SdlFuncs.getJoystickAxisInitialState(joystick.getAddress(), i, reading);
        returnArray[i] = reading;
      }
      return returnArray;
    }
  }

  /**
   * Get the initial state of an axis control on a joystick.
   *
   * @param joystick An SdlJoystickDevice containing joystick information.
   * @param axis the axis to query; the axis indices start at index 0.
   * @param state The initial value will be stored in this object
   * @return Returns true if this axis has any initial value, or false if not.
   * @throws Throwable
   */
  public boolean getJoystickAxisInitialState(
      SdlJoystickDevice joystick, int axis, SdlJoystickAxisReading state) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getJoystickAxisInitialState(joystick.getAddress(), axis, state);
    }
  }

  /**
   * Get the ball axis change since the last poll.
   *
   * @param joystick The SdlJoystickDevice to query.
   * @param ball The ball index to query; ball indices start at index 0.
   * @param ballReading stores the difference in the position since the last poll.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean getJoystickBall(
      SdlJoystickDevice joystick, int ball, SdlJoystickBallReading ballReading) throws Throwable {
    return SdlFuncs.getJoystickBall(joystick.getAddress(), ball, ballReading);
  }

  /**
   * Get the current state of a POV hat on a joystick.
   *
   * @param joystick An SdlJoystickDevice structure containing joystick information.
   * @param hat The hat index to get the state from; indices start at index 0.
   * @return Returns the current hat position.
   * @throws Throwable
   */
  public byte getJoystickHat(SdlJoystickDevice joystick, int hat) throws Throwable {
    return SdlFuncs.getJoystickHat(joystick.getAddress(), hat);
  }

  /**
   * Get the current state of a button on a joystick.
   *
   * @param joystick An SdlJoystickDevice structure containing joystick information.
   * @param button The button index to get the state from; indices start at index 0.
   * @return Returns true if the button is pressed, false otherwise.
   * @throws Throwable
   */
  public boolean getJoystickButton(SdlJoystickDevice joystick, int button) throws Throwable {
    return SdlFuncs.getJoystickButton(joystick.getAddress(), button);
  }

  /**
   * Start a rumble effect.
   *
   * @param joystick The joystick to vibrate.
   * @param uLowFrequencyRumble (Unsigned Short) The intensity of the low frequency (left) rumble
   *     motor, from 0 to 0xFFFF.
   * @param uHighFrequencyRumble (Unsigned Short) The intensity of the high frequency (right) rumble
   *     motor, from 0 to 0xFFFF.
   * @param uDurationMs (Unsigned Int)The duration of the rumble effect, in milliseconds.
   * @return Returns true, or false if rumble isn't supported on this joystick.
   * @throws Throwable
   */
  public boolean rumbleJoystick(
      SdlJoystickDevice joystick,
      short uLowFrequencyRumble,
      short uHighFrequencyRumble,
      int uDurationMs)
      throws Throwable {
    return SdlFuncs.rumbleJoystick(
        joystick.getAddress(), uLowFrequencyRumble, uHighFrequencyRumble, uDurationMs);
  }

  /**
   * Start a rumble effect in the joystick's triggers
   *
   * @param joystick The joystick to vibrate.
   * @param uLeftRumble (Unsigned Short) The intensity of the left trigger rumble motor, from 0 to
   *     0xFFFF.
   * @param uRightRumble (Unsigned Short) The intensity of the right trigger rumble motor, from 0 to
   *     0xFFFF.
   * @param uDurationMs (Unsigned Short) The duration of the rumble effect, in milliseconds.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean rumbleJoystickTriggers(
      SdlJoystickDevice joystick, short uLeftRumble, short uRightRumble, int uDurationMs)
      throws Throwable {
    return SdlFuncs.rumbleJoystickTriggers(
        joystick.getAddress(), uLeftRumble, uRightRumble, uDurationMs);
  }

  /**
   * Update a joystick's LED color.
   *
   * @param joystick The joystick to update.
   * @param uRed (Unsigned Byte) The intensity of the red LED.
   * @param uGreen (Unsigned Byte) The intensity of the green LED.
   * @param uBlue (Unsigned Byte) The intensity of the blue LED.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setJoystickLED(SdlJoystickDevice joystick, byte uRed, byte uGreen, byte uBlue)
      throws Throwable {
    return SdlFuncs.setJoystickLED(joystick.getAddress(), uRed, uGreen, uBlue);
  }

  /**
   * Send a joystick specific effect packet.
   *
   * @param joystick The joystick to affect.
   * @param data The data to send to the joystick.
   * @param size The size of the data to send to the joystick.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean sendJoystickEffect(SdlJoystickDevice joystick, MemorySegment data, int size)
      throws Throwable {
    return SdlFuncs.sendJoystickEffect(joystick.getAddress(), data, size);
  }

  /**
   * Get the connection state of a joystick.
   *
   * @param joystick The joystick to query.
   * @return Returns the connection state on success or SDL_JOYSTICK_CONNECTION_INVALID on failure;
   *     call SdlError.getError() for more information.
   * @throws Throwable
   */
  public int getJoystickConnectionState(SdlJoystickDevice joystick) throws Throwable {
    return SdlFuncs.getJoystickConnectionState(joystick.getAddress());
  }

  /**
   * Get the battery state of a joystick.
   *
   * @param joystick The joystick to query.
   * @param snapshot A Snapshot object to store the battery state of the joystick in
   * @throws Throwable
   */
  public void getJoystickPowerInfo(SdlJoystickDevice joystick, SdlJoystickPowerSnapshot snapshot)
      throws Throwable {
    SdlFuncs.getJoystickPowerInfo(joystick.getAddress(), snapshot);
  }
}
