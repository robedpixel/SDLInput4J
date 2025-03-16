package org.example;

import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.SdlInitFlagsFactory;
import robedpixel.sdl.rect.SdlFRectModel;
import robedpixel.sdl.rect.SdlRect;
import robedpixel.sdl.rect.SdlRectModel;

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
      SdlRect rect = sdlLib.getSdlRect();
      SdlRectModel rectModel = new SdlRectModel();
      SdlFRectModel fRectModel = new SdlFRectModel();
      rectModel.setX(2);
      rectModel.setY(2);
      rectModel.setW(2);
      rectModel.setH(2);
      System.out.println(rectModel.getX());
      System.out.println(rectModel.getY());
      System.out.println(rectModel.getW());
      System.out.println(rectModel.getH());
      rect.rectToFRect(rectModel, fRectModel);
      System.out.println(fRectModel.getX());
      System.out.println(fRectModel.getY());
      System.out.println(fRectModel.getW());
      System.out.println(fRectModel.getH());

    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
