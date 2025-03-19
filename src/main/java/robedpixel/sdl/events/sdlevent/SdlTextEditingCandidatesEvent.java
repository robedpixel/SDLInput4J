package robedpixel.sdl.events.sdlevent;

import lombok.Getter;
import robedpixel.sdl.video.SdlWindowId;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.StructLayout;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;

public class SdlTextEditingCandidatesEvent {
    public static final StructLayout objectLayout =
            MemoryLayout.structLayout(
                            ValueLayout.JAVA_INT.withName("type"),
                            ValueLayout.JAVA_INT.withName("reserved"),
                            ValueLayout.JAVA_LONG.withName("timestamp"),
                            ValueLayout.JAVA_INT.withName("windowID"),
                            ValueLayout.ADDRESS.withName("candidates"),
                            ValueLayout.JAVA_INT.withName("num_candidates"),
                            ValueLayout.JAVA_INT.withName("selected_candidate"),
                            ValueLayout.JAVA_BOOLEAN.withName("horizontal"),
                            ValueLayout.JAVA_BYTE.withName("padding1"),
                            ValueLayout.JAVA_BYTE.withName("padding2"),
                            ValueLayout.JAVA_BYTE.withName("padding3")
                    )
                    .withName("SDL_TextEditingCandidatesEvent");
    /**
     * SDL_EVENT_TEXT_EDITING_CANDIDATES
     */
    @Getter
    int type;
    @Getter int reserved;
    /**
     * (Unsigned Int64) In nanoseconds, populated using SDL_GetTicksNS()
     */
    @Getter long timestamp;
    /**
     * The window with keyboard focus, if any
     */
    @Getter
    SdlWindowId windowId;
    //  array to strings
    /**
     * The list of candidates, or null if there are no candidates available
     */
    @Getter
    String[] candidates;

    /**
     * The index of the selected candidate, or -1 if no candidate is selected
     */
    @Getter
    int selected_candidates;
    /**
     * true if the list is horizontal, false if it's vertical
     */
    @Getter
    boolean horizontal;
    private static final VarHandle typeHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
    private static final VarHandle reservedHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("reserved"));
    private static final VarHandle timestampHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("timestamp"));
    private static final VarHandle windowIdHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("windowID"));
    private static final VarHandle candidatesHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("candidates"));
    private static final VarHandle numCandidatesHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("num_candidates"));
    private static final VarHandle selectedCandidateHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("selected_candidate"));
    private static final VarHandle horizontalHandle =
            objectLayout.varHandle(MemoryLayout.PathElement.groupElement("horizontal"));
    public static SdlTextEditingCandidatesEvent getEventFromMemorySegment(MemorySegment segment) {
        SdlTextEditingCandidatesEvent retEvent = new SdlTextEditingCandidatesEvent();
        retEvent.type = (int) typeHandle.get(segment,0);
        retEvent.reserved = (int) reservedHandle.get(segment,0);
        retEvent.timestamp = (long) timestampHandle.get(segment,0);
        retEvent.windowId = new SdlWindowId();
        retEvent.windowId.setValue((int) windowIdHandle.get(segment,0));
        int numCandidates = (int)numCandidatesHandle.get(segment,0);
        if (numCandidates >0){
            retEvent.candidates  = new String[numCandidates];
            MemorySegment stringSegmentArray = ((MemorySegment) candidatesHandle.get(segment,0)).reinterpret(ValueLayout.ADDRESS.byteSize()*numCandidates);
            for (int i = 0; i<numCandidates;i++){
                MemorySegment stringSegment = stringSegmentArray.getAtIndex(ValueLayout.ADDRESS,i);
                retEvent.candidates[i] = stringSegment.reinterpret(Integer.MAX_VALUE).getString(0, StandardCharsets.US_ASCII);
            }
        }
        retEvent.selected_candidates = (int) selectedCandidateHandle.get(segment,0);
        retEvent.horizontal = (boolean) horizontalHandle.get(segment,9);
        return retEvent;
    }
}
