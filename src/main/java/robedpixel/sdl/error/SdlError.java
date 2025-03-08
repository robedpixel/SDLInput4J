package robedpixel.sdl.error;

import java.lang.foreign.Arena;

public class SdlError {
    private final NativeSdlErrorFuncs SdlFuncs;
    public SdlError(Arena allocator){
        SdlFuncs = NativeSdlErrorFuncs.getInstance(allocator);
    }

    /**
     * Retrieve a message about the last error that occurred on the current thread.
     * @return Returns a message with information about the specific error that occurred, or an empty string if there hasn't been an error message set since the last call to SDL_ClearError().
     * @throws Throwable
     */
    public String getError() throws Throwable {
        return SdlFuncs.getError();
    }

    /**
     * Clear any previous error message for this thread.
     * @return Returns true.
     * @throws Throwable
     */
    public Boolean clearError() throws Throwable{
        return SdlFuncs.clearError();
    }
}
