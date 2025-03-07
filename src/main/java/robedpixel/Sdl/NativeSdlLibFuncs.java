package robedpixel.Sdl;

import lombok.Getter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

class NativeSdlLibFuncs {
    private static volatile NativeSdlLibFuncs INSTANCE;
    private static final Object mutex = new Object();
    @Getter
    private final Arena globalAllocator;
    private final MethodHandle SDL_Init;
    private final MethodHandle SDL_Quit;
    private final MethodHandle SDL_QuitSubSystem;
    private final MethodHandle SDL_IsMainThread;
    private final MethodHandle SDL_RunOnMainThread;
    private final MethodHandle SDL_SetAppMetadata;
    private final MethodHandle SDL_SetAppMetadataProperty;
    private final MethodHandle SDL_GetAppMetadataProperty;
    public NativeSdlLibFuncs(){
        globalAllocator = Arena.ofShared();
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", globalAllocator);
        SDL_Init = Linker.nativeLinker().downcallHandle(
                library.find("SDL_Init").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.JAVA_INT)
        );Linker.nativeLinker().downcallHandle(
                library.find("SDL_Init").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.JAVA_INT)
        );
        SDL_Quit =Linker.nativeLinker().downcallHandle(
                library.find("SDL_Quit").orElseThrow(),
                FunctionDescriptor.ofVoid()
        );
        SDL_QuitSubSystem = Linker.nativeLinker().downcallHandle(
                library.find("SDL_QuitSubSystem").orElseThrow(),

                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.JAVA_INT)
        );
        SDL_IsMainThread = Linker.nativeLinker().downcallHandle(
                library.find("SDL_IsMainThread").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN)
        );
        SDL_RunOnMainThread = Linker.nativeLinker().downcallHandle(
                library.find("SDL_RunOnMainThread").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.JAVA_BOOLEAN)
        );
        //takes in native strings
        SDL_SetAppMetadata = Linker.nativeLinker().downcallHandle(
                library.find("SDL_SetAppMetadata").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
        SDL_SetAppMetadataProperty = Linker.nativeLinker().downcallHandle(
                library.find("SDL_SetAppMetadataProperty").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
        SDL_GetAppMetadataProperty = Linker.nativeLinker().downcallHandle(
                library.find("SDL_GetAppMetadataProperty").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS)
        );
    }
    public static NativeSdlLibFuncs getInstance() {
        NativeSdlLibFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new NativeSdlLibFuncs();
            }
        }
        return result;
    }
    public Boolean initLibrary(SdlInitFlags flags) throws Throwable {
        return (Boolean)SDL_Init.invoke(flags.getValue());
    }
    public void quitLibrary() throws Throwable{
        SDL_Quit.invoke();
    }
    public synchronized void quitSubSystem(SdlInitFlags flags) throws Throwable{
        SDL_QuitSubSystem.invoke(flags.getValue());
    }
    public synchronized Boolean isMainThread() throws Throwable {
        return (Boolean)SDL_IsMainThread.invoke();
    }
    public synchronized Boolean runOnMainThread(Arena localAllocator, MemorySegment callbackUpcallStub, MemorySegment userData, boolean waitComplete) throws Throwable {
        return (Boolean)SDL_RunOnMainThread.invoke(callbackUpcallStub,userData,waitComplete);
    }
    public synchronized Boolean setAppMetadata(Arena localAllocator, String appName,String appVersion, String appIdentifier) throws Throwable {
        return (Boolean)SDL_SetAppMetadata.invoke(localAllocator.allocateFrom(appName), localAllocator.allocateFrom(appVersion), localAllocator.allocateFrom(appIdentifier));
    }
    public synchronized Boolean setAppMetadataProperty(Arena localAllocator, String name, String value) throws Throwable {
        return (Boolean)SDL_SetAppMetadataProperty.invoke(localAllocator.allocateFrom(name), localAllocator.allocateFrom(value));
    }

    public synchronized String getAppMetadataProperty(Arena localAllocator, String name) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment)SDL_GetAppMetadataProperty.invoke(localAllocator.allocateFrom(name));
        return charArrayAddress.getString(0);
    }
}
