package robedpixel.Sdl;

import lombok.Getter;
import lombok.Setter;

import java.lang.foreign.MemorySegment;

public abstract class SdlMainThreadCallback {
    /**
     * @param userData A MemorySegment for a Java object of any structure for user data
     */
    void callback(MemorySegment userData) {

    }
}
