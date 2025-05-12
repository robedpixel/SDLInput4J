package robedpixel.sdl.video;


import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import robedpixel.sdl.properties.SdlPropertiesId;
import robedpixel.sdl.rect.SdlPointModel;
import robedpixel.sdl.rect.SdlRectModel;

public class SdlVideo {
  private final NativeSdlVideoFuncs SdlFuncs;
  private int cache = -1;

  public SdlVideo(Arena allocator) {
    SdlFuncs = NativeSdlVideoFuncs.getInstance(allocator);
  }

  /**
   * Get the number of video drivers compiled into SDL.
   *
   * @return Returns the number of built-in video drivers.
   * @throws Throwable
   */
  public int getNumVideoDrivers() throws Throwable {
    return SdlFuncs.getNumVideoDrivers();
  }

  /**
   * Get the name of a built-in video driver. Note that this function does not do bounds checking,
   * please make a call to getNumVideoDrivers to check for the maximum number of video drivers first
   *
   * @param index The index of a video driver.
   * @return Returns the name of the video driver with the given index.
   * @throws Throwable
   */
  @NonNull
  public String getVideoDriver(int index) throws Throwable {
    return SdlFuncs.getVideoDriver(index);
  }

  /**
   * Get the name of the currently initialized video driver.
   *
   * @return Returns the name of the current video driver or NULL if no driver has been initialized.
   * @throws Throwable
   */
  @Nullable
  public String getCurrentVideoDriver() throws Throwable {
    return SdlFuncs.getCurrentVideoDriver();
  }

  /**
   * Get the current system theme.
   *
   * @return Returns the current system theme as an ordinal of SdlSystemTheme, light, dark, or
   *     unknown.
   * @throws Throwable
   */
  public int getSystemTheme() throws Throwable {
    return SdlFuncs.getSystemTheme();
  }

  /**
   * Get a list of currently connected displays.
   *
   * @return Returns an array of display instance IDs or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  @Nullable
  public SdlDisplayIdArray getDisplays() throws Throwable {
    return SdlFuncs.getDisplays();
  }

  /**
   * Return the primary display.
   *
   * @return Returns the instance ID of the primary display on success or 0 on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  @NonNull
  public SdlDisplayId getPrimaryDisplay() throws Throwable {
    SdlDisplayId returnObject = new SdlDisplayId();
    returnObject.setValue(SdlFuncs.getPrimaryDisplay());
    return returnObject;
  }

  /**
   * Get the properties associated with a display.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns a valid property ID on success or 0 on failure; call SDLError.getError() for
   *     more information.
   * @throws Throwable
   */
  @NonNull
  public SdlPropertiesId getDisplayProperties(SdlDisplayId displayId) throws Throwable {
    SdlPropertiesId returnObject = new SdlPropertiesId();
    returnObject.setValue(SdlFuncs.getDisplayProperties(displayId.getValue()));
    return returnObject;
  }

  /**
   * Get the name of a display in UTF-8 encoding.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns the name of a display or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  @Nullable
  public String getDisplayName(SdlDisplayId displayId) throws Throwable {
    return SdlFuncs.getDisplayName(displayId.getValue());
  }

  /**
   * Get the desktop area represented by a display.
   *
   * @param displayId The instance ID of the display to query.
   * @param rect the SdlRectModel filled in with the display bounds.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean getDisplayBounds(SdlDisplayId displayId, SdlRectModel rect) throws Throwable {
    boolean returnObject = SdlFuncs.getDisplayBounds(displayId.getValue(), rect.getDataAddress());
    rect.updateValues();
    return returnObject;
  }

  /**
   * Get the usable desktop area represented by a display, in screen coordinates.
   *
   * @param displayId The instance ID of the display to query.
   * @param rect the SdlRectModel filled in with the display bounds.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean getDisplayUsableBounds(SdlDisplayId displayId, SdlRectModel rect)
      throws Throwable {
    boolean returnObject =
        SdlFuncs.getDisplayUsableBounds(displayId.getValue(), rect.getDataAddress());
    rect.updateValues();
    return returnObject;
  }

  /**
   * Get the orientation of a display when it is unrotated.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns the SDLDisplayOrientation ordinal value of the display, or
   *     SDL_ORIENTATION_UNKNOWN if it isn't available.
   * @throws Throwable
   */
  public int getNaturalDisplayOrientation(SdlDisplayId displayId) throws Throwable {
    return SdlFuncs.getNaturalDisplayOrientation(displayId.getValue());
  }

  /**
   * Get the orientation of a display.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns the SDLDisplayOrientation ordinal value of the display, or
   *     SDL_ORIENTATION_UNKNOWN if it isn't available.
   * @throws Throwable
   */
  public int getCurrentDisplayOrientation(SdlDisplayId displayId) throws Throwable {
    return SdlFuncs.getCurrentDisplayOrientation(displayId.getValue());
  }

  /**
   * Get the content scale of a display.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns the content scale of the display, or 0.0f on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public float getDisplayContentScale(SdlDisplayId displayId) throws Throwable {
    return SdlFuncs.getDisplayContentScale(displayId.getValue());
  }

  /**
   * Get a list of fullscreen display modes available on a display.
   *
   * @param displayId the instance ID of the display to query.
   * @return Returns an array of display mode objects or null on failure; call SdlError.getError()
   *     for more information
   * @throws Throwable
   */
  @Nullable
  public SdlDisplayModeArray getFullscreenDisplayModes(SdlDisplayId displayId) throws Throwable {
    return SdlFuncs.getFullscreenDisplayModes(displayId.getValue());
  }

  /**
   * Get the closest match to the requested display mode.
   *
   * @param displayId The instance ID of the display to query.
   * @param width The width in pixels of the desired display mode.
   * @param height The height in pixels of the desired display mode.
   * @param refreshRate The refresh rate of the desired display mode, or 0.0f for the desktop
   *     refresh rate.
   * @param includeHighDensityModes boolean to include high density modes in the search.
   * @param displayMode a displayMode object filled in with the closest display mode equal to or
   *     larger than the desired mode.
   * @return true on success or false on failure; call SdlError.getError() for more information.
   * @throws Throwable
   */
  public boolean getClosestFullscreenDisplayMode(
      SdlDisplayId displayId,
      int width,
      int height,
      float refreshRate,
      boolean includeHighDensityModes,
      SdlDisplayMode displayMode)
      throws Throwable {
    boolean returnObject =
        SdlFuncs.getClosestFullscreenDisplayMode(
            displayId.getValue(),
            width,
            height,
            refreshRate,
            includeHighDensityModes,
            displayMode.getDataAddress());
    displayMode.updateValues();
    return returnObject;
  }

  /**
   * Get information about the desktop's display mode.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns an object to the desktop display mode or null on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  @Nullable
  public SdlDisplayMode getDesktopDisplayMode(SdlDisplayId displayId) throws Throwable {
    MemorySegment segment = SdlFuncs.getDesktopDisplayMode(displayId.getValue());
    if (segment == MemorySegment.NULL) {
      return null;
    } else {
      return SdlDisplayMode.fromMemorySegment(segment);
    }
  }

  /**
   * Get information about the current display mode.
   *
   * @param displayId The instance ID of the display to query.
   * @return Returns an object to the current display mode or null on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  @Nullable
  public SdlDisplayMode getCurrentDisplayMode(SdlDisplayId displayId) throws Throwable {
    MemorySegment segment = SdlFuncs.getCurrentDisplayMode(displayId.getValue());
    if (segment == MemorySegment.NULL) {
      return null;
    } else {
      return SdlDisplayMode.fromMemorySegment(segment);
    }
  }

  /**
   * Get the display containing a point.
   *
   * @param point The point to query
   * @return Returns the instance ID of the display containing the point or 0 on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  @NonNull
  public SdlDisplayId getDisplayForPoint(SdlPointModel point) throws Throwable {
    SdlDisplayId returnObject = new SdlDisplayId();
    returnObject.setValue(SdlFuncs.getDisplayForPoint(point.getDataAddress()));
    return returnObject;
  }

  /**
   * Get the display primarily containing a rect.
   *
   * @param rect The rect to query.
   * @return Returns the instance ID of the display entirely containing the rect or closest to the
   *     center of the rect on success or 0 on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  @NonNull
  public SdlDisplayId getDisplayForRect(SdlRectModel rect) throws Throwable {
    SdlDisplayId returnObject = new SdlDisplayId();
    returnObject.setValue(SdlFuncs.getDisplayForRect(rect.getDataAddress()));
    return returnObject;
  }

  /**
   * Check whether the screensaver is currently enabled.
   *
   * @return Returns true if the screensaver is enabled, false if it is disabled.
   * @throws Throwable
   */
  public boolean screenSaverEnabled() throws Throwable {
    return SdlFuncs.screenSaverEnabled();
  }

  /**
   * Allow the screen to be blanked by a screen saver.
   *
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean enableScreenSaver() throws Throwable {
    return SdlFuncs.enableScreenSaver();
  }

  /**
   * Prevent the screen from being blanked by a screen saver.
   *
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean disableScreenSaver() throws Throwable {
    return SdlFuncs.disableScreenSaver();
  }
}
