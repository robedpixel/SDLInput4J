package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticLeftRightEffectBuilder {
  SdlHapticType type;
  int length;
  short largeMagnitude;
  short smallMagnitude;

  public SdlHapticLeftRightEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticLeftRightEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticLeftRightEffectBuilder setLargeMagnitude(short largeMagnitude) {
    this.largeMagnitude = largeMagnitude;
    return this;
  }

  public SdlHapticLeftRightEffectBuilder setSmallMagnitude(short smallMagnitude) {
    this.smallMagnitude = smallMagnitude;
    return this;
  }

  public SdlHapticLeftRightEffect build() {
    return new SdlHapticLeftRightEffect(this);
  }
}
