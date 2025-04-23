package robedpixel.sdl.gamepad;



import robedpixel.sdl.guid.NativeSdlGuidModel;
import robedpixel.sdl.joystick.SdlJoystickId;
import robedpixel.sdl.joystick.SdlJoystickIdArray;

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
     * Get the gamepad mapping string for a given GUID.
     * @param guid A structure containing the GUID for which a mapping is desired.
     * @return Returns a mapping string or null on failure; call
     * SdlError.getError() for more information.
     * @throws Throwable
     */
    public String getGamepadMappingForGUID(NativeSdlGuidModel guid) throws Throwable {
        return SdlFuncs.getGamepadMappingForGUID(guid);
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
     * Check if the given joystick is supported by the gamepad interface.
     * @param instanceId The joystick instance ID.
     * @return Returns true if the given joystick is supported by the gamepad interface, false if it isn't or it's an invalid index.
     * @throws Throwable
     */
    public boolean isGamepad(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.isGamepad(instanceId);
    }

    /**
     * Return whether a gamepad is currently connected.
     * @return Returns true if a gamepad is connected, false otherwise.
     * @throws Throwable
     */
    public boolean hasGamepad()throws Throwable{
        return SdlFuncs.hasGamepad();
    }

    /**
     * Get a list of currently connected gamepads.
     * @return Returns an array of joystick instance IDs or null on failure; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public SdlJoystickIdArray getGamepads() throws Throwable{
        return SdlFuncs.getGamepads();
    }

    /**
     * Get the implementation dependent name of a gamepad.
     * @param instanceId The joystick instance ID.
     * @return Returns the name of the selected gamepad. If no name can be found, this function returns NULL; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public String getGamepadNameForID(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.getGamepadNameForID(instanceId);
    }

    /**
     * Get the implementation dependent path of a gamepad.
     * @param instanceId The joystick instance ID.
     * @return Returns the path of the selected gamepad. If no path can be found, this function returns null; call SdlError.getError() for more information.
     * @throws Throwable
     */
    public String getGamepadPathForID(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.getGamepadPathForID(instanceId);
    }

    /**
     * Get the player index of a gamepad.
     * @param instanceId The joystick instance ID.
     * @return Returns the player index of a gamepad, or -1 if it's not available.
     * @throws Throwable
     */
    public int getGamepadPlayerIndexForID(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.getGamepadPlayerIndexForID(instanceId);
    }

    /**
     * Get the implementation-dependent GUID of a gamepad.
     * @param instanceId The joystick instance ID.
     * @return Returns the GUID of the selected gamepad. If called on an invalid index, this function returns a zero GUID.
     * @throws Throwable
     */
    public NativeSdlGuidModel getGamepadGUIDForID(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.getGamepadGUIDForID(instanceId);
    }

    /**
     * Get the USB vendor ID of a gamepad, if available.
     * @param instanceId The joystick instance ID.
     * @return (Unsigned Short) Returns the USB vendor ID of the selected gamepad. If called on an invalid index, this function returns zero.
     * @throws Throwable
     */
    public short getGamepadVendorForID(SdlJoystickId instanceId) throws Throwable{
        return SdlFuncs.getGamepadVendorForID(instanceId);
    }


}
