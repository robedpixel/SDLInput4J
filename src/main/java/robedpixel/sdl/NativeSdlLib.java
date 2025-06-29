package robedpixel.sdl;

import java.lang.foreign.*;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import robedpixel.sdl.error.SdlError;
import robedpixel.sdl.events.SdlEvents;
import robedpixel.sdl.gamepad.SdlGamepad;
import robedpixel.sdl.guid.SdlGuid;
import robedpixel.sdl.haptic.SdlHaptic;
import robedpixel.sdl.hints.SdlHints;
import robedpixel.sdl.joystick.SdlJoystick;
import robedpixel.sdl.keyboard.SdlKeyboard;
import robedpixel.sdl.misc.SdlMisc;
import robedpixel.sdl.mouse.SdlMouse;
import robedpixel.sdl.power.SdlPower;
import robedpixel.sdl.properties.SdlProperties;
import robedpixel.sdl.rect.SdlRect;
import robedpixel.sdl.sensors.SdlSensor;
import robedpixel.sdl.touch.SdlTouch;
import robedpixel.sdl.video.SdlVideo;

// linux library is libSDL3.so
// windows library is SDL3.dll
// singleton library, in charge of SDL init and quit, and methods to provide instances to functions

@Slf4j
public class NativeSdlLib implements AutoCloseable {
  private static volatile NativeSdlLib INSTANCE;
  private NativeSdlLibFuncs SdlFuncs;
  private LinkedList<AutoCloseable> closableList;

  /**
   * Initialize the SDL library
   *
   * @param flags Subsystem initialization flags.
   */
  public NativeSdlLib(SdlInitFlagsFactory.SDLFlagValue... flags) {
    try {
      SdlFuncs = NativeSdlLibFuncs.getInstance();
      log.debug("Initialising SDL3 Library");
      if (!SdlFuncs.initLibrary(SdlInitFlagsFactory.orSdlInitFlag(flags))) {
        throw new IllegalStateException("Unable to init SDL3 library!");
      }
      closableList = new LinkedList<>();
      log.debug("SDL3 library initialised!");
    } catch (IllegalArgumentException e) {
      log.error("Unable to find SDL3 Library!");
      log.error("{}", e.getMessage());
      throw e;
    } catch (UnsatisfiedLinkError e) {
      log.error("Unable to link to SDL library, is it loaded in the os path?");
      log.error("Error contents: {}", e.getMessage());
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Return whether this is the main thread.
   *
   * @return Returns true if this thread is the main thread, or false otherwise.
   * @throws Throwable
   */
  public boolean isMainThread() throws Throwable {
    return SdlFuncs.isMainThread();
  }

  /**
   * Shut down specific SDL subsystems.
   *
   * @param flags any of the flags used by SDL Initialisation; see SdlInitFlags for details.
   * @throws Throwable
   */
  public void quitSubSystem(SdlInitFlags flags) throws Throwable {
    SdlFuncs.quitSubSystem(flags);
  }

  /**
   * Call a function on the main thread during event processing.
   *
   * @param callbackUpcallStub The callback to call on the main thread.
   * @param waitComplete True to wait for the callback to complete, false to return immediately.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean runOnMainThread(SdlMainThreadCallback callbackUpcallStub, boolean waitComplete)
      throws Throwable {
    return SdlFuncs.runOnMainThread(
        callbackUpcallStub.getCallbackAllocator(),
        callbackUpcallStub.getCallbackAddress(),
        callbackUpcallStub.getUserData(),
        waitComplete);
  }

  /**
   * Specify basic metadata about your app.
   *
   * @param appName The name of the application ("My Game 2: Bad Guy's Revenge!").
   * @param appVersion The version of the application ("1.0.0beta5" or a git hash, or whatever makes
   *     sense).
   * @param appIdentifier A unique string in reverse-domain format that identifies this app
   *     ("com.example.mygame2").
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setAppMetadata(String appName, String appVersion, String appIdentifier)
      throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setAppMetadata(arena, appName, appVersion, appIdentifier);
    }
  }

  /**
   * Specify metadata about your app through a set of properties.
   *
   * @param name The name of the metadata property to set.
   * @param value The value of the property, or null to remove that property.
   * @return Returns true on success or false on failure; call SdlError.getError() for more
   *     information.
   * @throws Throwable
   */
  public boolean setAppMetadataProperty(String name, String value) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.setAppMetadataProperty(arena, name, value);
    }
  }

  /**
   * Get metadata about your app.
   *
   * @param name The name of the metadata property to get.
   * @return Returns the current value of the metadata property, or the default if it is not set,
   *     null for properties with no default.
   * @throws Throwable
   */
  @Nullable
  public String getAppMetadataProperty(String name) throws Throwable {
    try (Arena arena = Arena.ofConfined()) {
      return SdlFuncs.getAppMetadataProperty(arena, name);
    }
  }

  /**
   * Get the SDL Error module
   *
   * @return Error module for SDL
   */
  @NonNull
  public SdlError getSdlError() {
    return new SdlError(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Events module
   *
   * @return Events module for SDL
   */
  @NonNull
  public SdlEvents getSdlEvents() {
    return new SdlEvents(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Gamepad module
   *
   * @return Gamepad module for SDL
   */
  @NonNull
  public SdlGamepad getSdlGamepad() {
    return new SdlGamepad(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Guid module
   *
   * @return Guid module for SDL
   */
  @NonNull
  public SdlGuid getSdlGuid() {
    return new SdlGuid(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Haptic module
   *
   * @return Haptic module for SDL
   */
  @NonNull
  public SdlHaptic getSdlHaptic() {
    return new SdlHaptic(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Hints module
   *
   * @return Hints module for SDL
   */
  @NonNull
  public SdlHints getSdlHints() {
    return new SdlHints(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Joystick module
   *
   * @return Joystick module for SDL
   */
  @NonNull
  public SdlJoystick getSdlJoystick() {
    return new SdlJoystick(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Keyboard module
   *
   * @return Keyboard module for SDL
   */
  @NonNull
  public SdlKeyboard getSdlKeyboard() {
    SdlKeyboard keyboard = new SdlKeyboard(SdlFuncs.getGlobalAllocator());
    closableList.add(keyboard);
    return keyboard;
  }

  /**
   * Get the SDL Misc module
   *
   * @return Misc module for SDL
   */
  @NonNull
  public SdlMisc getSdlMisc() {
    return new SdlMisc(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Mouse module
   *
   * @return Mouse module for SDL
   */
  @NonNull
  public SdlMouse getSdlMouse() {
    return new SdlMouse(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Power module
   *
   * @return Power module for SDL
   */
  @NonNull
  public SdlPower getSdlPower() {
    return new SdlPower(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Properties module
   *
   * @return Properties module for SDL
   */
  @NonNull
  public SdlProperties getSdlProperties() {
    return new SdlProperties(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Rect module
   *
   * @return Rect module for SDL
   */
  @NonNull
  public SdlRect getSdlRect() {
    return new SdlRect(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Sensor module
   *
   * @return Sensor module for SDL
   */
  @NonNull
  public SdlSensor getSdlSensor() {
    return new SdlSensor(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Touch module
   *
   * @return Touch module for SDL
   */
  @NonNull
  public SdlTouch getSdlTouch() {
    return new SdlTouch(SdlFuncs.getGlobalAllocator());
  }

  /**
   * Get the SDL Video module
   *
   * @return Video module for SDL
   */
  @NonNull
  public SdlVideo getSdlVideo() {
    return new SdlVideo(SdlFuncs.getGlobalAllocator());
  }

  public static void sdlFree(MemorySegment pointer) throws Throwable {
    NativeSdlLibFuncs.getInstance().sdlFree(pointer);
  }

  @Override
  public void close() throws Exception {
    try {
      for (AutoCloseable closeable : closableList) {
        closeable.close();
      }
      closableList.clear();
      SdlFuncs.quitLibrary();
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
