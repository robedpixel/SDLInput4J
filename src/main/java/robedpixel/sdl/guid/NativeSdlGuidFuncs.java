package robedpixel.sdl.guid;


import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

class NativeSdlGuidFuncs {
    private static volatile NativeSdlGuidFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_GUIDToString;
    private final MethodHandle SDL_StringToGUID;
    private final Arena objectAllocator = Arena.ofAuto();
    private MemorySegment structAddress = objectAllocator.allocate(NativeSdlGuidModel.getStructLayout());
    public NativeSdlGuidFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_GUIDToString = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GUIDToString").orElseThrow(),

                FunctionDescriptor.ofVoid(NativeSdlGuidModel.getStructLayout(),ValueLayout.ADDRESS,ValueLayout.JAVA_INT)
        );
        SDL_StringToGUID = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GUIDToString").orElseThrow(),

                FunctionDescriptor.of(NativeSdlGuidModel.getStructLayout(),ValueLayout.ADDRESS)
        );
    }
    //TODO: test, may not be correct
    public synchronized String guidToString(Arena localAllocator, NativeSdlGuidModel guid, int chGuid) throws Throwable {
        char[] charArray = new char[chGuid];
        MemorySegment arrayAddress = localAllocator.allocateFrom(ValueLayout.JAVA_CHAR, charArray);
        VarHandle dataArray = NativeSdlGuidModel.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
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
    public synchronized NativeSdlGuidModel stringToGuid(Arena localAllocator, String pchGuid) throws Throwable {
        NativeSdlGuidModel returnObject = new NativeSdlGuidModel();
        MemorySegment stringAddress = localAllocator.allocateFrom(pchGuid);
        MemorySegment guidAddress = (MemorySegment)SDL_StringToGUID.invoke(stringAddress);
        VarHandle dataArray = NativeSdlGuidModel.getStructLayout().arrayElementVarHandle(MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
        for(int i =0; i<16;i++){
            returnObject.getData()[i] = (short)dataArray.get(guidAddress,i);
        }
        return returnObject;
    }
    public static NativeSdlGuidFuncs getInstance(Arena allocator) {
        NativeSdlGuidFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeSdlGuidFuncs(allocator);
            }
        }
        return result;
    }
}
