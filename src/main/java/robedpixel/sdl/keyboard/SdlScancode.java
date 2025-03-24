package robedpixel.sdl.keyboard;

public class SdlScancode {
  public static int SDL_SCANCODE_UNKNOWN = 0;

  /** \name Usage page 0x07 These values are from usage page 0x07 (USB keyboard page). */
  /* @{ */

  public static int SDL_SCANCODE_A = 4;

  public static int SDL_SCANCODE_B = 5;
  public static int SDL_SCANCODE_C = 6;
  public static int SDL_SCANCODE_D = 7;
  public static int SDL_SCANCODE_E = 8;
  public static int SDL_SCANCODE_F = 9;
  public static int SDL_SCANCODE_G = 10;
  public static int SDL_SCANCODE_H = 11;
  public static int SDL_SCANCODE_I = 12;
  public static int SDL_SCANCODE_J = 13;
  public static int SDL_SCANCODE_K = 14;
  public static int SDL_SCANCODE_L = 15;
  public static int SDL_SCANCODE_M = 16;
  public static int SDL_SCANCODE_N = 17;
  public static int SDL_SCANCODE_O = 18;
  public static int SDL_SCANCODE_P = 19;
  public static int SDL_SCANCODE_Q = 20;
  public static int SDL_SCANCODE_R = 21;
  public static int SDL_SCANCODE_S = 22;
  public static int SDL_SCANCODE_T = 23;
  public static int SDL_SCANCODE_U = 24;
  public static int SDL_SCANCODE_V = 25;
  public static int SDL_SCANCODE_W = 26;
  public static int SDL_SCANCODE_X = 27;
  public static int SDL_SCANCODE_Y = 28;
  public static int SDL_SCANCODE_Z = 29;

  public static int SDL_SCANCODE_1 = 30;
  public static int SDL_SCANCODE_2 = 31;
  public static int SDL_SCANCODE_3 = 32;
  public static int SDL_SCANCODE_4 = 33;
  public static int SDL_SCANCODE_5 = 34;
  public static int SDL_SCANCODE_6 = 35;
  public static int SDL_SCANCODE_7 = 36;
  public static int SDL_SCANCODE_8 = 37;
  public static int SDL_SCANCODE_9 = 38;
  public static int SDL_SCANCODE_0 = 39;

  public static int SDL_SCANCODE_RETURN = 40;
  public static int SDL_SCANCODE_ESCAPE = 41;
  public static int SDL_SCANCODE_BACKSPACE = 42;
  public static int SDL_SCANCODE_TAB = 43;
  public static int SDL_SCANCODE_SPACE = 44;

  public static int SDL_SCANCODE_MINUS = 45;
  public static int SDL_SCANCODE_EQUALS = 46;
  public static int SDL_SCANCODE_LEFTBRACKET = 47;
  public static int SDL_SCANCODE_RIGHTBRACKET = 48;

  /**
   * < Located at the lower left of the return key on ISO keyboards and at the right end of the
   * QWERTY row on ANSI keyboards. Produces REVERSE SOLIDUS (backslash) and VERTICAL LINE in a US
   * layout; REVERSE SOLIDUS and VERTICAL LINE in a UK Mac layout; NUMBER SIGN and TILDE in a UK
   * Windows layout; DOLLAR SIGN and POUND SIGN in a Swiss German layout; NUMBER SIGN and APOSTROPHE
   * in a German layout; GRAVE ACCENT and POUND SIGN in a French Mac layout; and ASTERISK and MICRO
   * SIGN in a French Windows layout.
   */
  public static int SDL_SCANCODE_BACKSLASH = 49;

  /**
   * < ISO USB keyboards actually use this code instead of 49 for the same key; but all OSes I've
   * seen treat the two codes identically. So; as an implementor; unless your keyboard generates
   * both of those codes and your OS treats them differently; you should generate public static int
   * SDL_SCANCODE_BACKSLASH instead of this code. As a user; you should not rely on this code
   * because public static int SDL will never generate it with most (all?) keyboards.
   */
  public static int SDL_SCANCODE_NONUSHASH = 50;

  public static int SDL_SCANCODE_SEMICOLON = 51;
  public static int SDL_SCANCODE_APOSTROPHE = 52;

  /**
   * < Located in the top left corner (on both ANSI and ISO keyboards). Produces GRAVE ACCENT and
   * TILDE in a US Windows layout and in US and UK Mac layouts on ANSI keyboards; GRAVE ACCENT and
   * NOT SIGN in a UK Windows layout; SECTION SIGN and PLUS-MINUS SIGN in US and UK Mac layouts on
   * ISO keyboards; SECTION SIGN and DEGREE SIGN in a Swiss German layout (Mac: only on ISO
   * keyboards); CIRCUMFLEX ACCENT and DEGREE SIGN in a German layout (Mac: only on ISO keyboards);
   * SUPERSCRIPT TWO and TILDE in a French Windows layout; COMMERCIAL AT and NUMBER SIGN in a French
   * Mac layout on ISO keyboards; and LESS-THAN SIGN and GREATER-THAN SIGN in a Swiss German;
   * German; or French Mac layout on ANSI keyboards.
   */
  public static int SDL_SCANCODE_GRAVE = 53;

  public static int SDL_SCANCODE_COMMA = 54;
  public static int SDL_SCANCODE_PERIOD = 55;
  public static int SDL_SCANCODE_SLASH = 56;

  public static int SDL_SCANCODE_CAPSLOCK = 57;

  public static int SDL_SCANCODE_F1 = 58;
  public static int SDL_SCANCODE_F2 = 59;
  public static int SDL_SCANCODE_F3 = 60;
  public static int SDL_SCANCODE_F4 = 61;
  public static int SDL_SCANCODE_F5 = 62;
  public static int SDL_SCANCODE_F6 = 63;
  public static int SDL_SCANCODE_F7 = 64;
  public static int SDL_SCANCODE_F8 = 65;
  public static int SDL_SCANCODE_F9 = 66;
  public static int SDL_SCANCODE_F10 = 67;
  public static int SDL_SCANCODE_F11 = 68;
  public static int SDL_SCANCODE_F12 = 69;

  public static int SDL_SCANCODE_PRINTSCREEN = 70;
  public static int SDL_SCANCODE_SCROLLLOCK = 71;
  public static int SDL_SCANCODE_PAUSE = 72;

  /** < insert on PC; help on some Mac keyboards (but does send code 73; not 117) */
  public static int SDL_SCANCODE_INSERT = 73;

  public static int SDL_SCANCODE_HOME = 74;
  public static int SDL_SCANCODE_PAGEUP = 75;
  public static int SDL_SCANCODE_DELETE = 76;
  public static int SDL_SCANCODE_END = 77;
  public static int SDL_SCANCODE_PAGEDOWN = 78;
  public static int SDL_SCANCODE_RIGHT = 79;
  public static int SDL_SCANCODE_LEFT = 80;
  public static int SDL_SCANCODE_DOWN = 81;
  public static int SDL_SCANCODE_UP = 82;

  /** < num lock on PC; clear on Mac keyboards */
  public static int SDL_SCANCODE_NUMLOCKCLEAR = 83;

  public static int SDL_SCANCODE_KP_DIVIDE = 84;
  public static int SDL_SCANCODE_KP_MULTIPLY = 85;
  public static int SDL_SCANCODE_KP_MINUS = 86;
  public static int SDL_SCANCODE_KP_PLUS = 87;
  public static int SDL_SCANCODE_KP_ENTER = 88;
  public static int SDL_SCANCODE_KP_1 = 89;
  public static int SDL_SCANCODE_KP_2 = 90;
  public static int SDL_SCANCODE_KP_3 = 91;
  public static int SDL_SCANCODE_KP_4 = 92;
  public static int SDL_SCANCODE_KP_5 = 93;
  public static int SDL_SCANCODE_KP_6 = 94;
  public static int SDL_SCANCODE_KP_7 = 95;
  public static int SDL_SCANCODE_KP_8 = 96;
  public static int SDL_SCANCODE_KP_9 = 97;
  public static int SDL_SCANCODE_KP_0 = 98;
  public static int SDL_SCANCODE_KP_PERIOD = 99;

  /**
   * < This is the additional key that ISO keyboards have over ANSI ones; located between left shift
   * and Y. Produces GRAVE ACCENT and TILDE in a US or UK Mac layout; REVERSE SOLIDUS (backslash)
   * and VERTICAL LINE in a US or UK Windows layout; and LESS-THAN SIGN and GREATER-THAN SIGN in a
   * Swiss German; German; or French layout.
   */
  public static int SDL_SCANCODE_NONUSBACKSLASH = 100;

  /** < windows contextual menu; compose */
  public static int SDL_SCANCODE_APPLICATION = 101;

  /**
   * < The USB document says this is a status flag; not a physical key - but some Mac keyboards do
   * have a power key.
   */
  public static int SDL_SCANCODE_POWER = 102;

  public static int SDL_SCANCODE_KP_EQUALS = 103;
  public static int SDL_SCANCODE_F13 = 104;
  public static int SDL_SCANCODE_F14 = 105;
  public static int SDL_SCANCODE_F15 = 106;
  public static int SDL_SCANCODE_F16 = 107;
  public static int SDL_SCANCODE_F17 = 108;
  public static int SDL_SCANCODE_F18 = 109;
  public static int SDL_SCANCODE_F19 = 110;
  public static int SDL_SCANCODE_F20 = 111;
  public static int SDL_SCANCODE_F21 = 112;
  public static int SDL_SCANCODE_F22 = 113;
  public static int SDL_SCANCODE_F23 = 114;
  public static int SDL_SCANCODE_F24 = 115;
  public static int SDL_SCANCODE_EXECUTE = 116;

  /** < AL Integrated Help Center */
  public static int SDL_SCANCODE_HELP = 117;

  /** < Menu (show menu) */
  public static int SDL_SCANCODE_MENU = 118;

  public static int SDL_SCANCODE_SELECT = 119;

  /** < AC Stop */
  public static int SDL_SCANCODE_STOP = 120;

  /** < AC Redo/Repeat */
  public static int SDL_SCANCODE_AGAIN = 121;

  /** < AC Undo */
  public static int SDL_SCANCODE_UNDO = 122;

  /** < AC Cut */
  public static int SDL_SCANCODE_CUT = 123;

  /** < AC Copy */
  public static int SDL_SCANCODE_COPY = 124;

  /** < AC Paste */
  public static int SDL_SCANCODE_PASTE = 125;

  /** < AC Find */
  public static int SDL_SCANCODE_FIND = 126;

  public static int SDL_SCANCODE_MUTE = 127;
  public static int SDL_SCANCODE_VOLUMEUP = 128;
  public static int SDL_SCANCODE_VOLUMEDOWN = 129;
  /* not sure whether there's a reason to enable these */
  /*     public static int SDL_SCANCODE_LOCKINGCAPSLOCK = 130;  */
  /*     public static int SDL_SCANCODE_LOCKINGNUMLOCK = 131; */
  /*     public static int SDL_SCANCODE_LOCKINGSCROLLLOCK = 132; */
  public static int SDL_SCANCODE_KP_COMMA = 133;
  public static int SDL_SCANCODE_KP_EQUALSAS400 = 134;

  /** < used on Asian keyboards; see footnotes in USB doc */
  public static int SDL_SCANCODE_INTERNATIONAL1 = 135;

  public static int SDL_SCANCODE_INTERNATIONAL2 = 136;

  /** < Yen */
  public static int SDL_SCANCODE_INTERNATIONAL3 = 137;

  public static int SDL_SCANCODE_INTERNATIONAL4 = 138;
  public static int SDL_SCANCODE_INTERNATIONAL5 = 139;
  public static int SDL_SCANCODE_INTERNATIONAL6 = 140;
  public static int SDL_SCANCODE_INTERNATIONAL7 = 141;
  public static int SDL_SCANCODE_INTERNATIONAL8 = 142;
  public static int SDL_SCANCODE_INTERNATIONAL9 = 143;

  /** < Hangul/English toggle */
  public static int SDL_SCANCODE_LANG1 = 144;

  /** < Hanja conversion */
  public static int SDL_SCANCODE_LANG2 = 145;

  /** < Katakana */
  public static int SDL_SCANCODE_LANG3 = 146;

  /** < Hiragana */
  public static int SDL_SCANCODE_LANG4 = 147;

  /** < Zenkaku/Hankaku */
  public static int SDL_SCANCODE_LANG5 = 148;

  /** < reserved */
  public static int SDL_SCANCODE_LANG6 = 149;

  /** < reserved */
  public static int SDL_SCANCODE_LANG7 = 150;

  /** < reserved */
  public static int SDL_SCANCODE_LANG8 = 151;

  /** < reserved */
  public static int SDL_SCANCODE_LANG9 = 152;

  /** < Erase-Eaze */
  public static int SDL_SCANCODE_ALTERASE = 153;

  public static int SDL_SCANCODE_SYSREQ = 154;

  /** < AC Cancel */
  public static int SDL_SCANCODE_CANCEL = 155;

  public static int SDL_SCANCODE_CLEAR = 156;
  public static int SDL_SCANCODE_PRIOR = 157;
  public static int SDL_SCANCODE_RETURN2 = 158;
  public static int SDL_SCANCODE_SEPARATOR = 159;
  public static int SDL_SCANCODE_OUT = 160;
  public static int SDL_SCANCODE_OPER = 161;
  public static int SDL_SCANCODE_CLEARAGAIN = 162;
  public static int SDL_SCANCODE_CRSEL = 163;
  public static int SDL_SCANCODE_EXSEL = 164;

  public static int SDL_SCANCODE_KP_00 = 176;
  public static int SDL_SCANCODE_KP_000 = 177;
  public static int SDL_SCANCODE_THOUSANDSSEPARATOR = 178;
  public static int SDL_SCANCODE_DECIMALSEPARATOR = 179;
  public static int SDL_SCANCODE_CURRENCYUNIT = 180;
  public static int SDL_SCANCODE_CURRENCYSUBUNIT = 181;
  public static int SDL_SCANCODE_KP_LEFTPAREN = 182;
  public static int SDL_SCANCODE_KP_RIGHTPAREN = 183;
  public static int SDL_SCANCODE_KP_LEFTBRACE = 184;
  public static int SDL_SCANCODE_KP_RIGHTBRACE = 185;
  public static int SDL_SCANCODE_KP_TAB = 186;
  public static int SDL_SCANCODE_KP_BACKSPACE = 187;
  public static int SDL_SCANCODE_KP_A = 188;
  public static int SDL_SCANCODE_KP_B = 189;
  public static int SDL_SCANCODE_KP_C = 190;
  public static int SDL_SCANCODE_KP_D = 191;
  public static int SDL_SCANCODE_KP_E = 192;
  public static int SDL_SCANCODE_KP_F = 193;
  public static int SDL_SCANCODE_KP_XOR = 194;
  public static int SDL_SCANCODE_KP_POWER = 195;
  public static int SDL_SCANCODE_KP_PERCENT = 196;
  public static int SDL_SCANCODE_KP_LESS = 197;
  public static int SDL_SCANCODE_KP_GREATER = 198;
  public static int SDL_SCANCODE_KP_AMPERSAND = 199;
  public static int SDL_SCANCODE_KP_DBLAMPERSAND = 200;
  public static int SDL_SCANCODE_KP_VERTICALBAR = 201;
  public static int SDL_SCANCODE_KP_DBLVERTICALBAR = 202;
  public static int SDL_SCANCODE_KP_COLON = 203;
  public static int SDL_SCANCODE_KP_HASH = 204;
  public static int SDL_SCANCODE_KP_SPACE = 205;
  public static int SDL_SCANCODE_KP_AT = 206;
  public static int SDL_SCANCODE_KP_EXCLAM = 207;
  public static int SDL_SCANCODE_KP_MEMSTORE = 208;
  public static int SDL_SCANCODE_KP_MEMRECALL = 209;
  public static int SDL_SCANCODE_KP_MEMCLEAR = 210;
  public static int SDL_SCANCODE_KP_MEMADD = 211;
  public static int SDL_SCANCODE_KP_MEMSUBTRACT = 212;
  public static int SDL_SCANCODE_KP_MEMMULTIPLY = 213;
  public static int SDL_SCANCODE_KP_MEMDIVIDE = 214;
  public static int SDL_SCANCODE_KP_PLUSMINUS = 215;
  public static int SDL_SCANCODE_KP_CLEAR = 216;
  public static int SDL_SCANCODE_KP_CLEARENTRY = 217;
  public static int SDL_SCANCODE_KP_BINARY = 218;
  public static int SDL_SCANCODE_KP_OCTAL = 219;
  public static int SDL_SCANCODE_KP_DECIMAL = 220;
  public static int SDL_SCANCODE_KP_HEXADECIMAL = 221;

  public static int SDL_SCANCODE_LCTRL = 224;
  public static int SDL_SCANCODE_LSHIFT = 225;

  /** < alt; option */
  public static int SDL_SCANCODE_LALT = 226;

  /** < windows; command (apple); meta */
  public static int SDL_SCANCODE_LGUI = 227;

  public static int SDL_SCANCODE_RCTRL = 228;
  public static int SDL_SCANCODE_RSHIFT = 229;

  /** < alt gr; option */
  public static int SDL_SCANCODE_RALT = 230;

  /** < windows; command (apple); meta */
  public static int SDL_SCANCODE_RGUI = 231;

  /**
   * < I'm not sure if this is really not covered by any of the above; but since there's a special
   * public static int SDL_KMOD_MODE for it I'm adding it here
   */
  public static int SDL_SCANCODE_MODE = 257;

  /* @} */
  /* Usage page 0x07 */

  /**
   * \name Usage page 0x0C
   *
   * <p>These values are mapped from usage page 0x0C (USB consumer page).
   *
   * <p>There are way more keys in the spec than we can represent in the current scancode range; so
   * pick the ones that commonly come up in real world usage.
   */
  /* @{ */
  /** < Sleep */
  public static int SDL_SCANCODE_SLEEP = 258;

  /** < Wake */
  public static int SDL_SCANCODE_WAKE = 259;

  /** < Channel Increment */
  public static int SDL_SCANCODE_CHANNEL_INCREMENT = 260;

  /** < Channel Decrement */
  public static int SDL_SCANCODE_CHANNEL_DECREMENT = 261;

  /** < Play */
  public static int SDL_SCANCODE_MEDIA_PLAY = 262;

  /** < Pause */
  public static int SDL_SCANCODE_MEDIA_PAUSE = 263;

  /** < Record */
  public static int SDL_SCANCODE_MEDIA_RECORD = 264;

  /** < Fast Forward */
  public static int SDL_SCANCODE_MEDIA_FAST_FORWARD = 265;

  /** < Rewind */
  public static int SDL_SCANCODE_MEDIA_REWIND = 266;

  /** < Next Track */
  public static int SDL_SCANCODE_MEDIA_NEXT_TRACK = 267;

  /** < Previous Track */
  public static int SDL_SCANCODE_MEDIA_PREVIOUS_TRACK = 268;

  /** < Stop */
  public static int SDL_SCANCODE_MEDIA_STOP = 269;

  /** < Eject */
  public static int SDL_SCANCODE_MEDIA_EJECT = 270;

  /** < Play / Pause */
  public static int SDL_SCANCODE_MEDIA_PLAY_PAUSE = 271;

  /** Media Select */
  public static int SDL_SCANCODE_MEDIA_SELECT = 272;

  /** < AC New */
  public static int SDL_SCANCODE_AC_NEW = 273;

  /** < AC Open */
  public static int SDL_SCANCODE_AC_OPEN = 274;

  /** < AC Close */
  public static int SDL_SCANCODE_AC_CLOSE = 275;

  /** < AC Exit */
  public static int SDL_SCANCODE_AC_EXIT = 276;

  /** < AC Save */
  public static int SDL_SCANCODE_AC_SAVE = 277;

  /** < AC Print */
  public static int SDL_SCANCODE_AC_PRINT = 278;

  /** < AC Properties */
  public static int SDL_SCANCODE_AC_PROPERTIES = 279;

  /** < AC Search */
  public static int SDL_SCANCODE_AC_SEARCH = 280;

  /** < AC Home */
  public static int SDL_SCANCODE_AC_HOME = 281;

  /** < AC Back */
  public static int SDL_SCANCODE_AC_BACK = 282;

  /** < AC Forward */
  public static int SDL_SCANCODE_AC_FORWARD = 283;

  /** < AC Stop */
  public static int SDL_SCANCODE_AC_STOP = 284;

  /** < AC Refresh */
  public static int SDL_SCANCODE_AC_REFRESH = 285;

  /** < AC Bookmarks */
  public static int SDL_SCANCODE_AC_BOOKMARKS = 286;

  /* @} */
  /* Usage page 0x0C */

  /**
   * \name Mobile keys
   *
   * <p>These are values that are often used on mobile phones.
   */
  /* @{ */
  /**
   * < Usually situated below the display on phones and used as a multi-function feature key for
   * selecting a software defined function shown on the bottom left of the display.
   */
  public static int SDL_SCANCODE_SOFTLEFT = 287;

  /**
   * < Usually situated below the display on phones and used as a multi-function feature key for
   * selecting a software defined function shown on the bottom right of the display.
   */
  public static int SDL_SCANCODE_SOFTRIGHT = 288;

  /** < Used for accepting phone calls. */
  public static int SDL_SCANCODE_CALL = 289;

  /** < Used for rejecting phone calls. */
  public static int SDL_SCANCODE_ENDCALL = 290;

  /* @} */
  /* Mobile keys */

  /* Add any other keys here. */
  /** < 400-500 reserved for dynamic keycodes */
  public static int SDL_SCANCODE_RESERVED = 400;

  /** < not a key; just marks the number of scancodes for array bounds */
  public static int SDL_SCANCODE_COUNT = 512;
}
