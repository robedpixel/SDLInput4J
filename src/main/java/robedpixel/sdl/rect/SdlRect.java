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

  /**
   * Determine whether a point resides inside a rectangle.
   * @param p The point to test.
   * @param r The rectangle to test.
   * @return Returns true if p is contained by r, false otherwise.
   */
  public boolean pointInRect(SdlPointModel p, SdlRectModel r){
    return r.getData().contains(p.getData());
  }

  /**
   * Determine whether a rectangle has no area.
   * @param r The rectangle to test.
   * @return Returns true if the rectangle is "empty", false otherwise.
   */
  public boolean rectEmpty(SdlRectModel r){
    return r.getData().isEmpty();
  }

  /**
   * Determine whether two rectangles are equal.
   * @param a The first rectangle to test.
   * @param b The second rectangle to test.
   * @return Returns true if the rectangles are equal, false otherwise.
   */
  public boolean rectsEqual(SdlRectModel a, SdlRectModel b){
    return a.getData().equals(b.getData());
  }

  /**
   * Determine whether two rectangles intersect.
   * @param A An SdlRectModel representing the first rectangle.
   * @param B An SdlRectModel representing the second rectangle.
   * @return Returns true if there is an intersection, false otherwise.
   * @throws Throwable
   */
  public boolean hasRectIntersection(SdlRectModel A, SdlRectModel B) throws Throwable {
    return SdlFuncs.hasRectIntersection(A.getDataAddress(),B.getDataAddress());
  }
}
