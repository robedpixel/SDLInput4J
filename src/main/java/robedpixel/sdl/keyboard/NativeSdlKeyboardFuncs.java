package robedpixel.sdl.keyboard;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import robedpixel.sdl.rect.SdlRect;
import robedpixel.sdl.rect.SdlRectModel;
import robedpixel.sdl.video.SdlWindow;

public class NativeSdlKeyboardFuncs {
  private static volatile NativeSdlKeyboardFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_HasKeyboard;
  private final MethodHandle SDL_GetKeyboards;
  private final MethodHandle SDL_GetKeyboardNameForID;
  private final MethodHandle SDL_GetKeyboardFocus;
  private final MethodHandle SDL_GetKeyboardState;
  private final MethodHandle SDL_ResetKeyboard;
  private final MethodHandle SDL_GetModState;
  private final MethodHandle SDL_SetModState;
  private final MethodHandle SDL_GetKeyFromScancode;
  private final MethodHandle SDL_GetScancodeFromKey;
  private final MethodHandle SDL_SetScancodeName;
  private final MethodHandle SDL_GetScancodeName;
  private final MethodHandle SDL_GetScancodeFromName;
  private final MethodHandle SDL_GetKeyName;
  private final MethodHandle SDL_GetKeyFromName;
  private final MethodHandle SDL_StartTextInput;
  private final MethodHandle SDL_StartTextInputWithProperties;
  private final MethodHandle SDL_TextInputActive;
  private final MethodHandle SDL_StopTextInput;
  private final MethodHandle SDL_ClearComposition;
  private final MethodHandle SDL_SetTextInputArea;
  private final MethodHandle SDL_GetTextInputArea;
  private final MethodHandle SDL_HasScreenKeyboardSupport;
  private final MethodHandle SDL_ScreenKeyboardShown;

  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
  private final MemorySegment tempShortAddress = objectAllocator.allocate(ValueLayout.JAVA_SHORT);
  public NativeSdlKeyboardFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_HasKeyboard =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasKeyboard").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_GetKeyboards =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyboards").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetKeyboardNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyboardNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetKeyboardFocus =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyboardFocus").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_GetKeyboardState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyboardState").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_ResetKeyboard =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ResetKeyboard").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_GetModState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetModState").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT));
    SDL_SetModState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetModState").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_SHORT));
    SDL_GetKeyFromScancode =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyFromScancode").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_BOOLEAN));
    SDL_GetScancodeFromKey =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetScancodeFromKey").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_SetScancodeName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetScancodeName").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetScancodeName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetScancodeName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetScancodeFromName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetScancodeFromName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetKeyName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetKeyFromName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetKeyFromName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_StartTextInput =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StartTextInput").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_StartTextInputWithProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StartTextInputWithProperties").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_TextInputActive =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_TextInputActive").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_StopTextInput =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StopTextInput").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_ClearComposition =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ClearComposition").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_SetTextInputArea =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetTextInputArea").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
    SDL_GetTextInputArea =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetTextInputArea").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_HasScreenKeyboardSupport =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasScreenKeyboardSupport").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_ScreenKeyboardShown =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ScreenKeyboardShown").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
  }

  public synchronized boolean hasKeyboard() throws Throwable {
    return (boolean) SDL_HasKeyboard.invoke();
  }

  public SdlKeyboardIdArray getKeyboards() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetKeyboards.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        return new SdlKeyboardIdArray(temp, arraySize);
      }
    }
  }

  public synchronized String getKeyboardNameForID(int instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetKeyboardNameForID.invoke(instanceId);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized SdlWindow getKeyboardFocus() throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetKeyboardFocus.invoke();
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlWindow(temp);
    }
  }

  public boolean[] getKeyboardState() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetKeyboardState.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        boolean[] returnObject = new boolean[arraySize];
        for (int i = 0; i < returnObject.length; i++) {
          returnObject[i] = temp.getAtIndex(ValueLayout.JAVA_BOOLEAN, i);
        }
        return returnObject;
      }
    }
  }

  public synchronized void resetKeyboard() throws Throwable {
    SDL_ResetKeyboard.invoke();
  }

  public synchronized short getModState() throws Throwable {
    return (short) SDL_GetModState.invoke();
  }

  public synchronized void setModState(short modState) throws Throwable {
    SDL_SetModState.invoke(modState);
  }

  public synchronized int getKeyFromScancode(int scanCode, short modState, boolean keyEvent)
      throws Throwable {
    return (int) SDL_GetKeyFromScancode.invoke(scanCode, modState, keyEvent);
  }

  public int getScancodeFromKey(int key, short modState) throws Throwable {
    synchronized (addressMutex) {
      tempShortAddress.set(ValueLayout.JAVA_SHORT, 0, modState);
      return (int) SDL_GetScancodeFromKey.invoke(key, tempShortAddress);
    }
  }

  public boolean setScancodeName(Arena localAllocator, int scanCode,String name) throws Throwable {
    return (boolean)SDL_SetScancodeName.invoke(scanCode,localAllocator.allocateFrom(name));
  }
  public String getScancodeName(int scanCode) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetScancodeName.invoke(scanCode);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }
  public int getScancodeFromName(Arena localAllocator,String name) throws Throwable {
    return (int)SDL_GetScancodeFromName.invoke(localAllocator.allocateFrom(name));
  }
  public String getKeyName(int key) throws Throwable {
      MemorySegment charArrayAddress = (MemorySegment) SDL_GetKeyName.invoke(key);
      if (charArrayAddress == MemorySegment.NULL) {
        return null;
      } else {
        return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
      }
    }
  public int getKeyFromName(Arena localAllocator,String name) throws Throwable {
    return (int)SDL_GetKeyFromName.invoke(localAllocator.allocateFrom(name));
  }
  public boolean startTextInput(MemorySegment window) throws Throwable {
    return (boolean) SDL_StartTextInput.invoke(window);
  }
  public boolean startTextInputWithProperties(MemorySegment window,int props) throws Throwable {
    return (boolean) SDL_StartTextInputWithProperties.invoke(window,props);
  }
  public boolean textInputActive(MemorySegment window) throws Throwable {
    return (boolean) SDL_TextInputActive.invoke(window);
  }
  public boolean stopTextInput(MemorySegment window) throws Throwable {
    return (boolean) SDL_StopTextInput.invoke(window);
  }
  public boolean clearComposition(MemorySegment window) throws Throwable {
    return (boolean) SDL_ClearComposition.invoke(window);
  }
  public boolean setTextInputArea(MemorySegment window, MemorySegment rect, int cursor) throws Throwable {
    return (boolean) SDL_SetTextInputArea.invoke(window,rect,cursor);
  }
  public boolean getTextInputArea(MemorySegment window, MemorySegment rect, int cursor) throws Throwable {
    return  (boolean) SDL_GetTextInputArea.invoke(window,rect,cursor);
  }
  public boolean hasScreenKeyboardSupport() throws Throwable {
    return (boolean) SDL_HasScreenKeyboardSupport.invoke();
  }
  public boolean screenKeyboardShown(MemorySegment window) throws Throwable {
    return (boolean) SDL_ScreenKeyboardShown.invoke(window);
  }
  public static NativeSdlKeyboardFuncs getInstance(Arena allocator) {
    NativeSdlKeyboardFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlKeyboardFuncs(allocator);
      }
    }
    return result;
  }
}
