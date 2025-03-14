package robedpixel.sdl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NativeSdlLibTest {
  static NativeSdlLib sdlLib;

  @BeforeAll
  public static void setUpClass() {
    // executed only once, before the first test
    sdlLib = new NativeSdlLib();
  }

  @Test
  void isMainThread() {}
}
