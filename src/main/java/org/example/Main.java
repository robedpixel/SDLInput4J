package org.example;

import robedpixel.sdl.NativeSdlLib;
import robedpixel.sdl.SdlInitFlagsFactory;
import robedpixel.sdl.misc.SdlMisc;
import robedpixel.sdl.sensors.SdlSensor;
import robedpixel.sdl.sensors.SdlSensorDevice;
import robedpixel.sdl.sensors.SdlSensorIdArray;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try(NativeSdlLib sdlLib = new NativeSdlLib(SdlInitFlagsFactory.SDLFlagValue.SDL_INIT_GAMEPAD)){
            System.out.println(sdlLib.isMainThread());
            SdlSensor sensor = sdlLib.getSdlSensor();
            SdlSensorIdArray array = sensor.getSensors();
            System.out.println(array.getData().length);
            for(int i = 0;i<array.getData().length;i++){
                SdlSensorDevice device = sensor.openSensor(array.getData()[i]);
                System.out.println(sensor.getSensorName(device));
                sensor.closeSensor(device);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}