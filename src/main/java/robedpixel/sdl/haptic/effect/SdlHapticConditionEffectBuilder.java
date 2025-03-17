package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticConditionEffectBuilder {
  SdlHapticType type;
  SdlHapticDirection direction;
  int length;
  short delay;
  short button;
  short interval;
  final short[] rightSat = new short[3];
  final short[] leftSat = new short[3];
  final short[] rightCoeff = new short[3];
  final short[] leftCoeff = new short[3];
  final short[] deadband = new short[3];
  final short[] center = new short[3];

  public SdlHapticConditionEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticConditionEffectBuilder setDirection(SdlHapticDirection direction) {
    this.direction = direction;
    return this;
  }

  public SdlHapticConditionEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticConditionEffectBuilder setDelay(short delay) {
    this.delay = delay;
    return this;
  }

  public SdlHapticConditionEffectBuilder setButton(short button) {
    this.button = button;
    return this;
  }

  public SdlHapticConditionEffectBuilder setInterval(short interval) {
    this.interval = interval;
    return this;
  }

  public SdlHapticConditionEffectBuilder setRightSat(short x, short y, short z) {
    this.rightSat[0] = x;
    this.rightSat[1] = y;
    this.rightSat[2] = z;
    return this;
  }

  public SdlHapticConditionEffectBuilder setLeftSat(short x, short y, short z) {
    this.leftSat[0] = x;
    this.leftSat[1] = y;
    this.leftSat[2] = z;
    return this;
  }

  public SdlHapticConditionEffectBuilder setRightCoeff(short x, short y, short z) {
    this.rightCoeff[0] = x;
    this.rightCoeff[1] = y;
    this.rightCoeff[2] = z;
    return this;
  }

  public SdlHapticConditionEffectBuilder setLeftCoeff(short x, short y, short z) {
    this.leftCoeff[0] = x;
    this.leftCoeff[1] = y;
    this.leftCoeff[2] = z;
    return this;
  }

  public SdlHapticConditionEffectBuilder setDeadband(short x, short y, short z) {
    this.deadband[0] = x;
    this.deadband[1] = y;
    this.deadband[2] = z;
    return this;
  }

  public SdlHapticConditionEffectBuilder setCenter(short x, short y, short z) {
    this.center[0] = x;
    this.center[1] = y;
    this.center[2] = z;
    return this;
  }

  public SdlHapticConditionEffect build() {
    return new SdlHapticConditionEffect(this);
  }
}
