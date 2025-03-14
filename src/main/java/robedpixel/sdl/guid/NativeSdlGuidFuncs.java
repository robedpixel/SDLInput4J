package robedpixel.sdl.guid;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.charset.StandardCharsets;

class NativeSdlGuidFuncs {
  private static volatile NativeSdlGuidFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GUIDToString;
  private final MethodHandle SDL_StringToGUID;
  private final Arena objectAllocator = Arena.ofAuto();
  private MemorySegment structAddress =
      objectAllocator.allocate(NativeSdlGuidModel.getStructLayout());

  public NativeSdlGuidFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GUIDToString =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GUIDToString").orElseThrow(),
                FunctionDescriptor.ofVoid(
                    NativeSdlGuidModel.getStructLayout(),
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT));
    SDL_StringToGUID =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_StringToGUID").orElseThrow(),
                FunctionDescriptor.of(NativeSdlGuidModel.getStructLayout(), ValueLayout.ADDRESS));
  }

  // TODO: test, may not be correct
  public synchronized String guidToString(Arena localAllocator, NativeSdlGuidModel guid, int chGuid)
      throws Throwable {
    byte[] byteArray = new byte[chGuid];
    MemorySegment arrayAddress = localAllocator.allocateFrom(ValueLayout.JAVA_BYTE, byteArray);
    VarHandle dataArray =
        NativeSdlGuidModel.getStructLayout()
            .arrayElementVarHandle(
                MemoryLayout.PathElement.sequenceElement(),
                MemoryLayout.PathElement.groupElement("data"));
    for (int i = 0; i < guid.getData().length; i++) {
      dataArray.set(structAddress, i, guid.getData()[i]);
    }
    SDL_GUIDToString.invoke(structAddress, arrayAddress, chGuid);
    // Load in arrayAddress to chararray
    for (int i = 0; i < chGuid; i++) {
      byteArray[i] = arrayAddress.get(ValueLayout.JAVA_BYTE, i);
    }
    return new String(byteArray, StandardCharsets.US_ASCII);
  }

  // TODO: test, may not be correct
  public synchronized NativeSdlGuidModel stringToGuid(Arena localAllocator, String pchGuid)
      throws Throwable {
    MemorySegment stringAddress = localAllocator.allocateFrom(pchGuid);
    MemorySegment guidAddress = (MemorySegment) SDL_StringToGUID.invoke(stringAddress);
    return NativeSdlGuidModel.fromSegment(guidAddress);
  }

  public static NativeSdlGuidFuncs getInstance(Arena allocator) {
    NativeSdlGuidFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlGuidFuncs(allocator);
      }
    }
    return result;
  }
}
