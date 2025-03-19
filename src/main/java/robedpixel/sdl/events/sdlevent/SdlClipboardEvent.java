package robedpixel.sdl.events.sdlevent;

import lombok.Getter;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;

public class SdlClipboardEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_BOOLEAN.withName("owner"),
                            ValueLayout.JAVA_INT.withName("num_mime_types"),
                            ValueLayout.ADDRESS.withName("mime_types")
                    ).withName("SDL_ClipboardEvent");
    /**
     * Event type, shared with all events
     */
    @Getter
    int type;
    @Getter long reserved;
    /**
     * (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS()
     */
    @Getter long timestamp;
    /**
     * Are we owning the clipboard (internal update)
     */
    @Getter
    boolean owner;
    /**
     * Current mime types, null if there are no mime types available
     */
    @Getter
    String[] mimeTypes;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle ownerHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("owner"));
    private static final VarHandle numMimeTypesHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("num_mime_types"));
    private static final VarHandle mimeTypesHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("mime_types"));
    public static SdlClipboardEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlClipboardEvent  retEvent = new SdlClipboardEvent ();
        retEvent.type = (int) typeHandle.get(segment,0);
        retEvent.reserved = (int) reservedHandle.get(segment,0);
        retEvent.timestamp = (long) timestampHandle.get(segment,0);
        retEvent.owner = (boolean) ownerHandle.get(segment,0);
        int numMimeTypes = (int) numMimeTypesHandle.get(segment,0);
        if (numMimeTypes>0){
            retEvent.mimeTypes = new String[numMimeTypes];
            MemorySegment stringSegmentArray = ((MemorySegment) mimeTypesHandle.get(segment,0)).reinterpret(ValueLayout.ADDRESS.byteSize()*numMimeTypes);
            for (int i = 0; i<numMimeTypes;i++){
                MemorySegment stringSegment = stringSegmentArray.getAtIndex(ValueLayout.ADDRESS,i);
                retEvent.mimeTypes[i] = stringSegment.reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.US_ASCII);
            }
        }
        return retEvent;
    }
}
