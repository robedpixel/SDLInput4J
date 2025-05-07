package robedpixel.sdl.gamepad;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.guid.NativeSdlGuidModel;
import robedpixel.sdl.joystick.SdlJoystickId;
import robedpixel.sdl.joystick.SdlJoystickIdArray;

public class NativeSdlGamepadFuncs {
  private static volatile NativeSdlGamepadFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_AddGamepadMapping;
  private final MethodHandle SDL_AddGamepadMappingsFromFile;
  private final MethodHandle SDL_ReloadGamepadMappings;
  private final MethodHandle SDL_GetGamepadMappings;
  private final MethodHandle SDL_GetGamepadMappingForGUID;
  private final MethodHandle SDL_GetGamepadMapping;
  private final MethodHandle SDL_SetGamepadMapping;
  private final MethodHandle SDL_HasGamepad;
  private final MethodHandle SDL_GetGamepads;
  private final MethodHandle SDL_IsGamepad;
  private final MethodHandle SDL_GetGamepadNameForID;
  private final MethodHandle SDL_GetGamepadPathForID;
  private final MethodHandle SDL_GetGamepadPlayerIndexForID;
  private final MethodHandle SDL_GetGamepadGUIDForID;
  private final MethodHandle SDL_GetGamepadVendorForID;
  private final MethodHandle SDL_GetGamepadProductForID;
  private final MethodHandle SDL_GetGamepadProductVersionForID;
  private final MethodHandle SDL_GetGamepadTypeForID;
  private final MethodHandle SDL_GetRealGamepadTypeForID;
  private final MethodHandle SDL_GetGamepadMappingForID;
  private final MethodHandle SDL_OpenGamepad;
  private final MethodHandle SDL_GetGamepadFromID;
  private final MethodHandle SDL_GetGamepadFromPlayerIndex;
  private final MethodHandle SDL_GetGamepadProperties;
  private final MethodHandle SDL_GetGamepadID;
  private final MethodHandle SDL_GetGamepadName;
  private final MethodHandle SDL_GetGamepadPath;
  private final MethodHandle SDL_GetGamepadType;
  private final MethodHandle SDL_GetRealGamepadType;
  private final MethodHandle SDL_GetGamepadPlayerIndex;
  private final MethodHandle SDL_SetGamepadPlayerIndex;
  private final MethodHandle SDL_GetGamepadVendor;
  private final MethodHandle SDL_GetGamepadProduct;
  private final MethodHandle SDL_GetGamepadProductVersion;
  private final MethodHandle SDL_GetGamepadFirmwareVersion;
  private final MethodHandle SDL_GetGamepadSerial;
  private final MethodHandle SDL_GetGamepadSteamHandle;
  private final MethodHandle SDL_GetGamepadConnectionState;
  private final MethodHandle SDL_GetGamepadPowerInfo;
  private final MethodHandle SDL_GamepadConnected;
  private final MethodHandle SDL_GetGamepadJoystick;
  private final MethodHandle SDL_SetGamepadEventsEnabled;
  private final MethodHandle SDL_GamepadEventsEnabled;
  private final MethodHandle SDL_GetGamepadBindings;
  private final MethodHandle SDL_UpdateGamepads;
  private final MethodHandle SDL_GetGamepadTypeFromString;
  private final MethodHandle SDL_GetGamepadStringForType;
  private final MethodHandle SDL_GetGamepadAxisFromString;
  private final MethodHandle SDL_GetGamepadStringForAxis;
  private final MethodHandle SDL_GamepadHasAxis;
  private final MethodHandle SDL_GetGamepadAxis;
  private final MethodHandle SDL_GetGamepadButtonFromString;
  private final MethodHandle SDL_GetGamepadStringForButton;
  private final MethodHandle SDL_GamepadHasButton;
  private final MethodHandle SDL_GetGamepadButton;
  private final MethodHandle SDL_GetGamepadButtonLabelForType;
  private final MethodHandle SDL_GetGamepadButtonLabel;
  private final MethodHandle SDL_GetNumGamepadTouchpads;
  private final MethodHandle SDL_GetNumGamepadTouchpadFingers;
  private final MethodHandle SDL_GetGamepadTouchpadFinger;
  private final MethodHandle SDL_GamepadHasSensor;
  private final MethodHandle SDL_SetGamepadSensorEnabled;
  private final MethodHandle SDL_GamepadSensorEnabled;
  private final MethodHandle SDL_GetGamepadSensorDataRate;
  private final MethodHandle SDL_GetGamepadSensorData;
  private final MethodHandle SDL_RumbleGamepad;
  private final MethodHandle SDL_RumbleGamepadTriggers;
  private final MethodHandle SDL_SetGamepadLED;
  private final MethodHandle SDL_SendGamepadEffect;
  private final MethodHandle SDL_CloseGamepad;
  private final MethodHandle SDL_GetGamepadAppleSFSymbolsNameForButton;
  private final MethodHandle SDL_GetGamepadAppleSFSymbolsNameForAxis;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlGamepadFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_AddGamepadMapping =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_AddGamepadMapping").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_AddGamepadMappingsFromFile =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_AddGamepadMappingsFromFile").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_ReloadGamepadMappings =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ReloadGamepadMappings").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetGamepadMappings =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadMappings").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetGamepadMappingForGUID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadMappingForGUID").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, NativeSdlGuidModel.getStructLayout()));
    SDL_GetGamepadMapping =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadMapping").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_SetGamepadMapping =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetGamepadMapping").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_HasGamepad =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasGamepad").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_GetGamepads =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepads").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_IsGamepad =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_IsGamepad").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_GetGamepadNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadPathForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadPathForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadPlayerIndexForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadPlayerIndexForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetGamepadGUIDForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadGUIDForID").orElseThrow(),
                FunctionDescriptor.of(NativeSdlGuidModel.getStructLayout(), ValueLayout.JAVA_INT));
    SDL_GetGamepadVendorForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadVendorForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetGamepadProductForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadProductForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetGamepadProductVersionForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadProductVersionForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetGamepadTypeForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadTypeForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetRealGamepadTypeForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetRealGamepadTypeForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetGamepadMappingForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadMappingForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_OpenGamepad =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenGamepad").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadFromID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadFromID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadFromPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadFromPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetGamepadID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetGamepadName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetGamepadPath =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadPath").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetGamepadType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetRealGamepadType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("GetRealGamepadType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetGamepadPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_SetGamepadPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetGamepadPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadVendor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadVendor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetGamepadProduct =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadProduct").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetGamepadProductVersion =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadProductVersion").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetGamepadFirmwareVersion =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadFirmwareVersion").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_CloseGamepad =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CloseGamepad").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    SDL_GetGamepadAppleSFSymbolsNameForButton =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadAppleSFSymbolsNameForButton").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetGamepadAppleSFSymbolsNameForAxis =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGamepadAppleSFSymbolsNameForAxis").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
  }

  public synchronized int addGamepadMapping(Arena localAllocator, String mapping) throws Throwable {
    return (int) SDL_AddGamepadMapping.invoke(localAllocator.allocateFrom(mapping));
  }

  public synchronized int addGamepadMappingsFromFile(Arena localAllocator, String file)
      throws Throwable {
    return (int) SDL_AddGamepadMappingsFromFile.invoke(localAllocator.allocateFrom(file));
  }

  public synchronized boolean reloadGamepadMappings() throws Throwable {
    return (boolean) SDL_ReloadGamepadMappings.invoke();
  }

  public SdlGamepadMappingArray getGamepadMappings() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetGamepadMappings.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        return new SdlGamepadMappingArray(temp, arraySize);
      }
    }
  }

  public synchronized String getGamepadMappingForGUID(NativeSdlGuidModel guid) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadMappingForGUID.invoke(guid.getDataAddress());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      String returnObject = temp.reinterpret(Integer.MAX_VALUE).getString(0);
      NativeSdlLib.sdlFree(temp);
      return returnObject;
    }
  }

  public synchronized String getGamepadMapping(SdlGamepadDevice gamepad) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadMapping.invoke(gamepad.getAddress());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      String returnObject = temp.reinterpret(Integer.MAX_VALUE).getString(0);
      NativeSdlLib.sdlFree(temp);
      return returnObject;
    }
  }

  public synchronized boolean setGamepadMapping(
      Arena localAllocator, SdlJoystickId instanceId, String mapping) throws Throwable {
    if (mapping == null) {
      return (boolean) SDL_SetGamepadMapping.invoke(instanceId.getValue(), MemorySegment.NULL);
    } else {
      return (boolean)
          SDL_SetGamepadMapping.invoke(instanceId.getValue(), localAllocator.allocateFrom(mapping));
    }
  }

  public synchronized boolean hasGamepad() throws Throwable {
    return (boolean) SDL_HasGamepad.invoke();
  }

  public SdlJoystickIdArray getGamepads() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetGamepads.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        return new SdlJoystickIdArray(temp, arraySize);
      }
    }
  }

  public synchronized boolean isGamepad(SdlJoystickId instanceId) throws Throwable {
    return (boolean) SDL_IsGamepad.invoke(instanceId.getValue());
  }

  public synchronized String getGamepadNameForId(SdlJoystickId instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadNameForID.invoke(instanceId.getValue());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized String getGamepadPathForId(SdlJoystickId instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadPathForID.invoke(instanceId.getValue());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getGamepadPlayerIndexForId(SdlJoystickId instanceId) throws Throwable {
    return (int) SDL_GetGamepadPlayerIndexForID.invoke(instanceId.getValue());
  }

  public synchronized NativeSdlGuidModel getGamepadGuidForId(SdlJoystickId instanceId)
      throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadGUIDForID.invoke(instanceId.getValue());
    return NativeSdlGuidModel.fromSegment(temp);
  }

  public synchronized short getGamepadVendorForId(SdlJoystickId instanceId) throws Throwable {
    return (short) SDL_GetGamepadVendorForID.invoke(instanceId.getValue());
  }

  public synchronized short getGamepadProductForId(SdlJoystickId instanceId) throws Throwable {
    return (short) SDL_GetGamepadProductForID.invoke(instanceId.getValue());
  }

  public synchronized short getGamepadProductVersionForId(SdlJoystickId instanceId)
      throws Throwable {
    return (short) SDL_GetGamepadProductVersionForID.invoke(instanceId.getValue());
  }

  public synchronized int getGamepadTypeForId(SdlJoystickId instanceId) throws Throwable {
    return (int) SDL_GetGamepadTypeForID.invoke(instanceId.getValue());
  }

  public synchronized int getRealGamepadTypeForId(SdlJoystickId instanceId) throws Throwable {
    return (int) SDL_GetRealGamepadTypeForID.invoke(instanceId.getValue());
  }

  public synchronized String getGamepadMappingForId(SdlJoystickId instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadMappingForID.invoke(instanceId.getValue());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      String returnObject = temp.reinterpret(Integer.MAX_VALUE).getString(0);
      NativeSdlLib.sdlFree(temp);
      return returnObject;
    }
  }

  public synchronized SdlGamepadDevice openGamepad(SdlJoystickId instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_OpenGamepad.invoke(instanceId.getValue());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlGamepadDevice(temp, this);
    }
  }

  public synchronized SdlGamepadDeviceInstance getGamepadFromId(SdlJoystickId instanceId)
      throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadFromID.invoke(instanceId.getValue());
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlGamepadDeviceInstance(temp);
    }
  }

  public synchronized SdlGamepadDeviceInstance getGamepadFromPlayerIndex(int playerIndex)
      throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetGamepadFromPlayerIndex.invoke(playerIndex);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return new SdlGamepadDeviceInstance(temp);
    }
  }

  public synchronized void closeGamepad(MemorySegment gamepad) throws Throwable {
    SDL_CloseGamepad.invoke(gamepad);
  }

  public synchronized String getGamepadAppleSFSymbolsNameForButton(
      MemorySegment gamepad, int button) throws Throwable {
    MemorySegment temp =
        (MemorySegment) SDL_GetGamepadAppleSFSymbolsNameForButton.invoke(gamepad, button);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized String getGamepadAppleSFSymbolsNameForAxis(MemorySegment gamepad, int axis)
      throws Throwable {
    MemorySegment temp =
        (MemorySegment) SDL_GetGamepadAppleSFSymbolsNameForAxis.invoke(gamepad, axis);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public static NativeSdlGamepadFuncs getInstance(Arena allocator) {
    NativeSdlGamepadFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlGamepadFuncs(allocator);
      }
    }
    return result;
  }
}
