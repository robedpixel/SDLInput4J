package org.example;

import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.SdlInitFlagsFactory;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    // TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    try (NativeSdlLib sdlLib =
        new NativeSdlLib(
            SdlInitFlagsFactory.SDLFlagValue.SDL_INIT_GAMEPAD,
            SdlInitFlagsFactory.SDLFlagValue.SDL_INIT_VIDEO)) {
      System.out.println(sdlLib.isMainThread());
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
