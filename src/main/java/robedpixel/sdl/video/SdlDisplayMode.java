package robedpixel.sdl.video;

import lombok.Getter;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

//TODO:
public class SdlDisplayMode {
    public static final MemoryLayout objectLayout =
            MemoryLayout.structLayout(
                    ValueLayout.JAVA_INT.withName("displayID"),
                    ValueLayout.JAVA_INT.withName("format"),
                    ValueLayout.JAVA_INT.withName("w"),
                    ValueLayout.JAVA_INT.withName("h"),
                    ValueLayout.JAVA_FLOAT.withName("pixel_density"),
                    ValueLayout.JAVA_FLOAT.withName("refresh_rate"),
                    ValueLayout.JAVA_INT.withName("refresh_rate_numerator"),
                    ValueLayout.JAVA_INT.withName("refresh_rate_denominator"),
                    ValueLayout.ADDRESS.withName("internal")
                    )
                    .withName("SDL_DisplayMode");
    @Getter
    private int displayId;
    @Getter
    private int format;
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private float pixelDensity;
    @Getter
    private float refreshRate;
    @Getter
    private int refreshRateNumerator;
    @Getter
    private int refreshRateDenominator;
    @Getter
    private MemorySegment internal;
    @Getter
    private MemorySegment dataAddress;
    private Arena allocator = Arena.ofAuto();
    private static final VarHandle displayIdHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("displayID"));
    private static final VarHandle formatHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("format"));
    private static final VarHandle wHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("w"));
    private static final VarHandle hHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("h"));
    private static final VarHandle pixelDensityHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("pixel_density"));
    private static final VarHandle refreshRateHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("refresh_rate"));
    private static final VarHandle refRateNumHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("refresh_rate_numerator"));
    private static final VarHandle refRateDenomHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("refresh_rate_denominator"));
    private static final VarHandle internalHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("internal"));
    public SdlDisplayMode(){
        dataAddress = allocator.allocate(objectLayout);
        displayIdHandle.set(dataAddress,0,displayId);
        formatHandle.set(dataAddress,0,format);
        wHandle.set(dataAddress,0,width);
        hHandle.set(dataAddress,0,height);
        pixelDensityHandle.set(dataAddress,0,pixelDensity);
        refreshRateHandle.set(dataAddress,0,refreshRate);
        refreshRateHandle.set(dataAddress,0,refreshRateNumerator);
        refRateDenomHandle.set(dataAddress,0,refreshRateDenominator);
        internalHandle.set(dataAddress,0,internal);
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
        displayIdHandle.set(dataAddress,0,this.displayId);
    }
    public void setFormat(int format){
        this.format = format;
        formatHandle.set(dataAddress,0,this.format);
    }

    public void setWidth(int width) {
        this.width = width;
        wHandle.set(dataAddress,0,this.width);
    }

    public void setHeight(int height) {
        this.height = height;
        hHandle.set(dataAddress,0,this.height);
    }

    public void setPixelDensity(float pixelDensity) {
        this.pixelDensity = pixelDensity;
        pixelDensityHandle.set(dataAddress,0,this.pixelDensity);
    }

    public void setRefreshRate(float refreshRate) {
        this.refreshRate = refreshRate;
        refreshRateHandle.set(dataAddress,0,this.refreshRate);
    }

    public void setRefreshRateNumerator(int refreshRateNumerator) {
        this.refreshRateNumerator = refreshRateNumerator;
        refRateNumHandle.set(dataAddress,0,this.refreshRateNumerator);
    }

    public void setRefreshRateDenominator(int refreshRateDenominator) {
        this.refreshRateDenominator = refreshRateDenominator;
        refRateDenomHandle.set(dataAddress,0,this.refreshRateDenominator);
    }
    public void setInternal(MemorySegment internal) {
        this.internal = internal;
        internalHandle.set(dataAddress,0,this.internal);
    }

    public static SdlDisplayMode fromMemorySegment(MemorySegment segment){
        SdlDisplayMode displayMode = new SdlDisplayMode();
        displayMode.dataAddress = displayMode.allocator.allocate(objectLayout);

        displayMode.displayId = (int)displayIdHandle.get(segment,0);
        displayMode.format = (int) formatHandle.get(segment,0);
        displayMode.width = (int) wHandle.get(segment,0);
        displayMode.height = (int) hHandle.get(segment,0);
        displayMode.pixelDensity = (float) pixelDensityHandle.get(segment,0);
        displayMode.refreshRate = (float) refreshRateHandle.get(segment,0);
        displayMode.refreshRateNumerator = (int)refRateNumHandle.get(segment,0);
        displayMode.refreshRateDenominator = (int) refRateDenomHandle.get(segment,0);
        displayMode.internal = (MemorySegment) internalHandle.get(segment,0);

        displayIdHandle.set(displayMode.dataAddress,0,displayMode.displayId);
        formatHandle.set(displayMode.dataAddress,0,displayMode.format);
        wHandle.set(displayMode.dataAddress,0,displayMode.width);
        hHandle.set(displayMode.dataAddress,0,displayMode.height);
        pixelDensityHandle.set(displayMode.dataAddress,0,displayMode.pixelDensity);
        refreshRateHandle.set(displayMode.dataAddress,0,displayMode.refreshRate);
        refreshRateHandle.set(displayMode.dataAddress,0,displayMode.refreshRateNumerator);
        refRateDenomHandle.set(displayMode.dataAddress,0,displayMode.refreshRateDenominator);
        internalHandle.set(displayMode.dataAddress,0,displayMode.internal);
        return displayMode;
    }
    void updateValues(){
        displayId = (int)displayIdHandle.get(dataAddress,0);
        format = (int) formatHandle.get(dataAddress,0);
        width = (int) wHandle.get(dataAddress,0);
        height = (int) hHandle.get(dataAddress,0);
        pixelDensity = (float) pixelDensityHandle.get(dataAddress,0);
        refreshRate = (float) refreshRateHandle.get(dataAddress,0);
        refreshRateNumerator = (int)refRateNumHandle.get(dataAddress,0);
        refreshRateDenominator = (int) refRateDenomHandle.get(dataAddress,0);
        internal = (MemorySegment) internalHandle.get(dataAddress,0);
    }

}
