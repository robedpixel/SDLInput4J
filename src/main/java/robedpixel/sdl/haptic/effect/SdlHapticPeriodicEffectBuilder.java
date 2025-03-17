package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticPeriodicEffectBuilder {
  SdlHapticType type;
  SdlHapticDirection direction;
  int length;
  short delay;
  short button;
  short interval;
  short period;
  short magnitude;
  short offset;
  short phase;
  short attackLength;
  short attackLevel;
  short fadeLength;
  short fadeLevel;

  public SdlHapticPeriodicEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setDirection(SdlHapticDirection direction) {
    this.direction = direction;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setDelay(short delay) {
    this.delay = delay;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setButton(short button) {
    this.button = button;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setInterval(short interval) {
    this.interval = interval;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setPeriod(short period) {
    this.period = period;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setMagnitude(short magnitude) {
    this.magnitude = magnitude;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setOffset(short offset) {
    this.offset = offset;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setPhase(short phase) {
    this.phase = phase;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setAttackLength(short attackLength) {
    this.attackLength = attackLength;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setAttackLevel(short attackLevel) {
    this.attackLevel = attackLevel;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setFadeLength(short fadeLength) {
    this.fadeLength = fadeLength;
    return this;
  }

  public SdlHapticPeriodicEffectBuilder setFadeLevel(short fadeLevel) {
    this.fadeLevel = fadeLevel;
    return this;
  }

  public SdlHapticPeriodicEffect build() {
    return new SdlHapticPeriodicEffect(this);
  }
}
