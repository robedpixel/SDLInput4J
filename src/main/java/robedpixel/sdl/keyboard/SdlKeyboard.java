package robedpixel.sdl.keyboard;

// TODO: complete javadoc for keyboard
import java.lang.foreign.Arena;
import org.jspecify.annotations.Nullable;
import robedpixel.sdl.rect.SdlRectModel;
import robedpixel.sdl.video.SdlWindow;

public class SdlKeyboard {
  private NativeSdlKeyboardFuncs SdlFuncs;

  public SdlKeyboard(Arena allocator) {
    SdlFuncs = NativeSdlKeyboardFuncs.getInstance(allocator);
  }

  public boolean hasKeyboard() throws Throwable {
    return SdlFuncs.hasKeyboard();
  }

  /**
   * Return whether a keyboard is currently connected.
   *
   * @return Returns true if a keyboard is connected, false otherwise.
   * @throws Throwable
   */
  @Nullable
  public SdlKeyboardIdArray getKeyboards() throws Throwable {
    return SdlFuncs.getKeyboards();
  }

  /**
   * Get the name of a keyboard.
   *
   * @param instanceId The keyboard instance Id.
   * @return Returns the name of the selected keyboard or nullL on failure; call SDL_GetError() for
   *     more information.
   * @throws Throwable
   */
  @Nullable
  public String getKeyboardNameForId(int instanceId) throws Throwable {
    return SdlFuncs.getKeyboardNameForId(instanceId);
  }

  /**
   * Query the window which currently has keyboard focus.
   *
   * @return Returns the window with keyboard focus.
   * @throws Throwable
   */
  @Nullable
  public SdlWindow getKeyboardFocus() throws Throwable {
    return SdlFuncs.getKeyboardFocus();
  }

  /**
   * Get a snapshot of the current state of the keyboard.
   *
   * @return Returns an array of key states.
   * @throws Throwable
   */
  public boolean @Nullable [] getKeyboardState() throws Throwable {
    return SdlFuncs.getKeyboardState();
  }

  /**
   * Clear the state of the keyboard.
   *
   * @throws Throwable
   */
  public void resetKeyboard() throws Throwable {
    SdlFuncs.resetKeyboard();
  }

  /**
   * Get the current key modifier state for the keyboard.
   *
   * @return Returns an OR'd combination of the modifier keys for the keyboard. See SdlKeymod for
   *     details.
   * @throws Throwable
   */
  public short getModState() throws Throwable {
    return SdlFuncs.getModState();
  }

  /**
   * Set the current key modifier state for the keyboard.
   *
   * @param modState the desired SdlKeymod for the keyboard.
   * @throws Throwable
   */
  public void setModState(short modState) throws Throwable {
    SdlFuncs.setModState(modState);
  }

  /**
   * Get the key code corresponding to the given scancode according to the current keyboard layout.
   *
   * @param scanCode The desired SdlScancode to query.
   * @param modState The modifier state to use when translating the scancode to a keycode.
   * @param keyEvent True if the keycode will be used in key events.
   * @return Returns the SdlKeycode that corresponds to the given SdlScancode.
   * @throws Throwable
   */
  public int getKeyFromScancode(int scanCode, short modState, boolean keyEvent) throws Throwable {
    return SdlFuncs.getKeyFromScancode(scanCode, modState, keyEvent);
  }

  /**
   * Get the scancode corresponding to the given key code according to the current keyboard layout.
   *
   * @param key The desired SDL_Keycode to query.
   * @param modState the modifier state that would be used when the scancode generates this key, may
   *     be null.
   * @return Returns the SdlScancode that corresponds to the given SdlKeycode.
   * @throws Throwable
   */
  public int getScancodeFromKey(int key, Short modState) throws Throwable {
    return SdlFuncs.getScancodeFromKey(key, modState);
  }

  public boolean setScancodeName(Arena localAllocator, int scanCode, String name) throws Throwable {
    return SdlFuncs.setScancodeName(localAllocator, scanCode, name);
  }

  @Nullable
  public String getScancodeName(int scanCode) throws Throwable {
    return SdlFuncs.getScancodeName(scanCode);
  }

  public int getScancodeFromName(Arena localAllocator, String name) throws Throwable {
    return SdlFuncs.getScancodeFromName(localAllocator, name);
  }

  @Nullable
  public String getKeyName(int key) throws Throwable {
    return SdlFuncs.getKeyName(key);
  }

  public int getKeyFromName(String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getKeyFromName(arena, name);
    }
  }

  public boolean startTextInput(SdlWindow window) throws Throwable {
    return SdlFuncs.startTextInput(window.getAddress());
  }

  public boolean startTextInputWithProperties(SdlWindow window, int props) throws Throwable {
    return SdlFuncs.startTextInputWithProperties(window.getAddress(), props);
  }

  public boolean textInputActive(SdlWindow window) throws Throwable {
    return SdlFuncs.textInputActive(window.getAddress());
  }

  public boolean stopTextInput(SdlWindow window) throws Throwable {
    return SdlFuncs.stopTextInput(window.getAddress());
  }

  public boolean clearComposition(SdlWindow window) throws Throwable {
    return SdlFuncs.clearComposition(window.getAddress());
  }

  public boolean setTextInputArea(SdlWindow window, SdlRectModel rect, int cursor)
      throws Throwable {
    return SdlFuncs.setTextInputArea(window.getAddress(), rect.getDataAddress(), cursor);
  }

  public boolean getTextInputArea(SdlWindow window, SdlRectModel rect, int cursor)
      throws Throwable {
    return SdlFuncs.getTextInputArea(window.getAddress(), rect.getDataAddress(), cursor);
  }

  public boolean hasScreenKeyboardSupport() throws Throwable {
    return SdlFuncs.hasScreenKeyboardSupport();
  }

  public boolean screenKeyboardShown(SdlWindow window) throws Throwable {
    return SdlFuncs.screenKeyboardShown(window.getAddress());
  }
}
