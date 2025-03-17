package robedpixel.sdl.haptic.effect;

import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticCustomEffectBuilder {
  SdlHapticType type;
  SdlHapticDirection direction;
  int length;
  short delay;
  short button;
  short interval;
  byte channels;
  short period;
  short samples;
  short[] data;
  short attackLength;
  short attackLevel;
  short fadeLength;
  short fadeLevel;

  public SdlHapticCustomEffectBuilder setType(SdlHapticType type) {
    this.type = type;
    return this;
  }

  public SdlHapticCustomEffectBuilder setDirection(SdlHapticDirection direction) {
    this.direction = direction;
    return this;
  }

  public SdlHapticCustomEffectBuilder setLength(int length) {
    this.length = length;
    return this;
  }

  public SdlHapticCustomEffectBuilder setDelay(short delay) {
    this.delay = delay;
    return this;
  }

  public SdlHapticCustomEffectBuilder setButton(short button) {
    this.button = button;
    return this;
  }

  public SdlHapticCustomEffectBuilder setInterval(short interval) {
    this.interval = interval;
    return this;
  }

  public SdlHapticCustomEffectBuilder setChannels(byte channels) {
    this.channels = channels;
    return this;
  }

  public SdlHapticCustomEffectBuilder setPeriod(short period) {
    this.period = period;
    return this;
  }

  public SdlHapticCustomEffectBuilder setSamples(short samples) {
    this.samples = samples;
    return this;
  }

  public SdlHapticCustomEffectBuilder setData(short[] data) {
    this.data = data;
    return this;
  }

  public SdlHapticCustomEffectBuilder setAttackLength(short attackLength) {
    this.attackLength = attackLength;
    return this;
  }

  public SdlHapticCustomEffectBuilder setAttackLevel(short attackLevel) {
    this.attackLevel = attackLevel;
    return this;
  }

  public SdlHapticCustomEffectBuilder setFadeLength(short fadeLength) {
    this.fadeLength = fadeLength;
    return this;
  }

  public SdlHapticCustomEffectBuilder setFadeLevel(short fadeLevel) {
    this.fadeLevel = fadeLevel;
    return this;
  }

  public SdlHapticCustomEffect build() {
    return new SdlHapticCustomEffect(this);
  }
}
