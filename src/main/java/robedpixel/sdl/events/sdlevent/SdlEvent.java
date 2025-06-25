package robedpixel.sdl.events.sdlevent;

import lombok.Getter;

import java.lang.foreign.*;

public class SdlEvent implements AutoCloseable{
    @Getter
    private MemorySegment segment;
    private Arena arena = Arena.ofConfined();
  public static final UnionLayout objectLayout =
      MemoryLayout.unionLayout(
              ValueLayout.JAVA_INT.withName("type"),
              SdlCommonEvent.objectLayout.withName("common"),
              SdlDisplayEvent.objectLayout.withName("window"),
              SdlKeyboardDeviceEvent.objectLayout.withName("kdevice"),
              SdlKeyboardEvent.objectLayout.withName("key"),
              SdlTextEditingEvent.objectLayout.withName("edit"),
              SdlTextEditingCandidatesEvent.objectLayout.withName("edit_candidates"),
              SdlTextInputEvent.objectLayout.withName("text"),
              SdlMouseDeviceEvent.objectLayout.withName("mdevice"),
              SdlMouseMotionEvent.objectLayout.withName("motion"),
              SdlMouseButtonEvent.objectLayout.withName("button"),
              SdlMouseWheelEvent.objectLayout.withName("wheel"),
              SdlJoyDeviceEvent.objectLayout.withName("jdevice"),
              SdlJoyAxisEvent.objectLayout.withName("jaxis"),
              SdlJoyBallEvent.objectLayout.withName("jball"),
              SdlJoyHatEvent.objectLayout.withName("jhat"),
              SdlJoyButtonEvent.objectLayout.withName("jbutton"),
              SdlJoyBatteryEvent.objectLayout.withName("jbattery"),
              SdlGamepadDeviceEvent.objectLayout.withName("gdevice"),
              SdlGamepadAxisEvent.objectLayout.withName("gaxis"),
              SdlGamepadButtonEvent.objectLayout.withName("gbutton"),
              SdlGamepadTouchpadEvent.objectLayout.withName("gtouchpad"),
              SdlGamepadSensorEvent.objectLayout.withName("gsensor"),
              SdlAudioDeviceEvent.objectLayout.withName("adevice"),
              SdlCameraDeviceEvent.objectLayout.withName("cdevice"),
              SdlSensorEvent.objectLayout.withName("sensor"),
              SdlQuitEvent.objectLayout.withName("quit"),
              SdlUserEvent.objectLayout.withName("user"),
              SdlTouchFingerEvent.objectLayout.withName("tfinger"),
              SdlPenProximityEvent.objectLayout.withName("proximity"),
              SdlPenTouchEvent.objectLayout.withName("ptouch"),
              SdlPenMotionEvent.objectLayout.withName("pmotion"),
              SdlPenButtonEvent.objectLayout.withName("pbutton"),
              SdlPenAxisEvent.objectLayout.withName("paxis"),
              SdlRenderEvent.objectLayout.withName("render"),
              SdlDropEvent.objectLayout.withName("drop"),
              SdlClipboardEvent.objectLayout.withName("clipboard"))
          .withName("SDL_Event");
    public SdlEvent(){
        segment = arena.allocate(objectLayout);
    }

    @Override
    public void close() throws Exception {
        arena.close();
    }
}
