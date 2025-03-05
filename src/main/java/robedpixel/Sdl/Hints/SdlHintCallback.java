package robedpixel.Sdl.Hints;

import java.lang.foreign.MemorySegment;

public abstract class SdlHintCallback {
    /**
     * @param userData A MemorySegment for a Java object of any structure for user data passed to addHintCallback
     * @param name MemorySegment of a null terminated C-String passed to addHintCallback
     * @param oldValue MemorySegment of a null terminated C-String of the previous hint value
     * @param newValue MemorySegment of a null terminated C-String of the new hint value
     */
    void callback(MemorySegment userData,MemorySegment name,MemorySegment oldValue,MemorySegment newValue) {

    }
}
