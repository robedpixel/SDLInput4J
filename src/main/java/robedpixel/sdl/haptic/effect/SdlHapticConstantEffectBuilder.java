package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticConstantEffectBuilder {
  SdlHapticType type;
  SdlHapticDirection direction;
  int length;
  short delay;
  short button;
  short interval;
  short level;
  short attackLength;
  short attackLevel;
  short fadeLength;
  short fadeLevel;

  public SdlHapticConstantEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticConstantEffectBuilder setDirection(SdlHapticDirection direction) {
    this.direction = direction;
    return this;
  }

  public SdlHapticConstantEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticConstantEffectBuilder setDelay(short delay) {
    this.delay = delay;
    return this;
  }

  public SdlHapticConstantEffectBuilder setButton(short button) {
    this.button = button;
    return this;
  }

  public SdlHapticConstantEffectBuilder setInterval(short interval) {
    this.interval = interval;
    return this;
  }

  public SdlHapticConstantEffectBuilder setLevel(short level) {
    this.level = level;
    return this;
  }

  public SdlHapticConstantEffectBuilder setAttackLength(short attackLength) {
    this.attackLength = attackLength;
    return this;
  }

  public SdlHapticConstantEffectBuilder setAttackLevel(short attackLevel) {
    this.attackLevel = attackLevel;
    return this;
  }

  public SdlHapticConstantEffectBuilder setFadeLength(short fadeLength) {
    this.fadeLength = fadeLength;
    return this;
  }

  public SdlHapticConstantEffectBuilder setFadeLevel(short fadeLevel) {
    this.fadeLevel = fadeLevel;
    return this;
  }

  public SdlHapticConstantEffect build() {
    return new SdlHapticConstantEffect(this);
  }
}
