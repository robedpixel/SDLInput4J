package robedpixel.sdl.joystick;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import lombok.Getter;

public abstract class SdlVirtualJoystickDesc implements AutoCloseable {
  public static final MemoryLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_INT.withName("version"),
              ValueLayout.JAVA_SHORT.withName("type"),
              ValueLayout.JAVA_SHORT.withName("padding"),
              ValueLayout.JAVA_SHORT.withName("vendor_id"),
              ValueLayout.JAVA_SHORT.withName("product_id"),
              ValueLayout.JAVA_SHORT.withName("naxes"),
              ValueLayout.JAVA_SHORT.withName("nbuttons"),
              ValueLayout.JAVA_SHORT.withName("nballs"),
              ValueLayout.JAVA_SHORT.withName("nhats"),
              ValueLayout.JAVA_SHORT.withName("ntouchpads"),
              ValueLayout.JAVA_SHORT.withName("nsensors"),
              MemoryLayout.sequenceLayout(2, ValueLayout.JAVA_SHORT).withName("padding2"),
              ValueLayout.JAVA_INT.withName("button_mask"),
              ValueLayout.JAVA_INT.withName("axis_mask"),
              ValueLayout.ADDRESS.withName("name"),
              ValueLayout.ADDRESS.withName("touchpads"),
              ValueLayout.ADDRESS.withName("sensors"),
              ValueLayout.ADDRESS.withName("userdata"),
              ValueLayout.ADDRESS.withName("Update"),
              ValueLayout.ADDRESS.withName("SetPlayerIndex"),
              ValueLayout.ADDRESS.withName("Rumble"),
              ValueLayout.ADDRESS.withName("RumbleTriggers"),
              ValueLayout.ADDRESS.withName("SetLED"),
              ValueLayout.ADDRESS.withName("SendEffect"),
              ValueLayout.ADDRESS.withName("SetSensorsEnabled"),
              ValueLayout.ADDRESS.withName("Cleanup"))
          .withName("SDL_VirtualJoystickDesc");

  private static final VarHandle versionHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("version"));
  private static final VarHandle typeHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle vendorIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("vendor_id"));
  private static final VarHandle productIdHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("product_id"));
  private static final VarHandle naxesHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("naxes"));
  private static final VarHandle nButtonsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("nbuttons"));
  private static final VarHandle nBallsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("nballs"));
  private static final VarHandle nHatsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("nhats"));
  private static final VarHandle nTouchpadsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("ntouchpads"));
  private static final VarHandle nSensorsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("nsensors"));
  private static final VarHandle buttonMaskHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("button_mask"));
  private static final VarHandle axisMaskHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("axis_mask"));
  private static final VarHandle nameHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("name"));
  private static final VarHandle touchpadsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("touchpads"));
  private static final VarHandle sensorsHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("sensors"));
  private static final VarHandle userdataHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("userdata"));
  private static final VarHandle updateCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("Update"));
  private static final VarHandle setPlayerIndexCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("SetPlayerIndex"));
  private static final VarHandle rumbleCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("Rumble"));
  private static final VarHandle rumbleTriggersCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("RumbleTriggers"));
  private static final VarHandle setLedCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("SetLED"));
  private static final VarHandle sendEffectCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("SendEffect"));
  private static final VarHandle setSensorEnabledCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("SetSensorsEnabled"));
  private static final VarHandle cleanupCallbackHandle =
      objectLayout.varHandle(MemoryLayout.PathElement.groupElement("Cleanup"));

  private static final FunctionDescriptor updateCallbackHandleDescriptor =
      FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);
  private static final FunctionDescriptor setPLayerIndexCallbackHandleDescriptor =
      FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT);
  private static final FunctionDescriptor rumbleCallbackHandleDescriptor =
      FunctionDescriptor.of(
          ValueLayout.JAVA_BOOLEAN,
          ValueLayout.ADDRESS,
          ValueLayout.JAVA_SHORT,
          ValueLayout.JAVA_SHORT);
  private static final FunctionDescriptor rumbleTriggersCallbackHandleDescriptor =
      FunctionDescriptor.of(
          ValueLayout.JAVA_BOOLEAN,
          ValueLayout.ADDRESS,
          ValueLayout.JAVA_SHORT,
          ValueLayout.JAVA_SHORT);
  private static final FunctionDescriptor setLedCallbackHandleDescriptor =
      FunctionDescriptor.of(
          ValueLayout.JAVA_BOOLEAN,
          ValueLayout.ADDRESS,
          ValueLayout.JAVA_BYTE,
          ValueLayout.JAVA_BYTE,
          ValueLayout.JAVA_BYTE);
  private static final FunctionDescriptor sendEffectCallbackHandleDescriptor =
      FunctionDescriptor.of(
          ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT);
  private static final FunctionDescriptor setSensorsEnabledCallbackHandleDescriptor =
      FunctionDescriptor.of(
          ValueLayout.JAVA_BOOLEAN, ValueLayout.ADDRESS, ValueLayout.JAVA_BOOLEAN);
  private static final FunctionDescriptor cleanupCallbackHandleDescriptor =
      FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);

  private final MethodHandle updateCallbackMethodHandle;
  private final MethodHandle setPlayerIndexCallbackMethodHandle;
  private final MethodHandle rumbleCallbackMethodHandle;
  private final MethodHandle rumbleTriggersCallbackMethodHandle;
  private final MethodHandle setLedCallbackMethodHandle;
  private final MethodHandle sendEffectCallbackMethodHandle;
  private final MethodHandle setSensorsEnabledCallbackMethodHandle;
  private final MethodHandle cleanupCallbackMethodHandle;

  private final MemorySegment updateCallbackAddress;
  private final MemorySegment setPlayerIndexCallbackAddress;
  private final MemorySegment rumbleCallbackAddress;
  private final MemorySegment rumbleTriggersCallbackAddress;
  private final MemorySegment setLedCallbackAddress;
  private final MemorySegment sendEffectCallbackAddress;
  private final MemorySegment setSensorsEnabledCallbackAddress;
  private final MemorySegment cleanupCallbackAddress;

  private int uVersion;
  private short uType;
  private short uVendorId;
  private short uProductId;
  private short uNaxes;
  private short uNButtons;
  private short uNBalls;
  private short uNHats;
  private short uNTouchpads;
  private short uNSensors;
  private int uButtonMask;
  private int uAxisMask;
  String name;
  SdlVirtualJoystickTouchpadDesc touchpads;
  SdlVirtualJoystickSensorDesc sensors;
  @Getter private final MemorySegment dataAddress;

  @Getter private final Arena allocator = Arena.ofConfined();

  SdlVirtualJoystickDesc(SdlVirtualJoystickDescDataBuilder builder) {
    this.dataAddress = allocator.allocate(objectLayout);
    // initialise callback upstubs
    try {
      updateCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "update", updateCallbackHandleDescriptor.toMethodType());
      updateCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(updateCallbackMethodHandle, updateCallbackHandleDescriptor, allocator);
      setPlayerIndexCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "setPlayerIndex", setPLayerIndexCallbackHandleDescriptor.toMethodType());
      setPlayerIndexCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(
                  setPlayerIndexCallbackMethodHandle,
                  setPLayerIndexCallbackHandleDescriptor,
                  allocator);
      rumbleCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "rumble", rumbleCallbackHandleDescriptor.toMethodType());
      rumbleCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(rumbleCallbackMethodHandle, rumbleCallbackHandleDescriptor, allocator);
      rumbleTriggersCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "rumbleTriggers", rumbleTriggersCallbackHandleDescriptor.toMethodType());
      rumbleTriggersCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(
                  rumbleTriggersCallbackMethodHandle,
                  rumbleTriggersCallbackHandleDescriptor,
                  allocator);
      setLedCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "setLed", setLedCallbackHandleDescriptor.toMethodType());
      setLedCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(setLedCallbackMethodHandle, setLedCallbackHandleDescriptor, allocator);
      sendEffectCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "sendEffect", sendEffectCallbackHandleDescriptor.toMethodType());
      sendEffectCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(
                  sendEffectCallbackMethodHandle, sendEffectCallbackHandleDescriptor, allocator);
      setSensorsEnabledCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(
                  this,
                  "setSensorsEnabled",
                  setSensorsEnabledCallbackHandleDescriptor.toMethodType());
      setSensorsEnabledCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(
                  setSensorsEnabledCallbackMethodHandle,
                  setSensorsEnabledCallbackHandleDescriptor,
                  allocator);
      cleanupCallbackMethodHandle =
          MethodHandles.publicLookup()
              .bind(this, "cleanup", cleanupCallbackHandleDescriptor.toMethodType());
      cleanupCallbackAddress =
          Linker.nativeLinker()
              .upcallStub(cleanupCallbackMethodHandle, cleanupCallbackHandleDescriptor, allocator);
    } catch (Exception e) {
      throw new AssertionError("Error creating callback handle", e);
    }

    this.uVersion = (int) objectLayout.byteSize();
    this.uType = builder.uType;
    this.uVendorId = builder.uVendorId;
    this.uProductId = builder.uProductId;
    this.uNaxes = builder.uNaxes;
    this.uNButtons = builder.uNButtons;
    this.uNBalls = builder.uNBalls;
    this.uNHats = builder.uNHats;
    this.uNTouchpads = builder.uNTouchpads;
    this.uNSensors = builder.uNSensors;
    this.uButtonMask = builder.uButtonMask;
    this.uAxisMask = builder.uAxisMask;
    this.name = builder.name;
    this.touchpads = builder.touchpads;
    this.sensors = builder.sensors;
    // zero native object memory space
    dataAddress.fill((byte) 0);
    // load memory addresses into object memory address
    versionHandle.set(dataAddress, 0, this.uVersion);
    typeHandle.set(dataAddress, 0, this.uType);
    vendorIdHandle.set(dataAddress, 0, this.uVendorId);
    productIdHandle.set(dataAddress, 0, this.uProductId);
    naxesHandle.set(dataAddress, 0, this.uNaxes);
    nButtonsHandle.set(dataAddress, 0, this.uNButtons);
    nBallsHandle.set(dataAddress, 0, this.uNBalls);
    nHatsHandle.set(dataAddress, 0, this.uNHats);
    nTouchpadsHandle.set(dataAddress, 0, this.uNTouchpads);
    nSensorsHandle.set(dataAddress, 0, this.uNSensors);
    buttonMaskHandle.set(dataAddress, 0, this.uButtonMask);
    axisMaskHandle.set(dataAddress, 0, this.uAxisMask);
    nameHandle.set(dataAddress, 0, allocator.allocateFrom(this.name));
    touchpadsHandle.set(dataAddress, 0, touchpads.getDataAddress());
    sensorsHandle.set(dataAddress, 0, sensors.getDataAddress());
    updateCallbackHandle.set(dataAddress, 0, updateCallbackAddress);
    setPlayerIndexCallbackHandle.set(dataAddress, 0, setPlayerIndexCallbackAddress);
    rumbleCallbackHandle.set(dataAddress, 0, rumbleCallbackAddress);
    rumbleTriggersCallbackHandle.set(dataAddress, 0, rumbleTriggersCallbackAddress);
    setLedCallbackHandle.set(dataAddress, 0, setLedCallbackAddress);
    sendEffectCallbackHandle.set(dataAddress, 0, sendEffectCallbackAddress);
    setSensorEnabledCallbackHandle.set(dataAddress, 0, setSensorsEnabledCallbackAddress);
    cleanupCallbackHandle.set(dataAddress, 0, cleanupCallbackAddress);
  }

  void update(MemorySegment userData) {}

  void setPlayerIndex(MemorySegment userData, int playerIndex) {}

  boolean rumble(MemorySegment userDbata, short uLowFrequencyRumble, short uHighFrequencyRumble) {
    return true;
  }

  boolean rumbleTriggers(MemorySegment userData, short uLeftRumble, short uRightRumble) {
    return true;
  }

  boolean setLed(MemorySegment userData, byte uRed, byte uGreen, byte uBlue) {
    return true;
  }

  boolean sendEffect(MemorySegment userData, MemorySegment data, int size) {
    return true;
  }

  boolean setSensorsEnabled(MemorySegment userData, boolean enabled) {
    return true;
  }

  void cleanup(MemorySegment userdata) {}

  @Override
  public void close() {
    allocator.close();
  }
}
