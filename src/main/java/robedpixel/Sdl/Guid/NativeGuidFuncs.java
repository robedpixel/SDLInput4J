package robedpixel.Sdl.Guid;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

class NativeGuidFuncs {
    private final Arena allocator;
    private final MethodHandle SDL_GUIDToString;
    private final MethodHandle SDL_StringToGUID;
    public NativeGuidFuncs(Arena allocator) {
        this.allocator = allocator;
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GUIDToString = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GUIDToString").orElseThrow(),

                FunctionDescriptor.ofVoid(SdlGuid.getStructLayout(),ValueLayout.ADDRESS,ValueLayout.JAVA_INT)
        );
        SDL_StringToGUID = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GUIDToString").orElseThrow(),

                FunctionDescriptor.of(SdlGuid.getStructLayout(),ValueLayout.ADDRESS)
        );
    }
    //TODO: test, may not be correct
    public String guidToString(SdlGuid guid, int chGuid) throws Throwable {
        char[] charArray = new char[chGuid];
        MemorySegment arrayAddress = allocator.allocateFrom(ValueLayout.JAVA_CHAR, charArray);
        MemorySegment structAddress = allocator.allocate(SdlGuid.getStructLayout());
        VarHandle dataArray = SdlGuid.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
        for (int i=0;i<guid.getData().length;i++){
            dataArray.set(structAddress,i,guid.getData()[i]);
        }
        SDL_GUIDToString.invoke(guid,arrayAddress,chGuid);
        //Load in arrayAddress to chararray
        for (int i = 0;i<chGuid;i++){
            charArray[i] = arrayAddress.get(ValueLayout.JAVA_CHAR, i);
        }
        return String.valueOf(charArray);
    }
    //TODO: test, may not be correct
    public SdlGuid stringToGuid(String pchGuid) throws Throwable {
        SdlGuid returnObject = new SdlGuid();
        MemorySegment structAddress = allocator.allocate(SdlGuid.getStructLayout());
        MemorySegment stringAddress = allocator.allocateFrom(pchGuid);
        MemorySegment guidAddress = (MemorySegment)SDL_StringToGUID.invoke(stringAddress);
        VarHandle dataArray = SdlGuid.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
        for(int i =0; i<16;i++){
            returnObject.getData()[i] = (short)dataArray.get(guidAddress,i);
        }
        return returnObject;
    }
}
