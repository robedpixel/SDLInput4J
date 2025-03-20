package robedpixel.sdl.properties;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class SdlProperties {
  private final NativeSdlPropertiesFuncs SdlFuncs;

  public SdlProperties(Arena allocator) {
    SdlFuncs = NativeSdlPropertiesFuncs.getInstance(allocator);
  }

  /**
   * Get the global SDL properties.
   *
   * @return Returns a valid property Id on success or 0 on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public SdlPropertiesId getGlobalProperties() throws Throwable {
    SdlPropertiesId returnObject = new SdlPropertiesId();
    returnObject.setValue(SdlFuncs.getGlobalProperties());
    return returnObject;
  }

  /**
   * Create a group of properties.
   *
   * @return Returns an ID for a new group of properties, or 0 on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public SdlPropertiesId createProperties() throws Throwable {
    SdlPropertiesId returnObject = new SdlPropertiesId();
    returnObject.setValue(SdlFuncs.createProperties());
    return returnObject;
  }

  /**
   * Copy a group of properties.
   *
   * @param src The properties to copy
   * @param dst The destination properties.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean copyProperties(SdlPropertiesId src, SdlPropertiesId dst) throws Throwable {
    return SdlFuncs.copyProperties(src.getValue(), dst.getValue());
  }

  /**
   * Lock a group of properties.
   *
   * @param props The properties to lock.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean lockProperties(SdlPropertiesId props) throws Throwable {
    return SdlFuncs.lockProperties(props.getValue());
  }

  /**
   * Unlock a group of properties.
   *
   * @param props The properties to unlock.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public void unlockProperties(SdlPropertiesId props) throws Throwable {
    SdlFuncs.unlockProperties(props.getValue());
  }

  /**
   * Set a pointer property in a group of properties with a cleanup function that is called when the
   * property is deleted.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @param cleanup The function to call when this property is deleted, or NULL if no cleanup is
   *     necessary.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setPointerPropertyWithCleanup(
      SdlPropertiesId props, String name, MemorySegment value, SdlPropertyCleanupCallback cleanup)
      throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setPointerPropertyWithCleanup(
          arena,
          props.getValue(),
          name,
          value,
          cleanup.getCallbackAddress(),
          cleanup.getUserData());
    }
  }

  /**
   * Set a pointer property in a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setPointerProperty(int props, String name, MemorySegment value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setPointerProperty(arena, props, name, value);
    }
  }

  /**
   * Set a string property in a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setStringProperty(int props, String name, String value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setStringProperty(arena, props, name, value);
    }
  }

  /**
   * Set a number property in a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setNumberProperty(int props, String name, long value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setNumberProperty(arena, props, name, value);
    }
  }

  /**
   * Set a float property in a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setFloatProperty(int props, String name, float value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setFloatProperty(arena, props, name, value);
    }
  }

  /**
   * Set a boolean property in a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to modify.
   * @param value The new value of the property, or NULL to delete the property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setBooleanProperty(int props, String name, boolean value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setBooleanProperty(arena, props, name, value);
    }
  }

  /**
   * Return whether a property exists in a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @return Returns true if the property exists, or false if it doesn't.
   * @throws Throwable
   */
  public boolean hasProperty(int props, String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.hasProperty(arena, props, name);
    }
  }

  /**
   * Get the type of a property in a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @return Returns the SdlPropertyType ordinal of the property , or SDL_PROPERTY_TYPE_INVALID ordinal if it is not set.
   * @throws Throwable
   */
  public int getPropertyType(int props, String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getPropertyType(arena, props, name);
    }
  }

  /**
   * Get a pointer property from a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @param defaultValue The default value of the property.
   * @return Returns the value of the property, or default_value if it is not set or not a pointer
   *     property.
   * @throws Throwable
   */
  public MemorySegment getPointerProperty(int props, String name, MemorySegment defaultValue)
      throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getPointerProperty(arena, props, name, defaultValue);
    }
  }

  /**
   * Get a string property from a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @param defaultValue The default value of the property.
   * @return Returns the value of the property, or default_value if it is not set or not a string
   *     property.
   * @throws Throwable
   */
  public String getStringProperty(int props, String name, String defaultValue) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getStringProperty(arena, props, name, defaultValue);
    }
  }

  /**
   * Get a number property from a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @param defaultValue The default value of the property.
   * @return Returns the value of the property, or default_value if it is not set or not a number
   *     property.
   * @throws Throwable
   */
  public long getNumberProperty(int props, String name, long defaultValue) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getNumberProperty(arena, props, name, defaultValue);
    }
  }

  /**
   * Get a float property from a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @param defaultValue The default value of the property.
   * @return Returns the value of the property, or default_value if it is not set or not a float
   *     property.
   * @throws Throwable
   */
  public long getFloatProperty(int props, String name, float defaultValue) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getFloatProperty(arena, props, name, defaultValue);
    }
  }

  /**
   * Get a boolean property from a group of properties.
   *
   * @param props The properties to query.
   * @param name The name of the property to query.
   * @param defaultValue The default value of the property.
   * @return Returns the value of the property, or default_value if it is not set or not a boolean
   *     property.
   * @throws Throwable
   */
  public boolean getBooleanProperty(int props, String name, boolean defaultValue) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getBooleanProperty(arena, props, name, defaultValue);
    }
  }

  /**
   * Clear a property from a group of properties.
   *
   * @param props The properties to modify.
   * @param name The name of the property to clear.
   * @return Returns true on success or false on failure; call SDLError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean clearProperty(int props, String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.clearProperty(arena, props, name);
    }
  }

  /**
   * Enumerate the properties contained in a group of properties.
   *
   * @param props The properties to query.
   * @param callback The function to call for each property.
   * @param userData A pointer that is passed to callback.
   * @return Returns true on success or false on failure; call SDLError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean enumerateProperties(int props, MemorySegment callback, MemorySegment userData)
      throws Throwable {
    return SdlFuncs.enumerateProperties(props, callback, userData);
  }

  /**
   * Destroy a group of properties.
   *
   * @param props The properties to destroy.
   * @throws Throwable
   */
  public void destroyProperties(int props) throws Throwable {
    SdlFuncs.destroyProperties(props);
  }
}
