package org.example;

import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.SdlInitFlagsFactory;
import robedpixel.sdl.video.SdlDisplayIdArray;
import robedpixel.sdl.video.SdlVideo;

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
      SdlVideo video = sdlLib.getSdlVideo();
      SdlDisplayIdArray array = video.getDisplays();
      for (int i = 0; i < array.getData().length; i++) {
        System.out.println(video.getDisplayName(array.getData()[i]));
      }
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
