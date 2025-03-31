package robedpixel.sdl.haptic;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

class NativeSdlHapticFuncs {
  private static volatile NativeSdlHapticFuncs INSTANCE;
  private static final Object mutex = new Object();
  private static final Object addressMutex = new Object();
  private final MethodHandle SDL_GetHaptics;
  private final MethodHandle SDL_GetHapticNameForID;
  private final MethodHandle SDL_OpenHaptic;
  private final MethodHandle SDL_GetHapticFromID;
  private final MethodHandle SDL_GetHapticID;
  private final MethodHandle SDL_GetHapticName;
  private final MethodHandle SDL_IsMouseHaptic;
  private final MethodHandle SDL_OpenHapticFromMouse;
  private final MethodHandle SDL_IsJoystickHaptic;
  private final MethodHandle SDL_OpenHapticFromJoystick;
  private final MethodHandle SDL_CloseHaptic;
  private final MethodHandle SDL_GetMaxHapticEffects;
  private final MethodHandle SDL_GetMaxHapticEffectsPlaying;
  private final MethodHandle SDL_GetHapticFeatures;
  private final MethodHandle SDL_GetNumHapticAxes;
  private final MethodHandle SDL_HapticEffectSupported;
  private final MethodHandle SDL_CreateHapticEffect;
  private final MethodHandle SDL_UpdateHapticEffect;
  private final MethodHandle SDL_RunHapticEffect;
  private final MethodHandle SDL_StopHapticEffect;
  private final MethodHandle SDL_DestroyHapticEffect;
  private final MethodHandle SDL_GetHapticEffectStatus;
  private final MethodHandle SDL_SetHapticGain;
  private final MethodHandle SDL_SetHapticAutocenter;
  private final MethodHandle SDL_PauseHaptic;
  private final MethodHandle SDL_ResumeHaptic;
  private final MethodHandle SDL_StopHapticEffects;
  private final MethodHandle SDL_HapticRumbleSupported;
  private final MethodHandle SDL_InitHapticRumble;
  private final MethodHandle SDL_PlayHapticRumble;
  private final MethodHandle SDL_StopHapticRumble;
  private final Arena objectAllocator = Arena.ofAuto();
  private final MemorySegment tempIntAddress = objectAllocator.allocate(ValueLayout.JAVA_INT);

  public NativeSdlHapticFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetHaptics =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHaptics").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_GetHapticNameForID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticNameForID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_OpenHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenHaptic").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetHapticFromID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticFromID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetHapticID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticID").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetHapticName =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticName").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_IsMouseHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_IsMouseHaptic").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));

    SDL_OpenHapticFromMouse =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenHapticFromMouse").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS));
    SDL_IsJoystickHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_IsJoystickHaptic").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_OpenHapticFromJoystick =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_OpenHapticFromJoystick").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_CloseHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CloseHaptic").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    SDL_GetMaxHapticEffects =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMaxHapticEffects").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetMaxHapticEffectsPlaying =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetMaxHapticEffectsPlaying").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetHapticFeatures =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticFeatures").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetNumHapticAxes =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumHapticAxes").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_HapticEffectSupported =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HapticEffectSupported").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_CreateHapticEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CreateHapticEffect").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    SDL_UpdateHapticEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UpdateHapticEffect").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS));

    SDL_RunHapticEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_RunHapticEffect").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.JAVA_INT));
    SDL_StopHapticEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StopHapticEffect").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_DestroyHapticEffect =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_DestroyHapticEffect").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_GetHapticEffectStatus =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetHapticEffectStatus").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_SetHapticGain =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetHapticGain").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_SetHapticAutocenter =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetHapticAutocenter").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_INT));
    SDL_PauseHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PauseHaptic").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_ResumeHaptic =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ResumeHaptic").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_StopHapticEffects =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StopHapticEffects").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_HapticRumbleSupported =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HapticRumbleSupported").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_InitHapticRumble =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_InitHapticRumble").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
    SDL_PlayHapticRumble =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_PlayHapticRumble").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_FLOAT,
                    ValueLayout.JAVA_INT));
    SDL_StopHapticRumble =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StopHapticRumble").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS));
  }

  public SdlHapticIdArray getHaptics() throws Throwable {
    synchronized (addressMutex) {
      MemorySegment temp = (MemorySegment) SDL_GetHaptics.invoke(tempIntAddress);
      if (temp == MemorySegment.NULL) {
        return null;
      } else {
        int arraySize = tempIntAddress.get(ValueLayout.JAVA_INT, 0);
        return new SdlHapticIdArray(temp, arraySize);
      }
    }
  }

  public synchronized String getHapticId(int instanceId) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetHapticNameForID.invoke(instanceId);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized MemorySegment openHaptic(int instanceId) throws Throwable {
    return (MemorySegment) SDL_OpenHaptic.invoke(instanceId);
  }

  public synchronized MemorySegment getHapticFromId(int instanceId) throws Throwable {
    return (MemorySegment) SDL_GetHapticFromID.invoke(instanceId);
  }

  public synchronized int getHapticId(MemorySegment haptic) throws Throwable {
    return (int) SDL_GetHapticID.invoke(haptic);
  }

  public synchronized String getHapticName(MemorySegment haptic) throws Throwable {
    MemorySegment temp = (MemorySegment) SDL_GetHapticName.invoke(haptic);
    if (temp == MemorySegment.NULL) {
      return null;
    } else {
      return temp.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized boolean isMouseHaptic() throws Throwable {
    return (boolean) SDL_IsMouseHaptic.invoke();
  }

  public synchronized MemorySegment openHapticFromMouse() throws Throwable {
    return (MemorySegment) SDL_OpenHapticFromMouse.invoke();
  }

  public synchronized boolean isJoystickHaptic(MemorySegment joystick) throws Throwable {
    return (boolean) SDL_IsJoystickHaptic.invoke(joystick);
  }

  public synchronized MemorySegment openHapticFromJoystick(MemorySegment joystick)
      throws Throwable {
    return (MemorySegment) SDL_OpenHapticFromJoystick.invoke(joystick);
  }

  public synchronized void closeHaptic(MemorySegment haptic) throws Throwable {
    SDL_CloseHaptic.invoke(haptic);
  }

  public synchronized int getMaxHapticEffects(MemorySegment haptic) throws Throwable {
    return (int) SDL_GetMaxHapticEffects.invoke(haptic);
  }

  public synchronized int getMaxHapticEffectsPlaying(MemorySegment haptic) throws Throwable {
    return (int) SDL_GetMaxHapticEffectsPlaying.invoke(haptic);
  }

  public synchronized int getHapticFeatures(MemorySegment haptic) throws Throwable {
    return (int) SDL_GetHapticFeatures.invoke(haptic);
  }

  public synchronized int getNumHapticAxes(MemorySegment haptic) throws Throwable {
    return (int) SDL_GetNumHapticAxes.invoke(haptic);
  }

  public synchronized boolean hapticEffectSupported(MemorySegment haptic, MemorySegment effect)
      throws Throwable {
    return (boolean) SDL_HapticEffectSupported.invoke(haptic, effect);
  }

  public synchronized int createHapticEffect(MemorySegment haptic, MemorySegment effect)
      throws Throwable {
    return (int) SDL_CreateHapticEffect.invoke(haptic, effect);
  }

  public synchronized boolean updateHapticEffect(
      MemorySegment haptic, int effect, MemorySegment data) throws Throwable {
    return (boolean) SDL_UpdateHapticEffect.invoke(haptic, effect, data);
  }

  public synchronized boolean runHapticEffect(MemorySegment haptic, int effect, int iterations)
      throws Throwable {
    return (boolean) SDL_RunHapticEffect.invoke(haptic, effect, iterations);
  }

  public synchronized boolean stopHapticEffect(MemorySegment haptic, int effect) throws Throwable {
    return (boolean) SDL_StopHapticEffect.invoke(haptic, effect);
  }

  public synchronized void destroyHapticEffect(MemorySegment haptic, int effect) throws Throwable {
    SDL_DestroyHapticEffect.invoke(haptic, effect);
  }

  public synchronized boolean getHapticEffectStatus(MemorySegment haptic, int effect)
      throws Throwable {
    return (boolean) SDL_GetHapticEffectStatus.invoke(haptic, effect);
  }

  public synchronized boolean setHapticGain(MemorySegment haptic, int gain) throws Throwable {
    return (boolean) SDL_SetHapticGain.invoke(haptic, gain);
  }

  public synchronized boolean setHapticAutocenter(MemorySegment haptic, int autocenter)
      throws Throwable {
    return (boolean) SDL_SetHapticAutocenter.invoke(haptic, autocenter);
  }

  public synchronized boolean pauseHaptic(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_PauseHaptic.invoke(haptic);
  }

  public synchronized boolean resumeHaptic(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_ResumeHaptic.invoke(haptic);
  }

  public synchronized boolean stopHapticEffects(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_StopHapticEffects.invoke(haptic);
  }

  public synchronized boolean hapticRumbleSupported(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_HapticRumbleSupported.invoke(haptic);
  }

  public synchronized boolean initHapticRumble(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_InitHapticRumble.invoke(haptic);
  }

  public synchronized boolean playHapticRumble(MemorySegment haptic, int strength, int length)
      throws Throwable {
    return (boolean) SDL_PlayHapticRumble.invoke(haptic, strength, length);
  }

  public synchronized boolean stopHapticRumble(MemorySegment haptic) throws Throwable {
    return (boolean) SDL_StopHapticRumble.invoke(haptic);
  }

  public static NativeSdlHapticFuncs getInstance(Arena allocator) {
    NativeSdlHapticFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlHapticFuncs(allocator);
      }
    }
    return result;
  }
}
