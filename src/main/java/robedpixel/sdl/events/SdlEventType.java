package robedpixel.sdl.events;

import lombok.Getter;

//TODO: enumerate types
public enum SdlEventType {
    SDL_EVENT_FIRST(0),
    SDL_EVENT_QUIT(0x100), /** User-requested quit */
    SDL_EVENT_TERMINATING(0x101), /** The application is being terminated by the OS. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationWillTerminate()
     Called on Android in onDestroy()
     */
    SDL_EVENT_LOW_MEMORY(0x102),       /**< The application is low on memory, free memory if possible. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationDidReceiveMemoryWarning()
     Called on Android in onTrimMemory()
     */
    SDL_EVENT_WILL_ENTER_BACKGROUND(0x103), /**< The application is about to enter the background. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationWillResignActive()
     Called on Android in onPause()
     */
    SDL_EVENT_DID_ENTER_BACKGROUND(0x104), /**< The application did enter the background and may not get CPU for some time. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationDidEnterBackground()
     Called on Android in onPause()
     */
    SDL_EVENT_WILL_ENTER_FOREGROUND(0x105), /**< The application is about to enter the foreground. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationWillEnterForeground()
     Called on Android in onResume()
     */
    SDL_EVENT_DID_ENTER_FOREGROUND(0x106), /**< The application is now interactive. This event must be handled in a callback set with SDL_AddEventWatch().
     Called on iOS in applicationDidBecomeActive()
     Called on Android in onResume()
     */

    SDL_EVENT_LOCALE_CHANGED(0x107),  /**< The user's locale preferences have changed. */

    SDL_EVENT_SYSTEM_THEME_CHANGED(0x108), /**< The system theme changed */

    /* Display events */
    /* 0x150 was SDL_DISPLAYEVENT, reserve the number for sdl2-compat */
    SDL_EVENT_DISPLAY_ORIENTATION(0x151),   /**< Display orientation has changed to data1 */
    SDL_EVENT_DISPLAY_ADDED(0x152),                 /**< Display has been added to the system */
    SDL_EVENT_DISPLAY_REMOVED(0x153),               /**< Display has been removed from the system */
    SDL_EVENT_DISPLAY_MOVED(0x154),                 /**< Display has changed position */
    SDL_EVENT_DISPLAY_DESKTOP_MODE_CHANGED(0x155),  /**< Display has changed desktop mode */
    SDL_EVENT_DISPLAY_CURRENT_MODE_CHANGED(0x156),  /**< Display has changed current mode */
    SDL_EVENT_DISPLAY_CONTENT_SCALE_CHANGED(0x157), /**< Display has changed content scale */
    SDL_EVENT_DISPLAY_FIRST(SDL_EVENT_DISPLAY_ORIENTATION.value),
    SDL_EVENT_DISPLAY_LAST(SDL_EVENT_DISPLAY_CONTENT_SCALE_CHANGED.value),

    /* Window events */
    /* 0x200 was SDL_WINDOWEVENT, reserve the number for sdl2-compat */
    /* 0x201 was SDL_SYSWMEVENT, reserve the number for sdl2-compat */
    SDL_EVENT_WINDOW_SHOWN(0x202),     /**< Window has been shown */
    SDL_EVENT_WINDOW_HIDDEN(0x203),            /**< Window has been hidden */
    SDL_EVENT_WINDOW_EXPOSED(0x204),           /**< Window has been exposed and should be redrawn, and can be redrawn directly from event watchers for this event */
    SDL_EVENT_WINDOW_MOVED(0x205),             /**< Window has been moved to data1, data2 */
    SDL_EVENT_WINDOW_RESIZED(0x206),           /**< Window has been resized to data1xdata2 */
    SDL_EVENT_WINDOW_PIXEL_SIZE_CHANGED(0x207),/**< The pixel size of the window has changed to data1xdata2 */
    SDL_EVENT_WINDOW_METAL_VIEW_RESIZED(0x208),/**< The pixel size of a Metal view associated with the window has changed */
    SDL_EVENT_WINDOW_MINIMIZED(0x209),         /**< Window has been minimized */
    SDL_EVENT_WINDOW_MAXIMIZED(0x20a),         /**< Window has been maximized */
    SDL_EVENT_WINDOW_RESTORED(0x20b),          /**< Window has been restored to normal size and position */
    SDL_EVENT_WINDOW_MOUSE_ENTER(0x20c),       /**< Window has gained mouse focus */
    SDL_EVENT_WINDOW_MOUSE_LEAVE(0x20d),       /**< Window has lost mouse focus */
    SDL_EVENT_WINDOW_FOCUS_GAINED(0x20e),      /**< Window has gained keyboard focus */
    SDL_EVENT_WINDOW_FOCUS_LOST(0x20f),        /**< Window has lost keyboard focus */
    SDL_EVENT_WINDOW_CLOSE_REQUESTED(0x210),   /**< The window manager requests that the window be closed */
    SDL_EVENT_WINDOW_HIT_TEST(0x211),          /**< Window had a hit test that wasn't SDL_HITTEST_NORMAL */
    SDL_EVENT_WINDOW_ICCPROF_CHANGED(0x212),   /**< The ICC profile of the window's display has changed */
    SDL_EVENT_WINDOW_DISPLAY_CHANGED(0x213),   /**< Window has been moved to display data1 */
    SDL_EVENT_WINDOW_DISPLAY_SCALE_CHANGED(0x214), /**< Window display scale has been changed */
    SDL_EVENT_WINDOW_SAFE_AREA_CHANGED(0x215), /**< The window safe area has been changed */
    SDL_EVENT_WINDOW_OCCLUDED(0x216),          /**< The window has been occluded */
    SDL_EVENT_WINDOW_ENTER_FULLSCREEN(0x217),  /**< The window has entered fullscreen mode */
    SDL_EVENT_WINDOW_LEAVE_FULLSCREEN(0x218),  /**< The window has left fullscreen mode */
    SDL_EVENT_WINDOW_DESTROYED(0x219),         /**< The window with the associated ID is being or has been destroyed. If this message is being handled
     in an event watcher, the window handle is still valid and can still be used to retrieve any properties
     associated with the window. Otherwise, the handle has already been destroyed and all resources
     associated with it are invalid */
    SDL_EVENT_WINDOW_HDR_STATE_CHANGED(0x21a), /**< Window HDR properties have changed */
    SDL_EVENT_WINDOW_FIRST(SDL_EVENT_WINDOW_SHOWN.value),
    SDL_EVENT_WINDOW_LAST(SDL_EVENT_WINDOW_HDR_STATE_CHANGED.value),

    /* Keyboard events */
    SDL_EVENT_KEY_DOWN(0x300), /**< Key pressed */
    SDL_EVENT_KEY_UP(0x301),                  /**< Key released */
    SDL_EVENT_TEXT_EDITING(0x302),            /**< Keyboard text editing (composition) */
    SDL_EVENT_TEXT_INPUT(0x303),              /**< Keyboard text input */
    SDL_EVENT_KEYMAP_CHANGED(0x304),          /**< Keymap changed due to a system event such as an
     input language or keyboard layout change. */
    SDL_EVENT_KEYBOARD_ADDED(0x305),          /**< A new keyboard has been inserted into the system */
    SDL_EVENT_KEYBOARD_REMOVE(0x306),        /**< A keyboard has been removed */
    SDL_EVENT_TEXT_EDITING_CANDIDATES(0x307), /**< Keyboard text editing candidates */

    /* Mouse events */
    SDL_EVENT_MOUSE_MOTION(0x400), /**< Mouse moved */
    SDL_EVENT_MOUSE_BUTTON_DOWN(0x401),       /**< Mouse button pressed */
    SDL_EVENT_MOUSE_BUTTON_UP(0x402),         /**< Mouse button released */
    SDL_EVENT_MOUSE_WHEEL(0x403),             /**< Mouse wheel motion */
    SDL_EVENT_MOUSE_ADDED(0x404),             /**< A new mouse has been inserted into the system */
    SDL_EVENT_MOUSE_REMOVED(0x405),           /**< A mouse has been removed */

    /* Joystick events */
    SDL_EVENT_JOYSTICK_AXIS_MOTION(0x600), /**< Joystick axis motion */
    SDL_EVENT_JOYSTICK_BALL_MOTION(0x601),          /**< Joystick trackball motion */
    SDL_EVENT_JOYSTICK_HAT_MOTION(0x602),           /**< Joystick hat position change */
    SDL_EVENT_JOYSTICK_BUTTON_DOWN(0x603),          /**< Joystick button pressed */
    SDL_EVENT_JOYSTICK_BUTTON_UP(0x604),            /**< Joystick button released */
    SDL_EVENT_JOYSTICK_ADDED(0x605),                /**< A new joystick has been inserted into the system */
    SDL_EVENT_JOYSTICK_REMOVED(0x606),              /**< An opened joystick has been removed */
    SDL_EVENT_JOYSTICK_BATTERY_UPDATED(0x607),      /**< Joystick battery level change */
    SDL_EVENT_JOYSTICK_UPDATE_COMPLETE(0x608),      /**< Joystick update is complete */

    /* Gamepad events */
    SDL_EVENT_GAMEPAD_AXIS_MOTION(0x650), /**< Gamepad axis motion */
    SDL_EVENT_GAMEPAD_BUTTON_DOWN(0x651),          /**< Gamepad button pressed */
    SDL_EVENT_GAMEPAD_BUTTON_UP(0x652),            /**< Gamepad button released */
    SDL_EVENT_GAMEPAD_ADDED(0x653),                /**< A new gamepad has been inserted into the system */
    SDL_EVENT_GAMEPAD_REMOVED(0x654),              /**< A gamepad has been removed */
    SDL_EVENT_GAMEPAD_REMAPPED(0x655),             /**< The gamepad mapping was updated */
    SDL_EVENT_GAMEPAD_TOUCHPAD_DOWN(0x656),        /**< Gamepad touchpad was touched */
    SDL_EVENT_GAMEPAD_TOUCHPAD_MOTION(0x657),      /**< Gamepad touchpad finger was moved */
    SDL_EVENT_GAMEPAD_TOUCHPAD_UP(0x658),          /**< Gamepad touchpad finger was lifted */
    SDL_EVENT_GAMEPAD_SENSOR_UPDATE(0x659),        /**< Gamepad sensor was updated */
    SDL_EVENT_GAMEPAD_UPDATE_COMPLETE(0x65a),      /**< Gamepad update is complete */
    SDL_EVENT_GAMEPAD_STEAM_HANDLE_UPDATED(0x65b),  /**< Gamepad Steam handle has changed */

    /* Touch events */
    SDL_EVENT_FINGER_DOWN(0x700),
    SDL_EVENT_FINGER_UP(0x701),
    SDL_EVENT_FINGER_MOTION(0x702),
    SDL_EVENT_FINGER_CANCELED(0x703),

    /* 0x800, 0x801, and 0x802 were the Gesture events from SDL2. Do not reuse these values! sdl2-compat needs them! */

    /* Clipboard events */
    SDL_EVENT_CLIPBOARD_UPDATE(0x900), /**< The clipboard or primary selection changed */

    /* Drag and drop events */
    SDL_EVENT_DROP_FILE(0x1000), /**< The system requests a file open */
    SDL_EVENT_DROP_TEXT(0x1001),                 /**< text/plain drag-and-drop event */
    SDL_EVENT_DROP_BEGIN(0x1002),                /**< A new set of drops is beginning (NULL filename) */
    SDL_EVENT_DROP_COMPLETE(0x1003),             /**< Current set of drops is now complete (NULL filename) */
    SDL_EVENT_DROP_POSITION(0x1004),             /**< Position while moving over the window */

    /* Audio hotplug events */
    SDL_EVENT_AUDIO_DEVICE_ADDED(0x1100),  /**< A new audio device is available */
    SDL_EVENT_AUDIO_DEVICE_REMOVED(0x1101),         /**< An audio device has been removed. */
    SDL_EVENT_AUDIO_DEVICE_FORMAT_CHANGED(0x1102),  /**< An audio device's format has been changed by the system. */

    /* Sensor events */
    SDL_EVENT_SENSOR_UPDATE(0x1200),     /**< A sensor was updated */

    /* Pressure-sensitive pen events */
    SDL_EVENT_PEN_PROXIMITY_IN(0x1300),  /**< Pressure-sensitive pen has become available */
    SDL_EVENT_PEN_PROXIMITY_OUT(0x1301),          /**< Pressure-sensitive pen has become unavailable */
    SDL_EVENT_PEN_DOWN(0x1302),                   /**< Pressure-sensitive pen touched drawing surface */
    SDL_EVENT_PEN_UP(0x1303),                     /**< Pressure-sensitive pen stopped touching drawing surface */
    SDL_EVENT_PEN_BUTTON_DOWN(0x1304),            /**< Pressure-sensitive pen button pressed */
    SDL_EVENT_PEN_BUTTON_UP(0x1305),              /**< Pressure-sensitive pen button released */
    SDL_EVENT_PEN_MOTION(0x1306),                 /**< Pressure-sensitive pen is moving on the tablet */
    SDL_EVENT_PEN_AXIS(0x1307),                   /**< Pressure-sensitive pen angle/pressure/etc changed */

    /* Camera hotplug events */
    SDL_EVENT_CAMERA_DEVICE_ADDED(0x1400),  /**< A new camera device is available */
    SDL_EVENT_CAMERA_DEVICE_REMOVED(0x1401),         /**< A camera device has been removed. */
    SDL_EVENT_CAMERA_DEVICE_APPROVED(0x1402),        /**< A camera device has been approved for use by the user. */
    SDL_EVENT_CAMERA_DEVICE_DENIED(0x1403),          /**< A camera device has been denied for use by the user. */

    /* Render events */
    SDL_EVENT_RENDER_TARGETS_RESET(0x2000), /**< The render targets have been reset and their contents need to be updated */
    SDL_EVENT_RENDER_DEVICE_RESET(0x2001), /**< The device has been reset and all textures need to be recreated */
    SDL_EVENT_RENDER_DEVICE_LOST(0x2002), /**< The device has been lost and can't be recovered. */

    /* Reserved events for private platforms */
    SDL_EVENT_PRIVATE0(0x4000),
    SDL_EVENT_PRIVATE1(0x4001),
    SDL_EVENT_PRIVATE2(0x4002),
    SDL_EVENT_PRIVATE3(0x4003),

    /* Internal events */
    SDL_EVENT_POLL_SENTINEL(0x7F00), /**< Signals the end of an event poll cycle */

    /** Events SDL_EVENT_USER through SDL_EVENT_LAST are for your use,
     *  and should be allocated with SDL_RegisterEvents()
     */
    SDL_EVENT_USER(0x8000),

    /**
     *  This last event is only for bounding internal arrays
     */
    SDL_EVENT_LAST(0xFFFF),

    /* This just makes sure the enum is the size of Uint32 */
    SDL_EVENT_ENUM_PADDING(0x7FFFFFFF);
    @Getter
    private final int value;
    private SdlEventType(int value){
        this.value = value;
    }
}
