package robedpixel.sdl.video;


import java.lang.foreign.Arena;

public class SdlVideo {
    private final NativeSdlVideoFuncs SdlFuncs;
    private int cache = -1;
    public SdlVideo(Arena allocator) {
        SdlFuncs = NativeSdlVideoFuncs.getInstance(allocator);
    }
    /**
     * Get the number of video drivers compiled into SDL.
     * @return Returns the number of built-in video drivers.
     * @throws Throwable
     */
    public int getNumVideoDrivers() throws Throwable {
        return SdlFuncs.getNumVideoDrivers();
    }

    /**
     * Get the name of a built-in video driver.
     * Note that this function does not do bounds checking, please make a call to getNumVideoDrivers to check for
     * the maximum number of video drivers first
     * @param index The index of a video driver.
     * @return Returns the name of the video driver with the given index.
     * @throws Throwable
     */
    public String getVideoDriver(int index) throws Throwable {
        return SdlFuncs.getVideoDriver(index);
    }

    /**
     * Get the name of the currently initialized video driver.
     * @return Returns the name of the current video driver or NULL if no driver has been initialized.
     * @throws Throwable
     */
    public String getCurrentVideoDriver() throws Throwable{
        return SdlFuncs.getCurrentVideoDriver();
    }

    /**
     * Get the current system theme.
     * @return Returns the current system theme, light, dark, or unknown.
     * @throws Throwable
     */
    public SdlSystemTheme getSystemTheme() throws Throwable{
        return SdlSystemTheme.fromInt(SdlFuncs.getSystemTheme());
    }
}
