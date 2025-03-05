package robedpixel.Sdl;


import lombok.extern.slf4j.Slf4j;
import robedpixel.Sdl.Hints.Hints;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

//linux library is libSDL3.so
//windows library is SDL3.dll
//singleton library, in charge of SDL init and quit, and methods to provide instances to functions
//TODO: add sdl methods
@Slf4j
public class NativeSdlLib implements AutoCloseable{
    private static volatile NativeSdlLib INSTANCE;
    private NativeSdlLibFuncs SdlFuncs;
    public NativeSdlLib(SdlInitFlagsFactory.SDLFlagValue... flags){
            try {
                SdlFuncs = NativeSdlLibFuncs.getInstance();
                log.debug("Initialising SDL3 Library");
                if (!SdlFuncs.initLibrary(SdlInitFlagsFactory.orSdlInitFlag(flags))){
                    throw new IllegalStateException("Unable to init SDL3 library!");
                }
                log.debug("SDL3 library initialised!");
            }catch (IllegalArgumentException e){
                log.error("Unable to find SDL3 Library!");
                log.error("{}",e.getMessage());
                throw e;
            }catch(UnsatisfiedLinkError e){
                log.error("Unable to link to SDL library, is it loaded in the os path?");
                log.error("Error contents: {}",e.getMessage());
                throw e;
            }catch (Throwable e) {
                throw new RuntimeException(e);
            }
    }
    public Boolean isMainThread() throws Throwable{
        return SdlFuncs.isMainThread();
    }
    public void quitSubSystem(SdlInitFlags flags) throws Throwable {
        SdlFuncs.quitSubSystem(flags);
    }
    public Boolean runOnMainThread(SdlMainThreadCallback callbackUpcallStub, boolean waitComplete) throws Throwable {
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
                callbackUpcallStub.getCallbackAllocator());
        return SdlFuncs.runOnMainThread(callbackUpcallStub.getCallbackAllocator(),callbackFunc, callbackUpcallStub.getUserData(), waitComplete);
    }
    public Boolean setAppMetadata(String appName,String appVersion, String appIdentifier, Arena localAllocator)throws Throwable{
        try(Arena arena = Arena.ofConfined()){
            return SdlFuncs.setAppMetadata(arena,appName,appVersion,appIdentifier);
        }
    }
    public Boolean setAppMetadataProperty(String name, String value) throws Throwable {
        try(Arena arena = Arena.ofConfined()){
            return SdlFuncs.setAppMetadataProperty(arena, name, value);
        }
    }

    public String getAppMetadataProperty(String name, Arena localAllocator) throws Throwable {
        try(Arena arena = Arena.ofConfined()){
            return SdlFuncs.getAppMetadataProperty(arena, name);
        }
    }
    public Hints getHints(){
        return new Hints(SdlFuncs.getGlobalAllocator());
    }

    @Override
    public void close() throws Exception {
        try {
            SdlFuncs.quitLibrary();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
