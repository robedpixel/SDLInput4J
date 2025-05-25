package robedpixel.sdl.joystick;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import robedpixel.sdl.guid.NativeSdlGuidModel;

class NativeSdlJoystickFuncs {
  private static volatile NativeSdlJoystickFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_LockJoysticks;
  private final MethodHandle SDL_UnlockJoysticks;
  private final MethodHandle SDL_HasJoystick;
  private final MethodHandle SDL_GetJoysticks;
  private final MethodHandle SDL_GetJoystickNameForID;
  private final MethodHandle SDL_GetJoystickPathForID;
  private final MethodHandle SDL_GetJoystickPlayerIndexForID;
  private final MethodHandle SDL_GetJoystickGUIDForID;
  private final MethodHandle SDL_GetJoystickVendorForID;
  private final MethodHandle SDL_GetJoystickProductForID;
  private final MethodHandle SDL_GetJoystickProductVersionForID;
  private final MethodHandle SDL_GetJoystickTypeForID;
  private final MethodHandle SDL_OpenJoystick;
  private final MethodHandle SDL_GetJoystickFromID;
  private final MethodHandle SDL_GetJoystickFromPlayerIndex;
  private final MethodHandle SDL_AttachVirtualJoystick;
  private final MethodHandle SDL_DetachVirtualJoystick;
  private final MethodHandle SDL_IsJoystickVirtual;
  private final MethodHandle SDL_SetJoystickVirtualAxis;
  private final MethodHandle SDL_SetJoystickVirtualBall;
  private final MethodHandle SDL_SetJoystickVirtualButton;
  private final MethodHandle SDL_SetJoystickVirtualHat;
  private final MethodHandle SDL_SetJoystickVirtualTouchpad;
  private final MethodHandle SDL_SendJoystickVirtualSensorData;
  private final MethodHandle SDL_GetJoystickProperties;
  private final MethodHandle SDL_GetJoystickName;
  private final MethodHandle SDL_GetJoystickPath;
  private final MethodHandle SDL_GetJoystickPlayerIndex;
  private final MethodHandle SDL_SetJoystickPlayerIndex;
  private final MethodHandle SDL_GetJoystickGUID;
  private final MethodHandle SDL_GetJoystickVendor;
  private final MethodHandle SDL_GetJoystickProduct;
  private final MethodHandle SDL_GetJoystickProductVersion;
  private final MethodHandle SDL_GetJoystickFirmwareVersion;
  private final MethodHandle SDL_GetJoystickSerial;
  private final MethodHandle SDL_GetJoystickType;
  private final MethodHandle SDL_GetJoystickGUIDInfo;
  private final MethodHandle SDL_JoystickConnected;
  private final MethodHandle SDL_GetJoystickID;
  private final MethodHandle SDL_GetNumJoystickAxes;
  private final MethodHandle SDL_GetNumJoystickBalls;
  private final MethodHandle SDL_GetNumJoystickHats;
  private final MethodHandle SDL_GetNumJoystickButtons;
  private final MethodHandle SDL_SetJoystickEventsEnabled;
  private final MethodHandle SDL_JoystickEventsEnabled;
  private final MethodHandle SDL_UpdateJoysticks;
  private final MethodHandle SDL_GetJoystickAxis;
  private final MethodHandle SDL_GetJoystickAxisInitialState;
  private final MethodHandle SDL_GetJoystickBall;
  private final MethodHandle SDL_GetJoystickHat;
  private final MethodHandle SDL_GetJoystickButton;
  private final MethodHandle SDL_RumbleJoystick;
  private final MethodHandle SDL_RumbleJoystickTriggers;
  private final MethodHandle SDL_SetJoystickLED;
  private final MethodHandle SDL_SendJoystickEffect;

  private final MethodHandle SDL_CloseJoystick;
  private final MethodHandle SDL_GetJoystickConnectionState;
  private final MethodHandle SDL_GetJoystickPowerInfo;

  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
  private final MemorySegment temp2IntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);
  private final MemorySegment tempShortAddress = objectAllocator.allocate(ValueLayout.JAVA_SHORT);
  private final MemorySegment temp2ShortAddress = objectAllocator.allocate(ValueLayout.JAVA_SHORT);
  private final MemorySegment temp3ShortAddress = objectAllocator.allocate(ValueLayout.JAVA_SHORT);
  private final MemorySegment temp4ShortAddress = objectAllocator.allocate(ValueLayout.JAVA_SHORT);

  public NativeSdlJoystickFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_LockJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_LockJoysticks").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_UnlockJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UnlockJoysticks").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_HasJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasJoystick").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_GetJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoysticks").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetJoystickNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickPathForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPathForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickPlayerIndexForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPlayerIndexForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_GetJoystickGUIDForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickGUIDForID").orElseThrow(),
                FunctionDescriptor.of(NativeSdlGuidModel.getStructLayout(), ValueLayout.JAVA_INT));
    SDL_GetJoystickVendorForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickVendorForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetJoystickProductForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickProductForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetJoystickProductVersionForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickProductVersionForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.JAVA_INT));
    SDL_GetJoystickTypeForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickTypeForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_OpenJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickFromID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickFromID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickFromPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickFromPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_AttachVirtualJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_AttachVirtualJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_DetachVirtualJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_DetachVirtualJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_IsJoystickVirtual =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_IsJoystickVirtual").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_SetJoystickVirtualAxis =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickVirtualAxis").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_SHORT));
    SDL_SetJoystickVirtualBall =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickVirtualBall").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_SHORT));
    SDL_SetJoystickVirtualButton =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickVirtualButton").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_BOOLEAN));
    SDL_SetJoystickVirtualHat =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickVirtualHat").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_BYTE));
    SDL_SetJoystickVirtualTouchpad =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickVirtualTouchpad").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_FLOAT,
                    ValueLayout.JAVA_FLOAT,
                    ValueLayout.JAVA_FLOAT));
    SDL_SendJoystickVirtualSensorData =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SendJoystickVirtualSensorData").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_LONG,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
    SDL_GetJoystickProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetJoystickName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetJoystickPath =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPath").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetJoystickPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_SetJoystickPlayerIndex =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickPlayerIndex").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickGUID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickGUID").orElseThrow(),
                FunctionDescriptor.of(NativeSdlGuidModel.getStructLayout(), ValueLayout.ADDRESS));
    SDL_GetJoystickVendor =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickVendor").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetJoystickProduct =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickProduct").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetJoystickProductVersion =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickProductVersion").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetJoystickFirmwareVersion =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickFirmwareVersion").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS));
    SDL_GetJoystickSerial =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickSerial").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetJoystickType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickType").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetJoystickGUIDInfo =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickGUIDInfo").orElseThrow(),
                FunctionDescriptor.ofVoid(
                    NativeSdlGuidModel.getStructLayout(),
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_JoystickConnected =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_JoystickConnected").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_GetJoystickID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNumJoystickAxes =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumJoystickAxes").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNumJoystickBalls =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumJoystickBalls").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNumJoystickHats =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumJoystickHats").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNumJoystickButtons =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumJoystickButtons").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_SetJoystickEventsEnabled =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickEventsEnabled").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_BOOLEAN));
    SDL_JoystickEventsEnabled =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_JoystickEventsEnabled").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_UpdateJoysticks =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UpdateJoysticks").orElseThrow(), FunctionDescriptor.ofVoid());
    SDL_GetJoystickAxis =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickAxis").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_SHORT, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickAxisInitialState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickAxisInitialState").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS));
    SDL_GetJoystickBall =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickBall").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_GetJoystickHat =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickBall").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BYTE, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetJoystickButton =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickButton").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN));
    SDL_RumbleJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_RumbleJoystick").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_INT));
    SDL_RumbleJoystickTriggers =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_RumbleJoystickTriggers").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_SHORT,
                    ValueLayout.JAVA_INT));
    SDL_SetJoystickLED =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetJoystickLED").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_BYTE,
                    ValueLayout.JAVA_BYTE,
                    ValueLayout.JAVA_BYTE));
    SDL_SendJoystickEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SendJoystickEffect").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
    SDL_CloseJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CloseJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
    SDL_GetJoystickConnectionState =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickConnectionState").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetJoystickPowerInfo =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetJoystickPowerInfo").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
  }

  public synchronized void lockJoysticks() throws Throwable {
    SDL_LockJoysticks.invoke();
  }

  public synchronized void unlockJoysticks() throws Throwable {
    SDL_UnlockJoysticks.invoke();
  }

  public synchronized boolean hasJoystick() throws Throwable {
    return (boolean) SDL_HasJoystick.invoke();
  }

  @Nullable
  public SdlJoystickIdArray getJoysticks() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetJoysticks.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        temp = temp.reinterpret(arraySize * ValueLayout.JAVA_INT.byteSize());
        return new SdlJoystickIdArray(temp, arraySize);
      }
    }
  }

  @Nullable
  public synchronized String getJoystickNameForId(int instanceId) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetJoystickNameForID.invoke(instanceId);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  @Nullable
  public synchronized String getJoystickPathForId(int instanceId) throws Throwable {
    MemorySegment charArrayAddress = (MemorySegment) SDL_GetJoystickPathForID.invoke(instanceId);
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getJoystickPlayerIndexForId(int instanceId) throws Throwable {
    return (int) SDL_GetJoystickPlayerIndexForID.invoke(instanceId);
  }

  @Nullable
  public synchronized NativeSdlGuidModel getJoystickGuidForId(int instanceId) throws Throwable {
    MemorySegment guidAddress = (MemorySegment) SDL_GetJoystickGUIDForID.invoke(instanceId);
    if (guidAddress == MemorySegment.NULL) {
      return null;
    } else {
      return NativeSdlGuidModel.fromSegment(guidAddress);
    }
  }

  public synchronized short getJoystickVendorForId(int instanceId) throws Throwable {
    return (short) SDL_GetJoystickVendorForID.invoke(instanceId);
  }

  public synchronized short getJoystickProductForId(int instanceId) throws Throwable {
    return (short) SDL_GetJoystickProductForID.invoke(instanceId);
  }

  public synchronized short getJoystickProductVersionForId(int instanceId) throws Throwable {
    return (short) SDL_GetJoystickProductVersionForID.invoke(instanceId);
  }

  public synchronized int getJoystickTypeForId(int instanceId) throws Throwable {
    return (int) SDL_GetJoystickTypeForID.invoke(instanceId);
  }

  @NonNull
  public synchronized MemorySegment openJoystick(int instanceId) throws Throwable {
    return (MemorySegment) SDL_OpenJoystick.invoke(instanceId);
  }

  @NonNull
  public synchronized MemorySegment getJoystickFromId(int instanceId) throws Throwable {
    return (MemorySegment) SDL_GetJoystickFromID.invoke(instanceId);
  }

  @NonNull
  public synchronized MemorySegment getJoystickFromPlayerIndex(int playerIndex) throws Throwable {
    return (MemorySegment) SDL_GetJoystickFromPlayerIndex.invoke(playerIndex);
  }

  public synchronized int attachVirtualJoystick(MemorySegment desc) throws Throwable {
    return (int) SDL_AttachVirtualJoystick.invoke(desc);
  }

  public synchronized boolean detachVirtualJoystick(int instanceId) throws Throwable {
    return (boolean) SDL_DetachVirtualJoystick.invoke(instanceId);
  }

  public synchronized boolean isJoystickVirtual(int instanceId) throws Throwable {
    return (boolean) SDL_IsJoystickVirtual.invoke(instanceId);
  }

  public synchronized boolean setJoystickVirtualAxis(MemorySegment joystick, int axis, short value)
      throws Throwable {
    return (boolean) SDL_SetJoystickVirtualAxis.invoke(joystick, axis, value);
  }

  public synchronized boolean setJoystickVirtualBall(
      MemorySegment joystick, int ball, short xrel, short yrel) throws Throwable {
    return (boolean) SDL_SetJoystickVirtualBall.invoke(joystick, ball, xrel, yrel);
  }

  public synchronized boolean setJoystickVirtualButton(
      MemorySegment joystick, int button, boolean down) throws Throwable {
    return (boolean) SDL_SetJoystickVirtualButton.invoke(joystick, button, down);
  }

  public synchronized boolean setJoystickVirtualHat(MemorySegment joystick, int hat, byte uValue)
      throws Throwable {
    return (boolean) SDL_SetJoystickVirtualHat.invoke(joystick, hat, uValue);
  }

  public synchronized boolean setJoystickVirtualTouchpad(
      MemorySegment joystick,
      int touchpad,
      int finger,
      boolean down,
      float x,
      float y,
      float pressure)
      throws Throwable {
    return (boolean)
        SDL_SetJoystickVirtualTouchpad.invoke(joystick, touchpad, finger, down, x, y, pressure);
  }

  public synchronized boolean sendJoystickVirtualSensorData(
      MemorySegment joystick, int type, long uSensorTimestamp, MemorySegment data, int numValues)
      throws Throwable {
    return (boolean)
        SDL_SendJoystickVirtualSensorData.invoke(joystick, type, uSensorTimestamp, data, numValues);
  }

  public synchronized int getJoystickProperties(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetJoystickProperties.invoke(joystick);
  }

  @Nullable
  public synchronized String getJoystickName(MemorySegment joystick) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetJoystickName.invoke(joystick);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  @Nullable
  public synchronized String getJoystickPath(MemorySegment joystick) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetJoystickPath.invoke(joystick);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getJoystickPlayerIndex(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetJoystickPlayerIndex.invoke(joystick);
  }

  public synchronized boolean setJoystickPlayerIndex(MemorySegment joystick, int playerIndex)
      throws Throwable {
    return (boolean) SDL_SetJoystickPlayerIndex.invoke(joystick, playerIndex);
  }

  @Nullable
  public synchronized NativeSdlGuidModel getJoystickGuid(MemorySegment joystick) throws Throwable {
    MemorySegment guidAddress = (MemorySegment) SDL_GetJoystickGUID.invoke(joystick);
    if (guidAddress == MemorySegment.NULL) {
      return null;
    } else {
      return NativeSdlGuidModel.fromSegment(guidAddress);
    }
  }

  public synchronized short getJoystickVendor(MemorySegment joystick) throws Throwable {
    return (short) SDL_GetJoystickVendor.invoke(joystick);
  }

  public synchronized short getJoystickProduct(MemorySegment joystick) throws Throwable {
    return (short) SDL_GetJoystickProduct.invoke(joystick);
  }

  public synchronized short getJoystickProductVersion(MemorySegment joystick) throws Throwable {
    return (short) SDL_GetJoystickProductVersion.invoke(joystick);
  }

  public synchronized short getJoystickFirmwareVersion(MemorySegment joystick) throws Throwable {
    return (short) SDL_GetJoystickFirmwareVersion.invoke(joystick);
  }

  @Nullable
  public synchronized String getJoystickSerial(MemorySegment joystick) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetJoystickSerial.invoke(joystick);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized int getJoystickType(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetJoystickType.invoke(joystick);
  }

  public void getJoystickGuidInfo(MemorySegment guid, SdlJoystickGuidInfo guidInfo)
      throws Throwable {
    synchronized (addressMutex) {
      SDL_GetJoystickGUIDInfo.invoke(
          guid, tempShortAddress, temp2ShortAddress, temp3ShortAddress, temp4ShortAddress);
      guidInfo.setVendor(tempShortAddress.get(ValueLayout.JAVA_SHORT, 0));
      guidInfo.setProduct(temp2ShortAddress.get(ValueLayout.JAVA_SHORT, 0));
      guidInfo.setVersion(temp3ShortAddress.get(ValueLayout.JAVA_SHORT, 0));
      guidInfo.setCrc16(temp4ShortAddress.get(ValueLayout.JAVA_SHORT, 0));
    }
  }

  public synchronized boolean joystickConnected(MemorySegment joystick) throws Throwable {
    return (boolean) SDL_JoystickConnected.invoke(joystick);
  }

  public synchronized int getJoystickID(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetJoystickID.invoke(joystick);
  }

  public synchronized int getNumJoystickAxes(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetNumJoystickAxes.invoke(joystick);
  }

  public synchronized int getNumJoystickBalls(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetNumJoystickBalls.invoke(joystick);
  }

  public synchronized int getNumJoystickHats(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetNumJoystickHats.invoke(joystick);
  }

  public synchronized int getNumJoystickButtons(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetNumJoystickButtons.invoke(joystick);
  }

  public synchronized void setJoystickEventsEnabled(boolean enabled) throws Throwable {
    SDL_SetJoystickEventsEnabled.invoke(enabled);
  }

  public synchronized boolean joystickEventsEnabled() throws Throwable {
    return (boolean) SDL_JoystickEventsEnabled.invoke();
  }

  public synchronized void updateJoysticks() throws Throwable {
    SDL_UpdateJoysticks.invoke();
  }

  public synchronized short getJoystickAxis(MemorySegment joystick, int axis) throws Throwable {
    return (short) SDL_GetJoystickAxis.invoke(joystick, axis);
  }

  public synchronized boolean getJoystickAxisInitialState(
      MemorySegment joystick, int axis, SdlJoystickAxisReading state) throws Throwable {
    boolean returnObject =
        (boolean) SDL_GetJoystickAxisInitialState.invoke(joystick, axis, tempShortAddress);
    if (returnObject) {
      state.setState(tempShortAddress.get(ValueLayout.JAVA_SHORT, 0));
    }
    return returnObject;
  }

  public synchronized boolean getJoystickBall(
      MemorySegment joystick, int ball, SdlJoystickBallReading ballReading) throws Throwable {
    boolean returnObject =
        (boolean) SDL_GetJoystickBall.invoke(joystick, ball, tempIntAddress, temp2IntAddress);
    ballReading.setDx(tempIntAddress.get(ValueLayout.JAVA_INT, 0));
    ballReading.setDy(temp2IntAddress.get(ValueLayout.JAVA_INT, 0));
    return returnObject;
  }

  public synchronized byte getJoystickHat(MemorySegment joystick, int hat) throws Throwable {
    return (byte) SDL_GetJoystickHat.invoke(joystick, hat);
  }

  public synchronized boolean getJoystickButton(MemorySegment joystick, int button)
      throws Throwable {
    return (boolean) SDL_GetJoystickButton.invoke(joystick, button);
  }

  public synchronized boolean rumbleJoystick(
      MemorySegment joystick,
      short uLowFrequencyRumble,
      short uHighFrequencyRumble,
      int uDurationMs)
      throws Throwable {
    return (boolean)
        SDL_RumbleJoystick.invoke(joystick, uLowFrequencyRumble, uHighFrequencyRumble, uDurationMs);
  }

  public synchronized boolean rumbleJoystickTriggers(
      MemorySegment joystick, short uLeftRumble, short uRightRumble, int uDurationMs)
      throws Throwable {
    return (boolean)
        SDL_RumbleJoystickTriggers.invoke(joystick, uLeftRumble, uRightRumble, uDurationMs);
  }

  public synchronized boolean setJoystickLED(
      MemorySegment joystick, byte uRed, byte uGreen, byte uBlue) throws Throwable {
    return (boolean) SDL_SetJoystickLED.invoke(joystick, uRed, uGreen, uBlue);
  }

  public synchronized boolean sendJoystickEffect(
      MemorySegment joystick, MemorySegment data, int size) throws Throwable {
    return (boolean) SDL_SendJoystickEffect.invoke(joystick, data, size);
  }

  public synchronized void closeJoystick(MemorySegment joystick) throws Throwable {
    SDL_CloseJoystick.invoke(joystick);
  }

  public synchronized int getJoystickConnectionState(MemorySegment joystick) throws Throwable {
    return (int) SDL_GetJoystickConnectionState.invoke(joystick);
  }

  public void getJoystickPowerInfo(MemorySegment joystick, SdlJoystickPowerSnapshot snapshot)
      throws Throwable {
    synchronized (addressMutex) {
      int powerState = (int) SDL_GetJoystickPowerInfo.invoke(joystick, tempIntAddress);
      int percent = (int) tempIntAddress.get(ValueLayout.JAVA_INT, 0);
      snapshot.setPercent(percent);
      snapshot.setPowerState(powerState);
    }
  }

  @NonNull
  public static NativeSdlJoystickFuncs getInstance(Arena allocator) {
    NativeSdlJoystickFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlJoystickFuncs(allocator);
      }
    }
    return result;
  }
}
