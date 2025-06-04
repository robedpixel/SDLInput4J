package robedpixel.sdl.events.sdlevent;

import robedpixel.sdl.keyboard.SdlScancode;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;

//TODO: complete object
public class SdlKeyboardEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("windowID"),
                            ValueLayout.JAVA_INT.withName("which"),
                            ValueLayout.JAVA_INT.withName("scancode"),
                            ValueLayout.JAVA_INT.withName("key"),
                            ValueLayout.JAVA_INT.withName("mod"),
                            ValueLayout.JAVA_SHORT.withName("raw"),
                            ValueLayout.JAVA_BOOLEAN.withName("down"),
                            ValueLayout.JAVA_BOOLEAN.withName("repeat"))
                    .withName("SDL_KeyboardEvent");

}
