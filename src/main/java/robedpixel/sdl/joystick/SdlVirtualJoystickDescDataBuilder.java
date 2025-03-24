package robedpixel.sdl.joystick;

public class SdlVirtualJoystickDescDataBuilder {
  short uType;
  short uVendorId;
  short uProductId;
  short uNaxes;
  short uNButtons;
  short uNBalls;
  short uNHats;
  short uNTouchpads;
  short uNSensors;
  int uButtonMask;
  int uAxisMask;
  String name;
  SdlVirtualJoystickTouchpadDesc touchpads;
  SdlVirtualJoystickSensorDesc sensors;

  public SdlVirtualJoystickDescDataBuilder uType(short uType) {
    this.uType = uType;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uVendorId(short uVendorId) {
    this.uVendorId = uVendorId;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uProductId(short uProductId) {
    this.uProductId = uProductId;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNaxes(short uNaxes) {
    this.uNaxes = uNaxes;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNButtons(short uNButtons) {
    this.uNButtons = uNButtons;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNBalls(short uNBalls) {
    this.uNBalls = uNBalls;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNHats(short uNHats) {
    this.uNHats = uNHats;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNTouchpads(short uNTouchpads) {
    this.uNTouchpads = uNTouchpads;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uNSensors(short uNSensors) {
    this.uNSensors = uNSensors;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uButtonMask(int uButtonMask) {
    this.uButtonMask = uButtonMask;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder uAxisMask(int uAxisMask) {
    this.uAxisMask = uAxisMask;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder name(String name) {
    this.name = name;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder touchpads(SdlVirtualJoystickTouchpadDesc touchpads) {
    this.touchpads = touchpads;
    return this;
  }

  public SdlVirtualJoystickDescDataBuilder sensors(SdlVirtualJoystickSensorDesc sensors) {
    this.sensors = sensors;
    return this;
  }
}
