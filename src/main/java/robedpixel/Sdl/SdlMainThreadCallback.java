package robedpixel.Sdl;

import lombok.Getter;
import lombok.Setter;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public abstract class SdlMainThreadCallback implements AutoCloseable{
    @Getter
    private Arena callbackAllocator = Arena.ofConfined();
    @Getter
    private MemorySegment userData;
    public SdlMainThreadCallback(MemorySegment userData){
        this.userData = userData;
    }
    /**
     * @param userData A MemorySegment for a Java object of any structure for user data
     */
    void callback(MemorySegment userData) {

    }
    @Override
    public void close(){
        callbackAllocator.close();
    }
}
