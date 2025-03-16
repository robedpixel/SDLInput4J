package robedpixel.sdl.rect;

import java.lang.foreign.Arena;

public class SdlRect {
  private final NativeSdlRectFuncs SdlFuncs;

  public SdlRect(Arena allocator) {
    SdlFuncs = NativeSdlRectFuncs.getInstance(allocator);
  }

  /**
   * Convert an SdlRect to SdlFRect
   *
   * @param rect Am Sd;RectModel object
   * @param fRect An SdlFRectModel object to convert to
   * @throws Throwable
   */
  public void rectToFRect(SdlRectModel rect, SdlFRectModel fRect) throws Throwable {
    fRect.setX(rect.getX());
    fRect.setY(rect.getY());
    fRect.setW(rect.getW());
    fRect.setH(rect.getH());

  }
}
