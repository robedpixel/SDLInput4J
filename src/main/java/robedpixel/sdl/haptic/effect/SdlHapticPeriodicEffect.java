package robedpixel.sdl.haptic.effect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

class SdlHapticPeriodicEffect implements SdlHapticEffect {
  static final StructLayout objectLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_SHORT.withName("type"),
              SdlHapticDirection.objectLayout.withName("direction"),
              ValueLayout.JAVA_INT.withName("length"),
              ValueLayout.JAVA_SHORT.withName("delay"),
              ValueLayout.JAVA_SHORT.withName("button"),
              ValueLayout.JAVA_SHORT.withName("interval"),
              ValueLayout.JAVA_SHORT.withName("period"),
              ValueLayout.JAVA_SHORT.withName("magnitude"),
              ValueLayout.JAVA_SHORT.withName("offset"),
              ValueLayout.JAVA_SHORT.withName("phase"),
              ValueLayout.JAVA_SHORT.withName("attack_length"),
              ValueLayout.JAVA_SHORT.withName("attack_level"),
              ValueLayout.JAVA_SHORT.withName("fade_length"),
              ValueLayout.JAVA_SHORT.withName("fade_level"))
          .withName("SDL_HapticPeriodic");
  private MemorySegment segment;
  private final Arena allocator = Arena.ofAuto();
  @Getter private SdlHapticType type;
  @Getter private SdlHapticDirection direction;
  @Getter private int length;
  @Getter private short delay;
  @Getter private short button;
  @Getter private short interval;
  @Getter private short period;
  @Getter private short magnitude;
  @Getter private short offset;
  @Getter private short phase;
  @Getter private short attackLength;
  @Getter private short attackLevel;
  @Getter private short fadeLength;
  @Getter private short fadeLevel;

  private static final VarHandle typeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("type"));
  private static final VarHandle directionHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("direction"));
  private static final VarHandle lengthHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("length"));
  private static final VarHandle delayHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("delay"));
  private static final VarHandle buttonHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("button"));
  private static final VarHandle intervalHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("interval"));
  private static final VarHandle periodHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("period"));
  private static final VarHandle magnitudeHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("magnitude"));
  private static final VarHandle offsetHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("offset"));
  private static final VarHandle phaseHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("phase"));
  private static final VarHandle attackLengthHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("attack_length"));
  private static final VarHandle attackLevelHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("attack_level"));
  private static final VarHandle fadeLengthHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("fade_length"));
  private static final VarHandle fadeLevelHandle =
      SdlHapticEffectMemoryLayout.layout.varHandle(
          MemoryLayout.PathElement.groupElement("periodic"),
          MemoryLayout.PathElement.groupElement("fade_level"));

  SdlHapticPeriodicEffect(SdlHapticPeriodicEffectBuilder builder) {
    this.type = builder.type;
    this.direction = builder.direction;
    this.length = builder.length;
    this.delay = builder.delay;
    this.button = builder.button;
    this.interval = builder.interval;
    this.period = builder.period;
    this.magnitude = builder.magnitude;
    this.offset = builder.offset;
    this.phase = builder.phase;
    this.attackLength = builder.attackLength;
    this.attackLevel = builder.attackLevel;
    this.fadeLength = builder.fadeLength;
    this.fadeLevel = builder.fadeLevel;
    createMemorySegment();
  }

  private void createMemorySegment() {
    segment = allocator.allocate(SdlHapticEffectMemoryLayout.layout);
    if (type != null) {
      typeHandle.set(segment,0, type);
    }
    if (direction != null) {
      directionHandle.set(segment,0, direction);
    }
    lengthHandle.set(segment,0, direction);
    delayHandle.set(segment,0, delay);
    buttonHandle.set(segment,0, button);
    intervalHandle.set(segment,0, interval);
    periodHandle.set(segment,0, period);
    magnitudeHandle.set(segment,0, period);
    offsetHandle.set(segment,0, offset);
    phaseHandle.set(segment,0, phase);
    attackLengthHandle.set(segment,0, attackLength);
    attackLevelHandle.set(segment,0, attackLevel);
    fadeLengthHandle.set(segment,0, fadeLength);
    fadeLevelHandle.set(segment,0, fadeLevel);
  }

  public MemorySegment getMemorySegment() {
    return segment;
  }
}
