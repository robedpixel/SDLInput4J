package robedpixel.Sdl.Hints;

import lombok.Getter;
import lombok.Setter;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public abstract class SdlHintCallback implements AutoCloseable{
    @Getter
    private Arena callbackAllocator = Arena.ofConfined();
    @Getter
    private String name;
    @Getter
    private MemorySegment userData;
    public SdlHintCallback(MemorySegment userData, String name){
        this.userData = userData;
        this.name = name;
    }
    /**
     * @param userData A MemorySegment for a Java object of any structure for user data passed to addHintCallback
     * @param name MemorySegment of a null terminated C-String passed to addHintCallback
     * @param oldValue MemorySegment of a null terminated C-String of the previous hint value
     * @param newValue MemorySegment of a null terminated C-String of the new hint value
     */
    void callback(MemorySegment userData,MemorySegment name,MemorySegment oldValue,MemorySegment newValue) {

    }
    @Override
    public final void close(){
        callbackAllocator.close();
    }
}
