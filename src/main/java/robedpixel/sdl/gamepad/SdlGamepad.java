package robedpixel.sdl.gamepad;



import robedpixel.sdl.guid.NativeSdlGuidModel;
import robedpixel.sdl.joystick.SdlJoystickId;

import java.io.File;
import java.lang.foreign.Arena;

public class SdlGamepad {
    private final NativeSdlGamepadFuncs SdlFuncs;

    public SdlGamepad(Arena allocator) {
        SdlFuncs = NativeSdlGamepadFuncs.getInstance(allocator);
    }

    /**
     * Add support for gamepads that SDL is unaware of or change the binding of an existing gamepad.
     * @param mapping The mapping string.
     * @return Returns 1 if a new mapping is added, 0 if an existing mapping is updated, -1 on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public int addGamepadMapping(String mapping) throws Throwable {
        try(Arena localAllocator = Arena.ofConfined()) {
            return SdlFuncs.addGamepadMapping(localAllocator,mapping);
        }
    }

    /**
     * Load a set of gamepad mappings from a file.
     * @param file The mappings file to load.
     * @return Returns the number of mappings added or -1 on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public int addGamepadMappingsFromFile(File file) throws Throwable {
        try(Arena localAllocator = Arena.ofConfined()) {
            return SdlFuncs.addGamepadMappingsFromFile(localAllocator,file.getCanonicalPath());
        }
    }

    /**
     * Reinitialize the SDL mapping database to its initial state.
     * @return Returns true on success or false on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public boolean reloadGamepadMappings() throws Throwable{
        return SdlFuncs.reloadGamepadMappings();
    }

    /**
     * Get the current gamepad mappings.
     * @return Returns an array of the mapping strings or null on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public SdlGamepadMappingArray getGamepadMappings() throws Throwable{
        return SdlFuncs.getGamepadMappings();
    }

    /**
     * Get the current mapping of a gamepad.
     * @param gamepad The gamepad you want to get the current mapping for.
     * @return Returns a string that has the gamepad's mapping or null if no mapping is available; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public String getGamepadMapping(SdlGamepadDevice gamepad) throws Throwable {
        return SdlFuncs.getGamepadMapping(gamepad);
    }

    /**
     * Set the current mapping of a joystick or gamepad.
     * @param instanceId The joystick instance ID.
     * @param mapping The mapping to use for this device, or NULL to clear the mapping.
     * @return Returns true on success or false on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public boolean setGamepadMapping(SdlJoystickId instanceId, String mapping) throws Throwable {
        try(Arena localAllocator = Arena.ofConfined()) {
            return SdlFuncs.setGamepadMapping(localAllocator,instanceId,mapping);
        }
    }

    /**
     * Get the gamepad mapping string for a given GUID.
     * @param guid A structure containing the GUID for which a mapping is desired.
     * @return Returns a mapping string or null on failure; call
     * SdlError.getError() for more information.
     * @throws Throwable
     */
    public String getGamepadMappingForGUID(NativeSdlGuidModel guid) throws Throwable {
        return SdlFuncs.getGamepadMappingForGUID(guid);
    }
}
