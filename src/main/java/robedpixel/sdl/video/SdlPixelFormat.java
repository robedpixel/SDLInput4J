package robedpixel.sdl.video;

import java.nio.ByteOrder;

public class SdlPixelFormat {
    public static final int SDL_PIXELFORMAT_INDEX1LSB = 0x11100100;
    public static final int SDL_PIXELFORMAT_INDEX1MSB=0x11200100;
    public static final int SDL_PIXELFORMAT_INDEX2LSB=0x1c100200;
    public static final int SDL_PIXELFORMAT_INDEX2MSB=0x1c200200;
    public static final int SDL_PIXELFORMAT_INDEX4LSB=0x12100400;
    public static final int SDL_PIXELFORMAT_INDEX4MSB=0x12200400;
    public static final int SDL_PIXELFORMAT_INDEX8=0x13000801;
    public static final int SDL_PIXELFORMAT_RGB332=0x14110801;
    public static final int SDL_PIXELFORMAT_XRGB4444=0x15120c02;
    public static final int SDL_PIXELFORMAT_XBGR4444=0x15520c02;
    public static final int SDL_PIXELFORMAT_XRGB1555=0x15130f02;
    public static final int SDL_PIXELFORMAT_XBGR1555=0x15530f02;
    public static final int SDL_PIXELFORMAT_ARGB4444=0x15321002;
    public static final int SDL_PIXELFORMAT_RGBA4444=0x15421002;
    public static final int SDL_PIXELFORMAT_ABGR4444=0x15721002;
    public static final int SDL_PIXELFORMAT_BGRA4444=0x15821002;
    public static final int SDL_PIXELFORMAT_ARGB1555=0x15331002;
    public static final int SDL_PIXELFORMAT_RGBA5551=0x15441002;
    public static final int SDL_PIXELFORMAT_ABGR1555=0x15731002;
    public static final int SDL_PIXELFORMAT_BGRA5551=0x15841002;
    public static final int SDL_PIXELFORMAT_RGB565=0x15151002;
    public static final int SDL_PIXELFORMAT_BGR565=0x15551002;
    public static final int SDL_PIXELFORMAT_RGB24=0x17101803;
    public static final int SDL_PIXELFORMAT_BGR24=0x17401803;
    public static final int SDL_PIXELFORMAT_XRGB8888=0x16161804;
    public static final int SDL_PIXELFORMAT_RGBX8888=0x16261804;
    public static final int SDL_PIXELFORMAT_XBGR8888=0x16561804;
    public static final int SDL_PIXELFORMAT_BGRX8888=0x16661804;
    public static final int SDL_PIXELFORMAT_ARGB8888=0x16362004;
    public static final int SDL_PIXELFORMAT_RGBA8888=0x16462004;
    public static final int SDL_PIXELFORMAT_ABGR8888=0x16762004;
    public static final int SDL_PIXELFORMAT_BGRA8888=0x16862004;
    public static final int SDL_PIXELFORMAT_XRGB2101010=0x16172004;
    public static final int SDL_PIXELFORMAT_XBGR2101010=0x16572004;
    public static final int SDL_PIXELFORMAT_ARGB2101010=0x16372004;
    public static final int SDL_PIXELFORMAT_ABGR2101010=0x16772004;
    public static final int SDL_PIXELFORMAT_RGB48=0x18103006;
    public static final int SDL_PIXELFORMAT_BGR48=0x18403006;
    public static final int SDL_PIXELFORMAT_RGBA64=0x18204008;
    public static final int SDL_PIXELFORMAT_ARGB64=0x18304008;
    public static final int SDL_PIXELFORMAT_BGRA64=0x18504008;
    public static final int SDL_PIXELFORMAT_ABGR64=0x18604008;
    public static final int SDL_PIXELFORMAT_RGB48_FLOAT=0x1a103006;
    public static final int SDL_PIXELFORMAT_BGR48_FLOAT=0x1a403006;
    public static final int SDL_PIXELFORMAT_RGBA64_FLOAT=0x1a204008;
    public static final int SDL_PIXELFORMAT_ARGB64_FLOAT=0x1a304008;
    public static final int SDL_PIXELFORMAT_BGRA64_FLOAT=0x1a504008;
    public static final int SDL_PIXELFORMAT_ABGR64_FLOAT=0x1a604008;
    public static final int SDL_PIXELFORMAT_RGB96_FLOAT=0x1b10600c;
    public static final int SDL_PIXELFORMAT_BGR96_FLOAT=0x1b40600c;
    public static final int SDL_PIXELFORMAT_RGBA128_FLOAT=0x1b208010;
    public static final int SDL_PIXELFORMAT_ARGB128_FLOAT=0x1b308010;
    public static final int SDL_PIXELFORMAT_BGRA128_FLOAT=0x1b508010;
    public static final int SDL_PIXELFORMAT_ABGR128_FLOAT=0x1b608010;

    public static final int SDL_PIXELFORMAT_YV12=0x32315659;      /**< Planar mode: Y + V + U  (3 planes) */
    public static final int SDL_PIXELFORMAT_IYUV=0x56555949;      /**< Planar mode: Y + U + V  (3 planes) */
    public static final int SDL_PIXELFORMAT_YUY2=0x32595559;      /**< Packed mode: Y0+U0+Y1+V0 (1 plane) */
    public static final int SDL_PIXELFORMAT_UYVY=0x59565955;      /**< Packed mode: U0+Y0+V0+Y1 (1 plane) */
    public static final int SDL_PIXELFORMAT_YVYU=0x55595659;      /**< Packed mode: Y0+V0+Y1+U0 (1 plane) */
    public static final int SDL_PIXELFORMAT_NV12=0x3231564e;      /**< Planar mode: Y + U/V interleaved  (2 planes) */
    public static final int SDL_PIXELFORMAT_NV21=0x3132564e;      /**< Planar mode: Y + V/U interleaved  (2 planes) */
    public static final int SDL_PIXELFORMAT_P010=0x30313050;      /**< Planar mode: Y + U/V interleaved  (2 planes) */
    public static final int SDL_PIXELFORMAT_EXTERNAL_OES= 0x2053454f;     /**< Android video texture format */

    public static final int SDL_PIXELFORMAT_MJPG= 0x47504a4d;
    public static final int SDL_PIXELFORMAT_RGBA32;
    public static final int SDL_PIXELFORMAT_ARGB32;
    public static final int SDL_PIXELFORMAT_BGRA32;
    public static final int SDL_PIXELFORMAT_ABGR32;
    public static final int SDL_PIXELFORMAT_RGBX32;
    public static final int SDL_PIXELFORMAT_XRGB32;
    public static final int SDL_PIXELFORMAT_BGRX32;
    public static final int SDL_PIXELFORMAT_XBGR32;
    static{
        if (java.nio.ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            SDL_PIXELFORMAT_RGBA32 = SDL_PIXELFORMAT_RGBA8888;
            SDL_PIXELFORMAT_ARGB32 = SDL_PIXELFORMAT_ARGB8888;
            SDL_PIXELFORMAT_BGRA32 = SDL_PIXELFORMAT_BGRA8888;
            SDL_PIXELFORMAT_ABGR32 = SDL_PIXELFORMAT_ABGR8888;
            SDL_PIXELFORMAT_RGBX32 = SDL_PIXELFORMAT_RGBX8888;
            SDL_PIXELFORMAT_XRGB32 = SDL_PIXELFORMAT_XRGB8888;
            SDL_PIXELFORMAT_BGRX32 = SDL_PIXELFORMAT_BGRX8888;
            SDL_PIXELFORMAT_XBGR32 = SDL_PIXELFORMAT_XBGR8888;
        }else{
            SDL_PIXELFORMAT_RGBA32 = SDL_PIXELFORMAT_ABGR8888;
            SDL_PIXELFORMAT_ARGB32 = SDL_PIXELFORMAT_BGRA8888;
            SDL_PIXELFORMAT_BGRA32 = SDL_PIXELFORMAT_ARGB8888;
            SDL_PIXELFORMAT_ABGR32 = SDL_PIXELFORMAT_RGBA8888;
            SDL_PIXELFORMAT_RGBX32 = SDL_PIXELFORMAT_XBGR8888;
            SDL_PIXELFORMAT_XRGB32 = SDL_PIXELFORMAT_BGRX8888;
            SDL_PIXELFORMAT_BGRX32 = SDL_PIXELFORMAT_XRGB8888;
            SDL_PIXELFORMAT_XBGR32 = SDL_PIXELFORMAT_RGBX8888;
        }
    }
}
