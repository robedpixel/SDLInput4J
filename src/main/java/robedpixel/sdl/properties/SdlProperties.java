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
}
