package robedpixel.sdl.keyboard;

public class SdlKeycode {
  public static int SDLK_EXTENDED_MASK = (1 << 29);
  public static int SDLK_SCANCODE_MASK = (1 << 30);

  public static int SDL_SCANCODE_TO_KEYCODE(int x) {
    return x | SDLK_EXTENDED_MASK;
  }

  /** < 0 */
  public static int SDLK_UNKNOWN = 0x00000000;

  /** < '\r' */
  public static int SDLK_RETURN = 0x0000000d;

  /** < '\x1B' */
  public static int SDLK_ESCAPE = 0x0000001b;

  /** < '\b' */
  public static int SDLK_BACKSPACE = 0x00000008;

  /** < '\t' */
  public static int SDLK_TAB = 0x00000009;

  /** < ' ' */
  public static int SDLK_SPACE = 0x00000020;

  /** < '!' */
  public static int SDLK_EXCLAIM = 0x00000021;

  /** < '"' */
  public static int SDLK_DBLAPOSTROPHE = 0x00000022;

  /** < '#' */
  public static int SDLK_HASH = 0x00000023;

  /** < '$' */
  public static int SDLK_DOLLAR = 0x00000024;

  /** < '%' */
  public static int SDLK_PERCENT = 0x00000025;

  /** < '&' */
  public static int SDLK_AMPERSAND = 0x00000026;

  /** < '\'' */
  public static int SDLK_APOSTROPHE = 0x00000027;

  /** < '(' */
  public static int SDLK_LEFTPAREN = 0x00000028;

  /** < ')' */
  public static int SDLK_RIGHTPAREN = 0x00000029;

  /** < '*' */
  public static int SDLK_ASTERISK = 0x0000002a;

  /** < '+' */
  public static int SDLK_PLUS = 0x0000002b;

  /** < ',' */
  public static int SDLK_COMMA = 0x0000002c;

  /** < '-' */
  public static int SDLK_MINUS = 0x0000002d;

  /** < '.' */
  public static int SDLK_PERIOD = 0x0000002e;

  /** < '/' */
  public static int SDLK_SLASH = 0x0000002f;

  /** < '0' */
  public static int SDLK_0 = 0x00000030;

  /** < '1' */
  public static int SDLK_1 = 0x00000031;

  /** < '2' */
  public static int SDLK_2 = 0x00000032;

  /** < '3' */
  public static int SDLK_3 = 0x00000033;

  /** < '4' */
  public static int SDLK_4 = 0x00000034;

  /** < '5' */
  public static int SDLK_5 = 0x00000035;

  /** < '6' */
  public static int SDLK_6 = 0x00000036;

  /** < '7' */
  public static int SDLK_7 = 0x00000037;

  /** < '8' */
  public static int SDLK_8 = 0x00000038;

  /** < '9' */
  public static int SDLK_9 = 0x00000039;

  /** < ':' */
  public static int SDLK_COLON = 0x0000003a;

  /** < ';' */
  public static int SDLK_SEMICOLON = 0x0000003b;

  /** < '<' */
  public static int SDLK_LESS = 0x0000003c;

  /** < '=' */
  public static int SDLK_EQUALS = 0x0000003d;

  /** < '>' */
  public static int SDLK_GREATER = 0x0000003e;

  /** < '?' */
  public static int SDLK_QUESTION = 0x0000003f;

  /** < '@' */
  public static int SDLK_AT = 0x00000040;

  /** < '[' */
  public static int SDLK_LEFTBRACKET = 0x0000005b;

  /** < '\\' */
  public static int SDLK_BACKSLASH = 0x0000005c;

  /** < ']' */
  public static int SDLK_RIGHTBRACKET = 0x0000005d;

  /** < '^' */
  public static int SDLK_CARET = 0x0000005e;

  /** < '_' */
  public static int SDLK_UNDERSCORE = 0x0000005f;

  /** < '`' */
  public static int SDLK_GRAVE = 0x00000060;

  /** < 'a' */
  public static int SDLK_A = 0x00000061;

  /** < 'b' */
  public static int SDLK_B = 0x00000062;

  /** < 'c' */
  public static int SDLK_C = 0x00000063;

  /** < 'd' */
  public static int SDLK_D = 0x00000064;

  /** < 'e' */
  public static int SDLK_E = 0x00000065;

  /** < 'f' */
  public static int SDLK_F = 0x00000066;

  /** < 'g' */
  public static int SDLK_G = 0x00000067;

  /** < 'h' */
  public static int SDLK_H = 0x00000068;

  /** < 'i' */
  public static int SDLK_I = 0x00000069;

  /** < 'j' */
  public static int SDLK_J = 0x0000006a;

  /** < 'k' */
  public static int SDLK_K = 0x0000006b;

  /** < 'l' */
  public static int SDLK_L = 0x0000006c;

  /** < 'm' */
  public static int SDLK_M = 0x0000006d;

  /** < 'n' */
  public static int SDLK_N = 0x0000006e;

  /** < 'o' */
  public static int SDLK_O = 0x0000006f;

  /** < 'p' */
  public static int SDLK_P = 0x00000070;

  /** < 'q' */
  public static int SDLK_Q = 0x00000071;

  /** < 'r' */
  public static int SDLK_R = 0x00000072;

  /** < 's' */
  public static int SDLK_S = 0x00000073;

  /** < 't' */
  public static int SDLK_T = 0x00000074;

  /** < 'u' */
  public static int SDLK_U = 0x00000075;

  /** < 'v' */
  public static int SDLK_V = 0x00000076;

  /** < 'w' */
  public static int SDLK_W = 0x00000077;

  /** < 'x' */
  public static int SDLK_X = 0x00000078;

  /** < 'y' */
  public static int SDLK_Y = 0x00000079;

  /** < 'z' */
  public static int SDLK_Z = 0x0000007a;

  /** < '{' */
  public static int SDLK_LEFTBRACE = 0x0000007b;

  /** < '|' */
  public static int SDLK_PIPE = 0x0000007c;

  /** < '}' */
  public static int SDLK_RIGHTBRACE = 0x0000007d;

  /** < '~' */
  public static int SDLK_TILDE = 0x0000007e;

  /** < '\x7F' */
  public static int SDLK_DELETE = 0x0000007f;

  /** < '\xB1' */
  public static int SDLK_PLUSMINUS = 0x000000b1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CAPSLOCK) */
  public static int SDLK_CAPSLOCK = 0x40000039;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F1) */
  public static int SDLK_F1 = 0x4000003a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F2) */
  public static int SDLK_F2 = 0x4000003b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F3) */
  public static int SDLK_F3 = 0x4000003c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F4) */
  public static int SDLK_F4 = 0x4000003d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F5) */
  public static int SDLK_F5 = 0x4000003e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F6) */
  public static int SDLK_F6 = 0x4000003f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F7) */
  public static int SDLK_F7 = 0x40000040;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F8) */
  public static int SDLK_F8 = 0x40000041;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F9) */
  public static int SDLK_F9 = 0x40000042;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F10) */
  public static int SDLK_F10 = 0x40000043;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F11) */
  public static int SDLK_F11 = 0x40000044;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F12) */
  public static int SDLK_F12 = 0x40000045;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRINTSCREEN) */
  public static int SDLK_PRINTSCREEN = 0x40000046;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SCROLLLOCK) */
  public static int SDLK_SCROLLLOCK = 0x40000047;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAUSE) */
  public static int SDLK_PAUSE = 0x40000048;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_INSERT) */
  public static int SDLK_INSERT = 0x40000049;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HOME) */
  public static int SDLK_HOME = 0x4000004a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEUP) */
  public static int SDLK_PAGEUP = 0x4000004b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_END) */
  public static int SDLK_END = 0x4000004d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PAGEDOWN) */
  public static int SDLK_PAGEDOWN = 0x4000004e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RIGHT) */
  public static int SDLK_RIGHT = 0x4000004f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LEFT) */
  public static int SDLK_LEFT = 0x40000050;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DOWN) */
  public static int SDLK_DOWN = 0x40000051;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UP) */
  public static int SDLK_UP = 0x40000052;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_NUMLOCKCLEAR) */
  public static int SDLK_NUMLOCKCLEAR = 0x40000053;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DIVIDE) */
  public static int SDLK_KP_DIVIDE = 0x40000054;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MULTIPLY) */
  public static int SDLK_KP_MULTIPLY = 0x40000055;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MINUS) */
  public static int SDLK_KP_MINUS = 0x40000056;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUS) */
  public static int SDLK_KP_PLUS = 0x40000057;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_ENTER) */
  public static int SDLK_KP_ENTER = 0x40000058;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_1) */
  public static int SDLK_KP_1 = 0x40000059;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_2) */
  public static int SDLK_KP_2 = 0x4000005a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_3) */
  public static int SDLK_KP_3 = 0x4000005b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_4) */
  public static int SDLK_KP_4 = 0x4000005c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_5) */
  public static int SDLK_KP_5 = 0x4000005d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_6) */
  public static int SDLK_KP_6 = 0x4000005e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_7) */
  public static int SDLK_KP_7 = 0x4000005f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_8) */
  public static int SDLK_KP_8 = 0x40000060;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_9) */
  public static int SDLK_KP_9 = 0x40000061;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_0) */
  public static int SDLK_KP_0 = 0x40000062;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERIOD) */
  public static int SDLK_KP_PERIOD = 0x40000063;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_APPLICATION) */
  public static int SDLK_APPLICATION = 0x40000065;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_POWER) */
  public static int SDLK_POWER = 0x40000066;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALS) */
  public static int SDLK_KP_EQUALS = 0x40000067;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F13) */
  public static int SDLK_F13 = 0x40000068;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F14) */
  public static int SDLK_F14 = 0x40000069;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F15) */
  public static int SDLK_F15 = 0x4000006a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F16) */
  public static int SDLK_F16 = 0x4000006b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F17) */
  public static int SDLK_F17 = 0x4000006c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F18) */
  public static int SDLK_F18 = 0x4000006d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F19) */
  public static int SDLK_F19 = 0x4000006e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F20) */
  public static int SDLK_F20 = 0x4000006f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F21) */
  public static int SDLK_F21 = 0x40000070;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F22) */
  public static int SDLK_F22 = 0x40000071;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F23) */
  public static int SDLK_F23 = 0x40000072;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_F24) */
  public static int SDLK_F24 = 0x40000073;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXECUTE) */
  public static int SDLK_EXECUTE = 0x40000074;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_HELP) */
  public static int SDLK_HELP = 0x40000075;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MENU) */
  public static int SDLK_MENU = 0x40000076;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SELECT) */
  public static int SDLK_SELECT = 0x40000077;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_STOP) */
  public static int SDLK_STOP = 0x40000078;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AGAIN) */
  public static int SDLK_AGAIN = 0x40000079;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_UNDO) */
  public static int SDLK_UNDO = 0x4000007a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CUT) */
  public static int SDLK_CUT = 0x4000007b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_COPY) */
  public static int SDLK_COPY = 0x4000007c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PASTE) */
  public static int SDLK_PASTE = 0x4000007d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_FIND) */
  public static int SDLK_FIND = 0x4000007e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MUTE) */
  public static int SDLK_MUTE = 0x4000007f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEUP) */
  public static int SDLK_VOLUMEUP = 0x40000080;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_VOLUMEDOWN) */
  public static int SDLK_VOLUMEDOWN = 0x40000081;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COMMA) */
  public static int SDLK_KP_COMMA = 0x40000085;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EQUALSAS400) */
  public static int SDLK_KP_EQUALSAS400 = 0x40000086;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ALTERASE) */
  public static int SDLK_ALTERASE = 0x40000099;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SYSREQ) */
  public static int SDLK_SYSREQ = 0x4000009a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CANCEL) */
  public static int SDLK_CANCEL = 0x4000009b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEAR) */
  public static int SDLK_CLEAR = 0x4000009c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_PRIOR) */
  public static int SDLK_PRIOR = 0x4000009d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RETURN2) */
  public static int SDLK_RETURN2 = 0x4000009e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SEPARATOR) */
  public static int SDLK_SEPARATOR = 0x4000009f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OUT) */
  public static int SDLK_OUT = 0x400000a0;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_OPER) */
  public static int SDLK_OPER = 0x400000a1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CLEARAGAIN) */
  public static int SDLK_CLEARAGAIN = 0x400000a2;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CRSEL) */
  public static int SDLK_CRSEL = 0x400000a3;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_EXSEL) */
  public static int SDLK_EXSEL = 0x400000a4;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_00) */
  public static int SDLK_KP_00 = 0x400000b0;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_000) */
  public static int SDLK_KP_000 = 0x400000b1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_THOUSANDSSEPARATOR) */
  public static int SDLK_THOUSANDSSEPARATOR = 0x400000b2;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_DECIMALSEPARATOR) */
  public static int SDLK_DECIMALSEPARATOR = 0x400000b3;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYUNIT) */
  public static int SDLK_CURRENCYUNIT = 0x400000b4;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CURRENCYSUBUNIT) */
  public static int SDLK_CURRENCYSUBUNIT = 0x400000b5;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTPAREN) */
  public static int SDLK_KP_LEFTPAREN = 0x400000b6;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTPAREN) */
  public static int SDLK_KP_RIGHTPAREN = 0x400000b7;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LEFTBRACE) */
  public static int SDLK_KP_LEFTBRACE = 0x400000b8;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_RIGHTBRACE) */
  public static int SDLK_KP_RIGHTBRACE = 0x400000b9;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_TAB) */
  public static int SDLK_KP_TAB = 0x400000ba;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BACKSPACE) */
  public static int SDLK_KP_BACKSPACE = 0x400000bb;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_A) */
  public static int SDLK_KP_A = 0x400000bc;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_B) */
  public static int SDLK_KP_B = 0x400000bd;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_C) */
  public static int SDLK_KP_C = 0x400000be;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_D) */
  public static int SDLK_KP_D = 0x400000bf;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_E) */
  public static int SDLK_KP_E = 0x400000c0;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_F) */
  public static int SDLK_KP_F = 0x400000c1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_XOR) */
  public static int SDLK_KP_XOR = 0x400000c2;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_POWER) */
  public static int SDLK_KP_POWER = 0x400000c3;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PERCENT) */
  public static int SDLK_KP_PERCENT = 0x400000c4;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_LESS) */
  public static int SDLK_KP_LESS = 0x400000c5;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_GREATER) */
  public static int SDLK_KP_GREATER = 0x400000c6;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AMPERSAND) */
  public static int SDLK_KP_AMPERSAND = 0x400000c7;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLAMPERSAND) */
  public static int SDLK_KP_DBLAMPERSAND = 0x400000c8;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_VERTICALBAR) */
  public static int SDLK_KP_VERTICALBAR = 0x400000c9;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DBLVERTICALBAR) */
  public static int SDLK_KP_DBLVERTICALBAR = 0x400000ca;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_COLON) */
  public static int SDLK_KP_COLON = 0x400000cb;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HASH) */
  public static int SDLK_KP_HASH = 0x400000cc;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_SPACE) */
  public static int SDLK_KP_SPACE = 0x400000cd;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_AT) */
  public static int SDLK_KP_AT = 0x400000ce;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_EXCLAM) */
  public static int SDLK_KP_EXCLAM = 0x400000cf;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSTORE) */
  public static int SDLK_KP_MEMSTORE = 0x400000d0;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMRECALL) */
  public static int SDLK_KP_MEMRECALL = 0x400000d1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMCLEAR) */
  public static int SDLK_KP_MEMCLEAR = 0x400000d2;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMADD) */
  public static int SDLK_KP_MEMADD = 0x400000d3;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMSUBTRACT) */
  public static int SDLK_KP_MEMSUBTRACT = 0x400000d4;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMMULTIPLY) */
  public static int SDLK_KP_MEMMULTIPLY = 0x400000d5;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_MEMDIVIDE) */
  public static int SDLK_KP_MEMDIVIDE = 0x400000d6;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_PLUSMINUS) */
  public static int SDLK_KP_PLUSMINUS = 0x400000d7;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEAR) */
  public static int SDLK_KP_CLEAR = 0x400000d8;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_CLEARENTRY) */
  public static int SDLK_KP_CLEARENTRY = 0x400000d9;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_BINARY) */
  public static int SDLK_KP_BINARY = 0x400000da;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_OCTAL) */
  public static int SDLK_KP_OCTAL = 0x400000db;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_DECIMAL) */
  public static int SDLK_KP_DECIMAL = 0x400000dc;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_KP_HEXADECIMAL) */
  public static int SDLK_KP_HEXADECIMAL = 0x400000dd;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LCTRL) */
  public static int SDLK_LCTRL = 0x400000e0;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LSHIFT) */
  public static int SDLK_LSHIFT = 0x400000e1;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LALT) */
  public static int SDLK_LALT = 0x400000e2;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_LGUI) */
  public static int SDLK_LGUI = 0x400000e3;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RCTRL) */
  public static int SDLK_RCTRL = 0x400000e4;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RSHIFT) */
  public static int SDLK_RSHIFT = 0x400000e5;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RALT) */
  public static int SDLK_RALT = 0x400000e6;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_RGUI) */
  public static int SDLK_RGUI = 0x400000e7;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MODE) */
  public static int SDLK_MODE = 0x40000101;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SLEEP) */
  public static int SDLK_SLEEP = 0x40000102;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_WAKE) */
  public static int SDLK_WAKE = 0x40000103;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_INCREMENT) */
  public static int SDLK_CHANNEL_INCREMENT = 0x40000104;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CHANNEL_DECREMENT) */
  public static int SDLK_CHANNEL_DECREMENT = 0x40000105;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY) */
  public static int SDLK_MEDIA_PLAY = 0x40000106;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PAUSE) */
  public static int SDLK_MEDIA_PAUSE = 0x40000107;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_RECORD) */
  public static int SDLK_MEDIA_RECORD = 0x40000108;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_FAST_FORWARD) */
  public static int SDLK_MEDIA_FAST_FORWARD = 0x40000109;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_REWIND) */
  public static int SDLK_MEDIA_REWIND = 0x4000010a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_NEXT_TRACK) */
  public static int SDLK_MEDIA_NEXT_TRACK = 0x4000010b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PREVIOUS_TRACK) */
  public static int SDLK_MEDIA_PREVIOUS_TRACK = 0x4000010c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_STOP) */
  public static int SDLK_MEDIA_STOP = 0x4000010d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_EJECT) */
  public static int SDLK_MEDIA_EJECT = 0x4000010e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_PLAY_PAUSE) */
  public static int SDLK_MEDIA_PLAY_PAUSE = 0x4000010f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_MEDIA_SELECT) */
  public static int SDLK_MEDIA_SELECT = 0x40000110;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_NEW) */
  public static int SDLK_AC_NEW = 0x40000111;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_OPEN) */
  public static int SDLK_AC_OPEN = 0x40000112;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_CLOSE) */
  public static int SDLK_AC_CLOSE = 0x40000113;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_EXIT) */
  public static int SDLK_AC_EXIT = 0x40000114;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SAVE) */
  public static int SDLK_AC_SAVE = 0x40000115;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PRINT) */
  public static int SDLK_AC_PRINT = 0x40000116;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_PROPERTIES) */
  public static int SDLK_AC_PROPERTIES = 0x40000117;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_SEARCH) */
  public static int SDLK_AC_SEARCH = 0x40000118;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_HOME) */
  public static int SDLK_AC_HOME = 0x40000119;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BACK) */
  public static int SDLK_AC_BACK = 0x4000011a;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_FORWARD) */
  public static int SDLK_AC_FORWARD = 0x4000011b;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_STOP) */
  public static int SDLK_AC_STOP = 0x4000011c;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_REFRESH) */
  public static int SDLK_AC_REFRESH = 0x4000011d;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_AC_BOOKMARKS) */
  public static int SDLK_AC_BOOKMARKS = 0x4000011e;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTLEFT) */
  public static int SDLK_SOFTLEFT = 0x4000011f;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_SOFTRIGHT) */
  public static int SDLK_SOFTRIGHT = 0x40000120;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_CALL) */
  public static int SDLK_CALL = 0x40000121;

  /** < SDL_SCANCODE_TO_KEYCODE(SDL_SCANCODE_ENDCALL) */
  public static int SDLK_ENDCALL = 0x40000122;

  /** < Extended key Left Tab */
  public static int SDLK_LEFT_TAB = 0x20000001;

  /** < Extended key Level 5 Shift */
  public static int SDLK_LEVEL5_SHIFT = 0x20000002;

  /** < Extended key Multi-key Compose */
  public static int SDLK_MULTI_KEY_COMPOSE = 0x20000003;

  /** < Extended key Left Meta */
  public static int SDLK_LMETA = 0x20000004;

  /** < Extended key Right Meta */
  public static int SDLK_RMETA = 0x20000005;

  /** < Extended key Left Hyper */
  public static int SDLK_LHYPER = 0x20000006;

  /** < Extended key Right Hyper */
  public static int SDLK_RHYPER = 0x20000007;
}
