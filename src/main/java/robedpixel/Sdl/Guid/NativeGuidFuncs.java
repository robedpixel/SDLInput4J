package robedpixel.Sdl.Guid;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

class NativeGuidFuncs {
    private static volatile NativeGuidFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GUIDToString;
    private final MethodHandle SDL_StringToGUID;
    private final Arena objectAllocator = Arena.ofAuto();
    private MemorySegment structAddress = objectAllocator.allocate(SdlGuid.getStructLayout());
    public NativeGuidFuncs(Arena allocator) {
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
    public synchronized String guidToString(Arena localAllocator, SdlGuid guid, int chGuid) throws Throwable {
        char[] charArray = new char[chGuid];
        MemorySegment arrayAddress = localAllocator.allocateFrom(ValueLayout.JAVA_CHAR, charArray);
        VarHandle dataArray = SdlGuid.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
        for (int i=0;i<guid.getData().length;i++){
            dataArray.set(structAddress,i,guid.getData()[i]);
        }
        SDL_GUIDToString.invoke(structAddress,arrayAddress,chGuid);
        //Load in arrayAddress to chararray
        for (int i = 0;i<chGuid;i++){
            charArray[i] = arrayAddress.get(ValueLayout.JAVA_CHAR, i);
        }
        return String.valueOf(charArray);
    }
    //TODO: test, may not be correct
    public synchronized SdlGuid stringToGuid(Arena localAllocator, String pchGuid) throws Throwable {
        SdlGuid returnObject = new SdlGuid();
        MemorySegment stringAddress = localAllocator.allocateFrom(pchGuid);
        MemorySegment guidAddress = (MemorySegment)SDL_StringToGUID.invoke(stringAddress);
        VarHandle dataArray = SdlGuid.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
        for(int i =0; i<16;i++){
            returnObject.getData()[i] = (short)dataArray.get(guidAddress,i);
        }
        return returnObject;
    }
    public static NativeGuidFuncs getInstance(Arena allocator) {
        NativeGuidFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeGuidFuncs(allocator);
            }
        }
        return result;
    }
}
