package robedpixel.sdl.properties;


import java.lang.foreign.Arena;

public class SdlProperties {
    private final NativeSdlPropertiesFuncs SdlFuncs;

    public SdlProperties(Arena allocator) {
        SdlFuncs = NativeSdlPropertiesFuncs.getInstance(allocator);
    }
    public SdlPropertiesId getGlobalProperties() throws Throwable {
        SdlPropertiesId returnObject = new SdlPropertiesId();
        returnObject.setValue(SdlFuncs.getGlobalProperties());
        return returnObject;
    }
}
