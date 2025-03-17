package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticRampEffectBuilder {
  SdlHapticType type;
  SdlHapticDirection direction;
  int length;
  short delay;
  short button;
  short interval;
  short start;
  short end;
  short attackLength;
  short attackLevel;
  short fadeLength;
  short fadeLevel;

  public SdlHapticRampEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticRampEffectBuilder setDirection(SdlHapticDirection direction) {
    this.direction = direction;
    return this;
  }

  public SdlHapticRampEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticRampEffectBuilder setDelay(short delay) {
    this.delay = delay;
    return this;
  }

  public SdlHapticRampEffectBuilder setButton(short button) {
    this.button = button;
    return this;
  }

  public SdlHapticRampEffectBuilder setInterval(short interval) {
    this.interval = interval;
    return this;
  }

  public SdlHapticRampEffectBuilder setStart(short start) {
    this.start = start;
    return this;
  }

  public SdlHapticRampEffectBuilder setEnd(short end) {
    this.end = end;
    return this;
  }

  public SdlHapticRampEffectBuilder setAttackLength(short attackLength) {
    this.attackLength = attackLength;
    return this;
  }

  public SdlHapticRampEffectBuilder setAttackLevel(short attackLevel) {
    this.attackLevel = attackLevel;
    return this;
  }

  public SdlHapticRampEffectBuilder setFadeLength(short fadeLength) {
    this.fadeLength = fadeLength;
    return this;
  }

  public SdlHapticRampEffectBuilder setFadeLevel(short fadeLevel) {
    this.fadeLevel = fadeLevel;
    return this;
  }

  public SdlHapticRampEffect build() {
    return new SdlHapticRampEffect(this);
  }
}
