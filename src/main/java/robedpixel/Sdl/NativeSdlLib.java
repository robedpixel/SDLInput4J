package robedpixel.Sdl;


import lombok.extern.slf4j.Slf4j;
import robedpixel.Sdl.Hints.Hints;

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
    public Hints getHints(){
        return new Hints(SdlFuncs.getAllocator());
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
