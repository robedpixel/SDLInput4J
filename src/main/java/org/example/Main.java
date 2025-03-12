package org.example;

import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.SdlInitFlagsFactory;
import robedpixel.sdl.misc.SdlMisc;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try(NativeSdlLib sdlLib = new NativeSdlLib(SdlInitFlagsFactory.SDLFlagValue.SDL_INIT_GAMEPAD)){
            System.out.println(sdlLib.isMainThread());
            SdlMisc misc = sdlLib.getSdlMisc();
            misc.openUrl("https://www.google.com");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}