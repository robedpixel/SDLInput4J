package robedpixel.Sdl;

import lombok.Getter;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

class NativeSdlLibFuncs {
    private static volatile NativeSdlLibFuncs INSTANCE;
    private static final Object mutex = new Object();
    @Getter
    private final Arena allocator;
    private final MethodHandle SDL_Init;
    private final MethodHandle SDL_Quit;
    private final MethodHandle SDL_QuitSubSystem;
    private final MethodHandle SDL_IsMainThread;
    private final MethodHandle SDL_RunOnMainThread;
    private final MethodHandle SDL_SetAppMetadata;
    private final MethodHandle SDL_SetAppMetadataProperty;
    private final MethodHandle SDL_GetAppMetadataProperty;
    public NativeSdlLibFuncs(){
        allocator = Arena.ofShared();
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
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
    public void quitSubSystem(SdlInitFlags flags) throws Throwable{
        SDL_QuitSubSystem.invoke(flags.getValue());
    }
    public Boolean isMainThread() throws Throwable {
        return (Boolean)SDL_IsMainThread.invoke();
    }
    public Boolean runOnMainThread(SdlMainThreadCallback callbackUpcallStub, MemorySegment userData, boolean waitComplete) throws Throwable {
        MethodHandle callbackHandle;
        FunctionDescriptor callbackHandleDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS);
        try {
            callbackHandle = MethodHandles.publicLookup().bind(callbackUpcallStub,"callback",callbackHandleDescriptor.toMethodType());
        } catch (Exception e) {
            throw new AssertionError(
                    "Problem creating method handle compareHandle", e);
        }
        MemorySegment callbackFunc = Linker.nativeLinker().upcallStub(
                callbackHandle,
                callbackHandleDescriptor,
                allocator);
        return (Boolean)SDL_RunOnMainThread.invoke(callbackFunc,userData,waitComplete);
    }
    public Boolean setAppMetadata(String appName,String appVersion, String appIdentifier) throws Throwable {
        return (Boolean)SDL_SetAppMetadata.invoke(allocator.allocateFrom(appName),allocator.allocateFrom(appVersion),allocator.allocateFrom(appIdentifier));
    }
    public Boolean setAppMetadataProperty(String name, String value) throws Throwable {
        return (Boolean)SDL_SetAppMetadataProperty.invoke(allocator.allocateFrom(name),allocator.allocateFrom(value));
    }

    public String getAppMetadataProperty(String name) throws Throwable {
        MemorySegment charArrayAddress = (MemorySegment)SDL_GetAppMetadataProperty.invoke(allocator.allocateFrom(name));
        return charArrayAddress.getString(0);
    }
}
