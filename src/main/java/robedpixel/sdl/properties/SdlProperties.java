package robedpixel.sdl.properties;


import java.lang.foreign.Arena;

public class SdlProperties {
    private final NativeSdlPropertiesFuncs SdlFuncs;

    public SdlProperties(Arena allocator) {
        SdlFuncs = NativeSdlPropertiesFuncs.getInstance(allocator);
    }

    /**
     * Get the global SDL properties.
     * @return Returns a valid property Id on success or 0 on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public SdlPropertiesId getGlobalProperties() throws Throwable {
        SdlPropertiesId returnObject = new SdlPropertiesId();
        returnObject.setValue(SdlFuncs.getGlobalProperties());
        return returnObject;
    }

    /**
     * Create a group of properties.
     * @return Returns an ID for a new group of properties, or 0 on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public SdlPropertiesId createProperties() throws Throwable{
        SdlPropertiesId returnObject = new SdlPropertiesId();
        returnObject.setValue(SdlFuncs.createProperties());
        return returnObject;
    }

    /**
     * Copy a group of properties.
     * @param src The properties to copy
     * @param dst The destination properties.
     * @return Returns true on success or false on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public boolean copyProperties(SdlPropertiesId src, SdlPropertiesId dst) throws Throwable {
        return SdlFuncs.copyProperties(src.getValue(),dst.getValue());
    }

    /**
     * Lock a group of properties.
     * @param props The properties to lock.
     * @return Returns true on success or false on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public boolean lockProperties(SdlPropertiesId props)throws Throwable{
        return SdlFuncs.lockProperties(props.getValue());
    }
    /**
     * Unlock a group of properties.
     * @param props The properties to unlock.
     * @return Returns true on success or false on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public void unlockProperties(SdlPropertiesId props)throws Throwable{
        SdlFuncs.unlockProperties(props.getValue());
    }
}
