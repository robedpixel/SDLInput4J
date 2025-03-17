package robedpixel.sdl.haptic.effect;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import lombok.Getter;
import robedpixel.sdl.haptic.SdlHapticDirection;
import robedpixel.sdl.haptic.SdlHapticType;

public class SdlHapticCustomEffect {
  private final StructLayout objectLayout;
  static final StructLayout baseLayout =
      MemoryLayout.structLayout(
              ValueLayout.JAVA_SHORT.withName("type"),
              SdlHapticDirection.objectLayout.withName("direction"),
              ValueLayout.JAVA_INT.withName("length"),
              ValueLayout.JAVA_SHORT.withName("delay"),
              ValueLayout.JAVA_SHORT.withName("button"),
              ValueLayout.JAVA_SHORT.withName("interval"),
              ValueLayout.JAVA_BYTE.withName("channels"),
              ValueLayout.JAVA_SHORT.withName("period"),
              ValueLayout.JAVA_SHORT.withName("samples"),
              ValueLayout.ADDRESS.withName("data"),
              ValueLayout.JAVA_SHORT.withName("attack_length"),
              ValueLayout.JAVA_SHORT.withName("attack_level"),
              ValueLayout.JAVA_SHORT.withName("fade_length"),
              ValueLayout.JAVA_SHORT.withName("fade_level"))
          .withName("SDL_HapticCustom");
  ;
  MemorySegment segment;
  private final Arena allocator = Arena.ofAuto();
  @Getter private SdlHapticType type;
  @Getter private SdlHapticDirection direction;
  @Getter private int length;
  @Getter private short delay;
  @Getter private short button;
  @Getter private short interval;
  @Getter private byte channels;
  @Getter private short period;
  @Getter private short samples;
  @Getter private short[] data;
  @Getter private short attackLength;
  @Getter private short attackLevel;
  @Getter private short fadeLength;
  @Getter private short fadeLevel;
  private final VarHandle typeHandle;
  private final VarHandle directionHandle;
  private final VarHandle lengthHandle;
  private final VarHandle delayHandle;
  private final VarHandle buttonHandle;
  private final VarHandle intervalHandle;
  private final VarHandle channelsHandle;
  private final VarHandle periodHandle;
  private final VarHandle samplesHandle;
  private final VarHandle dataHandle;
  private final VarHandle attackLengthHandle;
  private final VarHandle attackLevelHandle;
  private final VarHandle fadeLengthHandle;
  private final VarHandle fadeLevelHandle;

  SdlHapticCustomEffect(SdlHapticCustomEffectBuilder builder) {
    this.type = builder.type;
    this.direction = builder.direction;
    this.length = builder.length;
    this.delay = builder.delay;
    this.button = builder.button;
    this.interval = builder.interval;
    this.period = builder.period;
    this.channels = builder.channels;
    this.samples = builder.samples;
    this.data = builder.data;
    this.attackLength = builder.attackLength;
    this.attackLevel = builder.attackLevel;
    this.fadeLength = builder.fadeLength;
    this.fadeLevel = builder.fadeLevel;
    this.objectLayout =
        MemoryLayout.structLayout(
                ValueLayout.JAVA_SHORT.withName("type"),
                SdlHapticDirection.objectLayout.withName("direction"),
                ValueLayout.JAVA_INT.withName("length"),
                ValueLayout.JAVA_SHORT.withName("delay"),
                ValueLayout.JAVA_SHORT.withName("button"),
                ValueLayout.JAVA_SHORT.withName("interval"),
                ValueLayout.JAVA_BYTE.withName("channels"),
                ValueLayout.JAVA_SHORT.withName("period"),
                ValueLayout.JAVA_SHORT.withName("samples"),
                MemoryLayout.sequenceLayout(this.channels * this.samples, ValueLayout.JAVA_SHORT),
                ValueLayout.JAVA_SHORT.withName("attack_length"),
                ValueLayout.JAVA_SHORT.withName("attack_level"),
                ValueLayout.JAVA_SHORT.withName("fade_length"),
                ValueLayout.JAVA_SHORT.withName("fade_level"))
            .withName("SDL_HapticCustom");
    typeHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("type"));
    directionHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("direction"));
    lengthHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("length"));
    delayHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("delay"));
    buttonHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("button"));
    intervalHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("interval"));
    channelsHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("channels"));
    periodHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("period"));
    samplesHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("samples"));
    dataHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("data"));
    attackLengthHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("attack_length"));
    attackLevelHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("attack_level"));
    fadeLengthHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("fade_length"));
    fadeLevelHandle =
        SdlHapticEffectMemoryLayout.layout.varHandle(
            MemoryLayout.PathElement.groupElement("custom"),
            MemoryLayout.PathElement.groupElement("fade_level"));
    createMemorySegment();
    ;
  }

  private void createMemorySegment() {
    segment = allocator.allocate(SdlHapticEffectMemoryLayout.layout);
    if (type != null) {
      typeHandle.set(segment, type);
    }
    if (direction != null) {
      directionHandle.set(segment, direction);
    }
    lengthHandle.set(segment, direction);
    delayHandle.set(segment, delay);
    buttonHandle.set(segment, button);
    intervalHandle.set(segment, interval);
    intervalHandle.set(segment, interval);
    attackLengthHandle.set(segment, attackLength);
    attackLevelHandle.set(segment, attackLevel);
    fadeLengthHandle.set(segment, fadeLength);
    fadeLevelHandle.set(segment, fadeLevel);
  }
}
