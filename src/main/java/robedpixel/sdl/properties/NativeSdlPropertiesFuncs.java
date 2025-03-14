package robedpixel.sdl.properties;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

// TODO: note, the name char arrays can be freed after they are passed to the function
class NativeSdlPropertiesFuncs {
  private static volatile NativeSdlPropertiesFuncs INSTANCE;
  private static final Object mutex = new Object();
  private final MethodHandle SDL_GetGlobalProperties;
  private final MethodHandle SDL_CreateProperties;
  private final MethodHandle SDL_CopyProperties;
  private final MethodHandle SDL_LockProperties;
  private final MethodHandle SDL_UnlockProperties;
  private final MethodHandle SDL_SetPointerPropertyWithCleanup;
  private final MethodHandle SDL_SetPointerProperty;
  private final MethodHandle SDL_SetStringProperty;
  private final MethodHandle SDL_SetNumberProperty;
  private final MethodHandle SDL_SetFloatProperty;
  private final MethodHandle SDL_SetBooleanProperty;
  private final MethodHandle SDL_HasProperty;
  private final MethodHandle SDL_GetPropertyType;
  private final MethodHandle SDL_GetPointerProperty;
  private final MethodHandle SDL_GetStringProperty;
  private final MethodHandle SDL_GetNumberProperty;
  private final MethodHandle SDL_GetFloatProperty;
  private final MethodHandle SDL_GetBooleanProperty;
  private final MethodHandle SDL_ClearProperty;
  private final MethodHandle SDL_EnumerateProperties;
  private final MethodHandle SDL_DestroyProperties;

  public NativeSdlPropertiesFuncs(Arena allocator) {
    SymbolLookup library = SymbolLookup.libraryLookup("SDL3", allocator);
    SDL_GetGlobalProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetGlobalProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    SDL_CreateProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CreateProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_INT));
    SDL_CopyProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_CopyProperties").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
    SDL_LockProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_LockProperties").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT));
    SDL_UnlockProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_UnlockProperties").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
    SDL_SetPointerPropertyWithCleanup =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetPointerPropertyWithCleanup").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_SetPointerProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetPointerProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_SetStringProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetStringProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_SetNumberProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetNumberProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_LONG));
    SDL_SetFloatProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetFloatProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_FLOAT));
    SDL_SetBooleanProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_SetBooleanProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_BOOLEAN));
    SDL_HasProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_HasProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetPropertyType =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetPropertyType").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_GetPointerProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetPointerProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_GetStringProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetStringProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_GetNumberProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetNumberProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_LONG,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_LONG));
    SDL_GetFloatProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetFloatProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_FLOAT,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_FLOAT));
    SDL_GetBooleanProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_GetBooleanProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.JAVA_BOOLEAN));
    SDL_ClearProperty =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_ClearProperty").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN, ValueLayout.JAVA_INT, ValueLayout.ADDRESS));
    SDL_EnumerateProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_EnumerateProperties").orElseThrow(),
                FunctionDescriptor.of(
                    ValueLayout.JAVA_BOOLEAN,
                    ValueLayout.JAVA_INT,
                    ValueLayout.ADDRESS,
                    ValueLayout.ADDRESS));
    SDL_DestroyProperties =
        Linker.nativeLinker()
            .downcallHandle(
                library.find("SDL_DestroyProperties").orElseThrow(),
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_INT));
  }

  public synchronized int getGlobalProperties() throws Throwable {
    return (int) SDL_GetGlobalProperties.invoke();
  }

  public synchronized int createProperties() throws Throwable {
    return (int) SDL_CreateProperties.invoke();
  }

  public synchronized boolean copyProperties(int src, int dst) throws Throwable {
    return (boolean) SDL_CopyProperties.invoke(src, dst);
  }

  public synchronized boolean lockProperties(int props) throws Throwable {
    return (boolean) SDL_LockProperties.invoke(props);
  }

  public synchronized void unlockProperties(int props) throws Throwable {
    SDL_UnlockProperties.invoke(props);
  }

  public synchronized boolean setPointerPropertyWithCleanup(
      Arena localAllocator,
      int props,
      String name,
      MemorySegment value,
      MemorySegment cleanup,
      MemorySegment userdata)
      throws Throwable {
    return (boolean)
        SDL_SetPointerPropertyWithCleanup.invoke(
            props, localAllocator.allocateFrom(name), value, cleanup, userdata);
  }

  public synchronized boolean setPointerProperty(
      Arena localAllocator, int props, String name, MemorySegment value) throws Throwable {
    return (boolean) SDL_SetPointerProperty.invoke(props, localAllocator.allocateFrom(name), value);
  }

  public synchronized boolean setStringProperty(
      Arena localAllocator, int props, String name, String value) throws Throwable {
    return (boolean)
        SDL_SetStringProperty.invoke(
            props, localAllocator.allocateFrom(name), localAllocator.allocateFrom(value));
  }

  public synchronized boolean setNumberProperty(
      Arena localAllocator, int props, String name, long value) throws Throwable {
    return (boolean) SDL_SetNumberProperty.invoke(props, localAllocator.allocateFrom(name), value);
  }

  public synchronized boolean setFloatProperty(
      Arena localAllocator, int props, String name, float value) throws Throwable {
    return (boolean) SDL_SetFloatProperty.invoke(props, localAllocator.allocateFrom(name), value);
  }

  public synchronized boolean setBooleanProperty(
      Arena localAllocator, int props, String name, boolean value) throws Throwable {
    return (boolean) SDL_SetBooleanProperty.invoke(props, localAllocator.allocateFrom(name), value);
  }

  public synchronized boolean hasProperty(Arena localAllocator, int props, String name)
      throws Throwable {
    return (boolean) SDL_HasProperty.invoke(props, localAllocator.allocateFrom(name));
  }

  public synchronized int getPropertyType(Arena localAllocator, int props, String name)
      throws Throwable {
    return (int) SDL_GetPropertyType.invoke(props, localAllocator.allocateFrom(name));
  }

  public synchronized MemorySegment getPointerProperty(
      Arena localAllocator, int props, String name, MemorySegment defaultValue) throws Throwable {
    return (MemorySegment)
        SDL_GetPointerProperty.invoke(props, localAllocator.allocateFrom(name), defaultValue);
  }

  public synchronized String getStringProperty(
      Arena localAllocator, int props, String name, String defaultValue) throws Throwable {
    MemorySegment charArrayAddress =
        (MemorySegment)
            SDL_GetStringProperty.invoke(
                props,
                localAllocator.allocateFrom(name),
                localAllocator.allocateFrom(defaultValue));
    if (charArrayAddress == MemorySegment.NULL) {
      return null;
    } else {
      return charArrayAddress.reinterpret(Integer.MAX_VALUE).getString(0);
    }
  }

  public synchronized long getNumberProperty(
      Arena localAllocator, int props, String name, long defaultValue) throws Throwable {
    return (long)
        SDL_GetNumberProperty.invoke(props, localAllocator.allocateFrom(name), defaultValue);
  }

  public synchronized long getFloatProperty(
      Arena localAllocator, int props, String name, float defaultValue) throws Throwable {
    return (long)
        SDL_GetFloatProperty.invoke(props, localAllocator.allocateFrom(name), defaultValue);
  }

  public synchronized boolean getBooleanProperty(
      Arena localAllocator, int props, String name, boolean defaultValue) throws Throwable {
    return (boolean)
        SDL_GetBooleanProperty.invoke(props, localAllocator.allocateFrom(name), defaultValue);
  }

  public synchronized boolean clearProperty(Arena localAllocator, int props, String name)
      throws Throwable {
    return (boolean) SDL_ClearProperty.invoke(props, localAllocator.allocateFrom(name));
  }

  public synchronized boolean enumerateProperties(
      int props, MemorySegment callback, MemorySegment userData) throws Throwable {
    return (boolean) SDL_EnumerateProperties.invoke(props, callback, userData);
  }

  public synchronized void destroyProperties(int props) throws Throwable {
    SDL_DestroyProperties.invoke(props);
  }

  public static NativeSdlPropertiesFuncs getInstance(Arena allocator) {
    NativeSdlPropertiesFuncs result = INSTANCE;
    if (result == null) {
      synchronized (mutex) {
        result = INSTANCE;
        if (result == null) INSTANCE = result = new NativeSdlPropertiesFuncs(allocator);
      }
    }
    return result;
  }
}
