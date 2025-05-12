package robedpixel.sdl.guid;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;
import org.jspecify.annotations.NonNull;

class NativeSdlGuidFuncs {
  private static volatile NativeSdlGuidFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GUIDToString;
  private final MethodHandle SDL_StringToGUID;

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

  @NonNull
  public synchronized String guidToString(
      Arena localAllocator, NativeSdlGuidModel guid, SdlGuidByteArray byteArray) throws Throwable {
    SDL_GUIDToString.invoke(
        guid.getDataAddress(), byteArray.getDataAddress(), byteArray.getArrayLength());
    // Load in arrayAddress to chararray
    for (int i = 0; i < byteArray.getArrayLength(); i++) {
      byteArray.setData(i, byteArray.getDataAddress().getAtIndex(ValueLayout.JAVA_BYTE, i));
    }
    return byteArray.getStringFromByteArray(StandardCharsets.US_ASCII);
  }

  // TODO: test, may not be correct
  @NonNull
  public synchronized NativeSdlGuidModel stringToGuid(Arena localAllocator, String pchGuid)
      throws Throwable {
    MemorySegment stringAddress = localAllocator.allocateFrom(pchGuid);
    MemorySegment guidAddress = (MemorySegment) SDL_StringToGUID.invoke(stringAddress);
    return NativeSdlGuidModel.fromSegment(guidAddress);
  }

  @NonNull
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
