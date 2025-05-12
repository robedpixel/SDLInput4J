package robedpixel.sdl.mouse;
//TODO: add nullablility annotations
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import robedpixel.sdl.video.SdlWindow;

public class SdlMouse {
  private final NativeSdlMouseFuncs SdlFuncs;

  public SdlMouse(Arena allocator) {
    SdlFuncs = NativeSdlMouseFuncs.getInstance(allocator);
  }

  /**
   * Return whether a mouse is currently connected.
   *
   * @return Returns true if a mouse is connected, false otherwise.
   * @throws Throwable
   */
  public boolean hasMouse() throws Throwable {
    return SdlFuncs.hasMouse();
  }

  /**
   * Get a list of currently connected mice.
   *
   * @return Returns an array of mouse instance IDs or null on failure; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public int[] getMice() throws Throwable {
    return SdlFuncs.getMice();
  }

  /**
   * Get the name of a mouse.
   *
   * @param instanceId The mouse instance ID.
   * @return Returns the name of the selected mouse, or null on failure; call SdlError.getError()
   *     for more information.
   * @throws Throwable
   */
  public String getMouseNameForID(int instanceId) throws Throwable {
    return SdlFuncs.getMouseNameForID(instanceId);
  }

  /**
   * Get the window which currently has mouse focus.
   *
   * @return Returns the window with mouse focus.
   * @throws Throwable
   */
  public SdlWindow getMouseFocus() throws Throwable {
    MemorySegment windowSegment = SdlFuncs.getMouseFocus();
    return new SdlWindow(windowSegment);
  }

  /**
   * Query SDL's cache for the synchronous mouse button state and the window-relative SDL-cursor
   * position.
   *
   * @param state Object to store the synchronous mouse button state and the window-relative
   *     SDL-cursor position.
   * @throws Throwable
   */
  public void getMouseState(SdlMouseState state) throws Throwable {
    SdlFuncs.getMouseState(state);
  }

  /**
   * Query the platform for the asynchronous mouse button state and the desktop-relative
   * platform-cursor position.
   *
   * @param state Object to store the asynchronous mouse button state and the desktop-relative
   *     platform-cursor position.
   * @throws Throwable
   */
  public void getGlobalMouseState(SdlMouseState state) throws Throwable {
    SdlFuncs.getGlobalMouseState(state);
  }

  /**
   * Query SDL's cache for the synchronous mouse button state and accumulated mouse delta since last
   * call.
   *
   * @param state Object to store the synchronous mouse button state and accumulated mouse delta
   *     since last call.
   * @throws Throwable
   */
  public void getRelativeMouseState(SdlMouseState state) throws Throwable {
    SdlFuncs.getRelativeMouseState(state);
  }

  /**
   * Move the mouse cursor to the given position within the window.
   *
   * @param window The window to move the mouse into, or null for the current mouse focus.
   * @param x The x coordinate within the window.
   * @param y The y coordinate within the window.
   * @throws Throwable
   */
  public void warpMouseInWindow(SdlWindow window, float x, float y) throws Throwable {
    MemorySegment windowAddress;
    if (window == null) {
      windowAddress = MemorySegment.NULL;
    } else {
      windowAddress = window.getAddress();
    }
    SdlFuncs.warpMouseInWindow(windowAddress, x, y);
  }

  /**
   * Move the mouse to the given position in global screen space.
   *
   * @param x The x coordinate.
   * @param y The y coordinate.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean warpMouseGlobal(float x, float y) throws Throwable {
    return SdlFuncs.warpMouseGlobal(x, y);
  }

  /**
   * Set a user-defined function by which to transform relative mouse inputs.
   *
   * @param callback A callback used to transform relative mouse motion, or null for default
   *     behavior.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setRelativeMouseTransform(SdlMouseMotionCallback callback) throws Throwable {
    if (callback == null) {
      return SdlFuncs.setRelativeMouseTransform(MemorySegment.NULL, MemorySegment.NULL);
    } else {
      return SdlFuncs.setRelativeMouseTransform(
          callback.getCallbackAddress(), callback.getUserData());
    }
  }

  /**
   * Query whether relative mouse mode is enabled for a window.
   *
   * @param window The window to change.
   * @param enabled True to enable relative mode, false to disable.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setWindowRelativeMouseMode(SdlWindow window, boolean enabled) throws Throwable {
    return SdlFuncs.setWindowRelativeMouseMode(window.getAddress(), enabled);
  }

  /**
   * Query whether relative mouse mode is enabled for a window.
   *
   * @param window The window to query.
   * @return Returns true if relative mode is enabled for a window or false otherwise.
   * @throws Throwable
   */
  public boolean getWindowRelativeMouseMode(SdlWindow window) throws Throwable {
    return SdlFuncs.getWindowRelativeMouseMode(window.getAddress());
  }

  /**
   * Capture the mouse and to track input outside an SDL window.
   *
   * @param enabled True to enable capturing, false to disable.
   * @return
   * @throws Throwable
   */
  public boolean captureMouse(boolean enabled) throws Throwable {
    return SdlFuncs.captureMouse(enabled);
  }

  /**
   * Create a cursor using the specified bitmap data and mask (in MSB format).
   *
   * @param bitmap Bitmap data used to create the cursor
   * @return Returns a new cursor with the specified parameters on success or null on failure; call
   *     SdlError.getError() for more information.
   * @throws Throwable
   */
  public SdlCursorInstance createCursor(SdlCursorBitmap bitmap) throws Throwable {
    MemorySegment cursorAddress =
        SdlFuncs.createCursor(
            bitmap.getData(),
            bitmap.getMask(),
            bitmap.getWidth(),
            bitmap.getHeight(),
            bitmap.getHotX(),
            bitmap.getHotY());
    if (cursorAddress == MemorySegment.NULL) {
      return null;
    }
    return new SdlCursorInstance(cursorAddress, this.SdlFuncs);
  }

  /*public MemorySegment  createColorCursor(MemorySegment surface,int hot_x, int hot_y){

  }*/

  /**
   * Create a system cursor.
   *
   * @param instanceId an SdlSystemCursor enum value.
   * @return Returns a cursor on success or null on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public SdlCursorInstance createSystemCursor(SdlSystemCursor instanceId) throws Throwable {
    MemorySegment cursorAddress = SdlFuncs.createSystemCursor(instanceId.ordinal());
    if (cursorAddress == MemorySegment.NULL) {
      return null;
    }
    return new SdlCursorInstance(cursorAddress, this.SdlFuncs);
  }

  /**
   * Set the active cursor.
   *
   * @param cursor A cursor to make active.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setCursor(SdlCursor cursor) throws Throwable {
    return SdlFuncs.setCursor(cursor.getAddress());
  }

  /**
   * Get the active cursor.
   *
   * @return Returns the active cursor or null if there is no mouse.
   * @throws Throwable
   */
  public SdlCursorReference getCursor() throws Throwable {
    MemorySegment cursorAddress = SdlFuncs.getCursor();
    if (cursorAddress == MemorySegment.NULL) {
      return null;
    }
    return new SdlCursorReference(cursorAddress);
  }

  /**
   * Get the default cursor.
   *
   * @return Returns the default cursor on success or NULL on failuree; call SdlError.getError() for
   *     more information.
   * @throws Throwable
   */
  public SdlCursorReference getDefaultCursor() throws Throwable {
    MemorySegment cursorAddress = SdlFuncs.getDefaultCursor();
    return new SdlCursorReference(cursorAddress);
  }

  /**
   * Show the cursor.
   *
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean showCursor() throws Throwable {
    return SdlFuncs.showCursor();
  }

  /**
   * Hide the cursor.
   *
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean hideCursor() throws Throwable {
    return SdlFuncs.hideCursor();
  }

  /**
   * Return whether the cursor is currently being shown.
   *
   * @return Returns true if the cursor is being shown, or false if the cursor is hidden.
   * @throws Throwable
   */
  public boolean cursorVisible() throws Throwable {
    return SdlFuncs.cursorVisible();
  }
}
