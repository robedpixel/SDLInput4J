package robedpixel.sdl.gamepad;

import robedpixel.sdl.guid.NativeSdlGuidModel;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class NativeSdlGamepadFuncs {
    private static volatile NativeSdlGamepadFuncs INSTANCE;
    private static final Object mutex = new Object();
    private final MethodHandle SDL_AddGamepadMapping;
    private final MethodHandle SDL_AddGamepadMappingsFromFile;
    private final MethodHandle SDL_ReloadGamepadMappings;
    private final MethodHandle SDL_GetGamepadMappings;
    private final MethodHandle SDL_GetGamepadMappingForGUID;
    private final MethodHandle SDL_GetGamepadMapping;
    private final MethodHandle SDL_SetGamepadMapping;
    private final MethodHandle SDL_HasGamepad;

    private final MethodHandle SDL_CloseGamepad;
    private final MethodHandle  SDL_GetGamepadAppleSFSymbolsNameForButton;
    private final MethodHandle  SDL_GetGamepadAppleSFSymbolsNameForAxis;
    public NativeSdlGamepadFuncs(Arena allocator) {
        SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
        SDL_AddGamepadMapping =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_AddGamepadMapping").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_INT,
                                        ValueLayout.ADDRESS));
        SDL_AddGamepadMappingsFromFile =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_AddGamepadMappingsFromFile").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_INT,
                                        ValueLayout.ADDRESS));
        SDL_ReloadGamepadMappings =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_ReloadGamepadMappings").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.ADDRESS,
                                        ValueLayout.ADDRESS));
        SDL_GetGamepadMappings=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGamepadMappings").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS));
        SDL_GetGamepadMappingForGUID=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGamepadMappingForGUID").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,NativeSdlGuidModel.getStructLayout()));
        SDL_GetGamepadMapping=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGamepadMapping").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.ADDRESS,ValueLayout.ADDRESS));
        SDL_SetGamepadMapping=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_SetGamepadMapping").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN,ValueLayout.JAVA_INT,ValueLayout.ADDRESS));
        SDL_HasGamepad =
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_HasGamepad").orElseThrow(),
                                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN));
        SDL_CloseGamepad=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_CloseGamepad").orElseThrow(),
                                FunctionDescriptor.ofVoid(
                                        ValueLayout.ADDRESS));
        SDL_GetGamepadAppleSFSymbolsNameForButton=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGamepadAppleSFSymbolsNameForButton").orElseThrow(),
                                FunctionDescriptor.of(
                                        ValueLayout.ADDRESS,
                                        ValueLayout.ADDRESS,
                                        ValueLayout.JAVA_INT));
        SDL_GetGamepadAppleSFSymbolsNameForAxis=
                Linker.nativeLinker()
                        .downcallHandle(
                                library.find("SDL_GetGamepadAppleSFSymbolsNameForAxis").orElseThrow(),
                                FunctionDescriptor.of(
                                        ValueLayout.ADDRESS,
                                        ValueLayout.ADDRESS,
                                        ValueLayout.JAVA_INT));
    }
    public synchronized int addGamepadMapping(Arena localAllocator, String mapping) throws Throwable {
        return (int) SDL_AddGamepadMapping.invoke(localAllocator.allocateFrom(mapping));
    }
    public synchronized int addGamepadMappingsFromFile(Arena localAllocator, String file) throws Throwable{
        return (int) SDL_AddGamepadMappingsFromFile.invoke(localAllocator.allocateFrom(file));
    }
    public synchronized boolean reloadGamepadMappings() throws Throwable{
        return (boolean)SDL_ReloadGamepadMappings.invoke();
    }
    public synchronized void closeGamepad(MemorySegment gamepad) throws Throwable {
        SDL_CloseGamepad.invoke(gamepad);
    }
    public synchronized String getGamepadAppleSFSymbolsNameForButton(MemorySegment gamepad, int button) throws Throwable {
        MemorySegment temp = (MemorySegment) SDL_GetGamepadAppleSFSymbolsNameForButton.invoke(gamepad,button);
        if (temp == MemorySegment.NULL) {
            return null;
        } else {
            return temp.reinterpret(Integer.MAX_VALUE).getString(0);
        }
    }
    public synchronized String getGamepadAppleSFSymbolsNameForAxis(MemorySegment gamepad, int axis) throws Throwable {
        MemorySegment temp = (MemorySegment) SDL_GetGamepadAppleSFSymbolsNameForAxis.invoke(gamepad,axis);
        if (temp == MemorySegment.NULL) {
            return null;
        } else {
            return temp.reinterpret(Integer.MAX_VALUE).getString(0);
        }
    }
    public static NativeSdlGamepadFuncs getInstance(Arena allocator) {
        NativeSdlGamepadFuncs result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null) INSTANCE = result = new NativeSdlGamepadFuncs(allocator);
            }
        }
        return result;
    }
}
