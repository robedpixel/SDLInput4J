package robedpixel.sdl.mouse;


import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class SdlMouse {
    private final NativeSdlMouseFuncs SdlFuncs;

    public SdlMouse(Arena allocator) {
        SdlFuncs = NativeSdlMouseFuncs.getInstance(allocator);
    }
    public boolean hasMouse() throws Throwable {
        return SdlFuncs.hasMouse();
    }
    public int[] getMice() throws Throwable{
        return  SdlFuncs.getMice();
    }
    public String getMouseNameForID(int instanceId) throws Throwable {
        return SdlFuncs.getMouseNameForID(instanceId);
    }
    public MemorySegment getMouseFocus() throws Throwable {

    }
    public void getMouseState(SdlMouseState state) throws Throwable {

    }
    public void  getGlobalMouseState(SdlMouseState state) throws Throwable {

    }
    public void getRelativeMouseState(SdlMouseState state) throws Throwable {

    }
    public void warpMouseInWindow(MemorySegment window,float x, float y) throws Throwable {

    }
    public boolean  warpMouseGlobal(float x, float y) throws Throwable {

    }
    public boolean setRelativeMouseTransform(MemorySegment callback, MemorySegment userdata) throws Throwable {

    }
    public boolean  setWindowRelativeMouseMode(MemorySegment window, boolean enabled) throws Throwable {

    }
    public boolean  getWindowRelativeMouseMode(MemorySegment window) throws Throwable {

    }
    public boolean captureMouse(boolean enabled) throws Throwable {
        return SdlFuncs.captureMouse(enabled);
    }
    public SdlCursorInstance createCursor(SdlCursorBitmap bitmap) throws Throwable {
        MemorySegment cursorAddress = SdlFuncs.createCursor(bitmap.getData(),bitmap.getMask(),bitmap.getWidth(),bitmap.getHeight(),bitmap.getHotX(),bitmap.getHotY());
        return new SdlCursorInstance(cursorAddress,this.SdlFuncs);
    }
    /*public MemorySegment  createColorCursor(MemorySegment surface,int hot_x, int hot_y){

    }*/
    public SdlCursorInstance createSystemCursor(int instanceId) throws Throwable {
        MemorySegment cursorAddress = SdlFuncs.createSystemCursor(instanceId);
        return new SdlCursorInstance(cursorAddress,this.SdlFuncs);
    }
    public boolean setCursor(SdlCursor cursor) throws Throwable {
        return SdlFuncs.setCursor(cursor.getAddress());
    }
    public SdlCursorReference getCursor() throws Throwable {
        MemorySegment cursorAddress = SdlFuncs.getCursor();
        return new SdlCursorReference(cursorAddress);
    }
    public SdlCursorReference getDefaultCursor() throws Throwable {
        MemorySegment cursorAddress = SdlFuncs.getDefaultCursor();
        return new SdlCursorReference(cursorAddress);
    }
    public boolean  showCursor() throws Throwable {
        return SdlFuncs.showCursor();
    }
    public boolean  hideCursor() throws Throwable {
        return SdlFuncs.hideCursor();
    }
    public boolean cursorVisible() throws Throwable {
        return SdlFuncs.cursorVisible();
    }
}
