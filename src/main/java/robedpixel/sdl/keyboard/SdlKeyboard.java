package robedpixel.sdl.keyboard;

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

  @Nullable
  public SdlKeyboardIdArray getKeyboards() throws Throwable {
    return SdlFuncs.getKeyboards();
  }

  @Nullable
  public String getKeyboardNameForId(int instanceId) throws Throwable {
    return SdlFuncs.getKeyboardNameForId(instanceId);
  }

  @Nullable
  public SdlWindow getKeyboardFocus() throws Throwable {
    return SdlFuncs.getKeyboardFocus();
  }

  public boolean @Nullable [] getKeyboardState() throws Throwable {
    return SdlFuncs.getKeyboardState();
  }

  public void resetKeyboard() throws Throwable {
    SdlFuncs.resetKeyboard();
  }

  public short getModState() throws Throwable {
    return SdlFuncs.getModState();
  }

  public void setModState(short modState) throws Throwable {
    SdlFuncs.setModState(modState);
  }

  public int getKeyFromScancode(int scanCode, short modState, boolean keyEvent) throws Throwable {
    return SdlFuncs.getKeyFromScancode(scanCode, modState, keyEvent);
  }

  public int getScancodeFromKey(int key, short modState) throws Throwable {
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
